package com.example.esperimenti.cadlist.model;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Categoria {
    private String uid;
    private String nome;


    public  Categoria(){

    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    @Override
    public String toString() {
        return nome;
    }
}
