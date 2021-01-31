package com.example.progetto_vacanze_app;
//MORASSUT MATTEO CL.5BIA 01/02/2021
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Gioco10 extends AppCompatActivity {
    //DICHIARAZIONE
    private TextView domanda1;
    private Button risposta1;
    private Button risposta2;
    private Button risposta3;
    private Button risposta4;
    private String giusta;
    private String nomeGiocatore;
    private CountDownTimer tempo;


    ArrayList<ArrayList<String>> listaDomande=new ArrayList<>();
    String[][] lista ={
            {"Chi è l'inventore del violino?","Gasparo Bertolotti","Bartolomeo Cristofori","Antonio Vivaldi","Adolph Rickenbacker"},
            {"In che anno morì Marco Polo?","1324","1284","1334","1298"},
            {"Quante punte ci sono sulla corona della Statua della Libertà?","Sette","Otto","Cinque","Dieci"},
            {"Cosa lasciò scritto sul suolo lunare Gene Cernan, l'ultimo uomo che mise piede sulla luna?","Le iniziali della figlia","Il simbolo della pace","God Bless America","Gene Was Here"},
            {"Chi è stato il primo 'Man of the Year' eletto dalla rivista 'Time'?","Mahatma Gandhi","F.D.Roosevelt","Winston Churchill","Charles Lindbergh"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioco10);

        Intent intent = getIntent();

        domanda1= findViewById(R.id.Domandatxt10);
        risposta1= findViewById(R.id.Risposta37);
        risposta2= findViewById(R.id.Risposta38);
        risposta3= findViewById(R.id.Risposta39);
        risposta4= findViewById(R.id.Risposta40);

        final int[] time = {300};
        TextView timer = (TextView)findViewById(R.id.timer10);

        tempo=new  CountDownTimer(300000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("0:"+checkDigit(time[0]));
                time[0]--;
            }

            public void onFinish() {
                Toast.makeText(Gioco10.this, "TEMPO SCADUTO HAI PERSO", Toast.LENGTH_LONG).show();
                writeToFile1(getApplicationContext());
                startActivity(new Intent(Gioco10.this, Schermata_Iniziale.class));
            }
        }.start();

        //CREAZIONE ARRAY CON LE DOMANDE
        for(int cont=0;cont<lista.length;cont++){
            ArrayList<String> aux=new ArrayList<>();
            aux.add(lista[cont][0]);        //DOMANDA
            aux.add(lista[cont][1]);        //RISPOSTA GIUSTA
            aux.add(lista[cont][2]);        //ALTRA SCELTA
            aux.add(lista[cont][3]);        //ALTRA SCELTA
            aux.add(lista[cont][4]);        //ALTRA SCELTA

            listaDomande.add(aux);
        }
        DomandaRandom();
    }

    public void DomandaRandom(){
        Random random =new Random();
        int numero=random.nextInt(listaDomande.size());

        ArrayList<String> quiz=listaDomande.get(numero);
        domanda1.setText(quiz.get(0));
        giusta=quiz.get(1);
        quiz.remove(0);
        Collections.shuffle(quiz);
        risposta1.setText(quiz.get(0));
        risposta2.setText(quiz.get(1));
        risposta3.setText(quiz.get(2));
        risposta4.setText(quiz.get(3));
    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    public void Controllo(View view){
        Button controllo= findViewById(view.getId());
        String riga=controllo.getText().toString();
        String alert;
        if(riga.equals(giusta)){
            alert="GIUSTA!!";
        }
        else{
            alert="SBAGLIATA";
        }
        AlertDialog.Builder costrutto= new AlertDialog.Builder(this);
        costrutto.setTitle(alert);
        costrutto.setMessage(giusta);
        costrutto.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tempo.cancel();
                if(riga.equals(giusta)){
                    writeToFile2(getApplicationContext());
                    startActivity(new Intent(Gioco10.this, VINCITORE.class));
                }
                else{
                    writeToFile1( getApplicationContext());
                    startActivity(new Intent(Gioco10.this, Schermata_Iniziale.class));
                }
            }
        });
        costrutto.setCancelable(false);
        costrutto.show();
    }


    private void writeToFile1(Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("Punteggi.txt", Context.MODE_APPEND));
            int soldi=500000;
            outputStreamWriter.write(Registrazione_Utente.nomeGiocatore +" - SOLDI VINTI: "+ soldi +" €"+ "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "ERRORE NEL FILE " + e.toString());
        }
    }

    private void writeToFile2(Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("Punteggi.txt", Context.MODE_APPEND));
            int soldi=1000000;
            outputStreamWriter.write(Registrazione_Utente.nomeGiocatore +" - SOLDI VINTI: "+ soldi +" €"+ "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "ERRORE NEL FILE " + e.toString());
        }
    }

}