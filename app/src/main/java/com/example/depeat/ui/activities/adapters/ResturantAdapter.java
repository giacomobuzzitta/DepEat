package com.example.depeat.ui.activities.adapters;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.depeat.R;
import com.example.depeat.datamodels.Resturant;

import java.util.ArrayList;

public class ResturantAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private ArrayList<Resturant> data;

    public ResturantAdapter(Context context,ArrayList<Resturant> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
    View view = inflater.inflate(R.layout.item_resturant,viewGroup,false);
        return new ResturantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int pos) {
        ResturantViewHolder vh = (ResturantViewHolder) viewHolder;
        vh.resturantName.setText(data.get(pos).getNome());
        vh.resturantIndirizzo.setText(data.get(pos).getIndirizzo());
        vh.resturantMinordine.setText(data.get(pos).getMinOrdine());
        vh.image.setImageResource(data.get(pos).getSrc());

        //Glide.with(context).load()    Caricare immagine da remoto
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ResturantViewHolder extends RecyclerView.ViewHolder{
        public TextView resturantName;
        public TextView resturantIndirizzo;
        public TextView resturantMinordine;
        public ImageView image;

        public ResturantViewHolder(@NonNull View itemView) {
            super(itemView);
            resturantName = itemView.findViewById(R.id.name_tv);
            resturantIndirizzo = itemView.findViewById(R.id.indirizzo_tv);
            resturantMinordine = itemView.findViewById(R.id.minordine_tv);
            image = itemView.findViewById(R.id.image_restaurant_logo);


        }
    }
}