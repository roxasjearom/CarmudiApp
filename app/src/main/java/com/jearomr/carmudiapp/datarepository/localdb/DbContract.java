package com.jearomr.carmudiapp.datarepository.localdb;

import android.provider.BaseColumns;

public class DbContract {

    //To avoid instantiating this contract class, make the constructor private.
    private DbContract(){}

    public static class DbCarAds implements BaseColumns {
        public static final String TABLE_CAR_ADS = "CAR_ADS";
        public static final String COLUMN_PAGE_KEY = "page_key";

        public static final int COLUMN_INDEX_ID = 0;
        public static final int COLUMN_INDEX_PAGE_KEY = 1;
    }

    public static class DbData implements BaseColumns{
        public static final String TABLE_CAR_AD_DATA = "CAR_AD_DATA";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_BRAND = "brand";
        public static final String COLUMN_DESCRIPTION = "description";

        public static final int COLUMN_INDEX_ID = 0;
        public static final int COLUMN_INDEX_NAME = 1;
        public static final int COLUMN_INDEX_PRICE = 2;
        public static final int COLUMN_INDEX_BRAND = 3;
        public static final int COLUMN_INDEX_DESCRIPTION = 4;
    }

    public static class DbAttributes implements BaseColumns{
        public static final String TABLE_CAR_AD_ATRRIBUTES = "CAR_AD_ATTRIBUTES";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_YEAR_BUILT = "year_built";
        public static final String COLUMN_ENGINE = "engine";
        public static final String COLUMN_PRICE_CONDITIONS = "price_conditions";
        public static final String COLUMN_PRICE_CONDITIONS_ID = "price_conditions_id";
        public static final String COLUMN_COLOR_FAMILY = "color_family";
        public static final String COLUMN_SEATS = "seats";
        public static final String COLUMN_DOORS = "doors";
        public static final String COLUMN_DRIVE_TYPE = "drive_type";
        public static final String COLUMN_WARRANTY_TYPE = "warranty_type";
        public static final String COLUMN_WARRANTY_YEARS = "warranty_years";
        public static final String COLUMN_WARRANTY_KMS = "warranty_kms";

        public static final int COLUMN_INDEX_ID = 0;
        public static final int COLUMN_INDEX_DESCRIPTION = 1;
        public static final int COLUMN_INDEX_YEAR_BUILT = 2;
        public static final int COLUMN_INDEX_ENGINE = 3;
        public static final int COLUMN_INDEX_PRICE_CONDITIONS = 4;
        public static final int COLUMN_INDEX_PRICE_CONDITIONS_ID = 5;
        public static final int COLUMN_INDEX_COLOR_FAMILY = 6;
        public static final int COLUMN_INDEX_SEATS = 7;
        public static final int COLUMN_INDEX_DOORS = 8;
        public static final int COLUMN_INDEX_DRIVE_TYPE = 9;
        public static final int COLUMN_INDEX_WARRANTY_TYPE = 10;
        public static final int COLUMN_INDEX_WARRANTY_YEARS = 11;
        public static final int COLUMN_INDEX_WARRANTY_KMS = 12;
    }

    public static class DbImages implements BaseColumns{
        public static final String TABLE_IMAGES = "CAR_AD_IMAGES";
        public static final String COLUMN_URL = "url";

        public static final int COLUMN_INDEX_ID = 0;
        public static final int COLUMN_INDEX_URL = 1;
    }

}
