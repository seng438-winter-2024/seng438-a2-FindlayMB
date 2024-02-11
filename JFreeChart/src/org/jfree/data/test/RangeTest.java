package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    private Range range1;
    private Range range2;
    private Range range3;
    private Range range4;
    private Range nullRange;
    private Range nullRange2;
    
    @BeforeClass public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { 
    	range1 = new Range(-1, 1);
    	range2 = new Range(1, 5);
    	range3 = new Range(2, 8);
    	range4 = new Range(4, 8);
    	nullRange = null;
    	nullRange2 = null;
    	
    }
    
    /*
     * 		Constrain test - value within range
     */
    @Test
    public void constrainWithinRange() {
    	assertEquals("Should be 0.5 because it is within the range of [-1, 1]", 0.5, range1.constrain(0.5), .000000001d);
    }
    
    /*
     * 		Constrain test - value out of upper bound
     */
    @Test
    public void constrainOutofUpperBound() {
    	assertEquals("Should be 1 because 1.5 is not range of [-1, 1]", 1.0, range1.constrain(1.5), .000000001d);
    }
    
    /*
     * 		Constrain test - value out of lower bound
     */
    @Test
    public void constrainOutofLowerBound() {
    	assertEquals("Should be -1 because -1.5 is not range of [-1, 1]", -1.0, range1.constrain(-1.5), .000000001d);
    }
    
    
    /*
     * 		Contain test - value within range
     */
    @Test 
    public void containsShouldBeTrue() {
    	assertTrue("0.5 should be within the range of -1 and 1", range1.contains(0.5));
    }
    
    /*
     * 		Contain test - value out of range
     */
    @Test 
    public void containsShouldBeFalse() {
    	assertFalse("1.5 is out of range of [-1, 1]", range1.contains(1.5));
    }
    
    
    /*
     *		combine function - both range are not null
     */
    @Test
    public void combineShouldReturnNewRange() {
    	Range expected = new Range(1, 8);
    	assertEquals("New range should be [1, 8]", expected, Range.combine(range4, range2));
    }
    
    /*
     *		combine function - one range is null
     */
    @Test
    public void combineShouldReturnRange2() {
    	assertEquals("New range should be [1, 5]", range2, Range.combine(range2, nullRange));
    }
    
    /*
     *		combine function - both range are null
     */
    @Test
    public void combineShouldReturnNull() {
    	assertEquals("New range should be null", null, Range.combine(nullRange, nullRange2));
    }
    
    /*
     *		equals function - equal objects
     */
    @Test
    public void equalShouldBeTrue() {
    	Range range = new Range(-1, 1);
    	assertTrue("Both objects should be equal", range1.equals(range));
    }
    
    /*
     *		equals function - different objects
     */
    @Test
    public void equalShouldBeFalse() {
    	Range range = new Range(-2, 1);
    	assertFalse("The two objects are not equal", range1.equals(range));
    }

    
    /*
     *		expand function 
     */
    @Test 
    public void expandTest() {
    	Range expected = new Range(-2, 2);
    	assertEquals("New range for exampleRange should be [-2, 2]", expected, Range.expand(range1, 0.5, 0.5));
    }
    
    
    /*
     *		expandToInclude function - expand and include new upper bound 
     */
    @Test 
    public void expandToIncludeNewUpperTest() {
    	Range expected = new Range(-1, 2);
    	assertEquals("New range for exampleRange should be [-1, 2]", expected, Range.expandToInclude(range1, 2));
    }
    
    /*
     *		expandToInclude function - expand and include new upper bound 
     */
    @Test 
    public void expandToIncludeNewLower() {
    	assertEquals("New range for exampleRange should be [2, 8]", range3, Range.expandToInclude(range4, 2));
    }

    /*
     *		getCentralValue function
     */
    @Test
    public void centralValueShouldBeZero() {
        assertEquals("The central value of -1 and 1 should be 0",
        0, range1.getCentralValue(), .000000001d);
        
    }
    
    /*
     *		getLength function
     */
    @Test 
    public void lengthTest() {
    	assertEquals("Length should be 2", 2, range1.getLength(), .000000001d);
    }
    
    /*
     *		getLowerBound function
     */
    @Test
    public void lowerBoundTest() {
    	assertEquals("Lower Bound should be -1", -1, range1.getLowerBound(), .000000001d);
    }
    
    /*
     *		getUpperBound function
     */
    @Test
    public void upperBoundTest() {
    	assertEquals("Upper Bound should be 1", 1, range1.getUpperBound(), .000000001d);
    }

    /*
     *		Hashcode function
     */
    @Test 
    public void hashcodeTest() {
    	String rangeStr = String.valueOf(-1) + String.valueOf(1);
    	int expected = rangeStr.hashCode();
    	assertEquals(expected, range1.hashCode());
    }
    
    /*
     *		intersects(double lower, double upper) function -  lower < 3 & 4 < upper
     */
    @Test
    public void intersectShouldBeTrue() {
    	
    	assertTrue("The range of [3, 4] intersects should with the range of [2, 8]", range3.intersects(3, 4));
    }
    
    /*
     *		intersects(double lower, double upper) function -  1 < lower < 5 < upper
     */
    @Test
    public void intersectShouldBeTrue2() {
    	
    	assertTrue("The range of [1, 5] intersects should with the range of [2, 8]", range3.intersects(1, 5));
    }
    
    /*
     *		intersects(double lower, double upper) function -  lower < 6 < upper < 10
     */
    @Test
    public void intersectShouldBeTrue3() {
    	
    	assertTrue("The range of [6, 10] intersects should with the range of [2, 8]", range3.intersects(6, 10));
    }
    
    /*
     *		intersects(double lower, double upper) function - 	1 < lower < upper < 10
     */
    @Test
    public void intersectShouldBeTrue4() {
    	
    	assertFalse("The range of [1, 10] intersects should with the range of [2, 8]", range3.intersects(1, 10));
    }
    
    /*
     *		intersects(double lower, double upper) function - 0 & 1 < lower < upper
     */
    @Test
    public void intersectShouldBeFalse() {
    	
    	assertFalse("The range of [0, 1] should not intersects with the range of [2, 8]", range3.intersects(0, 1));
    }
    
    /*
     *		intersects(double lower, double upper) function - lower < upper < 9 & 10
     */
    @Test
    public void intersectShouldBeFalse2() {
    	
    	assertFalse("The range of [9, 10] should not intersects with the range of [2, 8]", range3.intersects(9, 10));
    }
    
    /*
     *		shift(Range base, double delta) function - no zero crossing
     */
    @Test
    public void shiftShouldBeTrue() {
    	Range expected = new Range(4, 10);
    	assertEquals("The range of [2, 8] is shifted to the right by 2 and it now [4, 10]", expected, Range.shift(range3, 2));
    }
    
    /*
     *		shift(Range base, double delta) function - with zero crossing
     */
    @Test
    public void shiftShouldBeTrue2() {
    	Range expected = new Range(0, 1);
    	assertEquals("The range of [-1, 1] is shifted to the right by 3 and it now [0, 4] (does not allow zero crossing)", expected, Range.shift(range1, 3));
    }
    
    /*
     *		shift(Range base, double delta, boolean allowZeroCrossing) function - allow zero crossing
     */
    @Test
    public void shiftAllowZeroCrossingShouldBeTrue() {
    	Range input = new Range(-2, 2);
    	assertEquals("The range of [-2, 2] is shifted to the right by 3 and is now [1, 5]", range2, Range.shift(input, 3, true));
    }
    
    /*
     *		shift(Range base, double delta, boolean allowZeroCrossing) function - no zero crossing
     */
    @Test
    public void shiftAllowZeroCrossingShouldBeTrue2() {
    	Range input = new Range(-2, 2);
    	Range expected = new Range(0, 5);
    	assertEquals("The range of [-2, 2] is shifted to the right by 3 and is now [0, 5]", expected, Range.shift(input, 3, false));
    }

    /*
     * 		toString()
     */
    @Test
    public void toStringTest() {
    	String expected = "Range[-1.0,1.0]";
    	String actual = range1.toString();
    	assertEquals("Should be Range[-1.0,1.0]", expected, actual);
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}