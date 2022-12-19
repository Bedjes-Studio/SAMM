package UQAC.Mobile.SAMM;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Statistiques extends AppCompatActivity {

    List<Refuel> refuels = new ArrayList<Refuel>();
    //List<Maintenance> maintenances = new ArrayList<Maintenance>();
    List<Earning> earns = new ArrayList<Earning>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        TextView totalFuelValue = findViewById(R.id.totalFuelValue);
        TextView totalFuelDayValue = findViewById(R.id.totalFuelDayValue);
        TextView totalConsoValue = findViewById(R.id.totalConsoValue);

        TextView totalMaintValue = findViewById(R.id.totalMaintValue);
        TextView totalMaintDayValue = findViewById(R.id.totalMaintDayValue);

        TextView totalEarnValue = findViewById(R.id.totalEarnValue);
        TextView totalEarnDayValue = findViewById(R.id.totalEarnDayValue);

        refuels = generateDataFuel();
        //maintenances = generateDataMaint();
        earns = generateDataEarn();

        //total fuel
        float totalrefuel = totalCostRefuel(refuels);
        totalFuelValue.setText(String.valueOf(totalrefuel));
/*
        //total maintenance
        float totalmaint = totalCostMaint(maintenances);
        totalMaintValue.setText(String.valueOf(totalmaint));*/

        //total earn
        float totalearn = totalEarn(earns);
        totalEarnValue.setText(String.valueOf(totalearn));
    }

    public List<Refuel> generateDataFuel() {
        List<Refuel> refuels = new ArrayList<Refuel>();
        Refuel refuel1 = new Refuel("Essence", 1.6f, 86.64f, 54.15f, Calendar.getInstance().getTime(), 180000);
        Refuel refuel2 = new Refuel("Essence", 1.5f, 75f, 50f, Calendar.getInstance().getTime(), 175000);

        refuels.add(refuel1);
        refuels.add(refuel2);
        return refuels;
    }/*
    public List<Maintenance> generateDataMaint() {
        List<Maintenance> maintenances = new ArrayList<Maintenance>();
        Maintenance maint1 = new Maintenance();
        Maintenance maint2 = new Maintenance();

        maintenances.add(maint1);
        maintenances.add(maint2);
        return maintenances;
    }*/
    public List<Earning> generateDataEarn() {
        List<Earning> earnings = new ArrayList<Earning>();
        Earning earn1 = new Earning("test1", 50, Calendar.getInstance().getTime(), 180000);
        Earning earn2 = new Earning("test2", 42, Calendar.getInstance().getTime(), 175000);

        earnings.add(earn1);
        earnings.add(earn2);
        return earnings;
    }

    public float totalCostRefuel(List<Refuel> refuels) {
        float total =0;
        for (Refuel refuel : refuels) {
            total += refuel.getTotalCost();
        }
        return total;
    }/*
    public float totalCostMaint(List<Maintenance> maintenances) {
        float total =0;
        for (Maintenance maintenance : maintenances) {
            total += maintenance.get();
        }
        return total;
    }*/
    public float totalEarn(List<Earning> earns) {
        float total =0;
        for (Earning earn : earns) {
            total += earn.getValue();
        }
        return total;
    }
}
