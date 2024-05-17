package calsim.surrogate;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


/**
 * Manages external daily time series data like tide properties stored in a csv
 * file An important caller of this class is ANNMonth after expanding CalSIM
 * monthly data to daily.
 * 
 * @see calsim.surrogate.ANNMonth
 */
public class ExogenousTimeSeries {
	private static ExogenousTimeSeries _instance = null;
	private final String delimiter = ",";
	private boolean loaded;
	private LocalDate startDate = null;
	private double[][] data;  // First index is variable, second (fast) index is time

	private ExogenousTimeSeries() {
		this.startDate = LocalDate.of(1910, 1, 1); // TODO hardwires
		String csvFile = "calsim/surrogate/sf_ha_tidal_range.csv";
		ensureLoaded(csvFile);
	}

	public static synchronized ExogenousTimeSeries getInstance() {
		if (_instance == null) {
			_instance = new ExogenousTimeSeries();
		}
		return _instance;
	}

	private void ensureLoaded(String csvFile) {
		if (!loaded) {
			loadData(csvFile);
		}
	}

	public double[] dailyDataSlice(int colIndex, int year, int month, int day, int nday) {
		LocalDate request = LocalDate.of(year, month, day);

		long offset = ChronoUnit.DAYS.between(startDate, request);

		double[] slice = new double[nday];

		System.arraycopy(this.data[colIndex], (int) offset, slice, 0, nday);
		return slice;
	}
	
	boolean skipLine(String line) {
		if (line.startsWith("#") || (line.indexOf("#")==1)) {
			// Should be startsWith but that failed
			return true;
		}
		
		if (line.toLowerCase().startsWith("date") || (line.toLowerCase().indexOf("date") ==1 ) || 
			(line.indexOf(",")==1) || line.startsWith(",") )  {
			// Simple Header
			return true;
		}
		return false;
	}

	private CSVFileInfo fileInfo(InputStream stream) {
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String line = "";
		String[] tempArr;
		CSVFileInfo info = new CSVFileInfo();
		int nrow = 0;
		try {
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (skipLine(line)) continue;

				tempArr = line.split(delimiter);
				if (tempArr.length==0) continue; // empty line
				if (info.startDate == null) {
					info.startDate = LocalDate.parse(tempArr[0],formatter); // TODO better error control
				}
				if (tempArr.length > 1) {
					int nDataCol = tempArr.length - 1;
					if (info.nCol < 0) {
						info.nCol = nDataCol;
					}else {
						if (nDataCol != info.nCol)
							throw new IOException("Rows in exogenous time series file have differing number of colums: "+line);
					}
				}
				nrow ++;
				
			}
			//br.close();
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}


		info.nLine = nrow;
		return info;
	}

	
	private void loadData(String csv) {

		try {
			// This needed so that the packaging will work and the csv resource
			// be available
			ClassLoader loader = this.getClass().getClassLoader();
			InputStream stream = loader.getResourceAsStream(csv);
			CSVFileInfo info = this.fileInfo(stream);
			
			this.startDate = info.startDate;
			data = new double[info.nCol][info.nLine];
		    
			// reposition at beginning of file
			// TODO better way?
			stream = loader.getResourceAsStream(csv);
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            
			
			String line = "";
			String[] tempArr;
			int iRow = 0;
			while ((line = br.readLine()) != null) {
				if (skipLine(line)) {
					continue;
				}
				tempArr = line.split(delimiter);
				if (tempArr.length == 0) continue;
				//System.out.println(line);
                for (int jCol = 0; jCol < info.nCol; jCol++) {
                	this.data[jCol][iRow] = Double.valueOf(tempArr[1+jCol]);
                }
                iRow++;
			}
			br.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

}


/** Struct-like class used during parsing */
class CSVFileInfo{
	public LocalDate startDate=null;
	public int nLine = -1;
	public int nCol = -1;
}
