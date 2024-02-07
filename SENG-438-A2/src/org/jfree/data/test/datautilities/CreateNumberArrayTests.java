package org.jfree.data.test.datautilities;

import org.jfree.data.DataUtilities;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class CreateNumberArrayTests{

    private final double delta = 0.000000001d;
    @Before
    public void setUp() throws Exception {
        // Setup
        Mockery mockingContext = new Mockery();
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    /**
     * Test createNumberArray2D with fully empty array of double arrays
     * data = double[]{}
     * expected = Number[]{}
     */
    @Test
    public void validData_EmptyDoubleArray() {
        Number[] result = DataUtilities.createNumberArray(new double[]{});
        Number[] expected = {};
        System.out.print("Result: ");
        System.out.println(Arrays.deepToString(result));
        Assert.assertArrayEquals(expected, result);
    }

    /**
     * Test createNumberArray with basic double array
     * data = double[]{1d, 2d, 3d}
     * expected = Number[]{1d, 2d, 3d}
     */
    @Test
    public void validData_BasicDoubleArray() {
        Number[] result = DataUtilities.createNumberArray(new double[]{1d, 2d, 3d});
        Number[] expected = {1d, 2d, 3d};
        System.out.print("Result: ");
        System.out.println(Arrays.deepToString(result));
        Assert.assertArrayEquals(expected, result);
    }


    /**
     * Test createNumberArray with basic double array that contains the max value for a double
     * data = double[]{Double.MAX_VALUE, 2d, 3d}
     * expected = Number[]{Double.MAX_VALUE, 2d, 3d}
     */
    @Test
    public void validData_MaxValueInDoubleArray() {
        Number[] result = DataUtilities.createNumberArray(new double[]{Double.MAX_VALUE, 2d, 3d});
        Number[] expected = {Double.MAX_VALUE, 2d, 3d};
        System.out.print("Result: ");
        System.out.println(Arrays.deepToString(result));
        Assert.assertArrayEquals(expected, result);
    }


    /**
     * Test createNumberArray with basic double array that contains the min value for a double
     * data = double[]{Double.MIN_VALUE, 2d, 3d}
     * expected = Number[]{Double.MIN_VALUE, 2d, 3d}
     */
    @Test
    public void validData_MinValueInDoubleArray() {
        Number[] result = DataUtilities.createNumberArray(new double[]{Double.MIN_VALUE, 2d, 3d});
        Number[] expected = {Double.MIN_VALUE, 2d, 3d};
        System.out.print("Result: ");
        System.out.println(Arrays.deepToString(result));
        Assert.assertArrayEquals(expected, result);
    }


    /**
     * Test createNumberArray with a null value for data
     * this should throw an InvalidParameterException
     */
    @Test
    public void throw_InvalidParameterException_NullData() {
        exceptionRule.expect(InvalidParameterException.class);
        Number[] result = DataUtilities.createNumberArray(null);
        System.out.println(Arrays.toString(result)); // Shouldn't get here
    }

}
