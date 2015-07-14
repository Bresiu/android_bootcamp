package com.droidonroids.weatherbootcamp.data.network.entities;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class WeatherResponse {
	@SerializedName("weather") private ArrayList<Weather> weathers;
	@SerializedName("main") private Main main;
	@SerializedName("dt") private long date;
	@SerializedName("dt_text") private String dateText;

	public ArrayList<Weather> getWeathers() {
		return this.weathers;
	}

	public void setWeathers(ArrayList<Weather> weathers) {
		this.weathers = weathers;
	}

	public Main getMain() {
		return this.main;
	}

	public long getDate() {
		return date;
	}

	public String getDateText() {
		return dateText;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@Override public String toString() {
		return "WeatherResponse{" +
			"weathers=" + weathers +
			", main=" + main +
			'}';
	}
}
