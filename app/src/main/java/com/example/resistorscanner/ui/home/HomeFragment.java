package com.example.resistorscanner.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.resistorscanner.ColorValue;
import com.example.resistorscanner.MainActivity;
import com.example.resistorscanner.R;
import com.example.resistorscanner.ResistorValue;
import com.example.resistorscanner.ResistorValueColorStorage;

import java.util.ArrayList;
import java.util.Objects;

import static java.util.Objects.*;

public class HomeFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner1, spinner2, spinner3, spinner4;
    TextView display;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //loads the xml file for this fragment
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        //sets up ArrayAdapters to load the different types of band colors to the spinners
        ArrayAdapter<ColorValue> valueColors = new ArrayAdapter<ColorValue>(requireNonNull(this.getContext()), android.R.layout.simple_spinner_dropdown_item, ColorValue.getValueColors());
        ArrayAdapter<ColorValue> exponentColors = new ArrayAdapter<ColorValue>(requireNonNull(this.getContext()), android.R.layout.simple_spinner_dropdown_item, ColorValue.getExponentColors());
        ArrayAdapter<ColorValue> toleranceColors = new ArrayAdapter<ColorValue>(requireNonNull(this.getContext()), android.R.layout.simple_spinner_dropdown_item, ColorValue.getToleranceColors());

        // initializes the four spinners
        // the first two are for value bands, the third is for multiplier band, the fourth is for
        // tolerance band
        spinner1 = (Spinner) root.findViewById(R.id.spinner1);
        spinner1.setAdapter(valueColors);
        spinner1.setOnItemSelectedListener(this);

        spinner2 = (Spinner) root.findViewById(R.id.spinner2);
        spinner2.setAdapter(valueColors);
        spinner2.setOnItemSelectedListener(this);

        spinner3 = (Spinner) root.findViewById(R.id.spinner3);
        spinner3.setAdapter(exponentColors);
        spinner3.setOnItemSelectedListener(this);

        spinner4 = (Spinner) root.findViewById(R.id.spinner4);
        spinner4.setAdapter(toleranceColors);
        spinner4.setOnItemSelectedListener(this);

        // initializes the textview for displaying results
        display = (TextView) root.findViewById(R.id.display2);
        display.setTextSize(25);

        return root;
    }

    //action that activates when the button is pressed
    //TODO make this save to history, as conversion happens automatically
    public void convert(View view) {
        convert();
    }

    public void convert() {
        //reads the value of the color bands into an arraylist
        ArrayList<ColorValue> bands = new ArrayList<>();
        bands.add(ColorValue.valueOf(spinner1.getSelectedItem().toString().toUpperCase()));
        display.setText(bands.toString());
        bands.add(ColorValue.valueOf(spinner2.getSelectedItem().toString().toUpperCase()));
        bands.add(ColorValue.valueOf(spinner3.getSelectedItem().toString().toUpperCase()));
        bands.add(ColorValue.valueOf(spinner4.getSelectedItem().toString().toUpperCase()));

        //creates a colorStorage object to convert the value
        ResistorValue colorStorage = new ResistorValueColorStorage(bands);
        display.setText(colorStorage.outputStringValue());
    }

    //method called every time an item is selected from one of the spinners
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //updates the output field
        convert();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}