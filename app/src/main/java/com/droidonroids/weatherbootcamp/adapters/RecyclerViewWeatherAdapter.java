package com.droidonroids.weatherbootcamp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidonroids.weatherbootcamp.R;
import com.droidonroids.weatherbootcamp.data.network.entities.Weather;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecyclerViewWeatherAdapter extends RecyclerView.Adapter {
    private static final int ITEM_TYPE_BIG_WEATHER = 0;
    private static final int ITEM_TYPE_SMALL_WEATHER = 1;

    private List<WeatherResponse> mWeathers;

    public RecyclerViewWeatherAdapter(List<WeatherResponse> weathers) {
        mWeathers = weathers;
    }

    @Override
    public int getItemViewType(int position) {
        if (position < 3) {
           return ITEM_TYPE_BIG_WEATHER;
        } else {
            return ITEM_TYPE_SMALL_WEATHER;
        }
    }

    @Override
    public int getItemCount() {
        return mWeathers.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case ITEM_TYPE_BIG_WEATHER:
                 view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_big_weather, parent, false);
                return new BigWeatherHolder(view);
            case ITEM_TYPE_SMALL_WEATHER:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lv_item_weather, parent, false);
                return new WeatherItemHolder(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);

        WeatherResponse weatherResponse = mWeathers.get(position);
        switch (itemType) {
            case ITEM_TYPE_BIG_WEATHER:
                bindBigWeather((BigWeatherHolder) holder, weatherResponse);
                break;
            case ITEM_TYPE_SMALL_WEATHER:
                bindSmallWeather((WeatherItemHolder) holder, weatherResponse);
                break;
        }
    }

    private void bindBigWeather(BigWeatherHolder holder, WeatherResponse weather) {

    }

    private void bindSmallWeather(WeatherItemHolder holder, WeatherResponse weather) {

    }

    public static class WeatherItemHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_city_name)
        TextView mTvDate;
        @Bind(R.id.tv_temperature)
        TextView mTvTemperature;
        @Bind(R.id.iv_weather_icon)
        ImageView mIvWeatherIcon;

        public WeatherItemHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class BigWeatherHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_city_name)
        TextView mTvDate;
        @Bind(R.id.tv_description)
        TextView mTvPressure;
        @Bind(R.id.tv_temperature)
        TextView mTvTemperature;
        @Bind(R.id.tv_min_temp)
        TextView mTvMinTemp;
        @Bind(R.id.tv_max_temp)
        TextView mTvMaxTemp;
        @Bind(R.id.iv_weather_icon)
        ImageView mIvWeatherIcon;

        public BigWeatherHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
