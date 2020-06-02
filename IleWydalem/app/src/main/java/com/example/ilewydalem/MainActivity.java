package com.example.ilewydalem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e, h;

    DatabaseHelper db;
    Animation anim;
    Button btn, btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        e = (EditText) findViewById(R.id.LoginTXT);
        h = (EditText) findViewById(R.id.hasloTXT);
        btn = (Button) findViewById(R.id.rejestBTN);
        btn1 = (Button) findViewById(R.id.zalogujBTN);
        anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.jump);
        btn.startAnimation(anim);
        btn1.startAnimation(anim);
    }

    public void ClickMe(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.rejestBTN:
                i = new Intent(this, RejestracjaActivity.class);
                break;
            case R.id.zalogujBTN:
                String email = e.getText().toString();
                String haslo = h.getText().toString();
                Boolean czyIs = db.isExisting(email, haslo);
                if (czyIs) {
                    i = new Intent(this, StronaGlowna.class);
                    i.putExtra("email",email);
                } else {
                    Toast.makeText(this, "Błędny email lub haslo!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        if(i!=null) {
            startActivity(i);
        }
    }
}
