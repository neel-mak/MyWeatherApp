package cmpe277.com.MyWeatherApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CityView extends AppCompatActivity {

    RecyclerView rclView;
    private RecyclerView.Adapter adp;

    private List<DayForecastList> df=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_view);

        rclView=(RecyclerView)findViewById(R.id.rcl_View);


        rclView.setLayoutManager(new LinearLayoutManager(this));



        for(int i=0;i<30;i++) {
            DayForecastList fslt = new DayForecastList();
            fslt.setDate("Today");
            fslt.setMax("16c");
            fslt.setMin("10c");
            df.add(fslt);
        }

        adp = new DayForecastAdapter(this,df);
        rclView.setAdapter(adp);
    }
}
