package org.jfree.data.test;


import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;


public class RangeTest {
    private Range exampleRange;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception {
        exampleRange = new Range(-1, 1);
    }


    /**
     * This test tests central value getter
     * Expected outcome: 0
     */
    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
                0, exampleRange.getCentralValue(), .000000001d);
    }

    /**
     * This test tests lower bound getter
     * Expected outcome: -1
     */
    @Test
    public void getLowerBoundTest() {
        assertEquals("The lower bound should be -1",
                -1, exampleRange.getLowerBound(),.000000001d);
    }

    /**
     * This test tests upper bound getter
     * Expected outcome: 1
     */
    @Test
    public void getUpperBoundTest() {
        assertEquals("The upper bound should be 1",
                1, exampleRange.getUpperBound(),.000000001d);
    }

    /**
     * This test tests length getter
     * Expected outcome: 2
     */
    @Test
    public void getLengthTest() {
        assertEquals("The length should be 1 - (-1) = 2",
                2, exampleRange.getLength(),.000000001d);
    }


    /**
     * This test tests intersects function
     * with  does [1,3] intersect with [-1,1]
     * Expected outcome: true
     */
    @Test
    public void intersectsTrueRightTest() {
        assertEquals("Range [1,3] does intersect [-1,1]",
        true, exampleRange.intersects(1,3));
    }

    /**
     * This test tests intersects function
     * with does [-3,-2] intersect with [-1,1]
     * Expected outcome: false
     */
    @Test
    public void intersectsTrueLeftTest() {
        assertEquals("Range [-3,-1] does intersect [-1,1]",
                true, exampleRange.intersects(-3,-1));
    }

    /**
     * This test tests intersects function
     * with does [2,3] intersect with [-1,1]
     * Expected outcome: false
     */
    @Test
    public void intersectsFalseRightTest() {
        assertEquals("Range [2,3] does not intersect [-1,1]",
                false, exampleRange.intersects(2,3));
    }

    /**
     * This test tests intersects function
     * with does [-3,-2] intersect with [-1,1]
     * Expected outcome: false
     */
    @Test
    public void intersectsFalseLeftTest() {
        assertEquals("Range [-3,-2] does not intersect [-1,1]",
                false, exampleRange.intersects(-3,-2));
    }


    /**
     * This test tests constrain function
     * with constraining 5 into [-1,1]
     * Expected outcome: 1
     */
    @Test
    public void constrainUpperTest() {
        assertEquals("Expected 1",
                1, exampleRange.constrain(5),.000000001d);
    }

    /**
     * This test tests constrain function
     * with constraining 0 into [-1,1]
     * Expected outcome: 0
     */
    @Test
    public void constrainMiddleTest() {
        assertEquals("Expected 0",
                0, exampleRange.constrain(0),.000000001d);
    }

    /**
     * This test tests constrain function
     * with constraining -5 into [-1,1]
     * Expected outcome: -1
     */
    @Test
    public void constrainLowerTest() {
        assertEquals("Expected 1",
                -1, exampleRange.constrain(-5),.000000001d);
    }






    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
