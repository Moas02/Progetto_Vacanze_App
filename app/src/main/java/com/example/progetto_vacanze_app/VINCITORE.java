package com.example.progetto_vacanze_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class VINCITORE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v_i_n_c_i_t_o_r_e);

        //TORNO ALLA HOME
        Button gg= findViewById(R.id.PRimo);
        gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VINCITORE.this, Schermata_Iniziale.class));
            }

        });
    }
}