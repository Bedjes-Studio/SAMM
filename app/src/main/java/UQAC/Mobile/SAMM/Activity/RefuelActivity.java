package UQAC.Mobile.SAMM.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class RefuelActivity extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();
    String myFuelType;

    NetworkManager networkManager = new NetworkManager();

    FloatingActionButton back;
    Button save;

    EditText litterPrice;
    EditText totalCost;
    EditText litter;
    EditText mileage;
    EditText dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refuel);

        litterPrice = findViewById(R.id.litterPriceText);
        totalCost = findViewById(R.id.totalCostText);
        litter = findViewById(R.id.litterText);
        mileage = findViewById(R.id.mileageText);

        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        Log.d("ALEXIA", id);

        //https://andrologiciels.wordpress.com/astuces-android/divers-2/quitter-une-application/liste-deroulante-spinner/
        //-- Drop Down Fuel Type
        final Spinner spinnerFuelType = (Spinner) findViewById(R.id.spinnerFuelType);
        String[] lFuelType = {"Gasoline","Diesel Fuel", "Bio-diesel", "Ethanol"};
        ArrayAdapter<String> dataAdapterF = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lFuelType);
        dataAdapterF.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFuelType.setAdapter(dataAdapterF);

        //-- Gestion du Click sur la liste Fuel type
        spinnerFuelType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
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

        //-- Date Picker
        dateText = (EditText) findViewById(R.id.dateText);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateText.setText(dateFormat.format(new Date())); // it will show 16/07/2013

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RefuelActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // --Save
        save = findViewById(R.id.button_save_event);

        back = findViewById(R.id.backButtonCreationV);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getApplicationContext(),"save", Toast.LENGTH_SHORT).show();
                if( !litterPrice.getText().toString().isEmpty() && !totalCost.getText().toString().isEmpty() && !litter.getText().toString().isEmpty() && !mileage.getText().toString().isEmpty()){
                    Float litterPriceValue = Float.valueOf(litterPrice.getText().toString());
                    Float totalCostValue = Float.valueOf(totalCost.getText().toString());
                    Float litterValue = Float.valueOf(litter.getText().toString());
                    Integer mileageValue = Integer.valueOf(mileage.getText().toString());

                    Refuel refuel = new Refuel(myFuelType, litterPriceValue, totalCostValue, litterValue, myCalendar.getTime(),  mileageValue);

                    //Creer nouveau véhicule ici pour la bdd
                    //Car vehicule = new Car(new History(), null /*pour le moment je met nul mais à changer*/, Integer.parseInt(kilometrage.getText().toString()), typeCarbu.getText().toString(), Integer.parseInt(capacite.getText().toString()), spinner.getAdapter().toString(), marque.getText().toString(), modele.getText().toString(), nom.getText().toString());
                    networkManager.createRefuel(refuel, id);
                    Intent returnMenuIntent = new Intent(RefuelActivity.this, EventActivity.class);
                    returnMenuIntent.putExtra("id", id);
                    startActivity(returnMenuIntent);
                }else{
                    Toast.makeText(RefuelActivity.this, "Champ manquant ou mal complété !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnMenuIntent = new Intent(RefuelActivity.this, EventActivity.class);
                returnMenuIntent.putExtra("id", id);
                startActivity(returnMenuIntent);
            }
        });
    }

    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        dateText.setText(dateFormat.format(myCalendar.getTime()));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}