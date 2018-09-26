package com.example.esperimenti.cadlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Lista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
    }

    public void btnListaCategoria(View view) {
        Intent listcat = new Intent(getApplicationContext(),DadosCatProduto.class);
        startActivity(listcat);
    }

    public void btnProcurarProduto(View view) {


    }
}
