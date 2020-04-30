package com.example.resistorscanner;

import android.os.Bundle;

import android.util.Log;

import android.view.View;


import com.example.resistorscanner.ui.home.HomeFragment;
import com.example.resistorscanner.ui.notifications.ValueHistoryReceiver;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<ResistorValue> history;
    private ResistorValueSource valueSource;
    private ValueHistoryReceiver valueHistoryReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_camera, R.id.navigation_history)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        //sets up history object
        //uses linkedlist for fast adding to front of the list
        history = new LinkedList<ResistorValue>();
    }

    /**
     * Saves the current resistor value to the history, called by save button press
     *
     * @param v
     */
    public void saveToHistory(View v) {
        //adds currently displayed value to the front of the history list
        history.add(0, valueSource.getCurrentValue());
        Log.i("save button pressed", history.toString());
    }

    /**
     * Returns a reference to the list storing the resistor history
     *
     * @return list of saved resistor values, such that the first is the newest
     */
    public List<ResistorValue> getHistory() {
        return history;
    }

    /**
     * Sets the source of resistor values to be added to the history
     *
     * @param source
     */
    public void setResistorValueSource(ResistorValueSource source) {
        valueSource = source;
    }

    public void setValueHistoryReceiver(ValueHistoryReceiver receiver){
        valueHistoryReceiver = receiver;
    }

    /**
     * Clears the entire history
     * @param v
     */
    public void clearHistory(View v){
        history.clear();
        valueHistoryReceiver.updateHistory(history);
    }
}
