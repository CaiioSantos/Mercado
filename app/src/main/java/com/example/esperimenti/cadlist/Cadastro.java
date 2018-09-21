package com.example.esperimenti.cadlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void btnCadastroCategoria(View view) {
        Intent cadcategoria = new Intent(getApplicationContext(),CadCategoria.class);
        startActivity(cadcategoria);
    }

    public void btnCadastroProduto(View view) {
        Intent cadproduto = new Intent(getApplicationContext(),CadProduto.class);
        startActivity(cadproduto);
    }
}
