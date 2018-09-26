package com.example.esperimenti.cadlist.model;

public class Veiculo {
    private String placa;
    private String modelo;
    private String ano;

    public Veiculo() {
        }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString() {
        return placa;
    }
}
