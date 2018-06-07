package com.jearomr.carmudiapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Metadata {

    @SerializedName("product_count")
    @Expose
    private String productCount;
    @SerializedName("results")
    @Expose
    private List<CarAd> carAds = null;

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public List<CarAd> getCarAds() {
        return carAds;
    }

    public void setCarAds(List<CarAd> carAds) {
        this.carAds = carAds;
    }

}
