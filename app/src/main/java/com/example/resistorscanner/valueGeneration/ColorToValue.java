package com.example.resistorscanner.valueGeneration;
import java.util.Scanner;
//Basic Color to Value calculator needs to implement a tolerance feature.
public class colorToValue{
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
    //Tester method
    public static void main(String[] args) {
        //uses a scanner to take 3 string inputs
        Scanner board = new Scanner(System.in);
        //prompts user
        System.out.println("Enter 3 colors to output resistor value.");
        //takes in a string with three colors (method can work with more, but just testing with three
        String[] input = new String[3];
        //Store the values
        for(int i = 0; i<3; i++)
            input[i] = board.next();
        //runs the code
        int resistorVal = colorValue(input);
        //if the value is valid, returns the value
        if(resistorVal != -1)
        System.out.println(resistorVal + "ohms");
        board.close();
    }
}