package com.example.resistorscanner;

/**
 * Interface for objects 
 */
public interface ResistorValueSource {
    /**
     * Returns the ResistorValue currently displayed by the object
     * @return current value in the object, or null if no object currently present
     */
    public ResistorValue getCurrentValue();
}
