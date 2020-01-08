package android.example.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final long MIN_TIME =1000;
    private final long MIN_DIST = 5;
    public boolean stopMap = false;
    public TextView address;
    public String ADDRESS;
    Button button1;
    private GoogleMap mMap;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private LatLng latLng;
    Timer timer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PackageManager.PERMISSION_GRANTED);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},PackageManager.PERMISSION_GRANTED);


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //address = findViewById(R.id.map);

        Toast toast = Toast.makeText(getApplicationContext(),"Getting Current Location", Toast.LENGTH_LONG);
        toast.show();
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {


                try{
                    latLng = new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title("My Current Position"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,18),5000,null);

                    //try to get the current address from latitude and longitude in android studio


                    Toast located = Toast.makeText(getApplicationContext(),"Current Location Acquired",Toast.LENGTH_LONG);
                    located.show();


                    toast(getCompleteAddressString(location.getLatitude(),location.getLongitude()));
                    ADDRESS = getCompleteAddressString(location.getLatitude(),location.getLongitude());
                    //onStop(true);


                    //use a timer to get to the next view after three seconds after location has been aquired
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {

                            Intent newActivity = new Intent(MapsActivity.this, Details.class);
                            newActivity.putExtra("ADDRESS",ADDRESS.toString());
                            startActivity(newActivity);
                            finish();
                        }
                        //Make sure you choose an ideal delay based on coverage
                    },7000);

                    locationManager.removeUpdates(locationListener);

                }
                catch(SecurityException e){

                    e.printStackTrace();
                }
                onPause();
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            //toast("Status has changed for Maps");
            }

            @Override
            public void onProviderEnabled(String s) {

                toast("Provider enabled on maps");
            }

            @Override
            public void onProviderDisabled(String s) {

                toast("Provider disabled on Maps");
            }
        };
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

            try{
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_TIME,MIN_DIST,locationListener);
            }
            catch(SecurityException e){
            e.printStackTrace();
            toast("Not getting the location");
        }
        //address.setText(getCompleteAddressString(location.getLatitude(), location.getLongitude()));
    }

    public void toast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    public String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                Log.w("My Current address", strReturnedAddress.toString());
                stopMap=true;
            } else {
                Log.w("My Current address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("My Current address", "Cannot get Address!");
        }
        return strAdd;
    }


    //button in the maps view used to control the switch at a certain point desired by the user
//    public void onStop(boolean stopMap){
//        if(stopMap==true){
//            super.onStop();
//            button1=findViewById(R.id.button1);
//            button1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent newActivity = new Intent(MapsActivity.this, Details.class);
//                    newActivity.putExtra("ADDRESS",ADDRESS.toString());
//                    startActivity(newActivity);
//                }
//            });
//        }
//        else{
//            toast("Maps is still running");
//        }
//    }

}
