package com.jearomr.carmudiapp.carcatalogpage;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jearomr.carmudiapp.R;
import com.jearomr.carmudiapp.datarepository.CarCatalogViewModel;
import com.jearomr.carmudiapp.models.CarAd;

public class CarCatalogActivity extends AppCompatActivity {

    private RecyclerView rvCarCatalog;

    private CarCatalogAdapter carCatalogAdapter;

    private CarCatalogViewModel carCatalogViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        rvCarCatalog = findViewById(R.id.rvCarCatalog);

        setRecyclerView();

        carCatalogViewModel = ViewModelProviders.of(this).get(CarCatalogViewModel.class);
        carCatalogViewModel.getCarCatalog().observe(this, new Observer<PagedList<CarAd>>() {
            @Override
            public void onChanged(@Nullable PagedList<CarAd> carAds) {
                carCatalogAdapter.submitList(carAds);
            }
        });
    }

    private void setRecyclerView(){
        carCatalogAdapter = new CarCatalogAdapter(this);
        rvCarCatalog.setAdapter(carCatalogAdapter);
        rvCarCatalog.setLayoutManager(new LinearLayoutManager(this));
    }
}
