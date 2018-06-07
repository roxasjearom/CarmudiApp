package com.jearomr.carmudiapp.datarepository;


import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.jearomr.carmudiapp.models.CarAd;

public class CarCatalogDataFactory extends DataSource.Factory<Integer, CarAd> {

    public MutableLiveData<CarCatalogDataSource> carCatalogMutableLiveData = new MutableLiveData<>();

    @Override
    public DataSource<Integer, CarAd> create() {
        CarCatalogDataSource dataSource = new CarCatalogDataSource();
        carCatalogMutableLiveData.postValue(dataSource);

        return dataSource;
    }
}
