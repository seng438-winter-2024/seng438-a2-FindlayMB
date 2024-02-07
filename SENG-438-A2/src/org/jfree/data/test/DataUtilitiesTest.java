package org.jfree.data.test;

import org.jfree.data.DataUtilities;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CalculateColumnTotalTests.class,
        CalculateRowTotalTests.class,
        CreateNumberArrayTests.class,
        CreateNumberArray2DTests.class,
        GetCumulativePercentagesTests.class})
public class DataUtilitiesTest extends DataUtilities {

}
