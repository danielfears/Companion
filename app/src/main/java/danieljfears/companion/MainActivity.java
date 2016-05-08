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

    public static GPSTracker gps;
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

                //FindDistance();

            }
        });

        gps = new GPSTracker(MainActivity.this);

        if(gps.canGetLocation()) {
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

        } else {
            gps.showSettingsAlert();
        }

        // Output to system
        System.out.println("USER LAT :" + latitude);
        System.out.println("USER LONG :" + longitude);

    }


    }
