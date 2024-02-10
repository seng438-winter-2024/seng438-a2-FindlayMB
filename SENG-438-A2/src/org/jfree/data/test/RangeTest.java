package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.jmock.Mockery;
import org.junit.*;

public class RangeTest {
    private Range range1;
    private Range range2;
    private Range rangeExpandUp;
    private Range rangeExpandDown;
    private Mockery mockingContext;
    private Range mockRange;
    
    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    
    @Before
    public void setUp() throws Exception { 
    	range1 = new Range(-1, 1);
    	range2 = new Range(-10, -1);
    	
    	// ranges set up to test expandToInclude()
    	rangeExpandUp = Range.expandToInclude(new Range(-2, 2), 5);
    	rangeExpandDown = Range.expandToInclude(new Range(-2, 2), -5);
    }


    // ****** next two tests cover the getCentralValue() function ******//
    
    /**
     * This test tests if central value getter will actually 
     * return the central value
     * Expected outcome: 0
     */
    @Test
    public void getCentralValueReturnsCentralValue() {
        assertEquals("The central value of -1 and 1 should be 0",
                0, range1.getCentralValue(), .000000001d);
    }
    
    /**
     * This test tests if central value getter will return 
     * a value different from the actual center value
     * Expected outcome: 0
     */
    @Test
    public void getCentralValueReturnsImproperValue() {
        assertEquals("The central value of -1 and 1 should be 0",
                false, 0 != range1.getCentralValue());
    }
    
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
    
    // ****** next twelve tests cover the expandToInclude() function ******//
    /** 
     * The contains function will be used to test whether or not the
     * expandToInclude function works as intended. 
     * The contains function was tested before these tests to ensure that 
     * the contains function works as intended for the purposes of the 
     * expandToInclude tests.
     * Two ranges, rangeExpandUp and rangeExpand down will be tested.
     * Both of these ranges have an initial range of [-2,2].
     * rangeExpandUp has been expanded using the expandToInclude function
     * to include the value 5
     * rangeExpandDown has been expanded using the expandToInclude function
     * to include the value -5
     * The new bounds of these ranges are assumed to be the included values.
     * (rangeExpandUp [-2,2] -> [-2, 5], rangeExpandDown [-2,2] -> [-5, 2]
     */
    
    // below are six tests that test a range that has been expanded up
    
    /**
     * This test tests the expandToInclude function with a value within
     * the initial range
     * Expected outcome: true
     */    
    @Test
    public void expandToIncludeUpWithinInitialRange() {
        assertEquals("The value 0 is contained in the initial range [-2,2]",
                	true, rangeExpandUp.contains(0));
    }
    
    /**
     * This test tests the expandToInclude function with a value of the
     * initial UB
     * Expected outcome: true
     */    
    @Test
    public void expandToIncludeUpContainsInitialUB() {
        assertEquals("The value 2 is contained in the expanded range [-2,5]",
                	true, rangeExpandUp.contains(2));
    }
    
    /**
     * This test tests the expandToInclude function with a value of the
     * initial LB
     * Expected outcome: true
     */    
    @Test
    public void expandToIncludeUpMaintainsLB() {
        assertEquals("The value -2 is contained in the expanded range [-2,5]",
                	true, rangeExpandUp.contains(-2));
    }
    
    /**
     * This test tests the expandToInclude function with a value above
     * the initial range but below the new UB
     * Expected outcome: true
     */    
    @Test
    public void expandToIncludeUpAboveInitialRangeWithinUB() {
        assertEquals("The value 3 is contained in the expanded range [-2,5]",
                	true, rangeExpandUp.contains(3));
    }
    
    /**
     * This test tests the expandToInclude function with a value of the
     * new UB
     * Expected outcome: true
     */    
    @Test
    public void expandToIncludeUpExpandedRangeUB() {
        assertEquals("The value 5 is contained in the expanded range [-2,5]",
                	true, rangeExpandUp.contains(5));
    }
    
    /**
     * This test tests the expandToInclude function with a value above 
     * the expanded range UB
     * Expected outcome: false
     */    
    @Test
    public void expandToIncludeUpAboveExpandedRangeUB() {
        assertEquals("The value 6 is not contained in the expanded range [-2,5]",
                	false, rangeExpandUp.contains(6));
    }
    
    // below are six tests that test a range that has been expanded down
    
    /**
     * This test tests the expandToInclude function with a value within
     * the initial range
     * Expected outcome: true
     */    
    @Test
    public void expandToIncludeDownWithinInitialRange() {
        assertEquals("The value 0 is contained in the initial range [-2,2]",
                	true, rangeExpandDown.contains(0));
    }

    /**
     * This test tests the expandToInclude function with a value of the
     * initial LB
     * Expected outcome: true
     */    
    @Test
    public void expandToIncludeDownContainsInitialLB() {
        assertEquals("The value -2 is contained in the expanded range [-5,2]",
                	true, rangeExpandDown.contains(-2));
    }
    
    /**
     * This test tests the expandToInclude function with a value of the
     * initial UB 
     * Expected outcome: true
     */    
    @Test
    public void expandToIncludeDownMaintainsUB() {
        assertEquals("The value 2 is contained in the expanded range [-5,2]",
                	true, rangeExpandDown.contains(2));
    }
    
    /**
     * This test tests the expandToInclude function with a value below
     * the initial range but above the new LB
     * Expected outcome: true
     */    
    @Test
    public void expandToIncludeDownAboveInitialRangeWithinLB() {
        assertEquals("The value -3 is contained in the expanded range [-5,2]",
                	true, rangeExpandDown.contains(-3));
    }
    
    /**
     * This test tests the expandToInclude function with a value of the
     * new LB
     * Expected outcome: true
     */    
    @Test
    public void expandToIncludeDownExpandedRangeLB() {
        assertEquals("The value -5 is contained in the expanded range [-5,2]",
                	true, rangeExpandDown.contains(-5));
    }
    
    /**
     * This test tests the expandToInclude function with a value below 
     * the expanded range LB
     * Expected outcome: false
     */    
    @Test
    public void expandToIncludeDownBelowExpandedRangeLB() {
        assertEquals("The value 6 is not contained in the expanded range [-5, 2]",
                	false, rangeExpandDown.contains(-6));
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