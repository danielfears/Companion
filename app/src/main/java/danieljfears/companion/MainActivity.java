package danieljfears.companion;

import android.content.Intent;
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

                gps = new GPSTracker(MainActivity.this);

                //Toast.makeText(getApplicationContext(), "Find user location",   Toast.LENGTH_LONG).show();

                if(gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();

                    Toast.makeText(
                            getApplicationContext(),
                            "Your Location is -\nLat: " + latitude + "\nLong: "
                                    + longitude, Toast.LENGTH_LONG).show();
                } else {
                    gps.showSettingsAlert();
                }
            }
        });

    }

}