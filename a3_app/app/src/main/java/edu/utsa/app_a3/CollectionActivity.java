package edu.utsa.app_a3;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.utsa.app_a3.model.Picture;

public class CollectionActivity extends AppCompatActivity {
    // Declare variables
    ArrayList<Picture> pictureList;
    private Button returnButton;
    private ViewPager2 viewPager;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    private String userKey;

    // Swipe variables
    private int previousPosition = 0;
    private long swipeStartTime;
    private float startX;
    private float endX;
    private int swipeCount = 0;

    // Create the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Set up the activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_activity);

        // Initialize variables
        viewPager = findViewById(R.id.collectionViewPager);
        returnButton = findViewById(R.id.returnButton);

        // Initialize Firebase
        db = FirebaseDatabase.getInstance();
        mDatabase = db.getReference("swipeLog");

        // Get the user key from the intent
        userKey = mDatabase.push().getKey();

        try {
            // load the data from the file
            pictureList = LoadFile.loadData(this);

            // set up the adapter
            RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, pictureList);
            viewPager.setAdapter(recyclerViewAdapter);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Set up the return button
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CollectionActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    // Handle swipe events
    @Override
    public boolean dispatchTouchEvent (MotionEvent event) {
        // Get the current position of the view pager
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                swipeStartTime = System.currentTimeMillis();
                break;
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                long swipeEndTime = System.currentTimeMillis();
                long duration = swipeEndTime - swipeStartTime;

                float distance = Math.abs(endX - startX);
                float velocity = distance / duration;

                String swipeDirection = endX > startX ? "Right" : "Left";
                swipeCount++;

                // Create a map to store the swipe data
                Map<String, Object> swipeData = new HashMap<>();
                swipeData.put("x axis start", startX);
                swipeData.put("x axis end", endX);
                swipeData.put("swipe direction", swipeDirection);
                swipeData.put("velocity", velocity);
                swipeData.put("distance", distance);
                swipeData.put("duration", duration);
                swipeData.put("timestamp", System.currentTimeMillis());

                // Store the swipe data in the database and show a toast message
                mDatabase.child("User: " + userKey).child("Swipe " + swipeCount).setValue(swipeData);
                Toast.makeText(this, "Swiped " + swipeDirection, Toast.LENGTH_SHORT).show();
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
