package com.example.depeat.datamodels;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.view.View;

import com.example.depeat.R;
import com.example.depeat.ui.activities.RegisterActivity;
import com.example.depeat.ui.activities.ShopActivity;

public class Resturant {

    private String nome;
    private String indirizzo;
    private String minOrdine;
    @DrawableRes
    private int src;


    public Resturant(String nome, String indirizzo, String minOrdine,@DrawableRes int image){
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.minOrdine = minOrdine;
        src = image;
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

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

}
