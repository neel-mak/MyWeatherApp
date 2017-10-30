package cmpe277.com.myweatherapp;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by NEEL on 26-10-2017.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{


    private List<CityList> cityLists;
    private Context context;

    public CityAdapter(List<CityList> cityLists, Context context) {
        this.cityLists = cityLists;
        this.context = context;
    }


    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.city_lists,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CityAdapter.ViewHolder holder, int position) {
        CityList cityList= cityLists.get(position);

        holder.cityTime.setText(cityList.getCityTime());
        holder.cityName.setText(cityList.getCityName());
        holder.cityTemp.setText(cityList.getCityTemp());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"city",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView cityName;
        public TextView cityTime;
        public TextView cityTemp;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            cityName=(TextView)itemView.findViewById(R.id.cityName);
            cityTime=(TextView)itemView.findViewById(R.id.cityTime);
            cityTemp=(TextView)itemView.findViewById(R.id.cityTemp);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.relativeLayout);
        }
    }
}
