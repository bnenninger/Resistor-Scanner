package com.example.resistorscanner;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public enum ColorValue {
    // All values based on the resistor color value chart on Wikipedia
    // https://en.wikipedia.org/wiki/Electronic_color_code#Resistors
    BLACK   (0,  null,   true,  true,  false),
    BROWN   (1,  "1",    true,  true,  true),
    RED     (2,  "2",    true,  true,  true),
    ORANGE  (3,  "0.05", true,  true,  true),
    YELLOW  (4,  "0.02", true,  true,  true),
    GREEN   (5,  "0.5",  true,  true,  true),
    BLUE    (6,  "0.25", true,  true,  true),
    VIOLET  (7,  "0.1",  true,  true,  true),
    GREY    (8,  "0.01", true,  true,  true),
    WHITE   (9,  null,   true,  true,  false),
    GOLD    (-1, "5",    false, true,  true),
    SILVER  (-2, "10",   false, true,  true),
    NONE    (-4, "20",   false, false, true);


    private final int value;
    private final String tolerance;
    private final boolean isValue;
    private final boolean isExponent;
    private final boolean isTolerance;

    /**
     * Creates a color for the resistor color code.
     * @param value the value of the color, both for the number value and 10^x value.
     * @param tolerance the tolerance of the resistor.
     */
    /**
     * Creates a color for the resistor color code.
     * @param value the value of the color, both for the number value and 10^x value.
     * @param tolerance the tolerance of the resistor.
     * @param isValue true if the color can be used as a value, false otherwise.
     * @param isExponent true if the color can be used as an exponent, false otherwise.
     * @param isTolerance true if the color can be used as a tolerance, false otherwise.
     */
    //the tolerance is a String so that 0.01 and 5 can be the same data type without dealing with
    // issue with double rounding
    ColorValue(int value, String tolerance, boolean isValue, boolean isExponent, boolean isTolerance){
        this.value = value;
        this.tolerance = tolerance;
        this.isValue = isValue;
        this.isExponent = isExponent;
        this.isTolerance = isTolerance;
    }

    /**
     * Checks if a color band can be a numeric value.
     * @return true if the color can be used as a value, false otherwise.
     */
    public boolean isValue(){
        return isValue;
    }

    /**
     * Returns the integer value of the color.
     * @return Numeric value, in the range 0 - 9 inclusive.
     */
    public int getValue() {
        if (!isValue) {
            throw new UnsupportedOperationException("getValue attempted on color that does not represent value, " + this.name());
        }
        return value;
    }

    /**
     * Checks if a color band can be an exponent position.
     * @return true if the color can be used as an exponent, false otherwise.
     */
    public boolean isExponent(){
        return isExponent;
    }

    /**
     * Returns the exponent value of the color.
     * @return numeric value, in the range of -2 - 8, representing
     */
    public int getExponent(){
        if(!isExponent){
            throw new UnsupportedOperationException("getExponent attempted on color that does not represent exponent, " + this.name());
        }
        return value;
    }

    /**
     * Checks if a color band can be a tolerance position.
     * @return true if the color can be used as a tolerance, false otherwise.
     */
    public boolean isTolerance(){
        return isTolerance;
    }

    /**
     * Returns the tolerance value of the resistor as a String.
     * @return String name of the tolerance of the resistor.
     */
    public String getTolerance(){
        if(!isTolerance){
            throw new UnsupportedOperationException("getTolerance attempted on color that does not represent tolerance, " + this.name());
        }
        return tolerance;
    }

    /**
     * Returns an array of all ColorValues that can represent values
     * @return array of all colors that can be values
     */
    public static ColorValue[] getValueColors(){
        ArrayList<ColorValue> colors = new ArrayList<ColorValue>(Arrays.asList(ColorValue.values()));
        Iterator<ColorValue> iterator = colors.iterator();
        while(iterator.hasNext()){
            if(!iterator.next().isValue()){
                iterator.remove();
            }
        }
        return colors.toArray(new ColorValue[0]);
    }

    /**
     * Returns an array of all ColorValues that can represent exponents
     * @return array of all colors that can be exponents
     */
    public static ColorValue[] getExponentColors(){
        ArrayList<ColorValue> colors = new ArrayList<ColorValue>(Arrays.asList(ColorValue.values()));
        Iterator<ColorValue> iterator = colors.iterator();
        while(iterator.hasNext()){
            if(!iterator.next().isExponent()){
                iterator.remove();
            }
        }
        return colors.toArray(new ColorValue[0]);
    }

    /**
     * Returns an array of all ColorValues that can represent tolerances
     * @return array of all colors that can be tolerances
     */
    public static ColorValue[] getToleranceColors(){
        ArrayList<ColorValue> colors = new ArrayList<ColorValue>(Arrays.asList(ColorValue.values()));
        Iterator<ColorValue> iterator = colors.iterator();
        while(iterator.hasNext()){
            if(!iterator.next().isTolerance()){
                iterator.remove();
            }
        }
        return colors.toArray(new ColorValue[0]);
    }
}
