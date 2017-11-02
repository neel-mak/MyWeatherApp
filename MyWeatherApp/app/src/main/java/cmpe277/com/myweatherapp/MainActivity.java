package cmpe277.com.myweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CityList> cityLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cityLists=new ArrayList<>();

        for(int i=0;i<10;i++)
        {
            CityList cityList= new CityList(
                    "6:00",
                    "City" + (i+1),
                    "27c"
            );
            cityLists.add(cityList);
        }

        adapter = new CityAdapter(cityLists,this);
        recyclerView.setAdapter(adapter);
    }
}


