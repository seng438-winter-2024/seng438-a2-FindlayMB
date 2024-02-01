
# Range.class

- double getLowerBound()
  - Returns lower bound.  
- double getUpperBound()
  - Returns upper bound. 
- double getLength()
  - Returns length of range, upper - lower.   
- double getCentralValue()
  - Returns the central (or median) value for the range.
- boolean contains(double value)
- boolean intersects(double lower, double upper)
- double constrain(double value)
- Range combine(Range range1, Range range2)
- Range expandToInclude(Range range, double value)
- Range expand(Range range, double lowerMargin, double upperMargin)
- Range shift(Range base, double delta)
  - same as shift(base, delta, false) 
- Range shift(Range base, double delta, boolean allowZeroCrossing) 
- boolean equals(Object obj)
- int hashCode()
- String toString()


# DataUtilities.class 

- double calculateColumnTotal(Values2D data, int column)
  - Returns the sum of the values in one column of the supplied data table. With invalid input, a total of zero will be returned.
  -
  - Throws InvalidParameterException if data
- double calculateRowTotal(Values2D data, int row)
- Number[] createNumberArray(double[] data)
- Number[][] createNumberArray2D(double[][] data)
- KeyedValues getCumulativePercentages(KeyedValues data)
