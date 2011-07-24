package junit.tests;

import extra166y.Ops.Generator;
import junit.extensions.ParallelTestSuite;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * TestSuite that runs all the JUnit tests
 * 
 */
public class ParallelAllTests {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}

	public static ParallelTestSuite suite() {
		final int i[]= new int[1];
		ParallelTestSuite suite= new ParallelTestSuite("Framework Tests", 3);
		suite.generate(new Generator<Test>() {
			public Test op() {
				switch (i[0]) {
				case 0:
					return junit.tests.framework.AllTests.suite();
				case 1:
					return junit.tests.runner.AllTests.suite();
				case 2:
					return junit.tests.extensions.AllTests.suite();
				}
				i[0]++;
				return null;
			}
		});
		return suite;
	}
}