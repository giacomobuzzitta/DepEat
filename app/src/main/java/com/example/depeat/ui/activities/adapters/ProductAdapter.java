package com.example.depeat.ui.activities.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.depeat.R;
import com.example.depeat.datamodels.Product;
import com.example.depeat.ui.activities.OnQuantityChangeListener;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter {

    ArrayList<Product> prodotti;
    OnQuantityChangeListener onQuantityChangeListener;
    public Context context;

    public OnQuantityChangeListener getOnQuantityChangeListener() {
        return onQuantityChangeListener;
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener onQuantityChangeListener) {
        this.onQuantityChangeListener = onQuantityChangeListener;
    }

    public ProductAdapter(Context context, ArrayList<Product> prodotti) {
        this.context = context;
        this.prodotti = prodotti;
    }

    public void setData(ArrayList<Product>prodotti){
        this.prodotti=prodotti;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.i("error", parent.getContext().toString());
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop, parent, false);

        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductViewHolder ph = (ProductViewHolder) holder;

        ph.nomeProdotto.setText(prodotti.get(position).getNome());
        ph.quantity.setText(String.valueOf(prodotti.get(position).getQuantita()));
    }

    @Override
    public int getItemCount() {
        return prodotti.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nomeProdotto, prezzo, plus, quantity, less;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            nomeProdotto = itemView.findViewById(R.id.name_productTV);
            prezzo = itemView.findViewById(R.id.price_productTV);
            plus = itemView.findViewById(R.id.plusTV);
            quantity = itemView.findViewById(R.id.quantityTV);
            less = itemView.findViewById(R.id.lessTV);

            plus.setOnClickListener(this);
            less.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Product p = prodotti.get(getAdapterPosition());

            if (v.getId() == R.id.plusTV) {
                p.increaseQuantitiy();
                onQuantityChangeListener.onChange(p.getPrezzo());
            } else if (v.getId() == R.id.lessTV) {
                if (p.getQuantita() > 0) {
                    p.decreaseQuantitiy();
                    onQuantityChangeListener.onChange(p.getPrezzo() * (-1));
                }
            }
            notifyItemChanged(getAdapterPosition());
        }
    }
}

