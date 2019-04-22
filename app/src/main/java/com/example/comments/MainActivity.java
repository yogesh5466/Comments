package com.example.comments;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef,myRef1;
    private List<comments> commentsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CommentAdapter mAdapter;
    ArrayList<User> u = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new CommentAdapter(commentsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Comment");
        myRef1=database.getReference("USER");

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!u.isEmpty()){
                    u.clear();
                }

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    u.add(user);
                    Log.d("msg1", user.Name);

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hellofail", "Failed to read value.", error.toException());
            }
        });

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!commentsList.isEmpty()){
                    commentsList.clear();
                }

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Comment comment = postSnapshot.getValue(Comment.class);
                    for(int i=0;i<u.size();i++){
                        if(comment.UniqueID==u.get(i).UniqueID){
                            comments c = new comments(u.get(i).Name,comment.CreateDate,comment.Commenttext);
                            commentsList.add(c);
                            Log.d("msg", u.get(i).Name);
                            Log.d("msg", comment.CreateDate);
                            Log.d("msg", comment.Commenttext);
                        }
                    }


                }

                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hellofail", "Failed to read value.", error.toException());
            }
        });


    }
}
