package UQAC.Mobile.SAMM;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class listVehicules extends AppCompatActivity{

    ArrayList<Car> carModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        FloatingActionButton button_add_car = findViewById(R.id.button_add_car);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_car);

        carModels.add(new Car(new History(), new ArrayList<>(), 5000, "Essence", 100, "Voiture", "Hyundai", "I20", "Voiture1"));
        carModels.add(new Car(new History(), new ArrayList<>(), 5000, "Diesel", 200, "Voiture", "Hyundai", "I30", "Voiture2"));

        CarAdapter adapter = new CarAdapter(this, carModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button_add_car.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent addVehiculeIntent = new Intent(listVehicules.this, addVehicule.class);
                //addNoteIntent.putExtra("title", "Titre de la note");
                startActivity(addVehiculeIntent);
            }
        });
    }
}
