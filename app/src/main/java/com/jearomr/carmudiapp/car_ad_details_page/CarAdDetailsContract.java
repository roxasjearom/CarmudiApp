package com.jearomr.carmudiapp.car_ad_details_page;

import com.jearomr.carmudiapp.models.CarAd;
import com.jearomr.carmudiapp.models.Image;

import java.util.List;

public interface CarAdDetailsContract {

    interface View{
        void showImages(List<Image> carAdImages);
        void showDetails(CarAd carAd);
    }

    interface Presenter{
        void loadDetails();
        void loadImages();
    }

}
