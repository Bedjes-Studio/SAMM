package UQAC.Mobile.SAMM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class listVehicules extends AppCompatActivity{

    ArrayList<Car> carModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        FloatingActionButton button_add_car = findViewById(R.id.button_add_car);

        button_add_car.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent addVehiculeIntent = new Intent(listVehicules.this, addVehicule.class);
                //addNoteIntent.putExtra("title", "Titre de la note");
                startActivity(addVehiculeIntent);
            }
        });
    }

    private void setUpListVehicule(){

    }
}
