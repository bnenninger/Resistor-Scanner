package com.example.resistorscanner.ui.notifications;

import com.example.resistorscanner.ResistorValue;

import java.util.List;

public interface ValueHistoryReceiver {
    /**
     * Updates the history stored by the receiver
     */
    public void updateHistory(List<ResistorValue> history);
}
