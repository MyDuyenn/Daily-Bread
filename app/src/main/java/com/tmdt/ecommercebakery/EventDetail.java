package com.tmdt.ecommercebakery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.tmdt.ecommercebakery.Model.Event;
import com.tmdt.ecommercebakery.Model.Products;
import com.tmdt.ecommercebakery.Prevalent.Prevalent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class EventDetail extends AppCompatActivity {

    private Button addToCartButton;
    private ImageButton btnBack;
    private ImageView eventImage;
    private ElegantNumberButton numberButton;
    private TextView eventName, eventDescription;
    private String eventID = "", state = "Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        eventID = getIntent().getStringExtra("pid");

        btnBack = findViewById(R.id.back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventDetail.this, EventScreen.class);
                startActivity(intent);
            }
        });
        eventImage = (ImageView) findViewById(R.id.detail_event_image);
        eventName = (TextView) findViewById(R.id.detail_event_name);
        eventDescription = (TextView) findViewById(R.id.detail_event_description);


        getEventDetails(eventID);


    }

    @Override
    protected void onStart() {
        super.onStart();

    }




    private void getEventDetails(String productID) {

        //them cho nay
        DatabaseReference eventRef = FirebaseDatabase.getInstance().getReference().child("Event");

        eventRef.child(eventID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists())
                {
                    Event event = dataSnapshot.getValue(Event.class);

                    eventName.setText(event.getEventName());
                    eventDescription.setText(event.getEventDescription());
                    Picasso.get().load(event.getEventImage()).into(eventImage);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
