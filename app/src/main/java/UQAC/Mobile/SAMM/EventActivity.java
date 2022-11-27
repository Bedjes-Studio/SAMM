package UQAC.Mobile.SAMM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
}