package junit.tests;

import extra166y.Ops.Generator;
import junit.extensions.ActiveTestSuite;
import junit.extensions.ParallelTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * TestSuite that runs all the JUnit tests
 * 
 */
public class ParallelThreadsAllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
	
	public static Test suite() {
		ActiveTestSuite suite= new ActiveTestSuite("Framework Tests");
		suite.addTest(junit.tests.framework.AllTests.suite());
		suite.addTest(junit.tests.runner.AllTests.suite());
		suite.addTest(junit.tests.extensions.AllTests.suite());
		return suite;
	}
}