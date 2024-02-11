package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

public class RangeTest {
    private Range range1;
    private Range range2;
    private Range range3;
    private Range range4;
    private Range range5;
    private Range range6;

    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    
    @Before
    public void setUp() throws Exception { 
        // ranges to be used for testing
    	range1 = new Range(-1, 1);
    	range2 = new Range(-10, -1);
    	range3 = new Range(-2, 2);
    	range4 = new Range(-2, 0);
    	range5 = new Range(0, 2);
    	range6 = new Range(1, 2);
    }

    // ****** next ten tests cover the intersects() function ****** //

    /**
     * This test tests intersects function
     * with does [-3,-2] intersect with [-1,1]
     * test case: lower < LB and upper < LB
     * Expected outcome: false
     */
    @Test
    public void intersects_FalseLeftTest() {
        assertEquals("Range [-3,-2] does not intersect [-1,1]",
                false, range1.intersects(-3,-2));
    }

    /**
     * This test tests intersects function
     * with does [-1, 5] intersect with [1, 10]
     * test case: lower < LB and LB < upper < UB
     * Expected outcome: true
     */
    @Test
    public void intersects_Left() {
        assertEquals("Range [-1, 5] does intersect [1, 10]",
                true, range2.intersects(-1, 5));
    }

    /**
     * This test tests intersects function
     * with does [2, 5] intersect with [1, 10]
     * test case: LB < lower < UB and LB < upper < UB
     * Expected outcome: true
     */
    @Test
    public void intersects_Encapsulation() {
        assertEquals("Range [2, 5] does intersect [1, 10]",
                true, range2.intersects(2, 5));
    }

    /**
     * This test tests intersects function
     * with does [2, 11] intersect with [1, 10]
     * test case:  lower = LB and LB < upper < UB
     * Expected outcome: true
     */
    @Test
    public void intersects_Right() {
        assertEquals("Range [2, 11] does intersect [1, 10]",
                true, range2.intersects(2, 11));
    }

    /**
     * This test tests intersects function
     * with does [2,3] intersect with [-1,1]
     * test case: lower > UB and upper > UB
     * Expected outcome: false
     */
    @Test
    public void intersects_FalseRightTest() {
        assertEquals("Range [2,3] does not intersect [-1,1]",
                false, range1.intersects(2,3));
    }

    /**
     * This test tests intersects function
     * with does [-3,-2] intersect with [-1,1]
     * test case: lower < LB and upper = LB
     * Expected outcome: false
     */
    @Test
    public void intersects_LeftEdgeBackwards() {
        assertEquals("Range [-3,-1] does intersect [-1,1]",
                true, range1.intersects(-3,-1));
    }

    /**
     * This test tests intersects function
     * with does [1, 5] intersect with [1, 10]
     * test case:  lower = LB and LB < upper < UB
     * Expected outcome: true
     */
    @Test
    public void intersects_LeftEdgeForward() {
        assertEquals("Range [1, 5] does intersect [1, 10]",
                true, range2.intersects(1, 5));
    }

    /**
     * This test tests intersects function
     * with does [1, 10] intersect with [1, 10]
     * test case: lower = LB and upper = LB
     * Expected outcome: true
     */
    @Test
    public void intersects_Self() {
        assertEquals("Range [1, 10] does intersect [1, 10]",
                true, range2.intersects(1, 10));
    }

    /**
     * This test tests intersects function
     * with does [2, 10] intersect with [1, 10]
     * test case: LB < lower < UB and upper = UB
     * Expected outcome: true
     */
    @Test
    public void intersects_RightEdgeBackward() {
        assertEquals("Range [2, 10] does intersect [1, 10]",
                true, range2.intersects(2, 10));
    }    

