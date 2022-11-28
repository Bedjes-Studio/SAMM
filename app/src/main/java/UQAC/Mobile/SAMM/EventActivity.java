package UQAC.Mobile.SAMM;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        this.setTitle("Event");

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_event);

        //create and set the layout manager for the RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // recup list d'event (history)???
        //List<Event> event= fct recup history

        //EventAdapterClass eventAdapterClass = new EventAdapterClass(event)
        //EventAdapterClass eventAdapter = new EventAdapterClass(event)
        //set the adapter
        //recyclerView.setAdapter(adapter);

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