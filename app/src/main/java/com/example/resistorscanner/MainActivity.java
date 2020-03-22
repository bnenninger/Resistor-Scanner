package com.example.resistorscanner;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    Spinner spinner1, spinner2, spinner3, spinner4;

    // TextView for displaying the result
    TextView display;

    private static final String TAG = "MainActivity";

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

        //sets up ArrayAdapters to load the different types of band colors to the spinners
        ArrayAdapter<ColorValue> valueColors = new ArrayAdapter<ColorValue>(this, android.R.layout.simple_spinner_dropdown_item, ColorValue.getValueColors());
        ArrayAdapter<ColorValue> exponentColors = new ArrayAdapter<ColorValue>(this, android.R.layout.simple_spinner_dropdown_item, ColorValue.getExponentColors());
        ArrayAdapter<ColorValue> toleranceColors = new ArrayAdapter<ColorValue>(this, android.R.layout.simple_spinner_dropdown_item, ColorValue.getToleranceColors());

        // initializes the four spinners
        // the first two are for value bands, the third is for multiplier band, the fourth is for
        // tolerance band
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setAdapter(valueColors);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(valueColors);
        spinner2.setOnItemSelectedListener(this);

        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setAdapter(exponentColors);
        spinner3.setOnItemSelectedListener(this);

        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner4.setAdapter(toleranceColors);
        spinner4.setOnItemSelectedListener(this);

        // initializes the textview for displaying results
        display = (TextView) findViewById(R.id.display);
        display.setTextSize(25);
    }

    //method called every time an item is selected from one of the spinners
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //makes the color value display temporarily at the bottom of the screen
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        //updates the output field
        convert();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //action that activates when the button is pressed
    //TODO make this save to history, as conversion happens automatically
    public void convert(View view) {
        convert();
    }

    /**
     * Converts the colors to a resistor value and outputs the result to the text field
     */
    public void convert() {
        //reads the value of the color bands into an arraylist
        ArrayList<ColorValue> bands = new ArrayList<>();
        bands.add(ColorValue.valueOf(spinner1.getSelectedItem().toString().toUpperCase()));
        bands.add(ColorValue.valueOf(spinner2.getSelectedItem().toString().toUpperCase()));
        bands.add(ColorValue.valueOf(spinner3.getSelectedItem().toString().toUpperCase()));
        bands.add(ColorValue.valueOf(spinner4.getSelectedItem().toString().toUpperCase()));

        //creates a colorStorage object to convert the value
        ResistorValue colorStorage = new ResistorValueColorStorage(bands);
        display.setText(colorStorage.outputStringValue());
    }

}
