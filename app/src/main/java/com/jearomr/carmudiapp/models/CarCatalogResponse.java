package com.jearomr.carmudiapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarCatalogResponse {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}
