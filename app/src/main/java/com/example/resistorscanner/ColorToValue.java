
package com.example.resistorscanner;

//Note: This only works for a 4 color resistor and doesn't do anything for tolerance as is
public class ColorToValue{
    /**
     * Converts an array of color strings to the numeric value of the resistor. Does not account for
     * tolerance band. Only works with 4-band resistor.
     * @param s Array of Strings representing the colors of the resistors. Must be in all lowercase.
     * @return integer value of resistor
     */
    public static int colorValue(String[] s) {
        //Creates an Integer array to store the number values from a String Array.
        //This will store the tens digit, ones digit, and then the factor
        int[] count = new int[s.length];
        //runs through each possible color excluding gold and silver
        for(int i = 0; i < count.length; i++){
            if(s[i].equals ("black"))
                count[i] = 0;
            else if(s[i].equals("brown"))
                count[i] = 1;
            else if(s[i].equals ("red"))
                count[i] = 2;
            else if(s[i].equals ("orange"))
                count[i] = 3;
            else if(s[i].equals ("yellow"))
                count[i] = 4;
            else if(s[i].equals ("green"))
                count[i] = 5;
            else if(s[i].equals ("blue"))
                count[i] = 6;
            else if(s[i].equals ("violet"))
                count[i] = 7;
            else if(s[i].equals ("grey"))
                count[i] = 8;
            else if(s[i].equals ("white"))
                count[i] = 9;
            //error statement if color is not found
            else {
                System.out.println("Error: color not found");
                return -1;
            }
        }
        int value;//value to be returned
        int tens = count[0]*10;//tens digit
        int ones = count[1];//ones digit
        int multiplier = 1;//multiplier will run through a loop to calculate factor
        for(int j = count[2]; j>0; j--){
            multiplier = multiplier*10;
        }
        value = (tens + ones)*multiplier;//value is created and then returned
        return value;
    }
}