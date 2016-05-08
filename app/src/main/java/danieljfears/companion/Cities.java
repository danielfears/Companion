package danieljfears.companion;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.security.spec.AlgorithmParameterSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Cities extends AppCompatActivity {

    //Firebase
    Firebase mRootRef;
    ArrayList<String> mMessages = new ArrayList<>();
    List<ListObject> cities = new ArrayList<>();

    //UI
    ListView mListView;


    public static String CityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        ImageButton backbtn = (ImageButton) findViewById(R.id.backbtn);

        Firebase.setAndroidContext(this);

        mRootRef = new Firebase("https://danieljfears.firebaseio.com/");

        mListView = (ListView)findViewById(R.id.listView);

        backbtn.setOnClickListener(new View.OnClickListener() {
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
                String selectedCity=mMessages.get(position);
                //Toast.makeText(getApplicationContext(), "Choice : "+selectedCity,   Toast.LENGTH_SHORT).show();

                if(selectedCity.equals("Bath")) {

                    CityName = selectedCity;
                    Intent i= new Intent(Cities.this,Menu.class);
                    startActivity(i);

                }
                else {

                    Toast.makeText(getApplicationContext(), selectedCity + " under development. Bath working as proof of concept",   Toast.LENGTH_SHORT).show();

                }

            }
        });

        Firebase messagesRef = mRootRef.child("City");
        messagesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map<String, String> map = dataSnapshot.getValue(Map.class);
                String message = map.get("CityName");
                String image = map.get("ImgName");
                String desc = map.get("Desc");

                Log.v("E_VALUE", message);
                mMessages.add(message);
                //adapter.notifyDataSetChanged();

                int resID = getResources().getIdentifier(image , "drawable", getPackageName());

                cities.add(new ListObject(message, resID, desc));

                LocationAdapter adapter =  new LocationAdapter(cities);

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

    private class LocationAdapter extends ArrayAdapter<ListObject> {

        public LocationAdapter(List<ListObject> items) {
            super(Cities.this, 0, items);
        }

        @Override
        public android.view.View getView(int position, android.view.View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(
                        R.layout.city_list, null);
            }

            ImageView imgCity = (ImageView)convertView.findViewById(R.id.imgCity);
            TextView lblCity = (TextView)convertView.findViewById(R.id.lblCity);
            TextView lblDesc = (TextView)convertView.findViewById(R.id.lblDesc);

            ListObject location = cities.get(position);

            imgCity.setImageResource(location.getCityPicture());
            lblCity.setText(location.getCityName());
            lblDesc.setText(location.getCityDesc());

            return convertView;

        }// end get view

    }// end adapter class

    @Override
    protected void onPause() {
        super.onPause();

        mMessages.clear();
        cities.clear();

    }


}