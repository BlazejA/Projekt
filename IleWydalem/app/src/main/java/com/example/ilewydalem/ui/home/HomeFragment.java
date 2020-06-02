package com.example.ilewydalem.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ilewydalem.DatabaseHelper;
import com.example.ilewydalem.R;
import com.example.ilewydalem.StronaGlowna;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.WeakHashMap;

public class HomeFragment extends Fragment {


    private HomeViewModel homeViewModel;
    DatabaseHelper db;
    TextView dzis;

    ListView listView1, listView2, listView3;
    ArrayList<String> list1,list2,list3;
    ArrayAdapter adapter1,adapter2,adapter3;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        db = new DatabaseHelper(getContext());

        listView1 = (ListView) root.findViewById(R.id.lv1);
        listView2 = (ListView) root.findViewById(R.id.lv2);
        listView3 = (ListView) root.findViewById(R.id.lv3);
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();
        Cursor c = db.selectLast();

        if(c.getCount()==0){
            Toast.makeText(getContext(), "Brak wydatków dzisaj", Toast.LENGTH_SHORT).show();
        }else{
            while(c.moveToNext()){
                list1.add(c.getString(1));
                adapter1 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, list1);
                listView1.setAdapter(adapter1);

                list2.add(c.getString(2));
                adapter2 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, list2);
                listView2.setAdapter(adapter2);

                list3.add(c.getString(3));
                adapter3 = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, list3);
                listView3.setAdapter(adapter3);

            }
        }
        dzis = (TextView) root.findViewById(R.id.dzisTXT);
        String kwota = db.selectPrice(now()).toString();
        dzis.setText(kwota);

        return root;
    }
    public String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String d = sdf.format(cal.getTime());
        Log.d("Data teraz",d);
        return sdf.format(cal.getTime());
    }

}

