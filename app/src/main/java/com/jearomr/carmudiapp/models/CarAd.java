package com.jearomr.carmudiapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CarAd implements Parcelable{

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("images")
    @Expose
    private List<Image> images;

    private int pageKey; //Reference for the source page key

    public CarAd(Data data, int id, List<Image> images) {
        this.data = data;
        this.id = id;
        this.images = images;
    }

    protected CarAd(Parcel in) {
        data = in.readParcelable(Data.class.getClassLoader());
        id = in.readInt();
        images = in.createTypedArrayList(Image.CREATOR);
        pageKey = in.readInt();
    }

    public static final Creator<CarAd> CREATOR = new Creator<CarAd>() {
        @Override
        public CarAd createFromParcel(Parcel in) {
            return new CarAd(in);
        }

        @Override
        public CarAd[] newArray(int size) {
            return new CarAd[size];
        }
    };

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

    public int getPageKey() {
        return pageKey;
    }

    public void setPageKey(int pageKey) {
        this.pageKey = pageKey;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(data, i);
        parcel.writeInt(id);
        parcel.writeTypedList(images);
        parcel.writeInt(pageKey);
    }
}
