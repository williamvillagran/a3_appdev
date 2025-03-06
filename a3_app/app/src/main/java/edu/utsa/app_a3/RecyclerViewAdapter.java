package edu.utsa.app_a3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.utsa.app_a3.model.Picture;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    ArrayList<Picture> pictureList;
    private Context context;

    // Constructor
    public RecyclerViewAdapter(Context context, ArrayList<Picture> pictureList) {
        this.pictureList = pictureList;
        this.context = context;
    }

    // Create new views
    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.picture_card, parent, false);
        return new MyViewHolder(view);
    }

    // Bind data to views
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        Picture picture = pictureList.get(position);
        holder.textView.setText(picture.getPictureName());
        holder.imageView.setImageResource(picture.getImageID());

    }

    // Return the number of items in the list
    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    // ViewHolder class
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        // Constructor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imagePicture);
            textView = itemView.findViewById(R.id.textPicName);
        }
    }
}
