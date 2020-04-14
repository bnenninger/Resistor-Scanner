package com.example.resistorscanner.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.resistorscanner.MainActivity;
import com.example.resistorscanner.R;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    ListView listView;
    ArrayList<String> list = new ArrayList<String>();
    ArrayAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        listView = root.findViewById(R.id.list_view);

        list.add("Hello");
        list.add("Test");
        list.add("1200");
        list.add("150");
        list.add("250");
        list.add("45000");
        list.add("37000");
        list.add("180000");
        list.add("10");
        list.add("490");
        list.add("1500");
        list.add("2900");
        list.add("1000000");
        list.add("Hey it scrolls down");
        list.add("25");
        list.add("650");
        list.add("900");
        list.add("1000");

        adapter = new ArrayAdapter(requireNonNull(this.getContext()), android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);

        return root;
    }
}