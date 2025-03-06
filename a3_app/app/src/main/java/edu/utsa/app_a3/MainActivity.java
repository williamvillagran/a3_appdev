//
// Victor Garcia - bfo155
//
package edu.utsa.app_a3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Creates the main activity screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Creates the main activity screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // Creates fade into screen effect
        TextView welcomeText = findViewById(R.id.textView);
        welcomeText.setAlpha(0f);
        welcomeText.animate().alpha(1f).setDuration(1500);

        // Creates fade into screen effect
        Button beginButton = findViewById(R.id.beginButton);
        beginButton.setAlpha(0f);
        beginButton.animate().alpha(1f).setDuration(1500);

        // Creates fade into screen effect
        ImageView globeImage = findViewById(R.id.globeImage);
        globeImage.setAlpha(0f);
        globeImage.animate().alpha(1f).setDuration(1500);


        // Creates the button function to move to the Scroll Activity
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });

    }

}
