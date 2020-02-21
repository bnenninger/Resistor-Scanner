package com.example.resistorscanner;

import android.graphics.Color;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ColorConverterTest {
    @Test
    public void tolerance20percent(){
        //22 ohm +- 20%
        assertEquals(generateOutputString("22", "20",""),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.RED,ColorValue.RED,ColorValue.BROWN})));
        assertEquals(generateOutputString("2.2", "20","k"),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.RED,ColorValue.RED,ColorValue.ORANGE})));
    }

    @Test
    public void prefixTest(){
        assertEquals(generateOutputString("10", "5", "m"),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.SILVER, ColorValue.GOLD})));
        assertEquals(generateOutputString("100", "5", "m"),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.GOLD, ColorValue.GOLD})));
        assertEquals(generateOutputString("1.0", "5", ""),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.BLACK, ColorValue.GOLD})));
        assertEquals(generateOutputString("10", "5", ""),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.BROWN, ColorValue.GOLD})));
        assertEquals(generateOutputString("100", "5", ""),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.RED, ColorValue.GOLD})));
        assertEquals(generateOutputString("1.0", "5", "k"),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.ORANGE, ColorValue.GOLD})));
        assertEquals(generateOutputString("10", "5", "k"),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.YELLOW, ColorValue.GOLD})));
        assertEquals(generateOutputString("100", "5", "k"),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.GREEN, ColorValue.GOLD})));
        assertEquals(generateOutputString("1.0", "5", "M"),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.BLUE, ColorValue.GOLD})));
        assertEquals(generateOutputString("10", "5", "M"),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.VIOLET, ColorValue.GOLD})));
        assertEquals(generateOutputString("100", "5", "M"),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.GREY, ColorValue.GOLD})));
        assertEquals(generateOutputString("1.0", "5", "G"),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.WHITE, ColorValue.GOLD})));
    }

    @Test
    public void numDigitsTest(){
        assertEquals(generateOutputString("10.245", "5", ""),
                ColorConverter.convertColor(Arrays.asList(new ColorValue[]{ColorValue.BROWN, ColorValue.BLACK, ColorValue.RED, ColorValue.YELLOW, ColorValue.GREEN, ColorValue.BROWN, ColorValue.GOLD})));
    }

    private static String generateOutputString(String numberValues, String tolerance, String prefix){
        //unicode 00B1 is the plus or minus sign
        //unicode 2126 is the ohm sign
        return String.format("%s \u00B1%s%% %s\u2126", numberValues, tolerance,prefix);
    }
}