package UQAC.Mobile.SAMM.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import UQAC.Mobile.SAMM.Base.Cost;
import UQAC.Mobile.SAMM.API.NetworkManager;
import UQAC.Mobile.SAMM.R;

public class RepairActivity extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();
    String myFuelType;

    FloatingActionButton back;
    Button save;

    EditText reason;
    EditText cost;
    EditText payementMethod;
    EditText mileage;
    EditText dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);

        reason = findViewById(R.id.reasonText);
        cost = findViewById(R.id.costText);
        payementMethod = findViewById(R.id.payementMethodText);
        mileage = findViewById(R.id.mileageText);

        Intent intent = getIntent();
        String id = intent.getExtras().getString("id");
        Log.d("ALEXIA", id);

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
                new DatePickerDialog(RepairActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // --Save
        save = findViewById(R.id.button_save_event);

        back = findViewById(R.id.backButtonCreationV);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(getApplicationContext(),"save", Toast.LENGTH_SHORT).show();
                if( !reason.getText().toString().isEmpty() && !cost.getText().toString().isEmpty() && !payementMethod.getText().toString().isEmpty() && !mileage.getText().toString().isEmpty()){
                    String reasonValue = reason.getText().toString();
                    Float costValue = Float.valueOf(cost.getText().toString());
                    String payementMethodValue = payementMethod.getText().toString();
                    Integer mileageValue = Integer.valueOf(mileage.getText().toString());

                    Cost cost = new Cost(costValue, reasonValue, payementMethodValue, myCalendar.getTime(),  mileageValue);

                    //Creer nouveau véhicule ici pour la bdd
                    //Car vehicule = new Car(new History(), null /*pour le moment je met nul mais à changer*/, Integer.parseInt(kilometrage.getText().toString()), typeCarbu.getText().toString(), Integer.parseInt(capacite.getText().toString()), spinner.getAdapter().toString(), marque.getText().toString(), modele.getText().toString(), nom.getText().toString());
                    NetworkManager.createCost(cost, id);
                    Intent returnMenuIntent = new Intent(RepairActivity.this, EventActivity.class);
                    returnMenuIntent.putExtra("id", id);
                    startActivity(returnMenuIntent);
                }else{
                    Toast.makeText(RepairActivity.this, "Champ manquant ou mal complété !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnMenuIntent = new Intent(RepairActivity.this, EventActivity.class);
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