package com.example.resistorscanner;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class ColorConverter {

    public static String convertColor(List<ColorValue> values) {
        int numBands = values.size();
        List<ColorValue> digits;
        int exponent;
        String tolerance;
        //if there are only 3 colors, there is no error band
        if(numBands == 3){
            tolerance = "20";
            exponent = values.get(2).getExponent();
            digits = values.subList(0, 2);
        }
        //all other cases have the last two bands as exponent and tolerance
        else {
            tolerance = values.get(numBands - 1).getTolerance();
            exponent = values.get(numBands - 2).getExponent();
            digits = values.subList(0, numBands - 2);
        }
        String numberValues = getNumberValueString(digits, exponent);
        String prefix = getPrefix(exponent);
        return generateOutputString(numberValues, tolerance, prefix);
    }

    private static String generateOutputString(String numberValues, String tolerance, String prefix){
        //unicode 00B1 is the plus or minus sign
        //unicode 2126 is the ohm sign
        return String.format("%s \u00B1%s%% %s\u2126", numberValues, tolerance,prefix);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private static String getNumberValueString(List<ColorValue> numericValues, int exponent){
        // finds how far into the position in engineering notation the value is.
        // 1: one digit before decimal place, 2: 2 digits before decimal place,
        // 3: all 3 digits before decimal place
        int decimalPosition = Math.floorMod(exponent, 3) + 1;
        String output = "";
        for(int i = 0; i < decimalPosition; i++){
            //adds trailing zeroes if needed
            if(i >= numericValues.size()){
                output = output + "0";
            }
            else {
                output = output + numericValues.get(i).getValue();
            }
        }
        //if all digits have been added, can return now
        if(decimalPosition >= numericValues.size()){
            return output;
        }
        //adds decimal, if has not returned needs more digits after decimal place
        output = output + ".";
        //adds remaining digits
        for(int i = decimalPosition; i < numericValues.size(); i++){
            output = output + numericValues.get(i).getValue();
        }
        return output;
    }

    /**
     * Finds the prefix for the resistor value.
     * @param exponent the exponent on the ten for the resistor value.
     * @return String of the prefix, which is blank if it is 0-1000.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static String getPrefix(int exponent){
        //gets power of thousands, ie 1,000,000 would be 2 here
        //uses Math.floorDiv so that division is rounded toward negative values, rather than 0,
        // so that the kOhm and mOhm range do not appear identical
        int thousandsExponent = Math.floorDiv(exponent, 3);
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
}
