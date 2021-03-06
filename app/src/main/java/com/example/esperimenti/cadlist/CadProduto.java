package com.example.esperimenti.cadlist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.esperimenti.cadlist.model.Categoria;
import com.example.esperimenti.cadlist.model.Produto;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class    CadProduto extends AppCompatActivity {

    EditText edtnome,edtqtd,edtpreco;
    Categoria nomeCategoria;
    Spinner list_Categoria;

    private List<Categoria> listCategoria = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produto);

        edtnome = findViewById(R.id.nome_produto);
        edtqtd = findViewById(R.id.qtd_produto);
        edtpreco = findViewById(R.id.preco_produto);

        list_Categoria = findViewById(R.id.spinner);

        iniciarFirebase();



        databaseReference.child("Categoria").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCategoria.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Categoria cat = objSnapshot.getValue(Categoria.class);
                    listCategoria.add(cat);
                }


                final ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, listCategoria);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                list_Categoria.setAdapter(adapter);


                list_Categoria.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        nomeCategoria = adapter.getItem(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void iniciarFirebase() {
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }



    public void btnCadastro(View view) {
        Produto produto = new Produto();
        Categoria c = new Categoria();
        c.setNome(nomeCategoria.getNome());
        produto.setCategoria(c);
        produto.setUid(UUID.randomUUID().toString());
        produto.setNome(edtnome.getText().toString());
        produto.setQuantidade(edtqtd.getText().toString());
        produto.setPreco(edtpreco.getText().toString());
        databaseReference.child("Categoria").child(String.valueOf(c)).child(produto.getNome()).setValue(produto);
    }
}
