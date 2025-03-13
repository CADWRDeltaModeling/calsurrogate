package calsim.surrogate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class SurrogateMonthTest {
    private SurrogateMonth annMonth;
    private ArrayList<double[][]> floatInput;
    private int nDayHist = 118;
    private int nMonthHist = 5;

    @BeforeEach
    void setUp() {
        // Create a mock surrogate and disaggregators.
        Surrogate mock = new MockSurrogate(nDayHist);
        DisaggregateMonths spline = new DisaggregateMonthsSpline(nMonthHist);
        DisaggregateMonths repeat = new DisaggregateMonthsRepeat(nMonthHist);
        // For simplicity, use spline for most disaggregators.
        DisaggregateMonths[] disagg = { spline, spline, repeat, repeat, repeat };
        AggregateMonths agg = AggregateMonths.MONTHLY_MEAN;    
        annMonth = new SurrogateMonth(disagg, mock, agg);

        // Set up sample monthly inputs for 5 features. 
        // Here each feature is a 1×5 array (i.e. one batch, five time steps).
        double[][] sac  = { { 10000., 20000., 10000., 15000., 20000. } };
        double[][] exp  = { { 4000., 4000., 12000., 12000., 12000. } };
        double[][] mix0 = { { 1., 1., 0.33, 0.33, 0.33 } };
        double[][] mix1 = { { 0., 0., 0.33, 0.33, 0.33 } };
        double[][] mix2 = { { 0., 0., 0., 0., 0. } };

        floatInput = new ArrayList<>();
        floatInput.add(sac);
        floatInput.add(exp);
        floatInput.add(mix0);
        floatInput.add(mix1);
        floatInput.add(mix2);
    }

    @Test
    void testImposeGrid() {
        int nx0 = 4;
        int nx1 = 3;
        double loBound0 = 10.0;
        double hiBound0 = 20.0;
        double loBound1 = 30.0;
        double hiBound1 = 40.0;

        GridResult grid = annMonth.imposeGrid(floatInput, loBound0, hiBound0, nx0, loBound1, hiBound1, nx1);

        // Check that the grid arrays have the proper lengths.
        double[] grid0 = grid.getGridInput0();
        double[] grid1 = grid.getGridInput1();
        assertEquals(nx0, grid0.length);
        assertEquals(nx1, grid1.length);
        assertEquals(loBound0, grid0[0], 1e-6);
        assertEquals(hiBound0, grid0[nx0 - 1], 1e-6);
        assertEquals(loBound1, grid1[0], 1e-6);
        assertEquals(hiBound1, grid1[nx1 - 1], 1e-6);

        // Verify that the monthlyInputs for feature 0 and feature 1 have been modified appropriately.
        ArrayList<double[][]> gridInputs = grid.getMonthlyInputs();
        int gridBatch = nx0 * nx1;
        // For feature 0: each row should equal the corresponding grid0 value.
        double[][] feature0 = gridInputs.get(0);
        for (int i = 0; i < gridBatch; i++) {
            int ix = i / nx1;
            double expected = grid0[ix];
            for (int t = 0; t < feature0[i].length; t++) {
                assertEquals(expected, feature0[i][0], 1e-6);
            }
        }
        // For feature 1: each row should equal the corresponding grid1 value (based on column index).
        double[][] feature1 = gridInputs.get(1);
        for (int i = 0; i < gridBatch; i++) {
            int iy = i % nx1;
            double expected = grid1[iy];
            for (int t = 0; t < feature1[i].length; t++) {
                assertEquals(expected, feature1[i][0], 1e-6);
            }
        }
    }

    @Test
    void testEvaluateOnGrid() {
        int nx0 = 4;
        int nx1 = 3;
        double loBound0 = 4000.0;
        double hiBound0 = 25000.0;
        double loBound1 = 500.0;
        double hiBound1 = 12000.0;
        int year = 2010;
        int month = 10;

        GridResult grid = annMonth.evaluateOnGrid(floatInput, year, month,
                                                    loBound0, hiBound0, nx0,
                                                    loBound1, hiBound1, nx1);

        // Basic sanity checks on the grid result.
        assertEquals(nx0, grid.getGridInput0().length);
        assertEquals(nx1, grid.getGridInput1().length);
        double[][][] result = grid.getResult();
        assertEquals(nx0, result.length);
        assertEquals(nx1, result[0].length);

        // (Optional) If the surrogate returns known results, add more detailed assertions here.
    }
}