package danieljfears.companion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        System.out.println(Cities.CityName);

        TextView menuTitle = (TextView)findViewById(R.id.menuTitle);

        ImageButton backbtn = (ImageButton) findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        menuTitle.setText("Find places around " + Cities.CityName + "..");

        Button attractions = (Button) findViewById(R.id.attractions);
        Button restaurants = (Button) findViewById(R.id.restaurants);
        Button shopping = (Button) findViewById(R.id.shopping);
        Button transport = (Button) findViewById(R.id.transport);
        Button toilets = (Button) findViewById(R.id.toilets);
        Button groceries = (Button) findViewById(R.id.groceries);

        attractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        toilets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }
}
