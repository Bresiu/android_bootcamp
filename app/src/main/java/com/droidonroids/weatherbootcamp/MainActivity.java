package com.droidonroids.weatherbootcamp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.droidonroids.weatherbootcamp.adapters.RecyclerViewWeatherAdapter;
import com.droidonroids.weatherbootcamp.adapters.WeatherListViewAdapter;
import com.droidonroids.weatherbootcamp.data.network.ApiManager;
import com.droidonroids.weatherbootcamp.data.network.entities.ForecastResponse;
import com.droidonroids.weatherbootcamp.data.network.entities.WeatherResponse;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
	@Bind(R.id.lv_weather_list)
	ListView mLvWeatherList;
	@Bind(R.id.rv_weather_list)
	RecyclerView mRvWeatherList;

	private static final String TAG = "BOOTCAMP";
	private WeatherListViewAdapter mListAdapter;
	private RecyclerViewWeatherAdapter mRecyclerAdapter;

	@Override protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		final ApiManager apiManager = new ApiManager();
		apiManager.getWeatherWithObservable("Wrocław")
			.subscribeOn(Schedulers.newThread())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new Action1<WeatherResponse>() {
				@Override public void call(WeatherResponse weatherResponse) {
					Log.d(TAG, "\nOBSERVABLE\n");
					Log.d(TAG, weatherResponse.toString());
				}
			});

		Callback<WeatherResponse> callback = new Callback<WeatherResponse>() {
			@Override public void success(WeatherResponse weatherResponse, Response response) {
				Log.d(TAG, "\nCALLBACK\n");
				Log.d(TAG, weatherResponse.toString());
			}

			@Override public void failure(RetrofitError error) {
				Log.d(TAG, error.toString());
			}
		};
		apiManager.getWeatherWithCallback("Wrocław", callback);

		new AsyncTask<String, Void, WeatherResponse>() {
			@Override protected WeatherResponse doInBackground(String... params) {
				return apiManager.getWeatherWithSync(params[0]);
			}

			@Override protected void onPostExecute(WeatherResponse weatherResponse) {
				Log.d(TAG, "\nASYNC\n");
				Log.d(TAG, weatherResponse.toString());
			}
		}.execute("Wrocław");

		apiManager.getForecastWithObservable("Wrocław")
				.subscribeOn(Schedulers.newThread())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(new Subscriber<ForecastResponse>() {
					@Override
					public void onCompleted() {

					}

					@Override
					public void onError(Throwable e) {

					}

					@Override
					public void onNext(ForecastResponse forecastResponse) {
						mListAdapter = new WeatherListViewAdapter(MainActivity.this, forecastResponse.getWeatherResponses());
						mLvWeatherList.setAdapter(mListAdapter);

//						mRecyclerAdapter = new RecyclerViewWeatherAdapter(forecastResponse.getWeatherResponses());
//						mRvWeatherList.setAdapter(mRecyclerAdapter);
					}
				});
	}
}
