package com.jearomr.carmudiapp.car_ad_details_page;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jearomr.carmudiapp.R;
import com.jearomr.carmudiapp.Utils;
import com.jearomr.carmudiapp.models.CarAd;
import com.jearomr.carmudiapp.models.Image;

import java.util.List;

import static com.jearomr.carmudiapp.car_catalog_page.CarCatalogActivity.SELECTED_CAR_AD;

/**
 * Page for displaying the Car Ad details.
 * Uses an MVP approach here. The selectedCarAd object act as the data source here,
 * since we do not have another source(e.g. network repository) for the details.
 */
public class CarAdDetailsActivity extends AppCompatActivity implements CarAdDetailsContract.View{

    private ViewPager vpImages;

    private CarAd selectedCarAd;

    private TextView tvCarName, tvCarPrice, tvBuildYearValue, tvEngineValue, tvPriceConditionValue,
            tvColorFamilyValue, tvDoorsValue, tvDriveTypeValue, tvWarrantyTypeValue, tvDescriptionValue;

    private CarAdDetailsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_details);

        findViews();

        getSelectedCarAd();

        presenter = new CarAdDetailsPresenter(this, selectedCarAd);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadDetails();
        presenter.loadImages();
    }

    private void getSelectedCarAd(){
        if(getIntent()!=null){
            selectedCarAd = getIntent().getParcelableExtra(SELECTED_CAR_AD);
        }
    }

    private void findViews(){
        vpImages = findViewById(R.id.vpImages);
        tvCarName = findViewById(R.id.tvCarName);
        tvCarPrice = findViewById(R.id.tvCarPrice);
        tvBuildYearValue = findViewById(R.id.tvBuildYearValue);
        tvEngineValue = findViewById(R.id.tvEngineValue);
        tvPriceConditionValue = findViewById(R.id.tvPriceConditionValue);
        tvColorFamilyValue = findViewById(R.id.tvColorFamilyValue);
        tvDoorsValue = findViewById(R.id.tvDoorsValue);
        tvDriveTypeValue = findViewById(R.id.tvDriveTypeValue);
        tvWarrantyTypeValue = findViewById(R.id.tvWarrantyTypeValue);
        tvDescriptionValue = findViewById(R.id.tvDescriptionValue);
    }

    @Override
    public void showImages(List<Image> carAdImages) {
        CarAdImageAdapter imageAdapter = new CarAdImageAdapter(carAdImages, this);
        vpImages.setAdapter(imageAdapter);
    }

    @Override
    public void showDetails(CarAd carAd) {
        tvCarName.setText(carAd.getData().getName());
        tvCarPrice.setText(Utils.formatPrice(carAd.getData().getPrice()));
        //Details from Attributes
        tvBuildYearValue.setText(carAd.getData().getAttributes().getYearBuilt());
        tvEngineValue.setText(carAd.getData().getAttributes().getEngine());
        tvPriceConditionValue.setText(carAd.getData().getAttributes().getPriceConditions());
        tvColorFamilyValue.setText(carAd.getData().getAttributes().getColorFamily());
        tvDoorsValue.setText(carAd.getData().getAttributes().getDoors());
        tvDriveTypeValue.setText(carAd.getData().getAttributes().getDriveType());
        tvWarrantyTypeValue.setText(carAd.getData().getAttributes().getWarrantyType());
        tvDescriptionValue.setText(carAd.getData().getAttributes().getDescription());

    }
}
