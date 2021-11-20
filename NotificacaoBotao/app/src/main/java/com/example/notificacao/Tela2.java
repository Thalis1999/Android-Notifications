package com.example.notificacao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Tela2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela2);

        //Recupera o valor do extra
        String extra = getIntent().getStringExtra("testeExtra");
        Toast.makeText(Tela2.this, extra, Toast.LENGTH_SHORT).show();
    }
}