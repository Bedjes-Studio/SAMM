package UQAC.Mobile.SAMM;

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
        TextView totalConsoValue = findViewById(R.id.totalConsoValue);

        TextView totalMaintValue = findViewById(R.id.totalMaintValue);
        TextView totalMaintDayValue = findViewById(R.id.totalMaintDayValue);

        TextView totalEarnValue = findViewById(R.id.totalEarnValue);
        TextView totalEarnDayValue = findViewById(R.id.totalEarnDayValue);

        // fuel
        NetworkCallback callbackFuel = new NetworkCallback() {
            @Override
            public void onActionSuccess(Refuel[] refuels) {
                totalFuelValue.setText(String.valueOf(totalCostRefuel(refuels)));
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
                totalEarnValue.setText(String.valueOf(totalCostCosts(costs)));
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

    public float totalKmRefuel(Refuel[] refuels) {
        // get mini kilom
        // get max kilom
        // diff max -min
        // total cost / diff
        return 0;
    }

    public float totalCostCosts(Cost[] costs) {
        float total =0;
        for (Cost cost : costs) {
            total += cost.getValue();
        }
        return total;
    }


    public float totalCostEarning(Earning[] earns) {
        float total =0;
        for (Earning earn : earns) {
            total += earn.getValue();
        }
        return total;
    }
}
