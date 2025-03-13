package calsim.surrogate;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Manages external daily time series data (e.g., tide properties) stored in a CSV file.
 * <p>
 * The CSV file is expected to have a header on the first non-comment line, for example:
 * <pre>
 *     date,var1,var2,var3
 * </pre>
 * where the first column is labeled "date" (or "datetime", case-insensitive) and subsequent columns
 * are the variable names. Data lines then follow with the first token as the date (formatted as "yyyy-MM-dd")
 * and the remaining tokens as numeric values.
 * </p>
 * <p>
 * This class automatically parses the header to build a mapping from variable names (case-insensitive)
 * to their column index (zero-based, excluding the date column) and detects the start date from the first
 * data row. In addition to the existing method dailyDataSlice(int,...), it now supports:
 * <ul>
 *   <li>{@code colIndexForVariable(String name)} – returns the column index for a variable.</li>
 *   <li>{@code dailyDataSlice(String colName, ...)} – returns a slice of daily data for a given variable.</li>
 *   <li>Accessors for the start date, end date, and the total length (in days) of the time series.</li>
 * </ul>
 * </p>
 *
 * <p>Example CSV header:
 * <pre>
 *     date,Var1,Var2,Var3
 * </pre>
 * </p>
 *
 * @see calsim.surrogate.ANNMonth
 */
public class ExogenousTimeSeries {
    private static ExogenousTimeSeries _instance = null;
    protected final String delimiter = ",";
    private boolean loaded;
    private LocalDate startDate = null;
    private int dataLength; // number of data rows (days)
    protected double[][] data;  // First index: variable, Second (fast) index: time (day)

    // Stores header tokens (first token is "date", subsequent tokens are variable names)
    private String[] header;
    // Maps variable names (lower-case) to their zero-based column index in the data array
    private Map<String, Integer> colNameIndexMap = new HashMap<>();

    private final String csvFile = "calsim/surrogate/sf_tide.csv"; // TODO: hardwire    
    
    // Private constructor uses a CSV resource (hardwired file name) unless overridden.
    private ExogenousTimeSeries() {
        // Default start date is overridden after loading the file.
        ensureLoaded(csvFile);
    }

    /**
     * Returns the singleton instance.
     * @return the ExogenousTimeSeries instance.
     */
    public static synchronized ExogenousTimeSeries getInstance() {
        if (_instance == null) {
            _instance = new ExogenousTimeSeries();
        }
        return _instance;
    }

