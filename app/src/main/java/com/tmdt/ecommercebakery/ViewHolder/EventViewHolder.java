package com.tmdt.ecommercebakery.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.tmdt.ecommercebakery.Interface.ItemClickListener;
import com.tmdt.ecommercebakery.R;

public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtEventName, txtEventDescription;
    public ImageView imageView;
    public ItemClickListener listener;

    public EventViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.event_image);
        txtEventName = (TextView) itemView.findViewById(R.id.event_name);
        txtEventDescription = (TextView) itemView.findViewById(R.id.event_description);

    }

    public void setItemClickListener(ItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(), false);
    }
}
