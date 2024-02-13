package org.jfree.data.test.range;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

public class ConstrainTests {
    private Range range1;

    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    
    @Before
    public void setUp() throws Exception { 
        // ranges to be used for testing
    	range1 = new Range(-1, 1);
    }

    // ****** next five tests cover the constrain() function ****** //

    /**
     * This test tests constrain function
     * with constraining 0 into [-1,1]
     * Expected outcome: 0
     */
    @Test
    public void constrain_InRange() {
        assertEquals("Expected 0",
                0, range1.constrain(0),.000000001d);
    }

    /**
     * This test tests constrain function
     * with constraining -5 into [-1,1]
     * Expected outcome: -1
     */
    @Test
    public void constrain_UnderRange() {
        assertEquals("Expected 1",
                -1, range1.constrain(-5),.000000001d);
    }
    
    /**
     * This test tests constrain function
     * with constraining 5 into [-1,1]
     * Expected outcome: 1
     */
    @Test
    public void constrain_OverRange() {
        assertEquals("Expected 1",
                1, range1.constrain(5),.000000001d);
    }
    
    /**
     * This test tests constrain function
     * with constraining -1 into [-1,1]
     * Expected outcome: -1
     */
    @Test
    public void constrain_IsLowerBound() {
        assertEquals("Expected 1",
                -1, range1.constrain(-1),.000000001d);
    }
    
    /**
     * This test tests constrain function
     * with constraining 1 into [-1,1]
     * Expected outcome: 1
     */
    @Test
    public void constrain_IsUpperBound() {
        assertEquals("Expected 1",
                1, range1.constrain(1),.000000001d);
    }
}
