package com.example.ilewydalem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ClickMe(View v){
        Intent i = null;
        switch(v.getId()){
            case R.id.rejestBTN:
                 i = new Intent(this, RejestracjaActivity.class);
                break;
            case R.id.zalogujBTN:
                i = new Intent(this, StronaGlowna.class);
                break;
        }
        startActivity(i);
    }
}
