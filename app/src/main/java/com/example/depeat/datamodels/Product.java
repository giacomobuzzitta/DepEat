package com.example.depeat.datamodels;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Product {
    private String nome;
    private float prezzo;
    private int quantita;

    public Product(String nome, float prezzo, int quantita) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }
    public Product(JSONObject jsonArray) throws JSONException {

        this.nome=jsonArray.getString("name");
        this.prezzo=Float.valueOf(jsonArray.getString("price"));
        //agg altri
    }

    public void increaseQuantitiy(){
        this.quantita++;
    }

    public void decreaseQuantitiy(){
        if (quantita > 0) {
            this.quantita--;
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
}