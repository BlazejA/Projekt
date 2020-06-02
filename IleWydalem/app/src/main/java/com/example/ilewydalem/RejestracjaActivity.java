package com.example.ilewydalem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RejestracjaActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText email, haslo, potwierdz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejestracja);

        db = new DatabaseHelper(this);
        email = (EditText) findViewById(R.id.LoginTXT);
        haslo = (EditText) findViewById(R.id.regHasloTXT);
        potwierdz = (EditText) findViewById(R.id.powtorzTXT);
    }

    public void registerMe(View v) {
        String e = email.getText().toString();
        String h = haslo.getText().toString();
        String p = potwierdz.getText().toString();
        Toast.makeText(this, "klikkkkkk", Toast.LENGTH_SHORT).show();
        if (e.equals("") || h.equals("") || p.equals("")) {
            Toast.makeText(this, "Uzupełnij pola!", Toast.LENGTH_SHORT).show();
        } else {
            if (h.equals(p)) {
                Boolean sprawdz = db.checkEmail(e);
                if (sprawdz == true) {
                    Boolean insert = db.insertAcc(e, h);
                    if (insert == true) {
                        Toast.makeText(this, "Utworzono nowe konto!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Istnieje takie konto!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Podane hasła się różnią!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
