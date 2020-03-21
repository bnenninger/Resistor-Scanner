package com.example.resistorscanner;

import android.graphics.Camera;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Log;
//import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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

    ArrayList<String> bandValues = new ArrayList<String>();
    String[] bandArray = new String[bandValues.size()];
    String spinnerText1;
    String spinnerText2;
    String spinnerText3;
    String spinnerText4 = "gold";
    TextView display;

    private static final String TAG = "MainActivity";

    static {
        if(!OpenCVLoader.initDebug()){
            Log.d(TAG, "OpenCV not loaded");
        } else {
            Log.d(TAG, "OpenCV loaded");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!OpenCVLoader.initDebug()){
            Log.d(TAG, "OpenCV not loaded");
        } else {
            Log.d(TAG, "OpenCV loaded");
        }
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

        Spinner Spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner1.setAdapter(adapter1);
        Spinner1.setOnItemSelectedListener(this);

        Spinner Spinner2 = (Spinner) findViewById(R.id.spinner2);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner2.setAdapter(adapter1);
        Spinner2.setOnItemSelectedListener(this);

        Spinner Spinner3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.colors, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner3.setAdapter(adapter3);
        Spinner3.setOnItemSelectedListener(this);

        spinnerText1 = Spinner1.getSelectedItem().toString();
        spinnerText2 = Spinner2.getSelectedItem().toString();
        spinnerText3 = Spinner3.getSelectedItem().toString();

        bandValues.add(spinnerText1);
        bandValues.add(spinnerText2);
        bandValues.add(spinnerText3);

        display = (TextView)findViewById(R.id.display);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void convert(View view)
    {
       for(int i = 0; i < bandValues.size(); i++)
       {
           bandArray[i] = bandValues.get(i);
       }
       display.setTextSize(25);
       display.setText(ColorToValue.colorValue(bandArray));
    }

}
