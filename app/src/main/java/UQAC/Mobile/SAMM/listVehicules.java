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
import java.util.Calendar;
import java.util.List;

public class listVehicules extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        FloatingActionButton button_add_car = findViewById(R.id.button_add_car);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_car);

        // création de la callback
        NetworkCallback callback = new NetworkCallback() {

            @Override
            public void onActionSuccess(Car[] cars) {
                Toast.makeText(listVehicules.this, "Get car reussie", Toast.LENGTH_SHORT).show();

                CarAdapter adapter = new CarAdapter(listVehicules.this, cars);


                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(listVehicules.this));

                button_add_car.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent addVehiculeIntent = new Intent(listVehicules.this, addVehicule.class);
                        startActivity(addVehiculeIntent);

                        // NETWORK MANAGER TESTS

//                        Refuel refuel = new Refuel("Essence", 1.6f, 86.64f, 54.15f, Calendar.getInstance().getTime(), 180000);
//                        NetworkManager.getAllRefuel(new NetworkCallback());
//
//                        Car car = new Car("aaa");
//                        NetworkManager.getAllRefuel(car, new NetworkCallback());
                    }
                });

                adapter.setOnItemClickListener(new CarAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String id) {
                        Intent eventActivityIntent = new Intent(listVehicules.this, EventActivity.class);
                        eventActivityIntent.putExtra("id", id);
                        startActivity(eventActivityIntent);
                    }

                });

                adapter.setOnItemLongClickListener(new CarAdapter.OnItemLongClickListener() {
                    @Override
                    public void onItemLongClick(String id) {
                        Toast.makeText(listVehicules.this, "LongCLick", Toast.LENGTH_SHORT).show();
                        NetworkCallback callback1 = new NetworkCallback() {

                            @Override
                            public void onActionSuccess() {
                                Intent eventActivityIntent = new Intent(listVehicules.this, listVehicules.class);
                                eventActivityIntent.putExtra("id", id);
                                startActivity(eventActivityIntent);
                            }
                        };

                        NetworkManager.deleteCar(id, callback1);
                    }
                });
            }

            @Override
            public void onActionFailure() {
                Toast.makeText(listVehicules.this, "Get car ratee", Toast.LENGTH_SHORT).show();
            }
        };

        // appel networkmanager avec callback
        NetworkManager.getAllCar(callback);
    }

}
