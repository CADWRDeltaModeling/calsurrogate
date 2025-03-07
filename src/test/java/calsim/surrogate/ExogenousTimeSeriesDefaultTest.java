package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ExogenousTimeSeriesDefaultTest {

    @Test
    public void testDefaultExogenousTimeSeriesData() {
        // Get the default singleton instance.
        ExogenousTimeSeries ext = ExogenousTimeSeries.getInstance();

        // Check that the start date is properly set.
        LocalDate start = ext.getStartDate();
        assertNotNull(start, "Start date should not be null");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Start date: " + start);

        // Check that the length of the series is greater than zero.
        int length = ext.getLength();
        assertTrue(length > 0, "Time series length should be greater than 0");
        System.out.println("Time series length (days): " + length);

        // Verify that the end date equals start date plus (length - 1) days.
        LocalDate end = ext.getEndDate();
        assertEquals(start.plusDays(length - 1), end, "End date must equal start date plus length - 1");
        System.out.println("End date: " + end);

        // Retrieve a slice of daily data from column 0.
        // For this test, we request 5 days of data starting from the start date.
        double[] slice = ext.dailyDataSlice(0, start.getYear(), start.getMonthValue(), start.getDayOfMonth(), 5);
        assertNotNull(slice, "Data slice should not be null");
        assertEquals(5, slice.length, "Data slice length must be 5");

        // Optionally, print out the slice values.
        System.out.println("Data slice from column 0 starting at " + start + ":");
        for (int i = 0; i < slice.length; i++) {
            System.out.println("Day " + i + ": " + slice[i]);
        }
    }
}
