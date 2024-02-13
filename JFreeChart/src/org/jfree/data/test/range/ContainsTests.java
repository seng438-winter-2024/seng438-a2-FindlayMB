package org.jfree.data.test.range;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

public class ContainsTests {
    private Range range1;

    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    
    @Before
    public void setUp() throws Exception { 
        // ranges to be used for testing
    	range1 = new Range(-1, 1);
    }

    // ****** next five tests cover the contains() function ****** //
    
    /**
     * This test tests the contains function with an input of 0 
     * which is within the range [-1, 1]
     * Expected outcome: true
     */
    @Test
    public void contains_WithinRange() {
        assertEquals("The value 0 is contained in the range [-1,1]",
                true, range1.contains(0));
    }
    
    /**
     * This test tests the contains function with an input of -3
     * which is below the LB of the range [-1, 1]
     * Expected outcome: false
     */
    @Test
    public void contains_BelowLowerBound() {
        assertEquals("The value -3 is not contained in the range [-1,1",
                false, range1.contains(-3));
    }  

    /**
     * This test tests the contains function with an input of 3
     * which is above the UB of the range [-1, 1]
     * Expected outcome: false
     */
    @Test
    public void contains_AboveUpperBound() {
        assertEquals("The value 3 is not contained in the range [-1,1]",
                false, range1.contains(3));
    }
    /**
     * This test tests the contains function with an input of 1 
     * which is the UB of the range [-1, 1]
     * Expected outcome: true
     */
    @Test
    public void contains_UpperBound() {
        assertEquals("The value 1 is contained in the range [-1,1]",
                true, range1.contains(1));
    }
    
    /**
     * This test tests the contains function with an input of -1
     * which is the LB of the range [-1, 1]
     * Expected outcome: true
     */
    @Test
    public void contains_LowerBound() {
        assertEquals("The value -1 is contained in the range [-1,1]",
                true, range1.contains(-1));
    }
}
