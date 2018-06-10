package com.jearomr.carmudiapp.datarepository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.jearomr.carmudiapp.models.CarAd;

public interface CarCatalogRepository {

    LiveData<PagedList<CarAd>> getCarCatalog();

    CarCatalogDataFactory getCarCatalogDataFactory();

}
