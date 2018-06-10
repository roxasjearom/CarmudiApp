package com.jearomr.carmudiapp.datarepository;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.content.Context;

import com.jearomr.carmudiapp.models.CarAd;

import static android.arch.lifecycle.Transformations.switchMap;

public class CarCatalogRepositoryRetrofitImpl implements CarCatalogRepository {

    private CarCatalogDataFactory dataFactory;
    private static final int INITIAL_ITEMS = 50;
    private static final int MAX_ITEMS = 30;

    CarCatalogRepositoryRetrofitImpl(Context context) {
        dataFactory = new CarCatalogDataFactory(context);
    }

    @Override
    public LiveData<PagedList<CarAd>> getCarCatalog() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setInitialLoadSizeHint(INITIAL_ITEMS)
                .setPageSize(MAX_ITEMS)
                .build();

        return new LivePagedListBuilder(dataFactory, config)
                .setInitialLoadKey(1)
                .build();
    }

    @Override
    public CarCatalogDataFactory getCarCatalogDataFactory() {
        return dataFactory;
    }
}
