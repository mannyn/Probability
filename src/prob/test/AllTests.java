package prob.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( 
		{ 
			DiseaseTestCase.class,
			DrugTestingTestCase.class,
			StudentTrousersTestCase.class,
			MontyHallTestCase.class
			})
public class AllTests {
}
