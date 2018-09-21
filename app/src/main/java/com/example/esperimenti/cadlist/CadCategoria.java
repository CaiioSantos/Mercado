package com.example.esperimenti.cadlist;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.esperimenti.cadlist.model.Categoria;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CadCategoria extends AppCompatActivity {

    EditText edtNome;
    ListView list_dados;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Categoria categoriaSelecionada;

    private List<Categoria> listCategoria = new ArrayList<>();
    private String[] listaCategoriaString;
    private ArrayAdapter<String> arrayAdapterCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_categoria);

        edtNome = findViewById(R.id.edt_nomecategoria);

        list_dados = findViewById(R.id.listV_dados);

        iniciarFireBase();
        eventoDatabase();

        list_dados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                categoriaSelecionada = (Categoria)parent.getItemAtPosition(position);
                edtNome.setText(categoriaSelecionada.getNome());
            }
        });


    }

    private void eventoDatabase() {
        databaseReference.child("Categoria").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCategoria.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Categoria cat = objSnapshot.getValue(Categoria.class);
                    listCategoria.add(cat);
                }

                listaCategoriaString = new String[listCategoria.size()];

                for(int i = 0; i < listCategoria.size(); i++) {

                    listaCategoriaString[i] = listCategoria.get(i).getNome();
                }

                arrayAdapterCategoria = new ArrayAdapter<String>(CadCategoria.this,
                        android.R.layout.simple_list_item_1, listaCategoriaString);

                list_dados.setAdapter(arrayAdapterCategoria);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void iniciarFireBase() {
        FirebaseApp.initializeApp(getApplicationContext());
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }


    public void btnCadastroCategoria(View view) {
        Categoria c = new Categoria();
        c.setUid(UUID.randomUUID().toString());
        c.setNome(edtNome.getText().toString());
        databaseReference.child("Categoria").child(c.getNome()).setValue(c);
        limparCampo();
    }

    private void limparCampo() {
        edtNome.setText("");
        }
}
