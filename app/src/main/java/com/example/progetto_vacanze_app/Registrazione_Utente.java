package com.example.progetto_vacanze_app;
//MORASSUT MATTEO CL.5BIA 01/02/2021
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrazione_Utente extends AppCompatActivity {

    public static String nomeGiocatore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione__utente);

        //APRO LA SCHERMATA PER LA REGISTRAZIONI DELL'UTENTE
        Button gg= findViewById(R.id.gioco);
        gg.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if(((EditText) findViewById(R.id.Persona)).getText().toString().matches("") ) {
                    Toast.makeText(Registrazione_Utente.this, "COMPILA IL CAMPO NOME", Toast.LENGTH_LONG).show();
                }
                else{
                    Intent i = new Intent(Registrazione_Utente.this, Schermata1000.class);
                    nomeGiocatore = ((EditText) findViewById(R.id.Persona)).getText().toString();
                    i.putExtra("nomeGiocatore", nomeGiocatore);
                    Log.d("Stringa", nomeGiocatore);
                    startActivity(i);
                }
            }

        });

        //TORNO ALLA SCHERMATA PRINCIPALE
        Button kk= findViewById(R.id.Indietro);
        kk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registrazione_Utente.this, Schermata_Iniziale.class));
            }
        });
    }
}