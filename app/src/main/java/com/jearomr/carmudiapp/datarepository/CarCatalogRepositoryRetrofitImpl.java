package com.jearomr.carmudiapp.datarepository;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.jearomr.carmudiapp.models.CarAd;

public class CarCatalogRepositoryRetrofitImpl implements CarCatalogRepository {

    CarCatalogDataFactory dataFactory;
    private static final int MAX_ITEMS = 30;
    private LiveData<PagedList<CarAd>> carAds;

    public CarCatalogRepositoryRetrofitImpl() {
        dataFactory = new CarCatalogDataFactory();
    }

    @Override
    public LiveData<PagedList<CarAd>> getCarCatalog() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(MAX_ITEMS)
                .setPageSize(MAX_ITEMS)
                .build();

        carAds = new LivePagedListBuilder(dataFactory, config)
                .setInitialLoadKey(1)
                .build();

        return carAds;
    }
}
