package com.jearomr.carmudiapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Parcelable{

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("price")
    @Expose
    private double price;

    @SerializedName("brand")
    @Expose
    private String brand;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("attributes")
    @Expose
    private Attributes attributes;

    public Data(String name, double price, String brand, String description, Attributes attributes) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.description = description;
        this.attributes = attributes;
    }

    protected Data(Parcel in) {
        name = in.readString();
        price = in.readDouble();
        brand = in.readString();
        description = in.readString();
        attributes = in.readParcelable(Attributes.class.getClassLoader());
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeDouble(price);
        parcel.writeString(brand);
        parcel.writeString(description);
        parcel.writeParcelable(attributes, i);
    }
}
