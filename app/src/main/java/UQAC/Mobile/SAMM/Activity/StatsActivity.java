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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import UQAC.Mobile.SAMM.API.NetworkCallback;
import UQAC.Mobile.SAMM.API.NetworkManager;
import UQAC.Mobile.SAMM.Base.Cost;
import UQAC.Mobile.SAMM.Base.Earning;
import UQAC.Mobile.SAMM.Base.Refuel;
import UQAC.Mobile.SAMM.R;

/**
 * This activity shows the stats of the car (cost/fuel consumption...)
 */

public class StatsActivity extends AppCompatActivity {

    private List<Refuel> refuels = new ArrayList<Refuel>();
    //List<Maintenance> maintenances = new ArrayList<Maintenance>();
    private List<Earning> earns = new ArrayList<Earning>();
    private FloatingActionButton backButtonStat;

    private TextView totalFuelValue;
    private TextView totalFuelDayValue;
    private TextView totalMaintValue;
    private TextView totalMaintDayValue;
    private TextView totalEarnValue;
    private TextView totalEarnDayValue;

    private String intentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        readIntendExtras();
        findViewInLayout();
        setOnClickListeners();

        computeStats();

    }

    private void readIntendExtras() {
        Intent intent = getIntent();
        intentId = intent.getExtras().getString("id");
    }

    private void findViewInLayout() {
        backButtonStat = findViewById(R.id.backButtonStat);
        totalFuelValue = findViewById(R.id.totalFuelValue);
        totalFuelDayValue = findViewById(R.id.totalFuelDayValue);
        totalMaintValue = findViewById(R.id.totalMaintValue);
        totalMaintDayValue = findViewById(R.id.totalMaintDayValue);
        totalEarnValue = findViewById(R.id.totalEarnValue);
        totalEarnDayValue = findViewById(R.id.totalEarnDayValue);
    }

    private void setOnClickListeners() {
        backButtonStat.setOnClickListener((View view) -> {
            Intent returnMenuIntent = new Intent(StatsActivity.this, EventActivity.class);
            returnMenuIntent.putExtra("id", intentId);
            startActivity(returnMenuIntent);
        });
    }

    private void computeStats() {
        // fuel
        NetworkCallback callbackFuel = new NetworkCallback() {
            @Override
            public void onActionSuccess(Refuel[] refuels) {
                totalFuelValue.setText(String.valueOf(totalCostRefuel(refuels)));
                totalFuelDayValue.setText(String.valueOf(totalKmRefuel(refuels, Float.parseFloat(totalFuelValue.getText().toString()))));
            }
        };
        NetworkManager.getAllRefuels(intentId, callbackFuel);

        // earnings
        NetworkCallback callbackEarnings = new NetworkCallback() {
            @Override
            public void onActionSuccess(Earning[] earnings) {
                totalEarnValue.setText(String.valueOf(totalCostEarning(earnings)));
            }
        };
        NetworkManager.getAllEarning(intentId, callbackEarnings);

        // costs
        NetworkCallback callbackCost = new NetworkCallback() {
            @Override
            public void onActionSuccess(Cost[] costs) {
                totalMaintValue.setText(String.valueOf(totalCostCosts(costs)));
            }
        };
        NetworkManager.getAllCost(intentId, callbackCost);
    }

    // TODO : clean these methods when interface will be ready
    private float totalCostRefuel(Refuel[] refuels) {
        float total = 0;
        for (Refuel refuel : refuels) {
            total += refuel.getTotalCost();
        }
        return total;
    }

    private float totalKmRefuel(Refuel[] refuels, float cost) {
        // get mini kilom
        // get max kilom
        float min = refuels[0].getMileage();
        float max = refuels[0].getMileage();
        for (Refuel refuel : refuels) {
            if (refuel.getMileage() < min) {
                min = refuel.getMileage();
            }
            if (refuel.getMileage() > max) {
                max = refuel.getMileage();
            }
        }
        // diff max -min
        // total cost / diff
        if (max == min) {
            return cost;
        } else {
            return cost / (max - min);
        }
    }

    private float totalCostCosts(Cost[] costs) {
        float total = 0;
        for (Cost cost : costs) {
            total += cost.getValue();
        }
        return total;
    }

    private float totalKmCosts(Cost[] costs, float cost) {
        // get mini kilom
        float min = costs[0].getMileage();
        float max = costs[0].getMileage();
        for (Cost costV : costs) {
            if (costV.getMileage() < min) {
                min = costV.getMileage();
            }
            if (costV.getMileage() > max) {
                max = costV.getMileage();
            }
        }
        // get max kilom
        // diff max -min
        // total cost / diff
        return cost / (max - min);
    }


    private float totalCostEarning(Earning[] earns) {
        float total = 0;
        for (Earning earn : earns) {
            total += earn.getValue();
        }
        return total;
    }

    private float totalKmEarning(Earning[] earns, float cost) {
        // get mini kilom
        float min = earns[0].getMileage();
        float max = earns[0].getMileage();
        for (Earning earn : earns) {
            if (earn.getMileage() < min) {
                min = earn.getMileage();
            }
            if (earn.getMileage() > max) {
                max = earn.getMileage();
            }
        }
        // get max kilom
        // diff max -min
        // total cost / diff
        return cost / (max - min);
    }
}
