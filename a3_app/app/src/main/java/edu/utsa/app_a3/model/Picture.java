package edu.utsa.app_a3.model;

public class Picture {
    private String pictureName;
    private int imageID;

    // Constructor
    public Picture(String pictureName, int imageID) {
        this.pictureName = pictureName;
        this.imageID = imageID;
    }

    // Getters
    public String getPictureName() {
        return pictureName;
    }

    public int getImageID() {
        return imageID;
    }
}
