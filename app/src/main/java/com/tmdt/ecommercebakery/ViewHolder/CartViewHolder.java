package com.tmdt.ecommercebakery.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmdt.ecommercebakery.Interface.ItemClickListener;
import com.tmdt.ecommercebakery.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName, txtProductPrice, txtProductQuantity;
    private ItemClickListener itemClickListener;


    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        txtProductName = (TextView)itemView.findViewById(R.id.cart_prouct_name);
        txtProductPrice =  (TextView)itemView.findViewById(R.id.cart_prouct_price);
        txtProductQuantity = (TextView)itemView.findViewById(R.id.cart_product_quantity);

    }


    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}

