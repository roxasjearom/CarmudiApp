package com.jearomr.carmudiapp.retrofit;

import com.jearomr.carmudiapp.models.CarCatalogResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("api/cars/page:{page}/maxitems:{maxItems}/")
    Call<CarCatalogResponse> getCarCatalog(@Path("page") int page, @Path("maxItems") int maxItems);

}
