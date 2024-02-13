package org.jfree.data.test.range;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

public class ExpandToIncludeTests {
    private Range range1;

    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    
    @Before
    public void setUp() throws Exception { 
        // ranges to be used for testing
    	range1 = new Range(-2, 0);
    }

    // ****** next six tests cover the expandToInclude() function ****** //

    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of 0
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void expandToInclude_ExpandRangeWithin() {
        assertEquals("Range [-2, 2] expanded to a range of [-2, 2]",
        		range1, Range.expandToInclude(range1, 0));
    }
    
    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of -5
     * Expected outcome: returns a Range object with a range of [-5, 2]
     */
    @Test
    public void expandToInclude_ExpandRangeDown() {
        assertEquals("Range [-2, 2] expanded to a range of [-5, 2]",
        		range1, Range.expandToInclude(range1, -5));
    }

    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of 5
     * Expected outcome: returns a Range object with a range of [-2, 5]
     */
    @Test
    public void expandToInclude_ExpandRangeUp() {
        assertEquals("Range [-2, 2] expanded to a range of [-2, 5]",
        		range1, Range.expandToInclude(range1, 5));
    }
    
    /**
     * This test tests expandToInclude function with inputs of a range
     * of Null and a value to include of 2
     * Expected outcome: returns a Range object with a range of [2, 2]
     */
    @Test
    public void expandToInclude_ExpandRangeNull() {
        assertEquals("Range Null expanded to a range of [2, 2]",
        		new Range(2, 2), Range.expandToInclude(null, 2));
    }

    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of -2
     * Expected outcome: returns a Range object with a range of [2, 2]
     */
    @Test
    public void expandToInclude_ExpandRangeIsLB() {
        assertEquals("Range [-2, 2] expanded to a range of [-2, 2]",
        		range1, Range.expandToInclude(range1, -2));
    }
    
    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of 2
     * Expected outcome: returns a Range object with a range of [2, 2]
     */
    @Test
    public void expandToInclude_ExpandRangeIsUB() {
        assertEquals("Range [-2, 2] expanded to a range of [-2, 2]",
        		range1, Range.expandToInclude(range1, 2));
    }
}
