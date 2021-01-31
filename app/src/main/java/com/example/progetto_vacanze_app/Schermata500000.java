package com.example.progetto_vacanze_app;
//MORASSUT MATTEO CL.5BIA 01/02/2021
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Schermata500000 extends AppCompatActivity {
    private static final int Timer=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schermata500000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent nuovo=new Intent(Schermata500000.this, Gioco9.class);
                startActivity(nuovo);
                finish();
            }
        },Timer);
    }
}