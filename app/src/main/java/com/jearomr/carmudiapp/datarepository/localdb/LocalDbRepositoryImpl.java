package com.jearomr.carmudiapp.datarepository.localdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jearomr.carmudiapp.models.Attributes;
import com.jearomr.carmudiapp.models.CarAd;
import com.jearomr.carmudiapp.models.Data;
import com.jearomr.carmudiapp.models.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of local database repository which handles the saving and retrieving of offline data.
 */
public class LocalDbRepositoryImpl implements LocalDbRepository {

    private SQLiteDatabase db;

    public LocalDbRepositoryImpl(Context context) {
        db = DbHelper.getDbHelperInstance(context).getWritableDatabase();

    }

    @Override
    public void saveCarAd(CarAd carAd) {
        try {
            ContentValues values = new ContentValues();
            values.put(DbContract.DbCarAds._ID, carAd.getId());
            values.put(DbContract.DbCarAds.COLUMN_PAGE_KEY, carAd.getPageKey());

            db.replace(DbContract.DbCarAds.TABLE_CAR_ADS, null, values);

            saveCarAdData(carAd.getId(), carAd.getData());
            saveCarAdAttributes(carAd.getId(), carAd.getData().getAttributes());
            saveCarAdImages(carAd.getId(), carAd.getImages());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveCarAdData(int carAdId, Data data) {
        try {
            ContentValues values = new ContentValues();
            values.put(DbContract.DbData._ID, carAdId);
            values.put(DbContract.DbData.COLUMN_NAME, data.getName());
            values.put(DbContract.DbData.COLUMN_PRICE, data.getPrice());
            values.put(DbContract.DbData.COLUMN_BRAND, data.getBrand());
            values.put(DbContract.DbData.COLUMN_DESCRIPTION, data.getDescription());

            db.replace(DbContract.DbData.TABLE_CAR_AD_DATA, null, values);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveCarAdAttributes(int carAdId, Attributes attributes) {
        try {
            ContentValues values = new ContentValues();
            values.put(DbContract.DbAttributes._ID, carAdId);
            values.put(DbContract.DbAttributes.COLUMN_DESCRIPTION, attributes.getDescription());
            values.put(DbContract.DbAttributes.COLUMN_YEAR_BUILT, attributes.getYearBuilt());
            values.put(DbContract.DbAttributes.COLUMN_ENGINE, attributes.getEngine());
            values.put(DbContract.DbAttributes.COLUMN_PRICE_CONDITIONS, attributes.getPriceConditions());
            values.put(DbContract.DbAttributes.COLUMN_PRICE_CONDITIONS_ID, attributes.getPriceConditionsId());
            values.put(DbContract.DbAttributes.COLUMN_COLOR_FAMILY, attributes.getColorFamily());
            values.put(DbContract.DbAttributes.COLUMN_SEATS, attributes.getSeats());
            values.put(DbContract.DbAttributes.COLUMN_DOORS, attributes.getDoors());
            values.put(DbContract.DbAttributes.COLUMN_DRIVE_TYPE, attributes.getDriveType());
            values.put(DbContract.DbAttributes.COLUMN_WARRANTY_TYPE, attributes.getWarrantyType());
            values.put(DbContract.DbAttributes.COLUMN_WARRANTY_YEARS, attributes.getWarrantyYears());
            values.put(DbContract.DbAttributes.COLUMN_WARRANTY_KMS, attributes.getWarrantyKms());

            db.replace(DbContract.DbAttributes.TABLE_CAR_AD_ATRRIBUTES, null, values);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveCarAdImages(int carAdId, List<Image> images) {
        try {
            for(Image image:images){
                ContentValues values = new ContentValues();
                values.put(DbContract.DbImages._ID, carAdId);
                values.put(DbContract.DbImages.COLUMN_URL, image.getUrl());

                db.insert(DbContract.DbImages.TABLE_IMAGES, null, values);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<CarAd> getCarAds(int pageKey) {
        List<CarAd> carAds = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + DbContract.DbCarAds.TABLE_CAR_ADS +
                    " WHERE " + DbContract.DbCarAds.COLUMN_PAGE_KEY + "=?";
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(pageKey)});
            while (cursor.moveToNext()){
                int carAdId = cursor.getInt(DbContract.DbCarAds.COLUMN_INDEX_ID);
                Data data = getCarAdData(carAdId);
                List<Image> images = getCarAdImages(carAdId);
                CarAd carAd = new CarAd(data, carAdId, images);

                carAds.add(carAd);
            }
            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return carAds;
    }

    @Override
    public Data getCarAdData(int carAdId) {
        Data data = null;
        try {
            String query = "SELECT * FROM " + DbContract.DbData.TABLE_CAR_AD_DATA +
                    " WHERE " + DbContract.DbData._ID + "=?";
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(carAdId)});
            if(cursor.moveToFirst()){
                Attributes attributes = getCarAdAttributes(carAdId);
                data = new Data(
                        cursor.getString(DbContract.DbData.COLUMN_INDEX_NAME),
                        cursor.getDouble(DbContract.DbData.COLUMN_INDEX_PRICE),
                        cursor.getString(DbContract.DbData.COLUMN_INDEX_BRAND),
                        cursor.getString(DbContract.DbData.COLUMN_INDEX_DESCRIPTION),
                        attributes);
            }
            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public Attributes getCarAdAttributes(int carAdId) {
        Attributes attributes = null;
        try {
            String query = "SELECT * FROM " + DbContract.DbAttributes.TABLE_CAR_AD_ATRRIBUTES +
                    " WHERE " + DbContract.DbAttributes._ID + "=?";
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(carAdId)});

            if (cursor.moveToFirst()){
                attributes = new Attributes(
                        cursor.getString(DbContract.DbAttributes.COLUMN_INDEX_DESCRIPTION),
                        cursor.getString(DbContract.DbAttributes.COLUMN_INDEX_YEAR_BUILT),
                        cursor.getString(DbContract.DbAttributes.COLUMN_INDEX_ENGINE),
                        cursor.getString(DbContract.DbAttributes.COLUMN_INDEX_PRICE_CONDITIONS),
                        cursor.getString(DbContract.DbAttributes.COLUMN_INDEX_PRICE_CONDITIONS_ID),
                        cursor.getString(DbContract.DbAttributes.COLUMN_INDEX_COLOR_FAMILY),
                        cursor.getString(DbContract.DbAttributes.COLUMN_INDEX_SEATS),
                        cursor.getString(DbContract.DbAttributes.COLUMN_INDEX_DOORS),
                        cursor.getString(DbContract.DbAttributes.COLUMN_INDEX_DRIVE_TYPE),
                        cursor.getString(DbContract.DbAttributes.COLUMN_INDEX_WARRANTY_TYPE),
                        cursor.getString(DbContract.DbAttributes.COLUMN_INDEX_WARRANTY_YEARS),
                        cursor.getString(DbContract.DbAttributes.COLUMN_INDEX_WARRANTY_KMS)
                );
            }
            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return attributes;
    }

    @Override
    public List<Image> getCarAdImages(int carAdId) {
        List<Image> images = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + DbContract.DbImages.TABLE_IMAGES +
                    " WHERE " + DbContract.DbImages._ID + "=?";
            Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(carAdId)});
            while (cursor.moveToNext()){
                Image image = new Image(cursor.getString(DbContract.DbImages.COLUMN_INDEX_URL));
                images.add(image);
            }
            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return images;
    }
}
