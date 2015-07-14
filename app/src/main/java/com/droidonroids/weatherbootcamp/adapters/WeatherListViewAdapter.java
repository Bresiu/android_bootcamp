package com.droidonroids.weatherbootcamp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.droidonroids.weatherbootcamp.R;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;
import com.droidonroids.weatherbootcamp.helpers.image.AddressBuilder;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WeatherListViewAdapter extends ArrayAdapter<WeatherResponse> {
    private LayoutInflater mInflater;
    private Picasso mPicasso;

    public WeatherListViewAdapter(Context context, List<WeatherResponse> weathers) {
        super(context, R.layout.lv_item_weather, weathers);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPicasso = Picasso.with(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WeatherItemHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.lv_item_weather, parent, false);

            holder = new WeatherItemHolder(convertView);
            convertView.setTag(holder);
        }

        holder = (WeatherItemHolder) convertView.getTag();

        WeatherResponse weatherResponse = getItem(position);

        holder.mTvDate.setText(weatherResponse.getDateText());
        holder.mTvTemperature.setText(weatherResponse.getMain().getTemp() + "");
        holder.mTvPressure.setText(weatherResponse.getMain().getPressure() + "");
        if (weatherResponse.getWeathers().size() > 0) {
            String imageAddress = AddressBuilder.getImageAddress(weatherResponse.getWeathers().get(0).getIcon());
            mPicasso.load(imageAddress).into(holder.mIvWeatherIcon);
        }

        return convertView;
    }

    public static class WeatherItemHolder {
        @Bind(R.id.tv_city_name)
        TextView mTvDate;
        @Bind(R.id.tv_description)
        TextView mTvPressure;
        @Bind(R.id.tv_temperature)
        TextView mTvTemperature;
        @Bind(R.id.iv_weather_icon)
        ImageView mIvWeatherIcon;

        public WeatherItemHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
