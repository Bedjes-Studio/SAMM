package UQAC.Mobile.SAMM;

import static UQAC.Mobile.SAMM.NetworkManager.getEvents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    FloatingActionButton addEventButton;
    FloatingActionButton addRefuelButton;
    FloatingActionButton addRepairButton;
    FloatingActionButton addEarningButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_event);

        // création de la callback
        NetworkCallback callback = new NetworkCallback() {

            @Override
            public void onActionSuccess(Refuel[] refuels) {
                Toast.makeText(EventActivity.this, "Get refuel reussie", Toast.LENGTH_SHORT).show();

                //create and set the layout manager for the RecyclerView
                LinearLayoutManager layoutManager = new LinearLayoutManager(EventActivity.this);
                recyclerView.setLayoutManager(layoutManager);

                EventAdapterClass eventAdapterClass = new EventAdapterClass(refuels);
                EventAdapterClass eventAdapter = new EventAdapterClass(refuels);
                recyclerView.setAdapter(eventAdapter);

                addEventButton = findViewById(R.id.button_add_event);
                addRefuelButton = findViewById(R.id.button_add_refuel);
                addRepairButton = findViewById(R.id.button_add_repair);
                addEarningButton = findViewById(R.id.button_add_earning);

                addEventButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view){
                        addRefuelButton.setVisibility(View.VISIBLE);
                        addRepairButton.setVisibility(View.VISIBLE);
                        addEarningButton.setVisibility(View.VISIBLE);
                    }
                });

                addRefuelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent eventActivityIntent = new Intent(EventActivity.this, RefuelActivity.class);
                        startActivity(eventActivityIntent);
                    }
                });

                addEarningButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent eventActivityIntent = new Intent(EventActivity.this, EarningActivity.class);
                        startActivity(eventActivityIntent);
                    }
                });
            }

        };
        // TODO : find clicked car and pass it to the networkmanager
        Car car = new Car("aaa");
        // appel networkmanager avec callback
        NetworkManager.getAllRefuel(car, callback);

//        //create and set the layout manager for the RecyclerView
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        List<Event> eventList = getEvents();
//        EventAdapterClass eventAdapterClass = new EventAdapterClass(eventList);
//        EventAdapterClass eventAdapter = new EventAdapterClass(eventList);
//        recyclerView.setAdapter(eventAdapter);
//
//
//        addEventButton = findViewById(R.id.button_add_event);
//        addRefuelButton = findViewById(R.id.button_add_refuel);
//        addRepairButton = findViewById(R.id.button_add_repair);
//        addEarningButton = findViewById(R.id.button_add_earning);
//
//        addEventButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                addRefuelButton.setVisibility(View.VISIBLE);
//                addRepairButton.setVisibility(View.VISIBLE);
//                addEarningButton.setVisibility(View.VISIBLE);
//            }
//        });
//
//        addRefuelButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent eventActivityIntent = new Intent(EventActivity.this, RefuelActivity.class);
//                startActivity(eventActivityIntent);
//            }
//        });
//
//        addEarningButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent eventActivityIntent = new Intent(EventActivity.this, EarningActivity.class);
//                startActivity(eventActivityIntent);
//            }
//        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}