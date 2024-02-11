package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.jmock.Mockery;
import org.junit.*;

public class RangeTest {
    private Range range1;
    private Range range2;
    private Range rangeToExpand;
    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    
    @Before
    public void setUp() throws Exception { 
    	range1 = new Range(-1, 1);
    	range2 = new Range(-10, -1);
    	rangeToExpand = new Range(-2, 2);
    }


    // ****** next tests cover 
    
    // ****** next five tests cover the contains() function ******//
    
    /**
     * This test tests the contains function with a value within a range
     * Expected outcome: true
     */
    @Test
    public void containsWithinRange() {
        assertEquals("The value 0 is contained in the range [-1,1]",
                true, range1.contains(0));
    }
    
    /**
     * This test tests the contains function with a value of the upper bound
     * Expected outcome: true
     */
    @Test
    public void containsUpperBound() {
        assertEquals("The value 1 is contained in the range [-1,1]",
                true, range1.contains(1));
    }
    
    /**
     * This test tests the contains function with a value of the lower bound
     * Expected outcome: true
     */
    @Test
    public void containsLowerBound() {
        assertEquals("The value -1 is contained in the range [-1,1]",
                true, range1.contains(-1));
    }
    
    /**
     * This test tests the contains function with a value above the upper bound
     * Expected outcome: false
     */
    @Test
    public void containsAboveUpperBound() {
        assertEquals("The value 3 is not contained in the range [-1,1]",
                false, range1.contains(3));
    }
    
    /**
     * This test tests the contains function with a value below the lower bound
     * Expected outcome: false
     */
    @Test
    public void containsBelowLowerBound() {
        assertEquals("The value -3 is not contained in the range [-1,1",
                false, range1.contains(-3));
    }
    
