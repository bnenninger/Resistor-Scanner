package com.example.resistorscanner;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

class MainActivityImpl extends MainActivity {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
