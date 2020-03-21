package com.example.resistorscanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

// Test to verify that exceptions are thrown when an invalid value is used for a certain band,
// such as gold or silver being used as a number band
@RunWith(Parameterized.class)
public class ResistorValueColorStorageExceptionsTest {

    @Parameterized.Parameter(0)
    public ColorValue[] colorBands;

    //initializes the parameter values
    //each internal array is the array for a single test
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                //gold and silver as value bands
                {new ColorValue[] {ColorValue.GOLD, ColorValue.RED, ColorValue.GREEN}},
                {new ColorValue[] {ColorValue.SILVER, ColorValue.RED, ColorValue.GREEN}},
                //invalid tolerance bands
                {new ColorValue[] {ColorValue.RED, ColorValue.RED, ColorValue.RED, ColorValue.BLACK}},
                {new ColorValue[] {ColorValue.RED, ColorValue.RED, ColorValue.RED, ColorValue.WHITE}}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testExceptions(){
        assertThrows(UnsupportedOperationException.class, () -> {new ResistorValueColorStorage(Arrays.asList(colorBands));});
    }
}