    // ****** next six tests cover the expandToInclude() function ******//
    
    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of 5
     * Expected outcome: returns a Range object with a range of [-2, 5]
     */
    @Test
    public void expandToInclude_ExpandRangeUp() {
        assertEquals("Range [-2, 2] expanded to a range of [-2, 5]",
        		rangeToExpand, Range.expandToInclude(rangeToExpand, 5));
    }
    
    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of -5
     * Expected outcome: returns a Range object with a range of [-5, 2]
     */
    @Test
    public void expandToInclude_ExpandRangeDown() {
        assertEquals("Range [-2, 2] expanded to a range of [-5, 2]",
        		rangeToExpand, Range.expandToInclude(rangeToExpand, -5));
    }
    
    
    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of 0
     * Expected outcome: returns a Range object with a range of [-2, 2]
     */
    @Test
    public void expandToInclude_ExpandRangeWithin() {
        assertEquals("Range [-2, 2] expanded to a range of [-2, 2]",
        		rangeToExpand, Range.expandToInclude(rangeToExpand, 0));
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
     * from [-2, 2] and a value to include of 2
     * Expected outcome: returns a Range object with a range of [2, 2]
     */
    @Test
    public void expandToInclude_ExpandRangeIsUB() {
        assertEquals("Range [-2, 2] expanded to a range of [-2, 2]",
        		rangeToExpand, Range.expandToInclude(rangeToExpand, 2));
    }
      
    
    
    /**
     * This test tests expandToInclude function with inputs of a range
     * from [-2, 2] and a value to include of -2
     * Expected outcome: returns a Range object with a range of [2, 2]
     */
    @Test
    public void expandToInclude_ExpandRangeIsLB() {
        assertEquals("Range [-2, 2] expanded to a range of [-2, 2]",
        		rangeToExpand, Range.expandToInclude(rangeToExpand, -2));
    }
      
    
    // ****** next ten tests cover the intersects() function ******//
    
    /**
     * This test tests intersects function
     * with  does [1,3] intersect with [-1,1]
     * test case: lower = UB and upper > UB
     * Expected outcome: true
     */
    @Test
    public void intersectsRightEdgeForwards() {
        assertEquals("Range [1,3] does intersect [-1,1]",
        true, range1.intersects(1,3));
    }
    
    /**
     * This test tests intersects function
     * with does [-3,-2] intersect with [-1,1]
     * test case: lower < LB and upper = LB
     * Expected outcome: false
     */
    @Test
    public void intersectsLeftEdgeBackwards() {
        assertEquals("Range [-3,-1] does intersect [-1,1]",
                true, range1.intersects(-3,-1));
    }

    /**
     * This test tests intersects function
     * with does [2,3] intersect with [-1,1]
     * test case: lower > UB and upper > UB
     * Expected outcome: false
     */
    @Test
    public void intersectsFalseRightTest() {
        assertEquals("Range [2,3] does not intersect [-1,1]",
                false, range1.intersects(2,3));
    }

    /**
     * This test tests intersects function
     * with does [-3,-2] intersect with [-1,1]
     * test case: lower < LB and upper < LB
     * Expected outcome: false
     */
    @Test
    public void intersectsFalseLeftTest() {
        assertEquals("Range [-3,-2] does not intersect [-1,1]",
                false, range1.intersects(-3,-2));
    }
    
    /**
     * This test tests intersects function
     * with does [2, 5] intersect with [1, 10]
     * test case: LB < lower < UB and LB < upper < UB
     * Expected outcome: true
     */
    @Test
    public void intersectsEncapsulation() {
        assertEquals("Range [2, 5] does intersect [1, 10]",
                true, range2.intersects(2, 5));
    }
    
    /**
     * This test tests intersects function
     * with does [1, 10] intersect with [1, 10]
     * test case: lower = LB and upper = LB
     * Expected outcome: true
     */
    @Test
    public void intersectsSelf() {
        assertEquals("Range [1, 10] does intersect [1, 10]",
                true, range2.intersects(1, 10));
    }
    
    /**
     * This test tests intersects function
     * with does [-1, 5] intersect with [1, 10]
     * test case: lower < LB and LB < upper < UB
     * Expected outcome: true
     */
    @Test
    public void intersectsLeft() {
        assertEquals("Range [-1, 5] does intersect [1, 10]",
                true, range2.intersects(-1, 5));
    }
    
    /**
     * This test tests intersects function
     * with does [1, 5] intersect with [1, 10]
     * test case:  lower = LB and LB < upper < UB
     * Expected outcome: true
     */
    @Test
    public void intersectsLeftEdgeForward() {
        assertEquals("Range [1, 5] does intersect [1, 10]",
                true, range2.intersects(1, 5));
    }
    
    /**
     * This test tests intersects function
     * with does [2, 10] intersect with [1, 10]
     * test case: LB < lower < UB and upper = UB
     * Expected outcome: true
     */
    @Test
    public void intersectsRightEdgeBackward() {
        assertEquals("Range [2, 10] does intersect [1, 10]",
                true, range2.intersects(2, 10));
    }
    
    /**
     * This test tests intersects function
     * with does [2, 11] intersect with [1, 10]
     * test case:  lower = LB and LB < upper < UB
     * Expected outcome: true
     */
    @Test
    public void intersectsRight() {
        assertEquals("Range [2, 11] does intersect [1, 10]",
                true, range2.intersects(2, 11));
    }
    
    // ****** next five tests cover the constrain() function ******//
    
    /**
     * This test tests constrain function
     * with constraining 5 into [-1,1]
     * Expected outcome: 1
     */
    @Test
    public void constrainOverRange() {
        assertEquals("Expected 1",
                1, range1.constrain(5),.000000001d);
    }

    /**
     * This test tests constrain function
     * with constraining 0 into [-1,1]
     * Expected outcome: 0
     */
    @Test
    public void constrainInRange() {
        assertEquals("Expected 0",
                0, range1.constrain(0),.000000001d);
    }

    /**
     * This test tests constrain function
     * with constraining -5 into [-1,1]
     * Expected outcome: -1
     */
    @Test
    public void constrainUnderRange() {
        assertEquals("Expected 1",
                -1, range1.constrain(-5),.000000001d);
    }
    
    /**
     * This test tests constrain function
     * with constraining -1 into [-1,1]
     * Expected outcome: -1
     */
    @Test
    public void constrainIsLowerBound() {
        assertEquals("Expected 1",
                -1, range1.constrain(-1),.000000001d);
    }
    
    /**
     * This test tests constrain function
     * with constraining 1 into [-1,1]
     * Expected outcome: 1
     */
    @Test
    public void constrainIsUpperBound() {
        assertEquals("Expected 1",
                1, range1.constrain(1),.000000001d);
    }
    
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}