package UQAC.Mobile.SAMM.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import UQAC.Mobile.SAMM.API.NetworkCallback;
import UQAC.Mobile.SAMM.API.NetworkManager;
import UQAC.Mobile.SAMM.Base.Cost;
import UQAC.Mobile.SAMM.Base.Earning;
import UQAC.Mobile.SAMM.Base.Refuel;
import UQAC.Mobile.SAMM.R;

public class Statistiques extends AppCompatActivity {

    List<Refuel> refuels = new ArrayList<Refuel>();
    //List<Maintenance> maintenances = new ArrayList<Maintenance>();
    List<Earning> earns = new ArrayList<Earning>();
    FloatingActionButton backButtonStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        Log.d("ALEXIA", id);

        backButtonStat = findViewById(R.id.backButtonStat);

        TextView totalFuelValue = findViewById(R.id.totalFuelValue);
        TextView totalFuelDayValue = findViewById(R.id.totalFuelDayValue);

        TextView totalMaintValue = findViewById(R.id.totalMaintValue);
        TextView totalMaintDayValue = findViewById(R.id.totalMaintDayValue);

        TextView totalEarnValue = findViewById(R.id.totalEarnValue);
        TextView totalEarnDayValue = findViewById(R.id.totalEarnDayValue);

        // fuel
        NetworkCallback callbackFuel = new NetworkCallback() {
            @Override
            public void onActionSuccess(Refuel[] refuels) {
                totalFuelValue.setText(String.valueOf(totalCostRefuel(refuels)));
                totalFuelDayValue.setText(String.valueOf(totalKmRefuel(refuels, Float.valueOf(totalFuelValue.getText().toString()))));
            }
        };
        NetworkManager.getAllRefuel(id, callbackFuel);

        // earnings
        NetworkCallback callbackEarnings = new NetworkCallback() {
            @Override
            public void onActionSuccess(Earning[] earnings) {
                totalEarnValue.setText(String.valueOf(totalCostEarning(earnings)));
            }
        };
        NetworkManager.getAllEarning(id, callbackEarnings);

        // costs
        NetworkCallback callbackCost = new NetworkCallback() {
            @Override
            public void onActionSuccess(Cost[] costs) {
                totalMaintValue.setText(String.valueOf(totalCostCosts(costs)));
            }
        };
        NetworkManager.getAllCost(id, callbackCost);

        backButtonStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnMenuIntent = new Intent(Statistiques.this, EventActivity.class);
                returnMenuIntent.putExtra("id", id);
                startActivity(returnMenuIntent);
            }
        });
    }



    public float totalCostRefuel(Refuel[] refuels) {
        float total =0;
        for (Refuel refuel : refuels) {
            total += refuel.getTotalCost();
        }
        return total;
    }

    public float totalKmRefuel(Refuel[] refuels, float cost) {
        // get mini kilom
        // get max kilom
        float min = refuels[0].getMileage();
        float max = refuels[0].getMileage();
        for (Refuel refuel : refuels) {
            if(refuel.getMileage() < min){
                min = refuel.getMileage();
            }
            if(refuel.getMileage() > max){
                max = refuel.getMileage();
            }
        }
        // diff max -min
        // total cost / diff
        if(max == min){
            return cost;
        }else{
            return cost/(max-min);
        }
    }

    public float totalCostCosts(Cost[] costs) {
        float total =0;
        for (Cost cost : costs) {
            total += cost.getValue();
        }
        return total;
    }
    public float totalKmCosts(Cost[] costs, float cost) {
        // get mini kilom
        float min = costs[0].getMileage();
        float max = costs[0].getMileage();
        for (Cost costV : costs) {
            if(costV.getMileage() < min){
                min = costV.getMileage();
            }
            if(costV.getMileage() > max){
                max = costV.getMileage();
            }
        }
        // get max kilom
        // diff max -min
        // total cost / diff
        return cost/(max-min);
    }


    public float totalCostEarning(Earning[] earns) {
        float total =0;
        for (Earning earn : earns) {
            total += earn.getValue();
        }
        return total;
    }
    public float totalKmEarning(Earning[] earns, float cost) {
        // get mini kilom
        float min = earns[0].getMileage();
        float max = earns[0].getMileage();
        for (Earning earn : earns) {
            if(earn.getMileage() < min){
                min = earn.getMileage();
            }
            if(earn.getMileage() > max){
                max = earn.getMileage();
            }
        }
        // get max kilom
        // diff max -min
        // total cost / diff
        return cost/(max-min);
    }
}
