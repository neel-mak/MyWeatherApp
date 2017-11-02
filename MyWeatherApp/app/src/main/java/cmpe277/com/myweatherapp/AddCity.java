package cmpe277.com.myweatherapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * Created by NEEL on 01-11-2017.
 */

public class AddCity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,View.OnClickListener {
    protected GoogleApiClient mGoogleApiClient;
    private static final LatLngBounds myBounds = new LatLngBounds(
            new LatLng(-0, 0), new LatLng(0, 0));
    private RecyclerView myrecyclerView;
    private EditText myEditText;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerAdapter myRecycleAdapter;
    ImageView clearText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city_view);
        myEditText = (EditText)findViewById(R.id.add_city_text);
        clearText = (ImageView)findViewById(R.id.cleartext);

        myRecycleAdapter = new RecyclerAdapter(this,R.layout.citylist_rows,mGoogleApiClient,myBounds,null);
        mLinearLayoutManager = new LinearLayoutManager(this);
        myrecyclerView.setLayoutManager(mLinearLayoutManager);
        myrecyclerView.setAdapter(myRecycleAdapter);
        clearText.setOnClickListener(this);

        myEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
             if(!charSequence.toString().equals("") && mGoogleApiClient.isConnected()){
                 myRecycleAdapter.getFilter().filter(charSequence.toString());
             }
             else if(mGoogleApiClient.isConnected()){
                 Toast.makeText(getApplicationContext(),"Google API is not connected",Toast.LENGTH_SHORT).show();
             }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        myrecyclerView.addOnItemTouchListener(new Recycler_Listener(this, new Recycler_Listener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                final RecyclerAdapter.PlaceAutocomplete item = myRecycleAdapter.getItem(position);
                final String placeId = String.valueOf(item.placeId);
                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi.getPlaceById(mGoogleApiClient,placeId);
                placeResult.setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(@NonNull PlaceBuffer places) {
                        if(places.getCount()==1){
                            Toast.makeText(getApplicationContext(),String.valueOf(places.get(0).getLatLng()),Toast.LENGTH_SHORT).show();
                        }
                        else{

                        }
                    }
                });
            }
        })

        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    protected synchronized void buildGoogleApiClient(){
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build();
    }

    @Override
    public void onClick(View view) {
        if(view==clearText){
            myEditText.setText("");
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mGoogleApiClient.isConnected() && !mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

}