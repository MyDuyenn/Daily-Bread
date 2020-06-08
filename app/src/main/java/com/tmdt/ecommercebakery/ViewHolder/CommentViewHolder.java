package com.tmdt.ecommercebakery.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tmdt.ecommercebakery.Interface.ItemClickListener;
import com.tmdt.ecommercebakery.R;

public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtName, txtComemt, txtTime;
    private ItemClickListener itemClickListener;


    public CommentViewHolder(@NonNull View itemView) {
        super(itemView);

        txtName = (TextView)itemView.findViewById(R.id.contact_user_name);
        txtComemt =  (TextView)itemView.findViewById(R.id.contact_user_comment);
        txtTime = (TextView)itemView.findViewById(R.id.contact_date_time);


    }


    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);

    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }
}

