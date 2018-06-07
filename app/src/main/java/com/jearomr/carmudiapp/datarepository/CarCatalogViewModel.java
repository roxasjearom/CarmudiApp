package com.jearomr.carmudiapp.datarepository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.jearomr.carmudiapp.models.CarAd;

public class CarCatalogViewModel extends AndroidViewModel {

    private CarCatalogRepository carCatalogRepository;

    public CarCatalogViewModel(@NonNull Application application) {
        super(application);

        carCatalogRepository = new CarCatalogRepositoryRetrofitImpl();
    }

    public LiveData<PagedList<CarAd>> getCarCatalog(){
        return carCatalogRepository.getCarCatalog();
    }
}
