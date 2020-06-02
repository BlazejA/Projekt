package com.example.ilewydalem.ui.notifications;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.ilewydalem.DatabaseHelper;
import com.example.ilewydalem.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    ListView listView1;
    ArrayList<String> list1;
    ArrayAdapter adapter1;
    DatabaseHelper db;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        db = new DatabaseHelper(getContext());

        listView1 = (ListView) root.findViewById(R.id.lv1);
        String konto = getActivity().getIntent().getStringExtra("email");

        Cursor c = db.selectAll(konto);
        list1 = new ArrayList<>();

        if(c.getCount()==0){
            Toast.makeText(getContext(), "Brak wydatków", Toast.LENGTH_SHORT).show();
        }else{
            while(c.moveToNext()){
                list1.add(c.getString(1));
                adapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, list1);
                listView1.setAdapter(adapter1);
            }
        }

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                db.deleteAll();
                Toast.makeText(getContext(), "Usunięto wszytko!", Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }
}
