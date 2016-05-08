package danieljfears.companion;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.apache.http.params.HttpConnectionParams;
import org.json.JSONObject;

import java.io.InputStream;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static String PlaceCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Firebase.setAndroidContext(this);

        System.out.println(MainActivity.latitude);
        System.out.println(Menu.selectedCategory);
        System.out.println(Menu.selectedItem);

        DatabaseFetch();


            final Firebase ref = new Firebase("https://danieljfears.firebaseio.com/City/Bath/" + Menu.selectedCategory + "/" + PlaceCat);
            Query queryRef = ref.orderByKey();

            queryRef.addChildEventListener(new ChildEventListener() {

                @Override
                public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {

                    if(snapshot.getKey().toString() == "Lat"){
                        String latitude = snapshot.getValue().toString();
                        System.out.println("LAT: " + latitude);
                    }

                    if(snapshot.getKey().toString() == "Long"){
                        String longitude = snapshot.getValue().toString();
                        System.out.println("LAT: " + longitude);
                    }

                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }

            });

        System.out.println("THIS IS REF: " + ref);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    private void DatabaseFetch() {

        // ATMS
        if(Menu.selectedItem.equals("Halifax")) {

            PlaceCat = "/ATM1";

        }
        if(Menu.selectedItem.equals("Sainsbury's")) {

            PlaceCat = "/ATM2";

        }
        if(Menu.selectedItem.equals("HSBC")) {

            PlaceCat = "/ATM3";

        }
        if(Menu.selectedItem.equals("Barclays")) {

            PlaceCat = "/ATM4";

        }
        if(Menu.selectedItem.equals("Natwest")) {

            PlaceCat = "/ATM5";

        }


        // Attractions
        if(Menu.selectedItem.equals("Bath Abbey")) {

            PlaceCat = "/Attraction1";

        }
        if(Menu.selectedItem.equals("Roman Baths")) {

            PlaceCat = "/Attraction2";

        }
        if(Menu.selectedItem.equals("Holburne Museum")) {

            PlaceCat = "/Attraction3";

        }
        if(Menu.selectedItem.equals("Victoria")) {

            PlaceCat = "/Attraction4";

        }
        if(Menu.selectedItem.equals("Alexandra Park")) {

            PlaceCat = "/Attraction5";

        }
        if(Menu.selectedItem.equals("Prior Park")) {

            PlaceCat = "/Attraction6";

        }




        // Busses
        if(Menu.selectedItem.equals("Bus Station")) {

            PlaceCat = "/Stop1";

        }
        if(Menu.selectedItem.equals("James Str. W.")) {

            PlaceCat = "/Stop2";

        }
        if(Menu.selectedItem.equals("Victoria Park")) {

            PlaceCat = "/Stop3";

        }
        if(Menu.selectedItem.equals("St. James Parade")) {

            PlaceCat = "/Stop4";

        }


        // Groceries
        if(Menu.selectedItem.equals("Tesco Express")) {

            PlaceCat = "/Groceries1";

        }
        if(Menu.selectedItem.equals("Sainsbury's Center")) {

            PlaceCat = "/Groceries2";

        }
        if(Menu.selectedItem.equals("Sainsbury's Bus Station")) {

            PlaceCat = "/Groceries3";

        }
        if(Menu.selectedItem.equals("Sainsbury's")) {

            PlaceCat = "/Groceries4";

        }
        if(Menu.selectedItem.equals("Waitrose")) {

            PlaceCat = "/Groceries5";

        }


        // Restaurants
        if(Menu.selectedItem.equals("Las Iguanas")) {

            PlaceCat = "/Restaurant1";

        }
        if(Menu.selectedItem.equals("Wetherspoon")) {

            PlaceCat = "/Restaurant2";

        }
        if(Menu.selectedItem.equals("Cosy Club")) {

            PlaceCat = "/Restaurant3";

        }
        if(Menu.selectedItem.equals("Nandos")) {

            PlaceCat = "/Restaurant4";

        }
        if(Menu.selectedItem.equals("Jamie's Italian")) {

            PlaceCat = "/Restaurant5";

        }
        if(Menu.selectedItem.equals("La Croissanterie")) {

            PlaceCat = "/Restaurant6";

        }
        if(Menu.selectedItem.equals("Frankie & Benny's")) {

            PlaceCat = "/Restaurant7";

        }
        if(Menu.selectedItem.equals("Mission Buritto")) {

            PlaceCat = "/Restaurant8";

        }

        // Shops
        if(Menu.selectedItem.equals("Apple Store")) {

            PlaceCat = "/Shop1";

        }
        if(Menu.selectedItem.equals("Marks & Spencer")) {

            PlaceCat = "/Shop2";

        }
        if(Menu.selectedItem.equals("Debenhams")) {

            PlaceCat = "/Shop3";

        }
        if(Menu.selectedItem.equals("Boots")) {

            PlaceCat = "/Shop4";

        }
        if(Menu.selectedItem.equals("Currys PC World")) {

            PlaceCat = "/Shop5";

        }
        if(Menu.selectedItem.equals("Superdry")) {

            PlaceCat = "/Shop6";

        }
        if(Menu.selectedItem.equals("Post Office")) {

            PlaceCat = "/Shop7";

        }
        if(Menu.selectedItem.equals("Halfords")) {

            PlaceCat = "/Shop8";

        }

        // Toilets
        if(Menu.selectedItem.equals("M&S Top Floor")) {

            PlaceCat = "/Toilet1";

        }
        if(Menu.selectedItem.equals("Waitrose")) {

            PlaceCat = "/Toilet2";

        }
        if(Menu.selectedItem.equals("McDonalds")) {

            PlaceCat = "/Toilet3";

        }
        if(Menu.selectedItem.equals("Bus Station")) {

            PlaceCat = "/Toilet4";

        }
        if(Menu.selectedItem.equals("Wetherspoon")) {

            PlaceCat = "/Toilet5";

        }

        // Trains
        if(Menu.selectedItem.equals("Bath Spa Station")) {

            PlaceCat = "/Station1";

        }
        if(Menu.selectedItem.equals("Oldfield Park Station")) {

            PlaceCat = "/Station2";

        }


    }

}
