package com.example.depeat.ui.activities.adapters;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.depeat.R;
import com.example.depeat.datamodels.Resturant;
import com.example.depeat.ui.activities.RegisterActivity;
import com.example.depeat.ui.activities.ShopActivity;

import java.util.ArrayList;

public class ResturantAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private ArrayList<Resturant> data;
    private boolean isGrid;
    Context context;



    public ResturantAdapter(Context context, ArrayList<Resturant> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v;
        if (isGrid) {
            v = inflater.inflate(R.layout.item_resturant2, viewGroup, false);
        } else {
            v = inflater.inflate(R.layout.item_resturant, viewGroup, false);
        }
        return new ResturantViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {
        ResturantViewHolder vh = (ResturantViewHolder) viewHolder;
        vh.resturantName.setText(data.get(pos).getNome());
        vh.resturantIndirizzo.setText(data.get(pos).getIndirizzo());
        vh.resturantMinordine.setText(data.get(pos).getMinOrdine());
        if (!isGrid) {
            vh.image_cuoreR.setImageResource(R.drawable.likerosso);
            vh.image_cuoreN.setImageResource(R.drawable.likenero);
            vh.image_resturant.setImageResource(data.get(pos).getSrc());
        }

        //Glide.with(context).load()    Caricare immagine da remoto
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public boolean isGrid() {
        return isGrid;
    }

    public void setGrid(boolean grid) {
        isGrid = grid;
    }

    public class ResturantViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView resturantName;
        public TextView resturantIndirizzo;
        public TextView resturantMinordine;
        public ImageView image_resturant, image_cuoreN, image_cuoreR;
        CardView cardview;
        Button B_condividi;


        public ResturantViewHolder(@NonNull View itemView) {
            super(itemView);
            resturantName = itemView.findViewById(R.id.name_tv);
            resturantIndirizzo = itemView.findViewById(R.id.indirizzo_tv);
            resturantMinordine = itemView.findViewById(R.id.minordine_tv);
            image_resturant = itemView.findViewById(R.id.image_restaurant_logo);
            image_cuoreN = itemView.findViewById(R.id.imageCuoreN);
            image_cuoreR = itemView.findViewById(R.id.imageCuoreR);
            B_condividi = itemView.findViewById(R.id.B_condividi);
            cardview = itemView.findViewById(R.id.Bmenu);
            cardview.setOnClickListener(this);
            B_condividi.setOnClickListener(this);

            itemView.setOnClickListener(this);
            if (!isGrid) {
                image_cuoreN.setOnClickListener(this);
                image_cuoreR.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.Bmenu) {
                Intent i = new Intent(v.getContext(), ShopActivity.class);
                v.getContext().startActivity(i);
            } else if (v.getId() == R.id.imageCuoreN) {
                image_cuoreN.setVisibility(View.GONE);
                image_cuoreR.setVisibility(View.VISIBLE);
            } else if (v.getId() == R.id.imageCuoreR) {
                image_cuoreR.setVisibility(View.GONE);
                image_cuoreN.setVisibility(View.VISIBLE);
            } else if (v.getId() == R.id.B_condividi) {
                String nome =  resturantName.getText().toString();
                String indirizzo =  resturantIndirizzo.getText().toString();
                String minordine = resturantMinordine.getText().toString();

                String resoconto =  nome + "\n"  + " " + indirizzo + "\n"  + " " + minordine + "\n\n" + "[INFORMAZIONE INVIATA TRAMITE DEPeat]";
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, resoconto);
                context.startActivity(i);
            }
        }
    }
}