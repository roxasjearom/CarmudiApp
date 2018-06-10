package com.jearomr.carmudiapp.car_catalog_page;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jearomr.carmudiapp.GlideApp;
import com.jearomr.carmudiapp.R;
import com.jearomr.carmudiapp.Utils;
import com.jearomr.carmudiapp.models.CarAd;

/**
 * Paged List Adapter for endless scrolling implementation. Handles the display of data and the user interaction.
 */
public class CarCatalogAdapter extends PagedListAdapter<CarAd,CarCatalogAdapter.CarCatalogViewHolder>{

    private Context context;
    private CarCatalogActivity.OnItemSelectedCallback onItemSelectedCallback;

    CarCatalogAdapter(Context context, CarCatalogActivity.OnItemSelectedCallback onItemSelectedCallback) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.onItemSelectedCallback = onItemSelectedCallback;
    }

    @NonNull
    @Override
    public CarCatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_car_item, parent, false);

        return new CarCatalogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarCatalogViewHolder holder, int position) {
        CarAd carAd = getItem(position);
        holder.bindData(carAd);
    }

    private static final DiffUtil.ItemCallback<CarAd> DIFF_CALLBACK = new DiffUtil.ItemCallback<CarAd>() {
        @Override
        public boolean areItemsTheSame(CarAd oldItem, CarAd newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(CarAd oldItem, CarAd newItem) {
            return oldItem.equals(newItem);
        }
    };

    class CarCatalogViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivCarImage;
        private TextView tvCarName, tvCarPrice, tvCarBrand;

        CarCatalogViewHolder(View itemView) {
            super(itemView);

            ivCarImage = itemView.findViewById(R.id.ivCarImage);
            tvCarName = itemView.findViewById(R.id.tvCarName);
            tvCarPrice = itemView.findViewById(R.id.tvCarPrice);
            tvCarBrand = itemView.findViewById(R.id.tvCarBrand);
        }

        void bindData(final CarAd carAd){
            tvCarName.setText(carAd.getData().getName());
            tvCarPrice.setText(Utils.formatPrice(carAd.getData().getPrice()));
            tvCarBrand.setText(carAd.getData().getBrand());
            //Display the first image for the thumbnail
            GlideApp.with(context).load(carAd.getImages().get(0).getUrl())
                    .thumbnail(0.5f)
                    .into(ivCarImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemSelectedCallback.onItemSelected(carAd);
                }
            });
        }
    }

}
