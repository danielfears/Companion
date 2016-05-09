package danieljfears.companion;

import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trains extends AppCompatActivity {

    //Firebase
    Firebase mRootRef;
    ArrayList<String> mMessages = new ArrayList<>();
    List<PlaceObject> restaurants = new ArrayList<>();

    public static Integer dist = 0;
    public static String lon;
    public static String lat;

    //UI
    ListView mListView;

    //public static String RestaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trains);

        Firebase.setAndroidContext(this);

        mRootRef = new Firebase("https://danieljfears.firebaseio.com/City/" + Cities.CityName);

        mListView = (ListView)findViewById(R.id.listView);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fa.ttf");
        TextView back = (TextView) findViewById(R.id.back);
        back.setTypeface(typeface);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        final ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mMessages);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3)
            {
                Menu.selectedItem=mMessages.get(position);
                //Toast.makeText(getApplicationContext(), "Choice : "+selectedCity,   Toast.LENGTH_SHORT).show();

                /*RestaurantName = selectedRestaurant;
                Intent i= new Intent(Restaurants.this,Menu.class);
                startActivity(i); */
                Intent i= new Intent(Trains.this,MapsActivity.class);
                startActivity(i);

            }
        });

        Firebase messagesRef = mRootRef.child("/Train");
        messagesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map<String, String> map = dataSnapshot.getValue(Map.class);
                String name = map.get("Name");
                lon = map.get("Long").toString();
                lat = map.get("Lat").toString();

                FindDistance();

                //Log.v("E_VALUE", message);
                mMessages.add(name);
                //adapter.notifyDataSetChanged();

                restaurants.add(new PlaceObject(name, dist));

                LocationAdapter adapter =  new LocationAdapter(restaurants);

                mListView.setAdapter(adapter);

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

    }

    private class LocationAdapter extends ArrayAdapter<PlaceObject> {

        public LocationAdapter(List<PlaceObject> items) {
            super(Trains.this, 0, items);
        }

        @Override
        public android.view.View getView(int position, android.view.View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.place_list, null);
            }

            TextView lblName = (TextView)convertView.findViewById(R.id.lblName);
            TextView lblDist = (TextView)convertView.findViewById(R.id.lblDist);

            PlaceObject location = restaurants.get(position);

            lblName.setText(location.getPlaceName());
            lblDist.setText(String.valueOf(location.getPlaceDist()));

            return convertView;

        }// end get view

    }// end adapter class

    @Override
    protected void onResume() {
        super.onResume();

        mMessages.clear();
        restaurants.clear();

    }

    public void FindDistance() {

        double londouble = Double.parseDouble(lon);
        double latdouble = Double.parseDouble(lat);

        Location loc1 = new Location("");
        loc1.setLatitude(MainActivity.latitude);
        loc1.setLongitude(MainActivity.longitude);

        Location loc2 = new Location("");

        loc2.setLatitude(latdouble);
        loc2.setLongitude(londouble);

        float distanceInMeters = loc1.distanceTo(loc2);

        // Converts to miles
        //distanceInMeters = ((distanceInMeters / 1000) / 8) * 5;

        // Removes decimals and converts to int
        dist = Math.round(distanceInMeters);


        /*
        // Toast distance in meters
        Toast.makeText(
                getApplicationContext(),
                "Distance is: " + roundedDistance, Toast.LENGTH_LONG).show(); */

        // Output to system

        System.out.println("USERS LAT " + MainActivity.latitude);
        System.out.println("USERS LONG " + MainActivity.longitude);
        System.out.println("DIST " + dist);


    }

}