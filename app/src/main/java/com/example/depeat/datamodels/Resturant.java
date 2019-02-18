package com.example.depeat.datamodels;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Resturant {

    private String nome;
    private String indirizzo;
    private String minOrdine;
    public ArrayList<Product> prodotti;
    private String image_url;
    private String id;
    public static final String ENDPOINT="restaurants/";

    //public static final String

    public ArrayList<Product> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<Product> prodotti) {
        this.prodotti = prodotti;
    }

    public Resturant(JSONObject jsonObject) throws JSONException{
        nome=jsonObject.getString("name");
        indirizzo=jsonObject.getString("address");
        minOrdine=jsonObject.getString("min_order");
        image_url=jsonObject.getString("image_url");
        id=jsonObject.getString("id") ;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
