package com.example.resistorscanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;


import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/*
This class is a parameterized test. Information regarding how this works (and general junit
information) can be found at the following link:
https://www.vogella.com/tutorials/JUnit/article.html
Parameterized test information is under the header "4.5. Parameterized test"
The overall method for creating the test data is copied, there are likely other ways to do this.
Comments and exact initialization are specific to this class
 */
@RunWith(Parameterized.class)
public class ResistorValueColorStorageTest {

    //these parameters are set differently for each run of the test
    //the number in the @Parameter(x) notation indicates their position in the array initialized later
    @Parameter(0)
    public String expectedNumberValue;
    @Parameter(1)
    public String expectedTolerance;
    @Parameter(2)
    public String expectedPrefix;
    @Parameter(3)
    public ColorValue[] colorBands;

    //initializes the parameter values
    //each internal array is the array for a single test
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                //3-band resistor basic tests
                //22 ohm, 20%
                {"22", "20", "",
                        new ColorValue[] {ColorValue.RED, ColorValue.RED, ColorValue.BLACK}},
                //2.2 kohm, 20%
                {"2.2", "20", "k",
                        new ColorValue[] {ColorValue.RED, ColorValue.RED, ColorValue.RED}},

                //Tests of various prefixes
                //100 mOhm resistor
                {"100", "5", "m",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.SILVER,
                                ColorValue.GOLD}},
                //1 ohm
                {"1.0", "5", "",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.GOLD,
                                ColorValue.GOLD}},
                //10 ohm
                {"10", "5", "",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.BLACK,
                                ColorValue.GOLD}},
                //100 ohm
                {"100", "5", "",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.BROWN,
                                ColorValue.GOLD}},
                //1 kOhm
                {"1.0", "5", "k",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.RED,
                                ColorValue.GOLD}},
                // 10 kOhm
                {"10", "5", "k",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.ORANGE,
                                ColorValue.GOLD}},
                //100 kOhm
                {"100", "5", "k",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.YELLOW,
                                ColorValue.GOLD}},
                //1 MOhm
                {"1.0", "5", "M",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.GREEN,
                                ColorValue.GOLD}},
                //10 MOhm
                {"10", "5", "M",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.BLUE,
                                ColorValue.GOLD}},
                //100 MOhm
                {"100", "5", "M",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.VIOLET,
                                ColorValue.GOLD}},
                //1 GOhm
                {"1.0", "5", "G",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.GREY,
                                ColorValue.GOLD}},
                //10 GOhm
                {"10", "5", "G",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.WHITE,
                                ColorValue.GOLD}},

                //Test of high numbers of digits
                {"10.245", "5", "k",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.RED,
                                ColorValue.YELLOW, ColorValue.GREEN, ColorValue.BLACK,
                                ColorValue.GOLD}},
                {"102.45", "5", "k",
                        new ColorValue[] {ColorValue.BROWN, ColorValue.BLACK, ColorValue.RED,
                                ColorValue.YELLOW, ColorValue.GREEN, ColorValue.BROWN,
                                ColorValue.GOLD}}
        };
        return Arrays.asList(data);
    }

    // Method that is run as the test
    // Note: run flag on side does not mark this as completed, this is likely a product of this being
    // a parameterized test. The run flag for the whole class indicates whether the tests have been
    // passed
    @Test
    public void testValues() {
        ResistorValue v = new ResistorValueColorStorage(Arrays.asList(colorBands));
        assertEquals(generateOutputString(expectedNumberValue, expectedTolerance, expectedPrefix),
                v.outputStringValue());
    }

    // assembles the output string from the numbers, tolerance, and appropriate prefix
    // makes writing tests much more concise and allows it to be done without dealing with keys not
    // on the keyboard
    private static String generateOutputString(String numberValues, String tolerance, String prefix){
        //unicode 00B1 is the plus or minus sign
        //unicode 2126 is the ohm sign
        return String.format("%s \u00B1%s%% %s\u2126", numberValues, tolerance,prefix);
    }
}