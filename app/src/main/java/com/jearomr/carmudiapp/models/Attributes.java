package com.jearomr.carmudiapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attributes implements Parcelable{

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("year_built")
    @Expose
    private String yearBuilt;
    @SerializedName("engine")
    @Expose
    private String engine;
    @SerializedName("price_conditions")
    @Expose
    private String priceConditions;
    @SerializedName("price_conditions_id")
    @Expose
    private String priceConditionsId;
    @SerializedName("color_family")
    @Expose
    private String colorFamily;
    @SerializedName("seats")
    @Expose
    private String seats;
    @SerializedName("doors")
    @Expose
    private String doors;
    @SerializedName("drive_type")
    @Expose
    private String driveType;
    @SerializedName("warranty_type")
    @Expose
    private String warrantyType;
    @SerializedName("warranty_years")
    @Expose
    private String warrantyYears;
    @SerializedName("warranty_kms")
    @Expose
    private String warrantyKms;

    public Attributes(String description, String yearBuilt, String engine, String priceConditions,
                      String priceConditionsId, String colorFamily, String seats, String doors,
                      String driveType, String warrantyType, String warrantyYears, String warrantyKms) {
        this.description = description;
        this.yearBuilt = yearBuilt;
        this.engine = engine;
        this.priceConditions = priceConditions;
        this.priceConditionsId = priceConditionsId;
        this.colorFamily = colorFamily;
        this.seats = seats;
        this.doors = doors;
        this.driveType = driveType;
        this.warrantyType = warrantyType;
        this.warrantyYears = warrantyYears;
        this.warrantyKms = warrantyKms;
    }

    protected Attributes(Parcel in) {
        description = in.readString();
        yearBuilt = in.readString();
        engine = in.readString();
        priceConditions = in.readString();
        priceConditionsId = in.readString();
        colorFamily = in.readString();
        seats = in.readString();
        doors = in.readString();
        driveType = in.readString();
        warrantyType = in.readString();
        warrantyYears = in.readString();
        warrantyKms = in.readString();
    }

    public static final Creator<Attributes> CREATOR = new Creator<Attributes>() {
        @Override
        public Attributes createFromParcel(Parcel in) {
            return new Attributes(in);
        }

        @Override
        public Attributes[] newArray(int size) {
            return new Attributes[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(String yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getPriceConditions() {
        return priceConditions;
    }

    public void setPriceConditions(String priceConditions) {
        this.priceConditions = priceConditions;
    }

    public String getPriceConditionsId() {
        return priceConditionsId;
    }

    public void setPriceConditionsId(String priceConditionsId) {
        this.priceConditionsId = priceConditionsId;
    }

    public String getColorFamily() {
        return colorFamily;
    }

    public void setColorFamily(String colorFamily) {
        this.colorFamily = colorFamily;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(String warrantyType) {
        this.warrantyType = warrantyType;
    }

    public String getWarrantyYears() {
        return warrantyYears;
    }

    public void setWarrantyYears(String warrantyYears) {
        this.warrantyYears = warrantyYears;
    }

    public String getWarrantyKms() {
        return warrantyKms;
    }

    public void setWarrantyKms(String warrantyKms) {
        this.warrantyKms = warrantyKms;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeString(yearBuilt);
        parcel.writeString(engine);
        parcel.writeString(priceConditions);
        parcel.writeString(priceConditionsId);
        parcel.writeString(colorFamily);
        parcel.writeString(seats);
        parcel.writeString(doors);
        parcel.writeString(driveType);
        parcel.writeString(warrantyType);
        parcel.writeString(warrantyYears);
        parcel.writeString(warrantyKms);
    }
}
