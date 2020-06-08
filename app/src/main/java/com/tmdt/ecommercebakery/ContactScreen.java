package com.tmdt.ecommercebakery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ContactScreen extends AppCompatActivity {

    private String  Name, Comment, saveCurrentDate, saveCurrentTime;
    private Button SubmitBtn;
    private ImageButton BackButton;
    private EditText InputContactName, InputContactComment;

    private String cmtRandomKey;
    private DatabaseReference cmtRef;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contact_screen);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }






        cmtRef = FirebaseDatabase.getInstance().getReference().child("Comment");


        SubmitBtn = findViewById(R.id.btn_submit);
        InputContactName = findViewById(R.id.edt_name_cmt);
        InputContactComment = findViewById(R.id.edt_comment);

        BackButton = findViewById(R.id.back);
        loadingBar = new ProgressDialog(this);




        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VadidateContactData();
            }
        });

        //Button back to main
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ContactScreen.this, Home.class);
                startActivity(intent);
                finish();
            }
        });
    }





    private void VadidateContactData(){
        Comment = InputContactComment.getText().toString();
        Name = InputContactName.getText().toString();


        if(TextUtils.isEmpty(Comment)){
            Toast.makeText(this, "Please write your comment!!!!!", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(Name)){
            Toast.makeText(this, "Please write your name!!!!!", Toast.LENGTH_SHORT).show();
        }
        else {
            StoreContactInformation();
        }

    }


    private void StoreContactInformation() {

        //Loading Bar
        loadingBar.setTitle("Submit Successfully!!!");
        loadingBar.setMessage("Hello, please wait while we are submit the new comment.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        //Date Time
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        cmtRandomKey = saveCurrentDate   +   saveCurrentTime;


        SaveContactInfoToDatabase();


    }
    private void SaveContactInfoToDatabase(){
        HashMap<String, Object> cmtMap = new HashMap<>();
        cmtMap.put("pid", cmtRandomKey);
        cmtMap.put("cmtName", Name);
        cmtMap.put("cmtComment", Comment);
        cmtMap.put("date", saveCurrentDate);
        cmtMap.put("time", saveCurrentTime);



        cmtRef.child(cmtRandomKey).updateChildren(cmtMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(ContactScreen.this, Home.class);
                            startActivity(intent);
                            Toast.makeText(ContactScreen.this," Comment is submit successfully !!!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(ContactScreen.this, "Error: " + message, Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
