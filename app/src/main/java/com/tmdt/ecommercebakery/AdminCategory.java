package com.tmdt.ecommercebakery;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AdminCategory extends AppCompatActivity {

    private ImageView imgCake,  imgCrossant;
    private ImageView imgLightmeal, imgDessert;
    private ImageButton BtnBack, CheckOrdersBtn, maintainProductBtn, maintainProductBtn2, maintainProductBtn3, maintainProductBtn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        imgCake = (ImageView) findViewById(R.id.imgCake);
        imgCrossant = (ImageView)findViewById(R.id.imgCrossant);
        imgLightmeal= (ImageView)findViewById(R.id.imgLightmeal);
        imgDessert = (ImageView)findViewById(R.id.imgDessert);
        CheckOrdersBtn = findViewById(R.id.admin_check_orders);
        BtnBack = findViewById(R.id.back);

        //Btn back
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Admin-Maintain Cake
        maintainProductBtn = findViewById(R.id.admin_maintain);
        maintainProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, ProductScreen.class);
                intent.putExtra("Admin", "Admin");
                startActivity(intent);

            }
        });
        //Admin-Maintain Crossant
        maintainProductBtn2 = findViewById(R.id.admin_maintain2);
        maintainProductBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, ProductScreen2.class);
                intent.putExtra("Admin", "Admin");
                startActivity(intent);

            }
        });
        //Admin-Maintain LightMeal
        maintainProductBtn3 = findViewById(R.id.admin_maintain3);
        maintainProductBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, ProductScreen3.class);
                intent.putExtra("Admin", "Admin");
                startActivity(intent);

            }
        });
        //Admin-Maintain LightMeal
        maintainProductBtn4 = findViewById(R.id.admin_maintain4);
        maintainProductBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, ProductScreen4.class);
                intent.putExtra("Admin", "Admin");
                startActivity(intent);

            }
        });





        imgCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "Cake");
                startActivity(intent);
            }
        });
        imgCrossant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "Crossant");
                startActivity(intent);
            }
        });
        imgLightmeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "LightMeal");
                startActivity(intent);
            }
        });
        imgDessert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminAddNewProduct.class);
                intent.putExtra("category", "Dessert");
                startActivity(intent);
            }
        });


        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory.this, AdminNewOrdersActivity.class);
                startActivity(intent);
            }
        });
    }

}
