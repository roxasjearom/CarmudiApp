package com.jearomr.carmudiapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarAd {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}
