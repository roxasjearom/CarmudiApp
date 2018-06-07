package com.jearomr.carmudiapp.datarepository;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.jearomr.carmudiapp.models.CarAd;
import com.jearomr.carmudiapp.models.CarCatalogResponse;
import com.jearomr.carmudiapp.retrofit.ApiInterface;
import com.jearomr.carmudiapp.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CarCatalogDataSource extends PageKeyedDataSource<Integer, CarAd> {

    private ApiInterface apiInterface;

    public CarCatalogDataSource(){
        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, CarAd> callback) {
        Call<CarCatalogResponse> catalogResponseCall = apiInterface.getCarCatalog(1, params.requestedLoadSize);

        try {
            Response<CarCatalogResponse> response = catalogResponseCall.execute();

            if(response!=null){
                callback.onResult(response.body().getMetadata().getCarAds(), 1, 2);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, CarAd> callback) {
        Call<CarCatalogResponse> catalogResponseCall = apiInterface.getCarCatalog(params.key, params.requestedLoadSize);

        try {
            Response<CarCatalogResponse> response = catalogResponseCall.execute();

            if(response!=null){
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                callback.onResult(response.body().getMetadata().getCarAds(), adjacentKey);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, CarAd> callback) {
        Call<CarCatalogResponse> catalogResponseCall = apiInterface.getCarCatalog(params.key, params.requestedLoadSize);

        try {
            Response<CarCatalogResponse> response = catalogResponseCall.execute();

            if(response!=null){
                callback.onResult(response.body().getMetadata().getCarAds(), params.key + 1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
