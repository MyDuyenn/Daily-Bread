package com.tmdt.ecommercebakery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tmdt.ecommercebakery.Model.Comment;
import com.tmdt.ecommercebakery.Prevalent.Prevalent;
import com.tmdt.ecommercebakery.ViewHolder.CommentViewHolder;

public class AdminContactScreen extends AppCompatActivity {

    private DatabaseReference cmtRef;
    private RecyclerView recyclerView;
    ImageButton imageButton;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_contact_screen);



        cmtRef = FirebaseDatabase.getInstance().getReference().child("Comment");

        recyclerView = findViewById(R.id.comment_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        imageButton = findViewById(R.id.back);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminContactScreen.this, AdminHome.class);
                startActivity(intent);
            }
        });



    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Comment> options =
                new FirebaseRecyclerOptions.Builder<Comment>()
                        .setQuery(cmtRef, Comment.class)
                        .build();



        FirebaseRecyclerAdapter<Comment, CommentViewHolder> adapter =
                new FirebaseRecyclerAdapter<Comment, CommentViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull CommentViewHolder holder, final int position, @NonNull final Comment model) {
                        holder.txtName.setText(model.getCmtName());
                        holder.txtComemt.setText(model.getCmtComment());
                        holder.txtTime.setText(model.getTime());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                CharSequence options [] = new CharSequence[]
                                        {
                                                "Yes",
                                                "No"
                                        };
                                AlertDialog.Builder builder = new AlertDialog.Builder(AdminContactScreen.this);
                                builder.setTitle("Delete???");

                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        if (i == 0)
                                        {
                                            String uID = getRef(position).getKey();
                                            RemoveOrder(uID);
                                        }
                                        else
                                        {
                                            finish();
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_layout, parent, false);
                        CommentViewHolder holder = new CommentViewHolder(view );
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }
    private void RemoveOrder(String uID) {
        cmtRef.child(uID).removeValue();

    }

}
