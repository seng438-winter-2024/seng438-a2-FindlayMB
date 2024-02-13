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

public class CalculateRowTotalTests {
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
     * Set up mocking of a Values2D object for a given row
     *
     * @param row row number to total along in a Values2D object
     */
    public void setUpMocking(int row) {
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(5));
                one(values).getValue(row, 0);
                will(returnValue(1));
                one(values).getValue(row, 1);
                will(returnValue(1));
                one(values).getValue(row, 2);
                will(returnValue(1));
                one(values).getValue(row, 3);
                will(returnValue(1));
                one(values).getValue(row, 4);
                will(returnValue(1));
            }
        });
    }

    /**
     * Set up mocking of a Values2D object for a given row
     *
     * @param row             row number to total along in a Values2D object
     * @param outOfBoundsFlag Flag to see if row number is out of bounds
     */
    public void setUpMocking(int row, boolean outOfBoundsFlag) {
        if (!outOfBoundsFlag) {
            setUpMocking(row);
            return;
        }
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(5));
                one(values).getValue(row, 0);
                will(throwException(new IndexOutOfBoundsException()));
                one(values).getValue(row, 1);
                will(throwException(new IndexOutOfBoundsException()));
                one(values).getValue(row, 2);
                will(throwException(new IndexOutOfBoundsException()));
                one(values).getValue(row, 3);
                will(throwException(new IndexOutOfBoundsException()));
                one(values).getValue(row, 4);
                will(throwException(new IndexOutOfBoundsException()));
            }
        });
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    /**
     * Testing calculateRowTotal row out of left index bounds
     * row < 1 - columnAmt
     * Parameters: values = 5x5 matrix filled with 1's
     * row = -5
     * <p>
     * Expected output: 0.0
     */
    @Test
    public void invalidRow_OutsideLeftBound() {
        setUpMocking(-5, true);
        double result = DataUtilities.calculateRowTotal(values, -5);
        assertEquals(0.0, result, delta);
    }

    /**
     * Testing calculateRowTotal row out of left index bounds
     * row = 1 - columnAmt
     * Parameters: values = 5x5 matrix filled with 1's
     * row = -4
     * <p>
     * Expected output: 5.0
     */
    @Test
    public void validRow_LeftBoundary() {
        setUpMocking(-4);
        double result = DataUtilities.calculateRowTotal(values, -4);
        assertEquals(5.0, result, delta);
    }

    /**
     * Testing calculateRowTotal row out of left index bounds
     * 1 - columnAmt < row < 0
     * Parameters: values = 5x5 matrix filled with 1's
     * row = -2
     * <p>
     * Expected output: 5.0
     */
    @Test
    public void validRow_BetweenLeftBoundaryZero() {
        setUpMocking(-2);
        double result = DataUtilities.calculateRowTotal(values, -2);
        assertEquals(5.0, result, delta);
    }

    /**
     * Testing calculateRowTotal with row = 0
     * Parameters: values = 5x5 matrix filled with 1's
     * row = 0
     * <p>
     * Expected output: 5.0
     */
    @Test
    public void validRow_Zero() {
        setUpMocking(0);
        double result = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(5.0, result, delta);
    }

    /**
     * Testing calculateRowTotal row out of left index bounds
     * 0 < row < columnAmt - 1
     * Parameters: values = 5x5 matrix filled with 1's
     * row = 2
     * <p>
     * Expected output: 5.0
     */
    @Test
    public void validRow_BetweenZeroRightBoundary() {
        setUpMocking(2);
        double result = DataUtilities.calculateRowTotal(values, 2);
        assertEquals(5.0, result, delta);
    }

    /**
     * Testing calculateRowTotal row in upper bound
     * row = columnAmt - 1
     * Parameters: values = 5x5 matrix filled with 1's
     * row = 4
     * <p>
     * Expected output: 5.0
     */
    @Test
    public void validRow_RightBoundary() {
        setUpMocking(4);
        double result = DataUtilities.calculateRowTotal(values, 4);
        assertEquals(5.0, result, delta);
    }

    /**
     * Testing calculateRowTotal row out of left index bounds
     * row > columnAmt - 1
     * Parameters: values = 5x5 matrix filled with 1's
     * row = 5
     * <p>
     * Expected output: 0.0
     */
    @Test
    public void invalidRow_OutsideRightBound() {
        setUpMocking(5, true);
        double result = DataUtilities.calculateRowTotal(values, 5);
        assertEquals(0.0, result, delta);
    }

    /**
     * Test calculateRowTotal with a null value for data
     * this should throw an InvalidParameterException
     */
    @Test
    public void nullData_ThrowInvalidParameterException() {
        exceptionRule.expect(InvalidParameterException.class);
        double result = DataUtilities.calculateRowTotal(null, 1);
        System.out.println(result); // Shouldn't get here
    }

    /**
     * Test calculateRowTotal with a null value inside of data
     * this should throw an InvalidParameterException
     */
    @Test
    public void partialNullData_ThrowInvalidParameterException() {
        exceptionRule.expect(InvalidParameterException.class);
        mockingContext.checking(new Expectations() {
            {
                one(values).getColumnCount();
                will(returnValue(2));
                one(values).getValue(0, 0);
                will(returnValue(7.5));
                one(values).getValue(0, 1);
                will(returnValue(null));
            }
        });
        double result = DataUtilities.calculateColumnTotal(values, 0);
        System.out.println(result); // Shouldn't get here
    }
}
