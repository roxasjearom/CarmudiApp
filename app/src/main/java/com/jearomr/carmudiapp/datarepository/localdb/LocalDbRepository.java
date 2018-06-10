package com.jearomr.carmudiapp.datarepository.localdb;

import com.jearomr.carmudiapp.models.Attributes;
import com.jearomr.carmudiapp.models.CarAd;
import com.jearomr.carmudiapp.models.Data;
import com.jearomr.carmudiapp.models.Image;

import java.util.List;

public interface LocalDbRepository {

    void saveCarAd(CarAd carAd);

    void saveCarAdData(int carAdId, Data data);

    void saveCarAdAttributes(int carAdId, Attributes attributes);

    void saveCarAdImages(int carAdId, List<Image> images);

    List<CarAd> getCarAds(int pageKey);

    Data getCarAdData(int carAdId);

    Attributes getCarAdAttributes(int carAdId);

    List<Image> getCarAdImages(int carAdId);

}
