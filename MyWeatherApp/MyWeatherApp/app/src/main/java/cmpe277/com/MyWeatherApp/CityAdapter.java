package cmpe277.com.MyWeatherApp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cmpe277.com.MyWeatherApp.model.WeatherData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NEEL on 03-11-2017.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.PostViewHolder> {

    private Context context;
    private List<CityList> items;

    public CityAdapter(Context context, List<CityList> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.city_list_row,parent,false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, int position) {
        CityList cityList= items.get(position);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        CityInterface cityInterface = retrofit.create(CityInterface.class);


        final Call<WeatherData> weatherDataCall = cityInterface.sendCity(cityList.getCityName(),"metric","59a6c63bd557b295f04dac7838e85425");


        weatherDataCall.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                if(response.body() != null)
                holder.city.setText(response.body().getName());
                holder.temperature.setText(response.body().getMain().getTemp().toString());
                holder.country.setText(response.body().getName());


            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        TextView city;
        TextView temperature;
        TextView country;

        public PostViewHolder(View itemView) {
            super(itemView);
            city=(TextView)itemView.findViewById(R.id.cityName);
            temperature=(TextView)itemView.findViewById(R.id.cityTemp);
            country=(TextView)itemView.findViewById(R.id.cityCountry);

        }
    }
}
