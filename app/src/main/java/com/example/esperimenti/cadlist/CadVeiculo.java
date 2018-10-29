package com.example.esperimenti.cadlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class CadVeiculo extends AppCompatActivity {

    private EditText edtPlaca;
    private EditText edtTipoDeVeiculo;
    private EditText edtAno;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_veiculo);

    edtPlaca = findViewById(R.id.edt_PlacaCarro);
    edtTipoDeVeiculo = findViewById(R.id.edt_TipoDeVeiculo);
    edtAno = findViewById(R.id.edt_Ano);
    }
}
