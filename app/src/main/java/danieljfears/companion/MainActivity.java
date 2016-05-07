package danieljfears.companion;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    GPSTracker gps;
    public static double latitude;
    public static double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        Button buttongps = (Button) findViewById(R.id.buttongps);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {

                Intent i= new Intent(MainActivity.this,Cities.class);
                startActivity(i);

            }
        });

        buttongps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    GetLocation();



                    //double distance=Math.sqrt((bathlat-latitude)*(latitude-bathlat))

            }
        });

    }

    public void GetLocation() {

            gps = new GPSTracker(MainActivity.this);

            if(gps.canGetLocation()) {
                latitude = gps.getLatitude();
                longitude = gps.getLongitude();

            } else {
                gps.showSettingsAlert();
            }

        double bathlat = 51.375801;
        double bathlong = -2.359904;

                    /*Toast.makeText(
                            getApplicationContext(),
                            "Your Location is -\nLat: " + latitude + "\nLong: "
                                    + longitude, Toast.LENGTH_LONG).show(); */

        Location loc1 = new Location("");
        loc1.setLatitude(latitude);
        loc1.setLongitude(longitude);

        Location loc2 = new Location("");
        loc2.setLatitude(bathlat);
        loc2.setLongitude(bathlong);

        float distanceInMeters = loc1.distanceTo(loc2);

        // Converts to miles
        //distanceInMeters = ((distanceInMeters / 1000) / 8) * 5;
        int roundedDistance = Math.round(distanceInMeters);

        System.out.println(latitude);
        System.out.println(longitude);
        System.out.println(roundedDistance);

        Toast.makeText(
                getApplicationContext(),
                "Distance is: " + roundedDistance, Toast.LENGTH_LONG).show();

    }

}