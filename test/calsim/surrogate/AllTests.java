package calsim.surrogate;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AggregateMonthsTest.class, CircleLineSearchableTest.class, ConservativeSplineTest.class,
		DisaggregateMonthsTest.class, MockSurrogateTest.class, TensorCalsimTest.class })
public class AllTests {

}
