package cmpe277.com.MyWeatherApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;
import java.util.List;

import cmpe277.com.retrifitdemo.PlacesAutoComplete;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CityList> cityLists = new ArrayList<CityList>();
    List<String> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cities = new ArrayList<>();
        cities.add("SanJose");// default city
        //String[] city = {"london","sanjose","london","sanjose","london","sanjose"};
        // for loop to get city data
        // add into a list
        // pass the list into adapter..;

     /*   for(int i=0;i<city.length;i++) {
            CityList obj = new CityList(city[i]);
            cityLists.add(obj);
        }  */

        SharedPreferences settings = getSharedPreferences("PREF",0);
        String citiesString = settings.getString("cities","");
        String[] itemCities = citiesString.split(",");
        List<String> itm = new ArrayList<>();
        //itm.add("San Jose");
        for(int i=0;i<itemCities.length;i++)
        {
            itm.add(itemCities[i]);
        }

        for(int i=0;i<itm.size();i++)
        {
            CityList obj = new CityList(itm.get(i));
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
        /*Intent i= new Intent(MainActivity.this,PlacesAutoComplete.class);
        startActivity(i);*/
        try {
            Intent intent =
                    new PlaceAutocomplete
                            .IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                            .build(this);
            startActivityForResult(intent, 1);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // retrive the data by using getPlace() method.
                Place place = PlaceAutocomplete.getPlace(this, data);
                cities.add(place.getName().toString());
                StringBuilder stringBuilder=new StringBuilder();
                for(String s : cities)
                {
                    stringBuilder.append(s);
                    stringBuilder.append(",");
                }
                SharedPreferences settings = getSharedPreferences("PREF",0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("cities",stringBuilder.toString());
                editor.commit();


                Toast.makeText(this,place.getName(),Toast.LENGTH_LONG).show();

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                // TODO: Handle the error.
                Log.e("Tag", status.getStatusMessage());

            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
        }
    }
}
