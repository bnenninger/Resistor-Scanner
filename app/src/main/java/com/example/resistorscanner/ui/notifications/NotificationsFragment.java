package com.example.resistorscanner.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.resistorscanner.MainActivity;
import com.example.resistorscanner.R;
import com.example.resistorscanner.ResistorValue;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class NotificationsFragment extends Fragment implements ValueHistoryReceiver {

    private NotificationsViewModel notificationsViewModel;

    ListView listView;
    List<ResistorValue> history;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        //gets a reference to the history list from the main activity
        MainActivity activity = (MainActivity)this.getActivity();
        activity.setValueHistoryReceiver(this);
        history = activity.getHistory();

        //sets the listview to display the history
        listView = root.findViewById(R.id.list_view);
        refreshDisplay();

        return root;
    }

    private void refreshDisplay() {
        ArrayAdapter<ResistorValue> adapter = new ArrayAdapter(requireNonNull(this.getContext()), android.R.layout.simple_list_item_1, history);
        listView.setAdapter(adapter);
    }

    @Override
    public void updateHistory(List<ResistorValue> history) {
        this.history = history;
        refreshDisplay();
    }
}