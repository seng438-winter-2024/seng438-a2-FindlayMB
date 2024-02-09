package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.jmock.Mockery;
import org.junit.*;

public class RangeTest {
    private Range range1;
    private Range range2;
    private Range range3;
    private Range range4;
    private Range range5;
    private Mockery mockingContext;
    private Range mockRange;
    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	range1 = new Range(-1, 1);
    	range2 = new Range(-10, -1);
    	range3 = new Range(1, 10);
    	range4 = new Range(0, 3);
    	range5 = new Range(-3, 0);
    }


    // ****** next four tests cover the getCentralValue() function ******//
    
    /**
     * This test tests if central value getter will return 0
     * Expected outcome: 0
     */
    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
                0, range1.getCentralValue(), .000000001d);
    }
    
    /**
     * This test tests if central value getter will return a negative
     * Expected outcome: -5.5
     */
    @Test
    public void centralValueShouldBeNegative() {
        assertEquals("The central value of -1 and 1 should be 0",
                -5.5, range2.getCentralValue(), .000000001d);
    }
    
    /**
     * This test tests if central value getter will return a positive
     * Expected outcome: 5.5
     */
    @Test
    public void centralValueShouldBePositive() {
        assertEquals("The central value of -1 and 1 should be 0",
                5.5, range3.getCentralValue(), .000000001d);
    }
    
    /**
     * This test tests if central value getter will return an incorrect value
     * Expected outcome: 0
     */
    @Test
    public void centralValueShouldReturnCorrectValue() {
        assertEquals("The central value of -1 and 1 should be 0, not any other value",
                0, range1.getCentralValue(), .000000001d);
    }
    
    
    // ****** next four tests cover the getLowerBound() function ******//
    
    /**
     * This test tests if lower bound getter can return a negative
     * Expected outcome: -1
     */
    @Test
    public void getLowerBoundReturnsNegative() {
        assertEquals("The lower bound should be -1",
                -1, range1.getLowerBound(),.000000001d);
    }
    
    /**
     * This test tests if lower bound getter can return a positive
     * Expected outcome: 1
     */
    @Test
    public void getLowerBoundReturnsPositive() {
        assertEquals("The lower bound should be -1",
                1, range3.getLowerBound(),.000000001d);
    }
    
    /**
     * This test tests if lower bound getter can return a zero
     * Expected outcome: 0
     */
    @Test
    public void getLowerBoundReturnsZero() {
        assertEquals("The lower bound should be -1",
                0, range4.getLowerBound(),.000000001d);
    }
    
    /**
     * This test tests if lower bound getter will return an incorrect value
     * Expected outcome: 0
     */
    @Test
    public void getLowerBoundReturnsCorrectValue() {
        assertEquals("The lower bound should be -1, not any other value",
                -1, range1.getLowerBound(), .000000001d);
    }
    
    // ****** next four tests cover the getUpperBound() function ******//
    
    /**
     * This test tests if upper bound getter can return a negative
     * Expected outcome: -10
     */
    @Test
    public void getUpperBoundReturnsNegative() {
        assertEquals("The upper bound should be -1",
                -1, range2.getUpperBound(),.000000001d);
    }
    
    /**
     * This test tests if upper bound getter can return a positive
     * Expected outcome: 1
     */
    @Test
    public void getUpperBoundReturnsPositive() {
        assertEquals("The upper bound should be -1",
                1, range1.getUpperBound(),.000000001d);
    }
    
    /**
     * This test tests if upper bound getter can return a zero
     * Expected outcome: 0
     */
    @Test
    public void getUpperBoundReturnsZero() {
        assertEquals("The upper bound should be -1",
                0, range5.getUpperBound(),.000000001d);
    }
    
    /**
     * This test tests if upper bound getter will return an incorrect value
     * Expected outcome: 0
     */
    @Test
    public void getUpperBoundReturnsCorrectValue() {
        assertEquals("The upper bound should be 1, not any other value",
                1, range1.getUpperBound() ,.000000001d);
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
