package org.jfree.data.test.datautilities;

import org.jfree.data.DataUtilities;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class CreateNumberArray2DTests{

    private final double delta = 0.000000001d;


    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    /**
     * Test createNumberArray2D with fully empty array of double arrays
     * data = double[][]{{}}
     * expected = Number[][]{{}}
     */
    @Test
    public void validData_Empty2dDoubleArray(){
        Number[][] result = DataUtilities.createNumberArray2D(new double[][]{{}});
        Number[][] expected = {{}};
        System.out.print("Result: ");
        System.out.println(Arrays.deepToString(result));
        Assert.assertArrayEquals(expected, result);
    }


    /**
     * Test createNumberArray2D with basic multidimensional array
     * data = double[][]{{1d, 2d, 3d}}
     * expected = Number[][]{{1d, 2d, 3d}}
     */
    @Test
    public void validData_Basic1dDoubleArray(){
        Number[][] expected = {{1d, 2d, 3d}};
        Number[][] result = DataUtilities.createNumberArray2D(new double[][]{{1d, 2d, 3d}});
        System.out.print("Result: ");
        System.out.println(Arrays.deepToString(result));
        Assert.assertEquals("Should create a 2D array of Number objects", expected, result);
    }

    /**
     * Test createNumberArray2D with basic multidimensional double array
     * data = double[][]{{1d, 2d}, {1d, 2d}}
     * expected = Number[][]{{1d, 2d}, {1d, 2d}}
     */
    @Test
    public void validData_Basic2dDoubleArray() {
        Number[][] expected = {{1d, 2d}, {1d, 2d}};
        Number[][] result = DataUtilities.createNumberArray2D(new double[][]{{1d, 2d}, {1d, 2d}});
        System.out.print("Result: ");
        System.out.println(Arrays.deepToString(result));
        Assert.assertEquals("Should create a 2D array of Number objects", expected, result);
    }


    /**
     * Test createNumberArray2D with basic double array that contains the max value for a double
     * data = double[][]{{Double.MAX_VALUE, 2d},{3d, 4d}}
     * expected = Number[][]{{Double.MAX_VALUE, 2d},{3d, 4d}}
     */
    @Test
    public void validData_MaxValueIn2dDoubleArray() {
        Number[][] result = DataUtilities.createNumberArray2D(new double[][]{{Double.MAX_VALUE, 2d},{3d, 4d}});
        Number[][] expected = {{Double.MAX_VALUE, 2d},{3d, 4d}};
        System.out.print("Result: ");
        System.out.println(Arrays.deepToString(result));
        Assert.assertArrayEquals(expected, result);
    }


    /**
     * Test createNumberArray2D with basic double array that contains the min value for a double
     * data = double[][]{{Double.MIN_VALUE, 2d},{3d, 4d}}
     * expected = Number[][]{{Double.MIN_VALUE, 2d},{3d, 4d}}
     */
    @Test
    public void validData_MinValueIn2dDoubleArray() {
        Number[][] result = DataUtilities.createNumberArray2D(new double[][]{{Double.MIN_VALUE, 2d},{3d, 4d}});
        Number[][] expected = {{Double.MIN_VALUE, 2d},{3d, 4d}};
        System.out.print("Result: ");
        System.out.println(Arrays.deepToString(result));
        Assert.assertArrayEquals(expected, result);
    }


    /**
     * Test createNumberArray2D with a null value for data
     * this should throw an InvalidParameterException
     */
    @Test
    public void throw_InvalidParameterException_NullData() {
        exceptionRule.expect(InvalidParameterException.class);
        Number[][] result = DataUtilities.createNumberArray2D(null);
        System.out.println(Arrays.toString(result)); // Shouldn't get here
    }


    /**
     * Test createNumberArray2D with a null value inside of data
     * this should throw an InvalidParameterException
     * data = double[][]{{},{1d,2d}}
     *
     */
    @Test
    public void throw_InvalidParameterException_PartialNullData() {
        exceptionRule.expect(InvalidParameterException.class);
        Number[][] result = DataUtilities.createNumberArray2D(new double[][]{{},{1d,2d}});
        System.out.println(Arrays.toString(result)); // Shouldn't get here
    }

}