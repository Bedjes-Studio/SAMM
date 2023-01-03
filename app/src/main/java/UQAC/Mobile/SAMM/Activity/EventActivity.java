/*
 * Copyright 2022 - Hugo LANGLAIS & Alexia LACOMBE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

    private FloatingActionButton addEventButton;
    private FloatingActionButton addRefuelButton;
    private FloatingActionButton addRepairButton;
    private FloatingActionButton addEarningButton;
    //    FloatingActionButton addCostButton;
    private FloatingActionButton backButton;
    private FloatingActionButton statButton;

    private RecyclerView recyclerView;
    private EventAdapterClass adapter;


    private String intentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        readIntendExtras();
        findViewInLayout();
        NetworkManager.getAllEvents(intentId, callback);
    }

    private void readIntendExtras() {
        Intent intent = getIntent();
        intentId = intent.getExtras().getString("id");
    }

    private void findViewInLayout() {
        backButton = findViewById(R.id.backButtonCreationV);
        statButton = findViewById(R.id.buttonStats);
        recyclerView = findViewById(R.id.recycler_view_event);
        addEventButton = findViewById(R.id.button_add_event);
        addRefuelButton = findViewById(R.id.button_add_refuel);
        addRepairButton = findViewById(R.id.button_add_repair);
        addEarningButton = findViewById(R.id.button_add_earning);
//                addCostButton = findViewById(R.id.button_add_cost);
    }

    NetworkCallback callback = new NetworkCallback() {
        @Override
        public void onActionSuccess(Event[] events) {
            updateRecyclerview(events);
            setOnClickListeners();
        }
    };

    private void updateRecyclerview(Event[] events) {
        adapter = new EventAdapterClass(events);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(EventActivity.this));
    }

    private void setOnClickListeners() {
        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                eventActivityIntent.putExtra("id", intentId);
                startActivity(eventActivityIntent);
            }
        });

        addEarningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eventActivityIntent = new Intent(EventActivity.this, EarningActivity.class);
                eventActivityIntent.putExtra("id", intentId);
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
                eventActivityIntent.putExtra("id", intentId);
                startActivity(eventActivityIntent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnMenuIntent = new Intent(EventActivity.this, CarActivity.class);
                returnMenuIntent.putExtra("id", intentId);
                startActivity(returnMenuIntent);
            }
        });

        statButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (NetworkManager.events.size() > 0) {
                    Intent statsIntent = new Intent(EventActivity.this, Statistiques.class);
                    statsIntent.putExtra("id", intentId);
                    startActivity(statsIntent);
                } else {
                    Toast.makeText(EventActivity.this, "Enregistrez un événement pour voir les statistiques.", Toast.LENGTH_SHORT).show();
                }
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