package com.example.resistorscanner;

import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
//import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.opencv.android.OpenCVLoader;

import java.util.ArrayList;

import static android.hardware.Camera.open;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    Spinner spinner1, spinner2, spinner3, spinner4;

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

        spinner1 = (Spinner) findViewById(R.id.spinner1);
//        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<ColorValue> valueColors = new ArrayAdapter<ColorValue>(this, android.R.layout.simple_spinner_dropdown_item, ColorValue.getValueColors());
        spinner1.setAdapter(valueColors);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setAdapter(valueColors);
        spinner2.setOnItemSelectedListener(this);

        spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<ColorValue> exponentColors = new ArrayAdapter<ColorValue>(this, android.R.layout.simple_spinner_dropdown_item, ColorValue.getExponentColors());
        spinner3.setAdapter(exponentColors);
        spinner3.setOnItemSelectedListener(this);

        spinner4 = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<ColorValue> toleranceColors = new ArrayAdapter<ColorValue>(this, android.R.layout.simple_spinner_dropdown_item, ColorValue.getToleranceColors());
        spinner4.setAdapter(toleranceColors);
        spinner4.setOnItemSelectedListener(this);


        display = (TextView)findViewById(R.id.display);
        display.setTextSize(25);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        convert();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void convert(View view)
    {
        convert();
    }

    public void convert(){
        ArrayList<ColorValue> bands = new ArrayList<>();
        bands.add(ColorValue.valueOf(spinner1.getSelectedItem().toString().toUpperCase()));
        bands.add(ColorValue.valueOf(spinner2.getSelectedItem().toString().toUpperCase()));
        bands.add(ColorValue.valueOf(spinner3.getSelectedItem().toString().toUpperCase()));
        bands.add(ColorValue.valueOf(spinner4.getSelectedItem().toString().toUpperCase()));

        ResistorValue colorStorage = new ResistorValueColorStorage(bands);
        display.setText(colorStorage.outputStringValue());
    }

}
