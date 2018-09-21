package com.example.esperimenti.cadlist;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

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

public class CadProduto extends AppCompatActivity {

    EditText edtnome,edtqtd,edtpreco;
    private Spinner list_Categoria;

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


                ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(getApplicationContext(),
                        android.R.layout.simple_spinner_item, listCategoria);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                list_Categoria.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(this,
                android.R.layout.simple_spinner_item,catList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        list_Categoria.setAdapter(adapter);*/


    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    public void inserirFoto(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivityForResult(intent , 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data != null){
            Bundle bundle =  data.getExtras();
            if (bundle != null){
                Bitmap img = (Bitmap) bundle.get("data");

                ImageView iv = findViewById(R.id.imageView2);
                iv.setImageBitmap(img);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }



}