package com.jearomr.carmudiapp.datarepository;


import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.content.Context;

import com.jearomr.carmudiapp.models.CarAd;

/**
 * Data source creation for the Car Ads.
 */
public class CarCatalogDataFactory extends DataSource.Factory<Integer, CarAd> {

    public MutableLiveData<CarCatalogDataSource> carCatalogMutableLiveData;

    private Context context;

    CarCatalogDataFactory(Context context) {
        this.context = context;
    }

    @Override
    public DataSource<Integer, CarAd> create() {
        CarCatalogDataSource dataSource = new CarCatalogDataSource(context);
        carCatalogMutableLiveData = new MutableLiveData<>();
        carCatalogMutableLiveData.postValue(dataSource);

        return dataSource;
    }
}
