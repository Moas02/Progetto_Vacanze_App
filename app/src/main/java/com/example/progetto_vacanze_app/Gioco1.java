package com.example.progetto_vacanze_app;
//MORASSUT MATTEO CL.5BIA 01/02/2021
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Gioco1 extends AppCompatActivity {

    //DICHIARAZIONE
    private TextView timer;
    private TextView domanda;
    private Button risposta1;
    private Button risposta2;
    private Button risposta3;
    private Button risposta4;
    private String giusta;
    private String nomeGiocatore;
    private CountDownTimer tempo;


    ArrayList<ArrayList<String>> listaDomande=new ArrayList<>();
    String[][] lista ={
            {"Qual è la capitale dell'Australia?", "Canberra", "Roma", "New York", "Singapore"},
            {"Qual è, per antonomasia, il miglior amico dell'uomo?", "Cane", "Gatto", "Cavallo", "Facocero"},
            {"Come si chiama realmente Gerry Scotti?", "Virginio", "Virgilio", "Arturo", "Benito"},
            {"In che anno nacque Belen Rodriguez?", "1984", "1985","1983", "1894"},
            {"Qual è il mammifero più grande esistente?", "Balenottera azzurra", "Squalo bianco", "Calamaro gigante","Balenottera comune"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioco1);

        Intent intent = getIntent();

        domanda= findViewById(R.id.Domandatxt);
        risposta1= findViewById(R.id.Risposta1);
        risposta2= findViewById(R.id.Risposta2);
        risposta3= findViewById(R.id.Risposta3);
        risposta4= findViewById(R.id.Risposta4);

        final int[] time = {300};
        TextView timer = (TextView)findViewById(R.id.timer1);

             tempo=new  CountDownTimer(300000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("0:"+checkDigit(time[0]));
                time[0]--;
            }

            public void onFinish() {
                Toast.makeText(Gioco1.this, "TEMPO SCADUTO HAI PERSO", Toast.LENGTH_LONG).show();
                writeToFile(getApplicationContext());
                startActivity(new Intent(Gioco1.this, Schermata_Iniziale.class));
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

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }


    public void DomandaRandom(){
        Random random =new Random();
        int numero=random.nextInt(listaDomande.size());

        ArrayList<String> quiz=listaDomande.get(numero);
        domanda.setText(quiz.get(0));
        giusta=quiz.get(1);

        quiz.remove(0);                 //Visualizzo nei bottoni solo le risposte possibili e non la domanda
        Collections.shuffle(quiz);
        risposta1.setText(quiz.get(0));
        risposta2.setText(quiz.get(1));
        risposta3.setText(quiz.get(2));
        risposta4.setText(quiz.get(3));
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
                    Intent i = new Intent(Gioco1.this,/* Schermata2000.class*/VINCITORE.class);
                    i.putExtra("nomeGiocatore", nomeGiocatore);
                    startActivity(i);
                }
                else{
                    writeToFile(getApplicationContext());
                    startActivity(new Intent(Gioco1.this, Schermata_Iniziale.class));
                }
            }
        });
        costrutto.setCancelable(false);
        costrutto.show();
    }


    private void writeToFile(Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("Punteggi.txt", Context.MODE_APPEND));
            int soldi=0;
            outputStreamWriter.write(Registrazione_Utente.nomeGiocatore +" - SOLDI VINTI: "+ soldi +" €"+ "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "ERRORE NEL FILE " + e.toString());
        }
    }
}