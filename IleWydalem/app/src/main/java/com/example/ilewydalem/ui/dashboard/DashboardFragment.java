package com.example.ilewydalem.ui.dashboard;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ilewydalem.DatabaseHelper;
import com.example.ilewydalem.R;
import com.example.ilewydalem.ui.home.HomeFragment;
import com.example.ilewydalem.ui.home.HomeViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private HomeViewModel homeViewModel;
    DatabaseHelper db;
    EditText kategoria, wartosc, opis;
    Button btn;
    String data;

    TextView wyborDaty;
    DatePickerDialog.OnDateSetListener dateSetListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        db = new DatabaseHelper(getContext());
        kategoria = (EditText) root.findViewById(R.id.katTXT);
        wartosc = (EditText) root.findViewById(R.id.wartTXT);
        opis = (EditText) root.findViewById(R.id.opisTXT);
        btn = (Button) root.findViewById(R.id.button);
        wyborDaty = (TextView) root.findViewById(R.id.dataTXT);
        wyborDaty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int y = cal.get(Calendar.YEAR);
                int m = cal.get(Calendar.MONTH);
                int d = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_DeviceDefault, dateSetListener, y, m, d);
                dialog.getWindow().setBackgroundDrawable((new ColorDrawable((Color.CYAN))));
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                data = dayOfMonth + "." + month + "." + year;

                SimpleDateFormat sdf = new SimpleDateFormat("d.MM.yyyy");
                Date d = null;
                try {
                    d = sdf.parse(data);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                sdf.applyPattern("dd.MM.yyyy");
                String nowaData = sdf.format(d);

                data = nowaData;
            }

        };


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String k = kategoria.getText().toString();
                Double w = Double.parseDouble(wartosc.getText().toString());
                String o = opis.getText().toString();

                String konto = getActivity().getIntent().getStringExtra("email");
                boolean insert = db.insertExpese(k, w, o, data, konto);
                if (insert) {
                    Toast.makeText(getContext(), "Dodano!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Błąd!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }
}
