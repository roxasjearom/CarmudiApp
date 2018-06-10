package com.jearomr.carmudiapp.datarepository.localdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "data.db";
    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_CAR_ADS =
            "CREATE TABLE " + DbContract.DbCarAds.TABLE_CAR_ADS + "(" +
                    DbContract.DbCarAds._ID + " INTEGER PRIMARY KEY," +
                    DbContract.DbCarAds.COLUMN_PAGE_KEY + " INTEGER" +
                    ")";

    private static final String CREATE_TABLE_CAR_AD_DATA =
            "CREATE TABLE " + DbContract.DbData.TABLE_CAR_AD_DATA + "(" +
                    DbContract.DbData._ID + " INTEGER PRIMARY KEY," +
                    DbContract.DbData.COLUMN_NAME + " TEXT," +
                    DbContract.DbData.COLUMN_PRICE + " TEXT," +
                    DbContract.DbData.COLUMN_BRAND + " TEXT," +
                    DbContract.DbData.COLUMN_DESCRIPTION + " TEXT" +
                    ")";

    private static final String CREATE_TABLE_CAR_AD_ATTRIBUTES =
            "CREATE TABLE " + DbContract.DbAttributes.TABLE_CAR_AD_ATRRIBUTES + "(" +
                    DbContract.DbAttributes._ID + " INTEGER PRIMARY KEY," +
                    DbContract.DbAttributes.COLUMN_DESCRIPTION + " TEXT," +
                    DbContract.DbAttributes.COLUMN_YEAR_BUILT + " TEXT," +
                    DbContract.DbAttributes.COLUMN_ENGINE + " TEXT," +
                    DbContract.DbAttributes.COLUMN_PRICE_CONDITIONS + " TEXT," +
                    DbContract.DbAttributes.COLUMN_PRICE_CONDITIONS_ID + " INTEGER," +
                    DbContract.DbAttributes.COLUMN_COLOR_FAMILY + " TEXT," +
                    DbContract.DbAttributes.COLUMN_SEATS + " INTEGER," +
                    DbContract.DbAttributes.COLUMN_DOORS + " INTEGER," +
                    DbContract.DbAttributes.COLUMN_DRIVE_TYPE + " TEXT," +
                    DbContract.DbAttributes.COLUMN_WARRANTY_TYPE + " TEXT," +
                    DbContract.DbAttributes.COLUMN_WARRANTY_YEARS + " TEXT," +
                    DbContract.DbAttributes.COLUMN_WARRANTY_KMS + " TEXT" +
                    ")";

    private static final String CREATE_TABLE_CAR_AD_IMAGES =
            "CREATE TABLE " + DbContract.DbImages.TABLE_IMAGES + "(" +
                    DbContract.DbImages._ID + " INTEGER," +
                    DbContract.DbImages.COLUMN_URL + " TEXT" +
                    ")";


    private static DbHelper dbHelperInstance;

    public static DbHelper getDbHelperInstance(Context context){
        if(dbHelperInstance==null){
            dbHelperInstance = new DbHelper(context.getApplicationContext());
        }
        return dbHelperInstance;
    }

    private DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_CAR_ADS);
        sqLiteDatabase.execSQL(CREATE_TABLE_CAR_AD_DATA);
        sqLiteDatabase.execSQL(CREATE_TABLE_CAR_AD_ATTRIBUTES);
        sqLiteDatabase.execSQL(CREATE_TABLE_CAR_AD_IMAGES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
