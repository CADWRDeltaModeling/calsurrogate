package calsim.surrogate;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({InputSizeInfoTest.class, AggregateMonthsTest.class, CircleLineSearchableTest.class, ConservativeSplineTest.class,
		DisaggregateMonthsTest.class, DisaggregateMonthsDaysToOpsTest.class, DisaggregateMonthsRepeatTest.class,
		MockSurrogateTest.class, TensorCalsimTest.class, SalinitySurrogateManagerTest.class, LinearConstraintTest.class,
		SurrogateMonthTest.class })
public class AllTests {

}
