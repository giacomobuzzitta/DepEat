package com.example.depeat.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.depeat.R;
import com.example.depeat.datamodels.Product;
import com.example.depeat.datamodels.Resturant;
import com.example.depeat.datamodels.services.RestController;
import com.example.depeat.ui.activities.adapters.ProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity implements OnQuantityChangeListener, Response.Listener<String >,Response.ErrorListener {

    private TextView shopNameTv, shopAddress, totalTxtView;
    private Button checkout;
    private ProgressBar progressBar;

    private RecyclerView productRv;
    private RecyclerView.LayoutManager layoutManager;
    private ProductAdapter adapter;
    private RestController restController;
    private String id_restaurant;
    private ArrayList<Product> prodotti=new ArrayList<>();

    private float somma = 0.0f;

    private Resturant resturant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        Intent intent=getIntent();
        id_restaurant=intent.getStringExtra("resources");

        shopNameTv = findViewById(R.id.name_tv);
        shopAddress = findViewById(R.id.indirizzo_tv);
        totalTxtView = findViewById(R.id.textView);
        productRv = findViewById(R.id.product_rv);

        checkout = findViewById(R.id.checkout_menu);
        progressBar = findViewById(R.id.progressBar);


        //Request GET for Products
        Log.i("ShopActivity","before restController");
        restController = new RestController(this);
        restController.getRequest(Resturant.ENDPOINT+id_restaurant,this,this);

        Log.i("ShopActivity","after restController");

        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.VERTICAL);
        adapter = new ProductAdapter(this, prodotti);

        adapter.setOnQuantityChangeListener(this);

        productRv.setLayoutManager(layoutManager);
        productRv.setAdapter(adapter);
        productRv.addItemDecoration(new DividerItemDecoration(productRv.getContext(), ((LinearLayoutManager) layoutManager).getOrientation()));

    }



    public void updateTotal(float item) {
        somma += item;
        totalTxtView.setText(String.valueOf(somma));

    }

    public void updatePrograss(int progressoBar) {

        progressBar.setProgress(progressoBar);

    }

    @Override
    public void onChange(float price) {
        Log.d("Clicco", "qui");
        updateTotal(price);
        updatePrograss((int) somma);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.i("ShopActivity", "errore nell'errorResponse");

    }

    @Override
    public void onResponse(String response) {

        try {
            Log.i("ShopActivity","onResponse");
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray= jsonObject.getJSONArray("products");

            for(int i=0; i<jsonArray.length();i++){

                Product product=new Product(jsonArray.getJSONObject(i));
                prodotti.add(product);
                productRv.setAdapter(adapter);

            }
            adapter.setData(prodotti);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
