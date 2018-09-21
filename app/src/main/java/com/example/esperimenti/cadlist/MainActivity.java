package com.example.esperimenti.cadlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void btnCadastro(View view) {
        Intent cadastramento = new Intent(getApplicationContext(),Cadastro.class);
        startActivity(cadastramento);
        }


    public void btnLista(View view) {
        Intent listar =  new Intent(getApplicationContext(),Lista.class);
        startActivity(listar);
    }
}
