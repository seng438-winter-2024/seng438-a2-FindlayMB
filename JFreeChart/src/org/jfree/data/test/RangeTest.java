package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range exampleRange;
    private Range testRange;
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	exampleRange = new Range(-1, 1);
    	testRange = new Range(4, 5);
    }
    
    @Test
    public void constrainWithinRange() {
    	assertEquals("Should be 0.5 because it is within range", 0.5, exampleRange.constrain(0.5), .000000001d);
    }
    
    @Test
    public void constrainOutofRange1() {
    	assertEquals("Should be 1 because it is not range", 1.0, exampleRange.constrain(1.5), .000000001d);
    }
    
    /*
     * Need to take a look at, this is weird
     */
//    @Test
//    public void constrainOutofRange2() {
//    	assertEquals("Should be -1 because it is not range", -1.0, exampleRange.constrain(-1.5), .000000001d);
//    }
    
    @Test 
    public void containsShouldBeTrue() {
    	assertTrue("0.5 should be within the range of -1 and 1", exampleRange.contains(0.5));
    }
    
    
    /*
     *		combine function
     */
    @Test
    public void combineShouldReturnNewRange() {
    	Range range1 = new Range(4, 8);
    	Range range2 = new Range(1, 5);
    	Range expected = new Range(1, 8);
    	assertEquals("New range should be [1, 8]", expected, Range.combine(range1, range2));
    }
    
    /*
     *		combine function - one range is null
     */
    @Test
    public void combineShouldReturnRange2() {
    	Range range1 = null;
    	Range range2 = new Range(1, 5);
    	Range expected = new Range(1, 5);
    	assertEquals("New range should be [1, 5]", expected, Range.combine(range1, range2));
    }
    
    /*
     *		combine function - both are null
     */
    @Test
    public void combineShouldReturnNull() {
    	Range range1 = null;
    	Range range2 = null;
    	assertEquals("New range should be null", null, Range.combine(range1, range2));
    }
    
    /*
     *		equals function
     */

    
    /*
     *		expand function
     */
    @Test 
    public void expandTest() {
    	Range expected = new Range(-2, 2);
    	assertEquals("New range for exampleRange should be [-2, 2]", expected, Range.expand(exampleRange, 0.5, 0.5));
    }
    
    
    /*
     *		expandToInclude function
     */
    
    /*
     *		combine function
     */

    
    /*
     *		getCentralValue function
     */
    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
        0, exampleRange.getCentralValue(), .000000001d);
        
    }
    
    /*
     *		getLength function
     */
    @Test 
    public void lengthTest() {
    	assertEquals("Length should be 2", 2, exampleRange.getLength(), .000000001d);
    }
    
    /*
     *		getLowerBound function
     */
    @Test
    public void lowerBoundTest() {
    	assertEquals("Lower Bound should be -1", -1, exampleRange.getLowerBound(), .000000001d);
    }
    
    /*
     *		getUpperBound function
     */
    @Test
    public void upperBoundTest() {
    	assertEquals("Upper Bound should be 1", 1, exampleRange.getUpperBound(), .000000001d);
    }

    /*
     *		Hashcode function
     */
    @Test 
    public void hashcodeTest() {
    	String rangeStr = String.valueOf(-1) + String.valueOf(1);
    	int expected = rangeStr.hashCode();
    	assertEquals(expected, exampleRange.hashCode());
    }
    
    /*
     *		intersects(double lower, double upper) function
     */
    
    /*
     *		shift(Range base, double delta) function
     */
    
    /*
     *		shift(Range base, double delta) function
     */


    /*
     * 		toString()
     */
    @Test
    public void toStringTest() {
    	String expected = "Range[-1.0,1.0]";
    	String actual = exampleRange.toString();
    	assertEquals("Should be Range[-1.0,1.0]", expected, actual);
    }



    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}