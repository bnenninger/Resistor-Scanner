package com.example.resistorscanner;

public enum ColorValue {
    BLACK(0, null),
    BROWN(1, "1"),
    RED(2, "2"),
    ORANGE(3, null),
    YELLOW(4, null),
    GREEN(5, "0.5"),
    BLUE(6, "0.25"),
    VIOLET(7, "0.10"),
    GREY(8, "0.05"),
    WHITE(9, null),
    GOLD(-1,"5"),
    SILVER(-2, "10");


    private final int value;
    private final String tolerance;

    /**
     * Creates a color for the resistor color code.
     * @param value the value of the color, both for the number value and 10^x value.
     * @param tolerance the tolerance of the resistor.
     */
    //the tolerance is a String so that 0.01 and 5 can be the same data type without dealing with
    // issue with double rounding
    ColorValue(int value, String tolerance){
        this.value = value;
        this.tolerance = tolerance;
    }

    /**
     * Returns the integer value of the color, either for the numeric value or power of 10.
     * @return Numeric value and power of 10. Negative powers are only powers of 10, not values.
     */
    public int getValue(){
        return value;
    }

    /**
     * Returns the tolerance value of the resistor as a String.
     * @return String name of the tolerance of the resistor.
     */
    public String getTolerance(){
        if(tolerance == null){
            throw new UnsupportedOperationException("getting tolerance attempted on resistor color that does not represent tolerance");
        }
        return tolerance;
    }
}
