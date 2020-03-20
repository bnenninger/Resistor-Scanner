package com.example.resistorscanner;

import java.util.List;

/**
 * Class for storing a resistor value that stores a list of the colors and the equivalent converted
 * values.
 */
public class ResistorValueColorStorage implements ResistorValue {
    private final List<ColorValue> colors;
    private final int significantDigits;
    private final int multiplierExponent;
    private final String tolerance;

    /**
     * Creates a new storage object for resistor values
     * @param colors List of the color bands of the resistor
     */
    public ResistorValueColorStorage(List<ColorValue> colors){
        this.colors = colors;
        int numBands = colors.size();
        List<ColorValue> digits;
        //if there are only 3 colors, there is no error band
        if(numBands == 3){
            tolerance = "20";
            multiplierExponent = colors.get(2).getExponent();
            digits = colors.subList(0, 2);
        }
        //all other cases have the last two bands as exponent and tolerance
        else {
            tolerance = colors.get(numBands - 1).getTolerance();
            multiplierExponent = colors.get(numBands - 2).getExponent();
            digits = colors.subList(0, numBands - 2);
        }
        //sets the significant values
        int significantDigits = 0;
        for(ColorValue band: digits) {
            //shifts digits and adds next
            significantDigits *= 10;
            significantDigits += band.getValue();
        }
        this.significantDigits = significantDigits;
    }

    @Override
    public String toString(){
        return outputStringValue();
    }

    //TODO finish this method
    @Override
    public String outputStringValue() {
        //exponent that the value would have if it were in scientific notation
        //allows same logic to be used no matter the number of significant digits of the resistor
        int scientificNotationExponent = numDigits(significantDigits) + multiplierExponent - 1;
        String prefix = getPrefix(scientificNotationExponent);
        String numberValues = getNumberValueString(significantDigits, scientificNotationExponent);
        //unicode 00B1 is the plus or minus sign
        //unicode 2126 is the ohm sign
        return String.format("%s \u00B1%s%% %s\u2126", numberValues, tolerance, prefix);
    }

    private static String getNumberValueString(int digits, int scientificNotationExponent) {
        // finds how far into the position in engineering notation the value is.
        // 1: one digit before decimal place, 2: 2 digits before decimal place,
        // 3: all 3 digits before decimal place
        int decimalPosition = Math.floorMod(scientificNotationExponent, 3) + 1;
        String output = "";
        String digitString = Integer.toString(digits);
        //adds the digits before the decimal point
        for(int i = 0; i < decimalPosition; i++){
            //adds trailing zeroes if needed, when there are no other significant digits
            if(i >= digitString.length()){
                output = output + "0";
            }
            //otherwise adds the next digit
            else {
                output = output + digitString.charAt(i);
            }
        }
        //if all digits have been added, can return now
        if(decimalPosition >= digitString.length()){
            return output;
        }
        //adds decimal, if has not returned needs more digits after decimal place
        output = output + ".";
        //adds remaining digits
        for(int i = decimalPosition; i < digitString.length(); i++){
            output = output + digitString.charAt(i);
        }
        return output;
    }

    /**
     * Finds the prefix for the resistor value.
     * @param scientificNotationExponent the exponent on the ten for the resistor value, if represented in scientific
     *                 notation.
     * @return String of the prefix, which is blank if it is 0-1000.
     */
    private String getPrefix(int scientificNotationExponent) {
        //gets power of thousands, ie 1,000,000 would be 2 here
        //uses Math.floorDiv so that division is rounded toward negative values, rather than 0,
        // so that the kOhm and mOhm range do not appear identical
        int thousandsExponent = Math.floorDiv(scientificNotationExponent, 3);
        //converts to the string value
        String output;
        switch (thousandsExponent) {
            case -1:
                output = "m";
                break;
            case 0:
                output = "";
                break;
            case 1:
                output = "k";
                break;
            case 2:
                output = "M";
                break;
            case 3:
                output = "G";
                break;
            default:
                throw new IllegalArgumentException("invalid exponent passed");
        }
        return output;
    }

    /**
     * Returns the number of digits in n
     * @param n number to count digits of
     * @return number of digits in n
     */
    private int numDigits(int n){
        int numDigits = 0;
        while(n>0){
            numDigits++;
            n /= 10;
        }
        return numDigits;
    }

    @Override
    public ColorValue[] getResistorColors() {
        return colors.toArray(new ColorValue[0]);
    }

    @Override
    public double getValue() {
        return getMultiplier() * getSignificantDigits();
    }

    @Override
    public int getSignificantDigits() {
        return significantDigits;
    }

    @Override
    public double getMultiplier() {
        return Math.pow(10, getMultiplierExponent());
    }

    @Override
    public int getMultiplierExponent() {
        return multiplierExponent;
    }

    @Override
    public String getTolerance() {
        return tolerance;
    }
}
