package UQAC.Mobile.SAMM;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class addVehicule extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vehicule);


        FloatingActionButton backButton = findViewById(R.id.backButtonCreationV);

        //Info vehicule
        Spinner spinner = findViewById(R.id.spinner);
        EditText nom = findViewById(R.id.nameV);
        EditText marque = findViewById(R.id.brandV);
        EditText modele = findViewById(R.id.modelV);
        EditText numImmat = findViewById(R.id.immatriculation);
        EditText typeCarbu = findViewById(R.id.carburant);
        EditText capacite = findViewById(R.id.capacityV);
        EditText kilometrage = findViewById(R.id.text_view_mileage);
        Button creation = findViewById(R.id.buttonCreate);

        NetworkManager networkManager = new NetworkManager();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_vehicule, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent returnMenuIntent = new Intent(addVehicule.this, listVehicules.class);
                startActivity(returnMenuIntent);
            }
        });

        creation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!nom.getText().toString().isEmpty() && !marque.getText().toString().isEmpty() && !modele.getText().toString().isEmpty() && !numImmat.getText().toString().isEmpty() && !typeCarbu.getText().toString().isEmpty() && !capacite.getText().toString().isEmpty() && !kilometrage.getText().toString().isEmpty()){
                    //Creer nouveau véhicule ici pour la bdd
                    Car vehicule = new Car(new History(), null /*pour le moment je met nul mais à changer*/, Integer.parseInt(kilometrage.getText().toString()), 2000, typeCarbu.getText().toString(), Integer.parseInt(capacite.getText().toString()), spinner.getAdapter().toString(), marque.getText().toString(), modele.getText().toString(), nom.getText().toString());
                    NetworkManager.createCar(vehicule);
                    Intent returnMenuIntent = new Intent(addVehicule.this, listVehicules.class);
                    startActivity(returnMenuIntent);
                }else{
                    Toast.makeText(addVehicule.this, "Champ manquant ou mal complété !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
