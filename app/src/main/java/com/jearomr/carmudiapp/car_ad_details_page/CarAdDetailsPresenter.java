package com.jearomr.carmudiapp.car_ad_details_page;

import com.jearomr.carmudiapp.models.CarAd;

public class CarAdDetailsPresenter implements CarAdDetailsContract.Presenter {

    private CarAdDetailsContract.View view;

    //We can have a repository here for fetching more details,
    // but since the details are already included from the first API call,
    // I only get the data from the selected Car Ad.
    private CarAd selectedCarAd;

    CarAdDetailsPresenter(CarAdDetailsContract.View view, CarAd selectedCarAd) {
        this.view = view;
        this.selectedCarAd = selectedCarAd;
    }

    @Override
    public void loadDetails() {
        view.showDetails(selectedCarAd);
    }

    @Override
    public void loadImages() {
        view.showImages(selectedCarAd.getImages());
    }
}
