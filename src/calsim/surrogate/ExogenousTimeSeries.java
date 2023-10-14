package calsim.surrogate;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

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
	private double[] data;

	private ExogenousTimeSeries() {
		this.startDate = LocalDate.of(1910, 1, 1); // TODO hardwires
		String csvFile = "calsim/surrogate/sftide.csv";
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

		System.arraycopy(data, (int) offset, slice, 0, nday);
		return slice;
	}

	private void loadData(String csv) {
		System.out.println("Start");
		String firstDate = null;
		ArrayList<Double> inData = new ArrayList<Double>();

		try {
			// This needed so that the packaging will work and the csv resource
			// be available
			ClassLoader loader = this.getClass().getClassLoader();
			InputStream stream = loader.getResourceAsStream(csv);
			System.out.println(stream);
			BufferedReader br = new BufferedReader(new InputStreamReader(stream));

			String line = "";
			String[] tempArr;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#")) {
					continue;
				}
				tempArr = line.split(delimiter);
				Double val = Double.valueOf(tempArr[1]);
				inData.add(val);

				// for(String tempStr : tempArr) {
				// System.out.print(tempStr + " ");
				// }
				// System.out.println();
			}
			br.close();
			data = inData.stream().mapToDouble(Double::doubleValue).toArray();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		System.out.println("out");

	}

	public static void main(String[] argv) {
		ExogenousTimeSeries tide = ExogenousTimeSeries.getInstance();
		double[] tideSlice = tide.dailyDataSlice(0, 1924, 1, 5, 4);
		for (int i = 0; i < tideSlice.length; i++) {
			System.out.println(tideSlice[i]);
		}

	}

}
