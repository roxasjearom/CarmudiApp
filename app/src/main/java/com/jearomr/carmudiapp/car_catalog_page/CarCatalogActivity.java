package com.jearomr.carmudiapp.car_catalog_page;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jearomr.carmudiapp.R;
import com.jearomr.carmudiapp.car_ad_details_page.CarAdDetailsActivity;
import com.jearomr.carmudiapp.datarepository.CarCatalogDataFactory;
import com.jearomr.carmudiapp.datarepository.CarCatalogViewModel;
import com.jearomr.carmudiapp.models.CarAd;

/**
 * Main page of the app which displays Car Ads.
 * Handles the updating of the content using Google's Paging Library.
 * Since the data needs to be observed within a Lifecycle-aware class, I didn't implement a MVP approach here.
 */
public class CarCatalogActivity extends AppCompatActivity {

    private RecyclerView rvCarCatalog;

    private SwipeRefreshLayout refreshLayout;

    private CarCatalogAdapter carCatalogAdapter;

    private CarCatalogViewModel carCatalogViewModel;

    public static final String SELECTED_CAR_AD = "SELECTED_CAR_AD";
    private static final String RECYCLER_VIEW_STATE = "RECYCLER_VIEW_STATE";

    private Parcelable recyclerViewState;

    private RecyclerView.LayoutManager layoutManager;

    private CarCatalogDataFactory dataFactory;

    private ConstraintLayout errorMessageContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_list);

        carCatalogViewModel = ViewModelProviders.of(this).get(CarCatalogViewModel.class);
        dataFactory = carCatalogViewModel.getDataFactory();

        findViews();

        setRefreshLayoutListener();

        setRecyclerView();

        setDataSourceObserver();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        recyclerViewState = layoutManager.onSaveInstanceState();
        outState.putParcelable(RECYCLER_VIEW_STATE, recyclerViewState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            recyclerViewState = savedInstanceState.getParcelable(RECYCLER_VIEW_STATE);
        }
    }

    private void findViews(){
        rvCarCatalog = findViewById(R.id.rvCarCatalog);
        refreshLayout = findViewById(R.id.refreshLayout);
        errorMessageContainer = findViewById(R.id.errorMessageContainer);
    }

    private void setRefreshLayoutListener(){
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //force an invalidate call to clear the current page list
                dataFactory.carCatalogMutableLiveData.getValue().invalidate();
                refreshLayout.setRefreshing(false);
            }
        });
    }

    private void setDataSourceObserver(){
        //Updates the adapter to display new content from the live data
        carCatalogViewModel.getCarCatalog().observe(this, new Observer<PagedList<CarAd>>() {
            @Override
            public void onChanged(@Nullable PagedList<CarAd> carAds) {
                if (carAds != null) {
                    if(carAds.size()==0){
                        errorMessageContainer.setVisibility(View.VISIBLE);
                    }else{
                        errorMessageContainer.setVisibility(View.GONE);
                    }
                    carCatalogAdapter.submitList(carAds);
                    restoreRecyclerViewState();
                }

            }
        });
    }

    private void restoreRecyclerViewState(){
        if(recyclerViewState!=null){
            layoutManager.onRestoreInstanceState(recyclerViewState);
        }
    }

    private void setRecyclerView(){
        carCatalogAdapter = new CarCatalogAdapter(this, onItemSelectedCallback);
        rvCarCatalog.setAdapter(carCatalogAdapter);
        layoutManager = new LinearLayoutManager(this);
        rvCarCatalog.setLayoutManager(layoutManager);
    }

    private OnItemSelectedCallback onItemSelectedCallback = new OnItemSelectedCallback() {
        @Override
        public void onItemSelected(CarAd selectedCarAd) {
            showDetailsPage(selectedCarAd);
        }
    };

    private void showDetailsPage(CarAd selectedCarAd) {
        Intent intent = new Intent(CarCatalogActivity.this, CarAdDetailsActivity.class);
        intent.putExtra(SELECTED_CAR_AD, selectedCarAd);
        startActivity(intent);
    }

    public interface OnItemSelectedCallback{
        void onItemSelected(CarAd selectedCarAd);
    }
}
