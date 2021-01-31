package com.example.progetto_vacanze_app;
//MORASSUT MATTEO CL.5BIA 01/02/2021
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Schermata_Iniziale extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schermata__iniziale);

        //APRO UNA FINESTRA DI POP-UP PER RINGRAZIAMENTI
        Button pop= findViewById(R.id.altro);
        pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Schermata_Iniziale.this, popup.class));
            }
        });

        //APRO LA SCHERMATA PER LA REGISTRAZIONI DELL'UTENTE
        Button inizia= findViewById(R.id.Iniziamo);
        inizia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Schermata_Iniziale.this, Registrazione_Utente.class));
            }
        });


        //APRO STORICO DEI PUNTEGGI OTTENUTO DA OGNI GIOCATORE
        Button storico=findViewById(R.id.classifica);
            storico.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Schermata_Iniziale.this, Sorico.class));
                }
            });

    }

}