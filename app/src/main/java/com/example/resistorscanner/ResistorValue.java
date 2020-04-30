package com.example.resistorscanner;

public interface ResistorValue {


    /**
     * Returns a string representation of the resistor, in the format "1.2 ±5% kΩ".
     * @return value of the resistor in format "1.2 ±5% kΩ"
     */
    String outputStringValue();

    /**
     * Returns the color bands of the resistor.
     * @return array of ColorValues representing the color bands of the resistor
     */
    ColorValue[] getResistorColors();

    /**
     * Returns the value of the resistor.
     * @return double representation of the value of the resistor.
     */
    // getValue and getMultiplier both return doubles so that resistors with a value less than 1 can
    // use the same data type
    double getValue();

    /**
     * Gets the significant digits of the resistor.
     * @return integer representing the significant digits of the resistor.
     */
    int getSignificantDigits();

    /**
     * Gets the multiplier that is multiplied with the significant digits to get the value of the resistor
     * @return double representation of the multiplier.
     */
    // getValue and getMultiplier both return doubles so that resistors with a value less than 1 can
    // use the same data type
    double getMultiplier();

    /**
     * Gets the exponent of the multiplier, such that the value of 10^x is the multiplier.
     * @return integer exponent on 10 for the multiplier
     */
    int getMultiplierExponent();

    /**
     * Gets the tolerance value of the resistor.
     * @return String representation of the tolerance. Does not include "%", just has numeric values
     */
    //string used so that integer and decimal tolerance resistors can use the same data type
    String getTolerance();

}
