package com.jearomr.carmudiapp.datarepository;

import android.arch.paging.PageKeyedDataSource;
import android.content.Context;
import android.support.annotation.NonNull;

import com.jearomr.carmudiapp.Utils;
import com.jearomr.carmudiapp.datarepository.localdb.LocalDbRepository;
import com.jearomr.carmudiapp.datarepository.localdb.LocalDbRepositoryImpl;
import com.jearomr.carmudiapp.models.CarAd;
import com.jearomr.carmudiapp.models.CarCatalogResponse;
import com.jearomr.carmudiapp.retrofit.ApiInterface;
import com.jearomr.carmudiapp.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Data source that handles the request of data from a source (from network or from local DB).
 */
public class CarCatalogDataSource extends PageKeyedDataSource<Integer, CarAd> {

    private ApiInterface apiInterface;

    private LocalDbRepository localDbRepository;

    private Context context;

    CarCatalogDataSource(Context context){
        this.context = context;
        apiInterface = RetrofitClient.getClient().create(ApiInterface.class);
        localDbRepository = new LocalDbRepositoryImpl(context);
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, CarAd> callback) {
        Call<CarCatalogResponse> catalogResponseCall = apiInterface.getCarCatalog(1, params.requestedLoadSize);
        try {
            //Load offline data if device is offline
            if(Utils.isOffline(context)){
                callback.onResult(localDbRepository.getCarAds(1), null, 2);
                return;
            }

            Response<CarCatalogResponse> response = catalogResponseCall.execute();

            if(response!=null){
                callback.onResult(response.body().getMetadata().getCarAds(), null, 2);
                saveToLocalDb(response.body().getMetadata().getCarAds(), 1);
            }

        } catch (Exception e){
            e.printStackTrace();
            callback.onResult(localDbRepository.getCarAds(1), null, 2);
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, CarAd> callback) {
        Call<CarCatalogResponse> catalogResponseCall = apiInterface.getCarCatalog(params.key, params.requestedLoadSize);

        try {
            Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
            if(Utils.isOffline(context)){
                callback.onResult(localDbRepository.getCarAds(params.key), adjacentKey);
                return;
            }

            Response<CarCatalogResponse> response = catalogResponseCall.execute();

            if(response!=null){
                callback.onResult(response.body().getMetadata().getCarAds(), adjacentKey);
                saveToLocalDb(response.body().getMetadata().getCarAds(), params.key);
            }

        }catch (Exception e){
            e.printStackTrace();
            Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
            callback.onResult(localDbRepository.getCarAds(params.key), adjacentKey);
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, CarAd> callback) {
        Call<CarCatalogResponse> catalogResponseCall = apiInterface.getCarCatalog(params.key, params.requestedLoadSize);

        try {
            if(Utils.isOffline(context)){
                callback.onResult(localDbRepository.getCarAds(params.key), params.key + 1);
                return;
            }
            Response<CarCatalogResponse> response = catalogResponseCall.execute();

            if(response!=null){
                callback.onResult(response.body().getMetadata().getCarAds(), params.key + 1);
                saveToLocalDb(response.body().getMetadata().getCarAds(), params.key);
            }

        }catch (Exception e){
            e.printStackTrace();
            callback.onResult(localDbRepository.getCarAds(params.key), params.key + 1);
        }
    }

    /**
     * Used for saving the Car Ads that were retrieved from network for offline reference.
     * @param carAds CarAd object from network
     * @param pageKey Page key from the API
     */
    private void saveToLocalDb(List<CarAd> carAds, int pageKey){
        for (CarAd carAd:carAds){
            carAd.setPageKey(pageKey);
            localDbRepository.saveCarAd(carAd);
        }
    }
}
