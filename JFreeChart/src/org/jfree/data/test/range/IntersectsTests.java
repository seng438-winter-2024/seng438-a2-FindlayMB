package org.jfree.data.test.range;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

public class IntersectsTests {
    private Range range1;
    private Range range2;

    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }

    
    @Before
    public void setUp() throws Exception { 
        // ranges to be used for testing
    	range1 = new Range(-1, 1);
    	range2 = new Range(-10, -1);
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

}