package org.jfree.data.test.range;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

public class CombineTests {
    private Range range1;
    private Range range2;
    private Range range3;
    private Range range4;
    private Range range5;

    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    
    @Before
    public void setUp() throws Exception { 
        // ranges to be used for testing
    	range1 = new Range(-1, 1);
    	range2 = new Range(-2, 2);
    	range3 = new Range(-2, 0);
    	range4 = new Range(0, 2);
    	range5 = new Range(1, 2);
    }

    // ****** next nine tests cover the combine() function ****** //

    /**
     * This test tests the combine function with a range of null
     * and another range of null
     * Expected outcome: returns a Range object with a range of null
     */
    @Test
    public void combine_BothRangesAreNull() {
        assertEquals("The range null is combined with null to make a range of null", 
        		null, Range.combine(null, null));
    }

    /**
     * This test tests the combine function with a range of null
     * and another range of [-2 , 2]
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void combine_OneRangeIsNull() {
        assertEquals("The range null is combined with [-2, 2] to make a range of [-2, 2]", 
        		range2, Range.combine(null, range2));
    }

    /**
     * This test tests the combine function with a range of [-2, 0]
     * and another range of [-1, 1]
     * Expected outcome: returns a Range object with a range of [-2, 1]
     */
    @Test
    public void combine_Range1PartiallyInRange2() {
        assertEquals("The range [-2, 0] is combined with [-1, 1] to make a range of [-2, 1]", 
        		new Range(-2, 1), Range.combine(range3, range1));
    }

    /**
     * This test tests the combine function with a range of [-1, 1]
     * and another range of [0, 2]
     * Expected outcome: returns a Range object with a range of [-1, 2]
     */
    @Test
    public void combine_Range2PartiallyInRange1() {
        assertEquals("The range [-1, 1] is combined with [0, 2] to make a range of [-1, 2]", 
        		new Range(-1, 2), Range.combine(range1, range3));
    }

    /**
     * This test tests the combine function with a range of [-1, 1]
     * and another range of [-2 , 2]
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void combine_Range2EncapsulatesRange1() {
        assertEquals("The range [-1, 1] is combined with [-2, 2] to make a range of [-2, 2]", 
        		range2, Range.combine(range1, range2));
    }

    /**
     * This test tests the combine function with a range of [-2, 2]
     * and another range of [-1 , 1]
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void combine_Range1EncapsulatesRange2() {
        assertEquals("The range [-2, 2] is combined with [-1, 1] to make a range of [-2, 2]", 
        		range2, Range.combine(range2, range1));
    }

    /**
     * This test tests the combine function with a range of [-2, 0]
     * and another range of [1, 2]
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void combine_NoIntersection() {
        assertEquals("The range [-2, 0] is combined with [1, 2] to make a range of [-2, 2]", 
        		range2, Range.combine(range3, range5));
    }

    /**
     * This test tests the combine function with a range of [-2, 0]
     * and another range of [0, 2]
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void combine_IntersectsOnRange1UBandRange2LB() {
        assertEquals("The range [-2, 0] is combined with [0, 2] to make a range of [-2, 2]", 
        		range2, Range.combine(range3, range4));
    }
    
    /**
     * This test tests the combine function with a range of [0, 2]
     * and another range of [-2, 0]
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void combine_IntersectsOnRange2UBandRange1LB() {
        assertEquals("The range [0, 2] is combined with [-2, 0] to make a range of [-2, 2]", 
        		range2, Range.combine(range4, range3));
    }
    
    
    // ****** most of combine function tests pass when the arguments are flipped around ****** //
}
