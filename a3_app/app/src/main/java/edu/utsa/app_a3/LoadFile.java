package edu.utsa.app_a3;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import edu.utsa.app_a3.model.Picture;

public abstract class LoadFile {

    // Method to load data from a file
    public static ArrayList<Picture> loadData(Context context) throws IOException {
        ArrayList<Picture> pictureList = new ArrayList<>();
        InputStream file = context.getAssets().open("pictureID.csv");
        Scanner scan = new Scanner(file);

        while (scan.hasNextLine()) {
            // Read each line of the file
            String pictureName = scan.nextLine().trim();

            // Get the resource ID for the picture
            int imageID = context.getResources().getIdentifier(pictureName.toLowerCase(), "drawable", context.getPackageName());

            // Add the picture to the list
            pictureList.add(new Picture(pictureName,imageID));
        }
        // Close the scanner
        return pictureList;
    }
}
