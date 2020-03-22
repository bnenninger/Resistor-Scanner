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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.opencv.android.OpenCVLoader;

import static android.hardware.Camera.open;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {

    private static final String TAG = "MainActivity";

    static {
        if(!OpenCVLoader.initDebug()){
            Log.d(TAG, "OpenCV not loaded");
        } else {
            Log.d(TAG, "OpenCV loaded");
        }
    }

    private View view;

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
        ArrayAdapter<CharSequence> adapterValues = ArrayAdapter.createFromResource(this, R.array.colorsValues, android.R.layout.simple_spinner_item);
        adapterValues.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner1.setAdapter(adapterValues);
        Spinner1.setOnItemSelectedListener(this);

        Spinner Spinner2 = (Spinner) findViewById(R.id.spinner2);
        adapterValues.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner2.setAdapter(adapterValues);
        Spinner2.setOnItemSelectedListener(this);

        Spinner Spinner3 = (Spinner) findViewById(R.id.spinner3);
        adapterValues.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner3.setAdapter(adapterValues);
        Spinner3.setOnItemSelectedListener(this);

        Spinner Spinner4 = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapterTolerance = ArrayAdapter.createFromResource(this, R.array.colorsTolerance, android.R.layout.simple_spinner_item);
        adapterTolerance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner4.setAdapter(adapterTolerance);
        Spinner4.setOnItemSelectedListener(this);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void camera(View view) {

    }
}
