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
public class Gioco4 extends AppCompatActivity {
    //DICHIARAZIONE
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
            {"Quale animale sarebbe il miglior compagno dell'uomo nello spazio?","Capra","Cane","Gatto","Scimmia"},
            {"Qual è la capitale del Bangladesh","Dacca","Damasco","Teheran","Erevan"},
            {"Chi ha vinto più palloni d'oro?","Messi","Ronaldo","Zidane","Cruijff"},
            {"Chi era il capo della loggia massonica P2 nel 1981?","Licio Gelli","Salvatore Riina","Giulio Andreotti","Silvio Berlusconi"},
            {"Chi canta la canzone ''Canapa''?","Punkreas","883","Eiffel 65","Squallor"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioco4);

        Intent intent = getIntent();

        domanda= findViewById(R.id.Domandatxt4);
        risposta1= findViewById(R.id.Risposta13);
        risposta2= findViewById(R.id.Risposta14);
        risposta3= findViewById(R.id.Risposta15);
        risposta4= findViewById(R.id.Risposta16);

        final int[] time = {300};
        TextView timer = (TextView)findViewById(R.id.timer4);

        tempo=new  CountDownTimer(300000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText("0:"+checkDigit(time[0]));
                time[0]--;
            }

            public void onFinish() {
                Toast.makeText(Gioco4.this, "TEMPO SCADUTO HAI PERSO", Toast.LENGTH_LONG).show();
                writeToFile(getApplicationContext());
                startActivity(new Intent(Gioco4.this, Schermata_Iniziale.class));
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

        quiz.remove(0);
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
                    Intent i = new Intent(Gioco4.this, Schermata16000.class);
                    i.putExtra("nomeGiocatore", nomeGiocatore);
                    startActivity(i);
                }
                else{
                     writeToFile(getApplicationContext());
                    startActivity(new Intent(Gioco4.this, Schermata_Iniziale.class));
                }
            }
        });
        costrutto.setCancelable(false);
        costrutto.show();
    }


    private void writeToFile(Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("Punteggi.txt", Context.MODE_APPEND));
            int soldi=4000;
            outputStreamWriter.write(Registrazione_Utente.nomeGiocatore +" - SOLDI VINTI: "+ soldi +" €"+ "\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "ERRORE NEL FILE " + e.toString());
        }
    }
}