    /**
     * This test tests intersects function
     * with  does [1,3] intersect with [-1,1]
     * test case: lower = UB and upper > UB
     * Expected outcome: true
     */
    @Test
    public void intersects_RightEdgeForwards() {
        assertEquals("Range [1,3] does intersect [-1,1]",
        true, range1.intersects(1,3));
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
        		range3, Range.combine(null, range3));
    }

    /**
     * This test tests the combine function with a range of [-2, 0]
     * and another range of [-1, 1]
     * Expected outcome: returns a Range object with a range of [-2, 1]
     */
    @Test
    public void combine_Range1PartiallyInRange2() {
        assertEquals("The range [-2, 0] is combined with [-1, 1] to make a range of [-2, 1]", 
        		new Range(-2, 1), Range.combine(range4, range1));
    }

    /**
     * This test tests the combine function with a range of [-1, 1]
     * and another range of [0, 2]
     * Expected outcome: returns a Range object with a range of [-1, 2]
     */
    @Test
    public void combine_Range2PartiallyInRange1() {
        assertEquals("The range [-1, 1] is combined with [0, 2] to make a range of [-1, 2]", 
        		new Range(-1, 2), Range.combine(range1, range4));
    }

    /**
     * This test tests the combine function with a range of [-1, 1]
     * and another range of [-2 , 2]
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void combine_Range2EncapsulatesRange1() {
        assertEquals("The range [-1, 1] is combined with [-2, 2] to make a range of [-2, 2]", 
        		range3, Range.combine(range1, range3));
    }

    /**
     * This test tests the combine function with a range of [-2, 2]
     * and another range of [-1 , 1]
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void combine_Range1EncapsulatesRange2() {
        assertEquals("The range [-2, 2] is combined with [-1, 1] to make a range of [-2, 2]", 
        		range3, Range.combine(range3, range1));
    }

    /**
     * This test tests the combine function with a range of [-2, 0]
     * and another range of [1, 2]
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void combine_NoIntersection() {
        assertEquals("The range [-2, 0] is combined with [1, 2] to make a range of [-2, 2]", 
        		range3, Range.combine(range4, range6));
    }

    /**
     * This test tests the combine function with a range of [-2, 0]
     * and another range of [0, 2]
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void combine_IntersectsOnRange1UBandRange2LB() {
        assertEquals("The range [-2, 0] is combined with [0, 2] to make a range of [-2, 2]", 
        		range3, Range.combine(range4, range5));
    }
    
    /**
     * This test tests the combine function with a range of [0, 2]
     * and another range of [-2, 0]
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void combine_IntersectsOnRange2UBandRange1LB() {
        assertEquals("The range [0, 2] is combined with [-2, 0] to make a range of [-2, 2]", 
        		range3, Range.combine(range5, range4));
    }
    
    
    // ****** most of combine function tests pass when the arguments are flipped around ****** //


    // ****** next six tests cover the expandToInclude() function ****** //

    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of 0
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void expandToInclude_ExpandRangeWithin() {
        assertEquals("Range [-2, 2] expanded to a range of [-2, 2]",
        		range3, Range.expandToInclude(range3, 0));
    }
    
    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of -5
     * Expected outcome: returns a Range object with a range of [-5, 2]
     */
    @Test
    public void expandToInclude_ExpandRangeDown() {
        assertEquals("Range [-2, 2] expanded to a range of [-5, 2]",
        		range3, Range.expandToInclude(range3, -5));
    }

    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of 5
     * Expected outcome: returns a Range object with a range of [-2, 5]
     */
    @Test
    public void expandToInclude_ExpandRangeUp() {
        assertEquals("Range [-2, 2] expanded to a range of [-2, 5]",
        		range3, Range.expandToInclude(range3, 5));
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
        		range3, Range.expandToInclude(range3, -2));
    }
    
    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of 2
     * Expected outcome: returns a Range object with a range of [2, 2]
     */
    @Test
    public void expandToInclude_ExpandRangeIsUB() {
        assertEquals("Range [-2, 2] expanded to a range of [-2, 2]",
        		range3, Range.expandToInclude(range3, 2));
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
    
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
