package com.example.resistorscanner.valueGeneration;

//Note: This only works for a 4 color resistor and doesn't do anything for tolerance as is
public class ColorToValue{
    public static int colorValue(String[] s) {
        int[] count = new int[s.length-1];
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
            else {
                System.out.println("Error: color not found");
                return -1;
            }
        }
        int value;
        int tens = count[0]*10;
        int ones = count[1];
        int multiplier = 1;
        for(int j = count[2]; j>0; j--){
            multiplier = multiplier*10;
        }
        value = (tens + ones)*multiplier;
        return value;
    }
}