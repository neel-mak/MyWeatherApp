package cmpe277.com.MyWeatherApp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NEEL on 04-11-2017.
 */

public class DayForecastAdapter extends RecyclerView.Adapter<DayForecastAdapter.DayForecastViewHolder> {
    private Context context;
    private List<DayForecastList> lst;

    public DayForecastAdapter(Context context, List<DayForecastList> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public DayForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.day_forecast,parent,false);
        return new DayForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DayForecastViewHolder holder, int position) {
        DayForecastList dayforecastList=lst.get(position);
        holder.city_date.setText(dayforecastList.getDate());
        holder.city_max.setText(dayforecastList.getMax());
        holder.city_min.setText(dayforecastList.getMin());


    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public class DayForecastViewHolder extends RecyclerView.ViewHolder {
        TextView city_date;
        TextView city_max;
        TextView city_min;


        public DayForecastViewHolder(View itemView) {
            super(itemView);

            city_date=(TextView)itemView.findViewById(R.id.day_Date);
            city_max=(TextView)itemView.findViewById(R.id.day_Max);
            city_min=(TextView)itemView.findViewById(R.id.day_Min);

        }
    }
}
