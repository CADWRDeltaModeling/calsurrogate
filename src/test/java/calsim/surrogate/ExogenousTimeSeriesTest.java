package calsim.surrogate;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/** This test checks indexing and loading
 * ExogenousTimeSeriesDefaultTest uses the data file currently distributed 
 * with the library that includes San Francisco tidal energy and subtidal water levels.
 * TODO: should be configured, not burried.
 */
class ExogenousTimeSeriesTest {
	
    // Temporary CSV file content with a header.
    private static final String CSV_CONTENT =
            "Date,Var1,Var2,Var3\n" +
            "2020-01-01,1.0,2.0,3.0\n" +
            "2020-01-02,1.1,2.1,3.1\n" +
            "2020-01-03,1.2,2.2,3.2\n" +
            "2020-01-04,1.3,2.3,3.3\n" +
            "2020-01-05,1.4,2.4,3.4\n";

    // Create a temporary file and add it to the classpath via a custom loader, if needed.
    // For simplicity, we write to a temporary file and use that file path to initialize the ExogenousTimeSeries instance.
    private static Path tempCsvFile;

    

    
    @BeforeAll
    static void setup() throws IOException {
        tempCsvFile = Files.createTempFile("exogenous_test", ".csv");
        Files.write(tempCsvFile, CSV_CONTENT.getBytes());
        // For testing, we might override the loadData method or adjust the resource lookup.
        // One option is to set a system property or use dependency injection.
        // Here, we assume the file is accessible via its absolute path.
    }

    @Test
    void testHeaderParsingAndMapping() throws IOException {
        // Create an instance by directly invoking loadData with our temporary file.
        ExogenousTimeSeries ext = new ExogenousTimeSeriesForTest(tempCsvFile.toString());
        // Test that header was parsed.
        int colIndex = ext.colIndexForVariable("Var2");
        assertEquals(1, colIndex, "Var2 should map to column index 1");
    }

    @Test
    void testDailyDataSliceByColName() throws IOException {
        ExogenousTimeSeries ext = new ExogenousTimeSeriesForTest(tempCsvFile.toString());
        // Our CSV has 5 days starting at 2020-01-01.
        LocalDate start = ext.getStartDate();
        assertEquals(LocalDate.of(2020, 1, 1), start);
        // Test a slice from Var1 for 3 days starting at 2020-01-02.
        double[] slice = ext.dailyDataSlice("Var1", 2020, 1, 2, 3);
        // Expected values for Var1 on 2020-01-02, 2020-01-03, 2020-01-04 are 1.1, 1.2, 1.3.
        assertEquals(1.1, slice[0], 1e-6);
        assertEquals(1.2, slice[1], 1e-6);
        assertEquals(1.3, slice[2], 1e-6);
    }

    @Test
    void testTimeSeriesInfo() throws IOException {
        ExogenousTimeSeries ext = new ExogenousTimeSeriesForTest(tempCsvFile.toString());
        // There are 5 data rows.
        assertEquals(5, ext.getLength());
        // End date should be startDate plus 4 days.
        assertEquals(LocalDate.of(2020, 1, 5), ext.getEndDate());
    }

 // In ExogenousTimeSeriesTest.java
    private static class ExogenousTimeSeriesForTest extends ExogenousTimeSeries {
        public ExogenousTimeSeriesForTest(String csvPath) {
            super(csvPath);
        }       
    }
}
