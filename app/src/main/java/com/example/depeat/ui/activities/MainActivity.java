package com.example.depeat.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.depeat.R;
import com.example.depeat.datamodels.Resturant;
import com.example.depeat.ui.activities.adapters.ResturantAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView resturantRV;
    RecyclerView.LayoutManager layoutManager;
    ResturantAdapter adapter;
    ArrayList<Resturant> arrayList;

    Resturant r1 = new Resturant("Forno Impero","Via dei mille","7");
    Resturant r2 = new Resturant("Il carretto","Via Viale Europa","5");
    Resturant r3 = new Resturant("Peri peri","Via stazione","7");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resturantRV = findViewById(R.id.places_rv);
        layoutManager = new LinearLayoutManager(this);
        adapter = new ResturantAdapter(this,getData());

        resturantRV.setLayoutManager(layoutManager);
        resturantRV.setAdapter(adapter);

    }

    private ArrayList<Resturant> getData(){

        arrayList = new ArrayList<>();
        arrayList.add(r1);
        arrayList.add(r2);
        arrayList.add(r3);

        return arrayList;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.login_menu) {

            startActivity(new Intent(this, LoginActivity.class));
            return true;

        } else if (item.getItemId() == R.id.checkout_menu) {

            startActivity(new Intent(this, CheckoutActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
