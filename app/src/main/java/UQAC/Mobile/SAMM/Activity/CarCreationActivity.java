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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import UQAC.Mobile.SAMM.API.NetworkManager;
import UQAC.Mobile.SAMM.Base.Car;
import UQAC.Mobile.SAMM.Base.History;
import UQAC.Mobile.SAMM.R;

/**
 * This activity shows the form to create a new car
 */

public class CarCreationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private FloatingActionButton backButton;

    private Spinner spinner;
    private EditText nom;
    private EditText marque;
    private EditText modele;
    private EditText numImmat;
    private EditText typeCarbu;
    private EditText capacite;
    private EditText kilometrage;
    private Button creation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_vehicule);

        findViewInLayout();
        updateSpinner();
        setOnClickListeners();
    }

    private void findViewInLayout() {
        backButton = findViewById(R.id.backButtonCreationV);

        spinner = findViewById(R.id.spinner);
        nom = findViewById(R.id.nameV);
        marque = findViewById(R.id.brandV);
        modele = findViewById(R.id.modelV);
        numImmat = findViewById(R.id.immatriculation);
        typeCarbu = findViewById(R.id.carburant);
        capacite = findViewById(R.id.capacityV);
        kilometrage = findViewById(R.id.text_view_mileage);
        creation = findViewById(R.id.buttonCreate);
    }

    private void updateSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_vehicule, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    private void setOnClickListeners() {
        backButton.setOnClickListener((View view) -> {
            Intent returnMenuIntent = new Intent(CarCreationActivity.this, CarActivity.class);
            startActivity(returnMenuIntent);
        });

        creation.setOnClickListener((View view) -> {
            if (!nom.getText().toString().isEmpty() && !marque.getText().toString().isEmpty() && !modele.getText().toString().isEmpty() && !numImmat.getText().toString().isEmpty() && !typeCarbu.getText().toString().isEmpty() && !capacite.getText().toString().isEmpty() && !kilometrage.getText().toString().isEmpty()) {
                Car car = new Car(new History(), null /*pour le moment je met nul mais à changer*/, Integer.parseInt(kilometrage.getText().toString()), 2000, typeCarbu.getText().toString(), Integer.parseInt(capacite.getText().toString()), spinner.getAdapter().toString(), marque.getText().toString(), modele.getText().toString(), nom.getText().toString());
                NetworkManager.createCar(car);
                Intent returnMenuIntent = new Intent(CarCreationActivity.this, CarActivity.class);
                startActivity(returnMenuIntent);
            } else {
                Toast.makeText(CarCreationActivity.this, "Champ manquant ou mal complété !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // TODO : is this useless ?
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
