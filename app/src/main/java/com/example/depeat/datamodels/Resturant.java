package com.example.depeat.datamodels;

public class Resturant {

    private String nome;
    private String indirizzo;
    private String minOrdine;


    public Resturant(String nome, String indirizzo, String minOrdine){
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.minOrdine = minOrdine;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getMinOrdine() {
        return minOrdine;
    }

    public void setMinOrdine(String minOrdine) {
        this.minOrdine = minOrdine;
    }
}
