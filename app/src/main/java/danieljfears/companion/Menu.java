package danieljfears.companion;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    public static String selectedCategory;
    public static String selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        System.out.println(Cities.CityName);

        TextView menuTitle = (TextView)findViewById(R.id.menuTitle);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fa.ttf");
        TextView back = (TextView) findViewById(R.id.back);
        back.setTypeface(typeface);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        menuTitle.setText("Find places around " + Cities.CityName);

        Button attractions = (Button) findViewById(R.id.attractions);
        Button restaurants = (Button) findViewById(R.id.restaurants);
        Button shopping = (Button) findViewById(R.id.shopping);
        Button busses = (Button) findViewById(R.id.busses);
        Button trains = (Button) findViewById(R.id.trains);
        Button toilets = (Button) findViewById(R.id.toilets);
        Button groceries = (Button) findViewById(R.id.groceries);
        Button atms = (Button) findViewById(R.id.atms);

        attractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Attractions.class);
                startActivity(i);
                selectedCategory = "Attraction";
            }
        });

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Restaurants.class);
                startActivity(i);
                selectedCategory = "Restaurant";
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Shopping.class);
                startActivity(i);
                selectedCategory = "Shops";
            }
        });

        busses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, BusStops.class);
                startActivity(i);
                selectedCategory = "Bus";
            }
        });

        trains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Trains.class);
                startActivity(i);
                selectedCategory = "Train";
            }
        });

        toilets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Toilets.class);
                startActivity(i);
                selectedCategory = "Toilets";
            }
        });

        groceries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Groceries.class);
                startActivity(i);
                selectedCategory = "Groceries";
            }
        });

        atms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, ATMs.class);
                startActivity(i);
                selectedCategory = "ATMs";
            }
        });



    }
}