    // Add a new protected constructor for testing purposes.
    protected ExogenousTimeSeries(String csvPath) {
        // Instead of using the default classloader lookup,
        // load data from the provided file path.
        try {
            loadDataFromPath(csvPath);
            loaded = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    } 
    
    
    private void ensureLoaded(String csvFile) {
        if (!loaded) {
            loadData(csvFile);
            loaded = true;
        }
    }

    /**
     * Returns a slice of daily data for the given variable column index.
     * @param colIndex zero-based index of the variable (as stored internally)
     * @param year the year of the requested start date
     * @param month the month of the requested start date
     * @param day the day of the requested start date
     * @param nday the number of days to return
     * @return an array containing the requested slice of daily data
     */
    public double[] dailyDataSlice(int colIndex, int year, int month, int day, int nday) {
        LocalDate request = LocalDate.of(year, month, day);
        long offset = ChronoUnit.DAYS.between(startDate, request);
        double[] slice = new double[nday];
        System.arraycopy(this.data[colIndex], (int) offset, slice, 0, nday);
        return slice;
    }

    /**
     * Returns the column index for a given variable name (case-insensitive).
     * The date column is not included.
     *
     * @param variableName the name of the variable.
     * @return the zero-based column index in the data array.
     * @throws IllegalArgumentException if the variable is not found in the header.
     */
    public int colIndexForVariable(String variableName) {
        Integer index = colNameIndexMap.get(variableName.toLowerCase());
        if (index == null) {
            throw new IllegalArgumentException("Variable " + variableName + " not found in header");
        }
        return index;
    }

    /**
     * Returns a slice of daily data for the given variable name.
     *
     * @param colName the name of the variable.
     * @param year the year of the requested start date.
     * @param month the month of the requested start date.
     * @param day the day of the requested start date.
     * @param nday the number of days to return.
     * @return an array containing the requested slice of daily data.
     */
    public double[] dailyDataSlice(String colName, int year, int month, int day, int nday) {
        int colIndex = colIndexForVariable(colName);
        return dailyDataSlice(colIndex, year, month, day, nday);
    }

    /**
     * Returns the start date of the time series.
     *
     * @return the start date.
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Returns the end date of the time series.
     * This is computed as startDate plus (length - 1) days.
     *
     * @return the end date.
     */
    public LocalDate getEndDate() {
        return this.startDate.plusDays(this.dataLength - 1);
    }

    /**
     * Returns the total length of the time series in days.
     *
     * @return the number of days of data.
     */
    public int getLength() {
        return this.dataLength;
    }

    // Reads file information and header from the CSV input stream.
    protected CSVFileInfo fileInfo(InputStream stream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String line;
        CSVFileInfo info = new CSVFileInfo();
        int nrow = 0;
        try {
            // Read header first.
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] tokens = line.split(delimiter);
                String firstToken = tokens[0].toLowerCase();
                if (firstToken.equals("date") || firstToken.equals("datetime")) {
                    // Store header and build mapping (skip first column)
                    header = tokens;
                    for (int i = 1; i < tokens.length; i++) {
                        colNameIndexMap.put(tokens[i].toLowerCase(), i - 1);
                    }
                    break;
                } else {
                    throw new IOException("CSV file must contain a header with 'date' or 'datetime' as first column.");
                }
            }
            // Process the remaining lines to determine start date and number of data rows.
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] tokens = line.split(delimiter);
                if (tokens.length == 0) continue;
                if (info.startDate == null) {
                    info.startDate = LocalDate.parse(tokens[0], formatter);
                }
                // Expect tokens[0] is date and tokens[1...] are data.
                int nDataCol = tokens.length - 1;
                if (info.nCol < 0) {
                    info.nCol = nDataCol;
                } else if (nDataCol != info.nCol) {
                    throw new IOException("Rows in exogenous time series file have differing number of columns: " + line);
                }
                nrow++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        info.nLine = nrow;
        return info;
    }

    // Loads data from the CSV file, parsing the header and data.
    private void loadData(String csv) {
        try {
            ClassLoader loader = this.getClass().getClassLoader();
            InputStream stream = loader.getResourceAsStream(csv);
            if (stream == null) {
                throw new FileNotFoundException("CSV file not found: " + csv);
            }
            CSVFileInfo info = fileInfo(stream);
            this.setStartDate(info.startDate);
            data = new double[info.nCol][info.nLine];
            this.setDataLength(info.nLine);

            // Re-open stream to read data from the beginning (after header).
            stream = loader.getResourceAsStream(csv);
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // Skip header line and any comment lines.
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] tokens = line.split(delimiter);
                String firstToken = tokens[0].toLowerCase();
                if (firstToken.equals("date") || firstToken.equals("datetime")) {
                    // Skip header (already processed)
                    break;
                }
            }
            int iRow = 0;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] tokens = line.split(delimiter);
                if (tokens.length == 0) continue;
                // First token is date (ignored here) and subsequent tokens are data.
                for (int jCol = 0; jCol < info.nCol; jCol++) {
                    this.data[jCol][iRow] = Double.valueOf(tokens[1 + jCol]);
                }
                iRow++;
            }
            br.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    protected void loadDataFromPath(String path) throws IOException {
        try (InputStream stream = new FileInputStream(path)) {
            CSVFileInfo info = fileInfo(stream);
            this.startDate = info.startDate;
            double[][] tempData = new double[info.nCol][info.nLine];
            this.dataLength = info.nLine;

            // Re-read file to load data rows.
            try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
                String line;
                // Skip header line.
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) continue;
                    String[] tokens = line.split(delimiter);
                    String firstToken = tokens[0].toLowerCase();
                    if (firstToken.equals("date") || firstToken.equals("datetime")) {
                        break;
                    }
                }
                int iRow = 0;
                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty() || line.startsWith("#")) continue;
                    String[] tokens = line.split(delimiter);
                    for (int jCol = 0; jCol < info.nCol; jCol++) {
                        tempData[jCol][iRow] = Double.valueOf(tokens[1 + jCol]);
                    }
                    iRow++;
                }
            }
            this.data = tempData;
        }
    }

 // Inside ExogenousTimeSeries.java

    /**
     * Returns the CSV file name used to load the exogenous time series.
     */
    public String getFileName() {
        return this.csvFile;
    } 
    
    
	// Expose setters (or use package-private access) if needed:
	protected void setStartDate(LocalDate startDate) {
	    this.startDate = startDate;
	}
	protected void setDataLength(int n) {
	    this.dataLength = n;
	}
	protected void setData(double[][] data) {
	    this.data = data;
	}	
	
}

/** Struct-like class used during CSV parsing. */
class CSVFileInfo {
    public LocalDate startDate = null;
    public int nLine = -1;
    public int nCol = -1;
}