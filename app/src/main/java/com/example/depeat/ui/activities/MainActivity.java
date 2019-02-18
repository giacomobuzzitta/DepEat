package com.example.depeat.ui.activities;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.depeat.R;
import com.example.depeat.datamodels.Resturant;
import com.example.depeat.datamodels.services.RestController;
import com.example.depeat.ui.activities.adapters.ResturantAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Response.Listener<String>,Response.ErrorListener  {

    RecyclerView resturantRV;
    RecyclerView.LayoutManager layoutManager;
    ResturantAdapter adapter;
    public ArrayList<Resturant> restaurantArraylist;
    private String sharedPrefFile = "com.example.android.hellosharedprefs";
    SharedPreferences mPreferences;
    private boolean stato;
    RestController restController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurantArraylist = new ArrayList<>();
        //request get
        restController = new RestController(this);
        restController.getRequest(Resturant.ENDPOINT,this,this);

        resturantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        stato = mPreferences.getBoolean("Stato griglia", false);
        if (!stato) {
            layoutManager = new LinearLayoutManager(this);
        } else {
            layoutManager = new GridLayoutManager(this, 2);
        }

        adapter = new ResturantAdapter(this);
        resturantRV.setLayoutManager(layoutManager);
        resturantRV.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        layoutManager = mPreferences.getBoolean("Stato griglia", false) ? new GridLayoutManager(this, 2) : new LinearLayoutManager(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.login_menu) {

            startActivity(new Intent(this, LoginActivity.class));
            return true;

        } else if (item.getItemId() == R.id.checkout_menu) {

            startActivity(new Intent(this, CheckoutActivity.class));

        } else if (item.getItemId() == R.id.Grid_menu) {

            stato = mPreferences.getBoolean("Stato griglia", false);

            adapter.setGrid(stato);

            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putBoolean("Stato griglia", adapter.isGrid());


            layoutManager = adapter.isGrid() ? new GridLayoutManager(this, 2) : new LinearLayoutManager(this);
            resturantRV.setLayoutManager(layoutManager);
            resturantRV.setAdapter(adapter);
            adapter = new ResturantAdapter(this);

            preferencesEditor.apply();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("error",error.getMessage());
    }

    @Override
    public void onResponse(String response) {

        try {
            JSONArray jsonArray = new JSONArray(response);

            for(int i = 0; i<jsonArray.length();i++){
                Resturant resturant = new Resturant(jsonArray.getJSONObject(i));
                restaurantArraylist.add(resturant);
            }

            adapter.setData(restaurantArraylist);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}