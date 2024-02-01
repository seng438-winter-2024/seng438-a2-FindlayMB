package org.jfree.data.test;

import static org.junit.Assert.*;
import java.util.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;

import javax.xml.crypto.Data;
import java.security.InvalidParameterException;

@DisplayName("DataUtilities Test Class")
public class DataUtilitiesTest extends DataUtilities {

    private Mockery mockingContext;
    private Values2D values;

    private KeyedValues keyedValues;
    
    private double delta = 0.000000001d;
    @Before
    public void setUp() throws Exception {
        // Setup
        mockingContext = new Mockery();
        values = mockingContext.mock(Values2D.class);
        keyedValues = mockingContext.mock(KeyedValues.class);
        
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    /**
     * Testing calculateColumnTotal with a valid parameters
     * Parameters:  values = [[7.5],[2.5]]      2 rows and 1 column
     *              column = 0
     * Expected output: 10
     */
    @Test
    @DisplayName("Calculate Column Total with valid data")
    public void calculateColumnTotal_ForValidData() {
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount(); will(returnValue(2));
                one(values).getValue(0, 0); will(returnValue(7.5));
                one(values).getValue(1, 0); will(returnValue(2.5));
            }
        });
        double result = DataUtilities.calculateColumnTotal(values, 0);

        assertEquals(10.0, result, delta);
    }

    /**
     * Testing calculateColumnTotal with an invalid column parameter
     * Parameters:  values = [[7.5],[2.5]]      2 rows and 1 column
     *              column = 2
     * Expected output: 0
     */
    @Test
    public void calculateColumnTotal_InvalidParameter_ReturnsZero(){
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount(); will(throwException(new IndexOutOfBoundsException()));
                one(values).getValue(0, 2); will(returnValue(7.5));
                one(values).getValue(1, 2); will(returnValue(2.5));
            }
        });

        double result = DataUtilities.calculateColumnTotal(values, 2);

        assertEquals(0, result, delta);
    }

    /**
     * Testing calculateColumnTotal with a valid parameters
     * Parameters:  values = [[7.5],[null]]      2 rows and 1 column
     *              column = 0
     * Expected output: 10
     */
    @Test
    public void calculateColumnTotal_ThrowInvalidParameterException_PartialNullParameter(){
        exceptionRule.expect(InvalidParameterException.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount(); will(returnValue(2));
                one(values).getValue(0, 0); will(returnValue(7.5));
                one(values).getValue(1, 0); will(returnValue(null));
            }
        });
        double result = DataUtilities.calculateColumnTotal(values, 0);
    }


    /**
     * Test calculateColumnTotal with a null value for data
     * this should throw an InvalidParameterException
     */
    @Test
    public void calculateColumnTotal_ThrowInvalidParameterException_NullParameter(){
        exceptionRule.expect(InvalidParameterException.class);
        double result = DataUtilities.calculateColumnTotal(null, 1);
    }


    @Test
    @DisplayName("Calculate Row Total with valid data")
    public void calculateRowTotal_ForValidData() {
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount(); will(returnValue(2));
                one(values).getValue(0, 0); will(returnValue(7.5));
                one(values).getValue(0, 1); will(returnValue(2.5));
            }
        });
        double result = DataUtilities.calculateRowTotal(values, 0);

        assertEquals(10.0, result, delta);
    }

    @Test
    public void calculateRowTotal_InvalidParameter_ReturnsZero(){
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount(); will(throwException(new IndexOutOfBoundsException()));
                one(values).getValue(2, 0); will(returnValue(7.5));
                one(values).getValue(2, 1); will(returnValue(2.5));
            }
        });

        double result = DataUtilities.calculateRowTotal(values, 2);

        assertEquals(0, result, delta);
    }

    /**
     * Test calculateRowTotal with a null value for data
     * this should throw an InvalidParameterException
     */
    @Test
    public void calculateRowTotal_ThrowInvalidParameterException_NullParameter(){
        exceptionRule.expect(InvalidParameterException.class);
        double result = DataUtilities.calculateRowTotal(null, 1);
    }

    @Test
    public void calculateRowTotal_ThrowInvalidParameterException_PartialNullParameter(){
        exceptionRule.expect(InvalidParameterException.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount(); will(returnValue(2));
                one(values).getValue(0, 0); will(returnValue(7.5));
                one(values).getValue(0, 1); will(returnValue(null));
            }
        });
        double result = DataUtilities.calculateColumnTotal(values, 0);
    }


    @Test
    public void createNumberArray_ForValidParams(){
        double[] data = {1d,2d,3d};

        Number[] expected = {1d,2d,3d};

        Assert.assertArrayEquals(expected, DataUtilities.createNumberArray(data));

    }

    /**
     * Test createNumberArray with a null value for data
     * this should throw an InvalidParameterException
     */
    @Test
    public void createNumberArray_ThrowInvalidParameterException_NullParameter(){
        exceptionRule.expect(InvalidParameterException.class);
        Number[] result = DataUtilities.createNumberArray(null);
    }




    @Test
    public void createNumberArray2D_ForValidParams(){

        double[][] data = {{1,2},{1,2}};

        Number[][] expected = {{1d,2d},{1d,2d}};

        Assert.assertEquals("Should create a 2D array of Number objects",expected, DataUtilities.createNumberArray2D(data));

    }

    /**
     * Test createNumberArray2D with a null value for data
     * this should throw an InvalidParameterException
     */
    @Test
    public void createNumberArray2D_ThrowInvalidParameterException_NullParameter(){
        exceptionRule.expect(InvalidParameterException.class);
        Number[][] result = DataUtilities.createNumberArray2D(null);
    }


    @Test
    public void getCumulativePercentages_EmptyParam(){
        assertEquals(0,
                DataUtilities.getCumulativePercentages(new DefaultKeyedValues()).getItemCount(),
                delta);
    }
    @Test
    public void getCumulativePercentages_ForValidParams(){
        DefaultKeyedValues keyedValues = new DefaultKeyedValues();
        keyedValues.addValue((Comparable<?>)0,5d);
        keyedValues.addValue((Comparable<?>)1,9d);
        keyedValues.addValue((Comparable<?>)2,2d);

        HashMap<Comparable<?>, Double> expected = new HashMap<Comparable<?>, Double>();
        expected.put(0,0.3125);
        expected.put(1,0.875);
        expected.put(2,1.0);

        KeyedValues results = DataUtilities.getCumulativePercentages(keyedValues);

        for (Comparable<?> key : expected.keySet()) {
            assertEquals(expected.get(key), results.getValue(key).doubleValue(), delta);
        }
    }

    /**
     * Test createNumberArray2D with a null value for data
     * this should throw an InvalidParameterException
     */
    @Test
    public void getCumulativePercentages_ThrowInvalidParameterException_NullParameter(){
        exceptionRule.expect(InvalidParameterException.class);
        KeyedValues result = DataUtilities.getCumulativePercentages(null);
    }
}
