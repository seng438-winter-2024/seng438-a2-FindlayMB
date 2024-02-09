package org.jfree.data.test.datautilities;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

public class CalculateColumnTotalTests {
    private Mockery mockingContext;
    private Values2D values;

    private final double delta = 0.000000001d;
    @Before
    public void setUp() throws Exception {
        // Setup
        mockingContext = new Mockery();
        values = mockingContext.mock(Values2D.class);
    }

    /**
     * Set up mocking of a Values2D object for a given column
     * @param column column number to total along in a Values2D object
     */
    public void setUpMocking(int column) {
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount(); will(returnValue(5));
                one(values).getValue(0, column); will(returnValue(1));
                one(values).getValue(1, column); will(returnValue(1));
                one(values).getValue(2, column); will(returnValue(1));
                one(values).getValue(3, column); will(returnValue(1));
                one(values).getValue(4, column); will(returnValue(1));
            }
        });
    }
    
    public void setUpMocking(int column,boolean outOfBoundsFlag) {
        if (!outOfBoundsFlag){
            setUpMocking(column);
            return;
        }
        mockingContext.checking(new Expectations() {
            {
                one(values).getRowCount(); will(returnValue(5));
                one(values).getValue(0, column); will(throwException(new IndexOutOfBoundsException()));
                one(values).getValue(1, column); will(throwException(new IndexOutOfBoundsException()));
                one(values).getValue(2, column); will(throwException(new IndexOutOfBoundsException()));
                one(values).getValue(3, column); will(throwException(new IndexOutOfBoundsException()));
                one(values).getValue(4, column); will(throwException(new IndexOutOfBoundsException()));
            }
        });
    }



    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    /**
     * Testing calculateColumnTotal column out of left index bounds
     * column < 1 - rowAmt
     * Parameters:  values = 5x5 matrix filled with 1's
     *              column = -5
     * <p>
     * Expected output: 0.0
     */
    @Test
    public void invalidColumn_OutsideLeftBound() {
        setUpMocking(-5, true);
        double result = DataUtilities.calculateColumnTotal(values, -5);
        assertEquals(0.0, result, delta);
    }


    /**
     * Testing calculateColumnTotal column out of left index bounds
     * column = 1 - rowAmt
     * Parameters:  values = 5x5 matrix filled with 1's
     *              column = -4
     * <p>
     * Expected output: 5.0
     */
    @Test
    public void validColumn_LeftBoundary() {
        setUpMocking(-4);
        double result = DataUtilities.calculateColumnTotal(values, -4);
        assertEquals(5.0, result, delta);
    }

    /**
     * Testing calculateColumnTotal column out of left index bounds
     * 1 - rowAmt < column < 0
     * Parameters:  values = 5x5 matrix filled with 1's
     *              column = -2
     * <p>
     * Expected output: 5.0
     */
    @Test
    public void validColumn_Between_LeftBoundary_Zero() {
        setUpMocking(-2);
        double result = DataUtilities.calculateColumnTotal(values, -2);
        assertEquals(5.0, result, delta);
    }

    /**
     * Testing calculateColumnTotal with a valid parameters
     * column = 0
     * Parameters:  values = 5x5 matrix filled with 1's
     *              column = 0
     * <p>
     * Expected output: 5.0
     */
    @Test
    public void validColumn_Zero() {
        setUpMocking(0);
        double result = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(5.0, result, delta);
    }

    /**
     * Testing calculateColumnTotal column out of left index bounds
     * 0 < column < rowAmt - 1
     * Parameters:  values = 5x5 matrix filled with 1's
     *              column = 2
     * <p>
     * Expected output: 5.0
     */
    @Test
    public void validColumn_Between_Zero_RightBoundary() {
        setUpMocking(2);
        double result = DataUtilities.calculateColumnTotal(values, 2);
        assertEquals(5.0, result, delta);
    }

    /**
     * Testing calculateColumnTotal column in upper bound
     * column = rowAmt - 1
     * Parameters:  values = 5x5 matrix filled with 1's
     *              column = 4
     * <p>
     * Expected output: 5.0
     */
    @Test
    public void validColumn_RightBoundary() {
        setUpMocking(4);
        double result = DataUtilities.calculateColumnTotal(values, 4);
        assertEquals(5.0, result, delta);
    }


    /**
     * Testing calculateColumnTotal column out of right index bounds
     * column > rowAmt - 1
     * Parameters:  values = 5x5 matrix filled with 1's
     *              column = 5
     * <p>
     * Expected output: 0.0
     */
    @Test
    public void invalidColumn_OutsideRightBound() {
        setUpMocking(5, true);
        double result = DataUtilities.calculateColumnTotal(values, 5);
        assertEquals(0.0, result, delta);
    }

    /**
     * Test calculateColumnTotal with a null value for data
     * this should throw an InvalidParameterException
     */
    @Test
    public void throw_InvalidParameterException_NullData() {
        exceptionRule.expect(InvalidParameterException.class);
        double result = DataUtilities.calculateColumnTotal(null, 0);
    }

    /**
     * Test calculateColumnTotal with a null value inside of data
     * this should throw an InvalidParameterException
     */
    @Test
    public void throw_InvalidParameterException_PartialNullData() {
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


}
