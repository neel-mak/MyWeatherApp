package cmpe277.com.MyWeatherApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CityList> cityLists = new ArrayList<CityList>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String[] city = {"london","sanjose","london","sanjose","london","sanjose"};
        // for loop to get city data
        // add into a list
        // pass the list into adapter..;

        for(int i=0;i<city.length;i++) {
            CityList obj = new CityList(city[i]);
            cityLists.add(obj);
        }

        adapter = new CityAdapter(this,cityLists);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            getMenuInflater().inflate(R.menu.menu, menu);
            return true;
    }

    public void CityView(View v)
    {
        Intent i= new Intent(MainActivity.this,CityView.class);
        startActivity(i);
    }

    public void actionAdd(MenuItem menuItem)
    {
        Intent i= new Intent(MainActivity.this,CityAutocomplete.class);
        startActivity(i);
    }

}
