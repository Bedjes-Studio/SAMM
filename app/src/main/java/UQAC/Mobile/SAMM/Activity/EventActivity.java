package UQAC.Mobile.SAMM.Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import UQAC.Mobile.SAMM.Base.Event;
import UQAC.Mobile.SAMM.Adapter.EventAdapterClass;
import UQAC.Mobile.SAMM.API.NetworkCallback;
import UQAC.Mobile.SAMM.API.NetworkManager;
import UQAC.Mobile.SAMM.R;

public class EventActivity extends AppCompatActivity {

    FloatingActionButton addEventButton;
    FloatingActionButton addRefuelButton;
    FloatingActionButton addRepairButton;
    FloatingActionButton addEarningButton;
//    FloatingActionButton addCostButton;
    FloatingActionButton backButton;
    FloatingActionButton statButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        backButton = findViewById(R.id.backButtonCreationV);
        statButton = findViewById(R.id.buttonStats);

        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        Log.d("ALEXIA", id);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_event);

        // création de la callback
        NetworkCallback callback = new NetworkCallback() {

            @Override
//                public void onActionSuccess(Cost[] costs) {
                            public void onActionSuccess(Event[] events) {

//                Log.d("HUGO", "size: " + events.length);


//                Toast.makeText(EventActivity.this, "Get refuel reussie", Toast.LENGTH_SHORT).show();

                //create and set the layout manager for the RecyclerView
                LinearLayoutManager layoutManager = new LinearLayoutManager(EventActivity.this);
                recyclerView.setLayoutManager(layoutManager);

//                EventAdapterClass eventAdapter = new EventAdapterClass(refuels);
                EventAdapterClass eventAdapter = new EventAdapterClass(events);
//                EventAdapterClass eventAdapter = new EventAdapterClass(costs);
                recyclerView.setAdapter(eventAdapter);
                recyclerView.setAdapter(eventAdapter);


                addEventButton = findViewById(R.id.button_add_event);
                addRefuelButton = findViewById(R.id.button_add_refuel);
                addRepairButton = findViewById(R.id.button_add_repair);
                addEarningButton = findViewById(R.id.button_add_earning);
//                addCostButton = findViewById(R.id.button_add_cost);

                addEventButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view){
                        addRefuelButton.setVisibility(View.VISIBLE);
                        addRepairButton.setVisibility(View.VISIBLE);
                        addEarningButton.setVisibility(View.VISIBLE);
//                        addCostButton.setVisibility(View.VISIBLE);
                    }
                });

                addRefuelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("ALEXIA", "Want to add refuel");
                        Intent eventActivityIntent = new Intent(EventActivity.this, RefuelActivity.class);
                        eventActivityIntent.putExtra("id", id);
                        startActivity(eventActivityIntent);
                    }
                });

                addEarningButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent eventActivityIntent = new Intent(EventActivity.this, EarningActivity.class);
                        eventActivityIntent.putExtra("id", id);
                        startActivity(eventActivityIntent);
                    }
                });

//                addCostButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent eventActivityIntent = new Intent(EventActivity.this, CostActivity.class);
//                        eventActivityIntent.putExtra("id", id);
//                        startActivity(eventActivityIntent);
//                    }
//                });

                addRepairButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent eventActivityIntent = new Intent(EventActivity.this, RepairActivity.class);
                        eventActivityIntent.putExtra("id", id);
                        startActivity(eventActivityIntent);
                    }
                });

                backButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent returnMenuIntent = new Intent(EventActivity.this, CarActivity.class);
                        returnMenuIntent.putExtra("id", id);
                        startActivity(returnMenuIntent);
                    }
                });

                statButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (NetworkManager.events.size() > 0) {
                            Intent statsIntent = new Intent(EventActivity.this, Statistiques.class);
                            statsIntent.putExtra("id", id);
                            startActivity(statsIntent);
                        } else {
                            Toast.makeText(EventActivity.this, "Enregistrez un événement pour voir les statistiques.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        };
//        Car car = new Car("aaa");
        // appel networkmanager avec callback
//        Earning earning = new Earning("Covoiturage", 70, Calendar.getInstance().getTime(), 180000);
//        Cost cost = new Cost(70, "Covoiturage", "CB", Calendar.getInstance().getTime(), 180000);
//
//             NetworkManager.deleteCar( "639fd9508f3d7679bf4f9396", new NetworkCallback());
//        NetworkManager.getAllRefuel( id, callback);

//        NetworkCallback cb = new NetworkCallback(){
//            @Override
//            public void onActionSuccess(Event[] events){
//                Log.d("API", events.length + " - SIZE");
//            };
//        };

        NetworkManager.getAllEvents( id, callback);
//        NetworkManager.getAllCost( id, callback);
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