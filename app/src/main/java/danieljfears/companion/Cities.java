package danieljfears.companion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class Cities extends AppCompatActivity {

    //Firebase
    Firebase mRootRef;
    ArrayList<String> mMessages = new ArrayList<>();

    //UI
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);

        Firebase.setAndroidContext(this);

        mRootRef = new Firebase("https://danieljfears.firebaseio.com/");

        mListView = (ListView)findViewById(R.id.listView);

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
                Toast.makeText(getApplicationContext(), "Choice : "+selectedCity,   Toast.LENGTH_SHORT).show();

                //Intent i= new Intent(MainActivity.this,secondActivity.class);
                //i.putExtra("string",mMessages.get(position);
                //startActivity(i);
                //finish();

            }
        });

        Firebase messagesRef = mRootRef.child("City");
        messagesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map<String, String> map = dataSnapshot.getValue(Map.class);
                String message = map.get("CityName");
                Log.v("E_VALUE", message);
                mMessages.add(message);
                adapter.notifyDataSetChanged();

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
}