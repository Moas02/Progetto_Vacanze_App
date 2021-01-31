package com.example.progetto_vacanze_app;
//MORASSUT MATTEO CL.5BIA 01/02/2021
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    //CREO UN TIMER DI ATTESA
    private static final int Timer=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent nuovo=new Intent(MainActivity.this, Schermata_Iniziale.class);
                startActivity(nuovo);
                finish();
            }
        },Timer);
    }
}