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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import UQAC.Mobile.SAMM.API.NetworkManager;
import UQAC.Mobile.SAMM.R;
import UQAC.Mobile.SAMM.Base.Refuel;

/**
 * This activity shows the form to create refuel
 */

public class RefuelActivity extends AppCompatActivity {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy", Locale.US);
    private final Calendar myCalendar = Calendar.getInstance();

    private String myFuelType;


    private FloatingActionButton back;
    private Button save;

    private EditText litterPrice;
    private EditText totalCost;
    private EditText litter;
    private EditText mileage;
    private EditText dateText;
    private Spinner spinnerFuelType;

    private String intentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuel);

        readIntendExtras();
        findViewInLayout();
        updateRecyclerview();
        setOnClickListeners();
    }

    private void readIntendExtras() {
        Intent intent = getIntent();
        intentId = intent.getExtras().getString("id");
    }

    private void findViewInLayout() {
        spinnerFuelType = findViewById(R.id.spinnerFuelType);
        litterPrice = findViewById(R.id.litterPriceText);
        totalCost = findViewById(R.id.totalCostText);
        litter = findViewById(R.id.litterText);
        mileage = findViewById(R.id.mileageText);
        save = findViewById(R.id.button_save_event);
        back = findViewById(R.id.backButtonCreationV);
        dateText = findViewById(R.id.dateText);
        dateText.setText(dateFormat.format(new Date()));
    }

    private void updateRecyclerview() {
        //https://andrologiciels.wordpress.com/astuces-android/divers-2/quitter-une-application/liste-deroulante-spinner/
        //-- Drop Down Fuel Type
        ArrayAdapter<String> dataAdapterF = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Refuel.FUEL_TYPE);
        dataAdapterF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFuelType.setAdapter(dataAdapterF);
    }

    // TODO : clean this code
    private void setOnClickListeners() {

        spinnerFuelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                myFuelType = String.valueOf(spinnerFuelType.getSelectedItem());
//                Toast.makeText(RefuelActivity.this,
//                        "OnClickListener : " +
//                                "\nSpinner 1 : " + myFuelType,
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        DatePickerDialog.OnDateSetListener date = (DatePicker view, int year, int month, int day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            dateText.setText(dateFormat.format(myCalendar.getTime()));
        };

        dateText.setOnClickListener((View view) -> {
            new DatePickerDialog(RefuelActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        save.setOnClickListener((View view) -> {

            //Toast.makeText(getApplicationContext(),"save", Toast.LENGTH_SHORT).show();
            if (!litterPrice.getText().toString().isEmpty() && !totalCost.getText().toString().isEmpty() && !litter.getText().toString().isEmpty() && !mileage.getText().toString().isEmpty()) {
                float litterPriceValue = Float.parseFloat(litterPrice.getText().toString());
                float totalCostValue = Float.parseFloat(totalCost.getText().toString());
                float litterValue = Float.parseFloat(litter.getText().toString());
                int mileageValue = Integer.parseInt(mileage.getText().toString());

                Refuel refuel = new Refuel(myFuelType, litterPriceValue, totalCostValue, litterValue, myCalendar.getTime(), mileageValue);

                //Creer nouveau véhicule ici pour la bdd
                //Car vehicule = new Car(new History(), null /*pour le moment je met nul mais à changer*/, Integer.parseInt(kilometrage.getText().toString()), typeCarbu.getText().toString(), Integer.parseInt(capacite.getText().toString()), spinner.getAdapter().toString(), marque.getText().toString(), modele.getText().toString(), nom.getText().toString());
                NetworkManager.createRefuel(refuel, intentId);
                Intent returnMenuIntent = new Intent(RefuelActivity.this, EventActivity.class);
                returnMenuIntent.putExtra("id", intentId);
                startActivity(returnMenuIntent);
            } else {
                Toast.makeText(RefuelActivity.this, "Champ manquant ou mal complété !", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener((View view) -> {
            Intent returnMenuIntent = new Intent(RefuelActivity.this, EventActivity.class);
            returnMenuIntent.putExtra("id", intentId);
            startActivity(returnMenuIntent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}