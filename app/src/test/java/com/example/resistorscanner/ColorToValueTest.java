package com.example.resistorscanner;

import org.junit.Test;

import static org.junit.Assert.*;

public class ColorToValueTest {
    @Test
    public void basicTest(){
        assertEquals(12, ColorToValue.colorValue(new String[]{"brown", "red", "black", "silver"}));
        assertEquals(13000, ColorToValue.colorValue(new String[]{"brown", "orange", "orange", "tolerance"}));
    }

}