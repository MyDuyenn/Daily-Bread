package com.tmdt.ecommercebakery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class AdminAddEvent extends AppCompatActivity {

    private String  Description, PName, saveCurrentDate, saveCurrentTime;
    private Button AddEventButton;
    private ImageButton BackButton;
    private EditText InputEventName, InputEventDescription;
    private ImageView InputEventImage;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String eventRandomKey, downloadImageUrl;
    private StorageReference EventImagesRef;
    private DatabaseReference EventRef;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_event);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        EventImagesRef = FirebaseStorage.getInstance().getReference().child("Event Images");
        EventRef = FirebaseDatabase.getInstance().getReference().child("Event");


        AddEventButton = findViewById(R.id.add_new_event);
        InputEventImage = findViewById(R.id.select_event_image);
        InputEventName = findViewById(R.id.edtEventName);
        InputEventDescription = findViewById(R.id.edtEventDescription);

        BackButton = findViewById(R.id.back);

        loadingBar = new ProgressDialog(this);

        //Gọi mở Gallery
        InputEventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });


        AddEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VadidateEventData();
            }
        });

        //Button back to main
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminAddEvent.this, AdminHome.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //Hàm OpenGalary
    private void OpenGallery(){
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    //Hàm trả về kết quả
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick && resultCode==RESULT_OK && data!=null){
            ImageUri = data.getData();
            InputEventImage.setImageURI(ImageUri);
        }
    }

    private void VadidateEventData(){
        Description = InputEventDescription.getText().toString();
        PName = InputEventName.getText().toString();

        if (ImageUri == null)
        {
            Toast.makeText(this, "Image Event", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(Description)){
            Toast.makeText(this, "Please write event description", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(PName)){
            Toast.makeText(this, "Please write event name", Toast.LENGTH_SHORT).show();
        }
        else {
            StoreEventInformation();
        }

    }


    private void StoreEventInformation() {

        //Loading Bar
        loadingBar.setTitle("Add New Event");
        loadingBar.setMessage("Hello Admin, please wait while we are adding the new event.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();
        //Date Time
        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        eventRandomKey = saveCurrentDate   +   saveCurrentTime;

        final StorageReference filePath = EventImagesRef.child(ImageUri.getLastPathSegment() + eventRandomKey + "jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message = e.toString();
                Toast.makeText(AdminAddEvent.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AdminAddEvent.this, "Event Image UpLoad Successfully !!!", Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();

                        }
                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();
                            Toast.makeText(AdminAddEvent.this," Get Event image Url Successfully !!!", Toast.LENGTH_SHORT).show();

                            SaveEventInfoToDatabase();
                        }
                    }
                });
            }
        });

    }
    private void SaveEventInfoToDatabase(){
        HashMap<String, Object> eventMap = new HashMap<>();
        eventMap.put("pid", eventRandomKey);
        eventMap.put("eventName", PName);
        eventMap.put("eventDescription", Description);
        eventMap.put("eventImage", downloadImageUrl);
        eventMap.put("date", saveCurrentDate);
        eventMap.put("time", saveCurrentTime);



        EventRef.child(eventRandomKey).updateChildren(eventMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(AdminAddEvent.this, AdminHome.class);
                            startActivity(intent);
                            Toast.makeText(AdminAddEvent.this," Event is added successfully !!!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AdminAddEvent.this, "Error: " + message, Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
