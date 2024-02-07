package org.jfree.data.test.datautilities;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class GetCumulativePercentagesTests{
    private Mockery mockingContext;
    private KeyedValues values;

    private final double delta = 0.000000001d;
    @Before
    public void setUp() {
        // Setup
        mockingContext = new Mockery();
        values = mockingContext.mock(KeyedValues.class);
        mockingContext.checking(new Expectations() {
            {
                allowing(values).getItemCount(); will(returnValue(3));
                allowing(values).getKey(0); will(returnValue(0));
                allowing(values).getValue(0); will(returnValue(5d));
                allowing(values).getKey(1); will(returnValue(1));
                allowing(values).getValue(1); will(returnValue(9d));
                allowing(values).getKey(2); will(returnValue(2));
                allowing(values).getValue(2); will(returnValue(2d));

            }
        });
    }

    /**
     * Displays the key-value pairs in the KeyedValues object
     * @param kv key-value pairs stored in a KeyedValues object
     */
    public void displayKeyedValues(KeyedValues kv){
        for (Object key : kv.getKeys()) {
            System.out.println(key.toString()+ String.format(":%f",kv.getValue((Comparable<?>)key).doubleValue()));
        }
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    /**
     * Test getCumulativePercentages with empty keyed values
     * data = DefaultKeyedValues{}
     * size of resulting KeyedValues = 0
     */
    @Test
    public void validData_EmptyKeyedValues() {
        assertEquals(0,
                DataUtilities.getCumulativePercentages(new DefaultKeyedValues()).getItemCount());
    }


    /**
     * Test getCumulativePercentages with empty keyed values
     * data = DefaultKeyedValues{}
     * size of resulting KeyedValues = 0
     */
    @Test
    public void validData_SingleKeyedValue() {
        mockingContext.checking(new Expectations() {
            {
                allowing(values).getItemCount(); will(returnValue(1));
                one(values).getValue(0); will(returnValue(1d));
                one(values).getKey(0); will(returnValue(0));
            }
        });

        HashMap<Comparable<Integer>, Double> expected = new HashMap<>();
        expected.put(0, 1d);

        KeyedValues results = DataUtilities.getCumulativePercentages(values);

        for (Comparable<?> key : expected.keySet()) {
            assertEquals(expected.get(key), results.getValue(key).doubleValue(), delta);
        }
    }

    /**
     * Test getCumulativePercentages with empty keyed values
     * data = DefaultKeyedValues{}
     * size of resulting KeyedValues = 0
     */
    @Test
    public void validKeyedValues_AllPositive() {


        HashMap<Comparable<?>, Double> expected = new HashMap<>();
        expected.put(0, 0.3125);
        expected.put(1, 0.875);
        expected.put(2, 1.0);

        KeyedValues results = DataUtilities.getCumulativePercentages(values);

        displayKeyedValues(results);

        for (Comparable<?> key : expected.keySet()) {
            assertEquals(expected.get(key), results.getValue(key).doubleValue(), delta);
        }
    }

    /**
     * Test getCumulativePercentages with empty keyed values
     * data = DefaultKeyedValues{}
     * size of resulting KeyedValues = 0
     */
    @Test
    public void validKeyedValues_WithNegatives() {
        HashMap<Comparable<?>, Double> expected = new HashMap<>();
        expected.put(0, 0.3125);
        expected.put(1, 0.875);
        expected.put(2, 0.75);

        KeyedValues results = DataUtilities.getCumulativePercentages(values);

        displayKeyedValues(results);

        for (Comparable<?> key : expected.keySet()) {
            assertEquals(expected.get(key), results.getValue(key).doubleValue(), delta);
        }
    }


    /**
     * Test getCumulativePercentages with keyed values with the keys being strings
     * data = DefaultKeyedValues{}
     * size of resulting KeyedValues = 0
     */
    @Test
    public void validKeyedValues_StringKeys() {
        mockingContext.checking(new Expectations() {
            {
                allowing(values).getItemCount(); will(returnValue(3));
                allowing(values).getKey(0); will(returnValue("A"));
                allowing(values).getValue("A"); will(returnValue(5d));
                allowing(values).getValue(0); will(returnValue(5d));
                allowing(values).getKey(1); will(returnValue("B"));
                allowing(values).getValue("B"); will(returnValue(9d));
                allowing(values).getValue(1); will(returnValue(9d));
                allowing(values).getKey(2); will(returnValue("C"));
                allowing(values).getValue("C"); will(returnValue(2d));
                allowing(values).getValue(2); will(returnValue(2d));

            }
        });

        SortedMap<Comparable<String>, Double> expected = new TreeMap<>();
        expected.put("A", 0.3125);
        expected.put("B", 0.875);
        expected.put("C", 0.75);

        KeyedValues results = DataUtilities.getCumulativePercentages(values);

        for (int i=0; i<results.getKeys().size(); ++i) {
            System.out.println(results.getKey(i).toString()+ String.format(":%f",results.getValue(i).doubleValue()));
        }

        Iterator<Comparable<String>> expIter = expected.keySet().iterator();
        for (int i=0; i<expected.keySet().size(); ++i) {
            assertEquals(expected.get(expIter.next()), results.getValue(i).doubleValue(), delta);
        }
    }


    /**
     * Test getCumulativePercentages with a null value for data
     * this should throw an InvalidParameterException
     */
    @Test
    public void throw_InvalidParameterException_NullData() {
        exceptionRule.expect(InvalidParameterException.class);
        KeyedValues result = DataUtilities.getCumulativePercentages(null);
    }

    /**
     * Test getCumulativePercentages with a null value inside of data
     * this should throw an InvalidParameterException
     */
    @Test
    public void throw_InvalidParameterException_PartialNullData() {
        exceptionRule.expect(InvalidParameterException.class);
        KeyedValues result = DataUtilities.getCumulativePercentages(null);
    }
}
