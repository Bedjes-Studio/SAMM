package UQAC.Mobile.SAMM.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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
import UQAC.Mobile.SAMM.Activity.EventActivity;
import UQAC.Mobile.SAMM.Base.Earning;
import UQAC.Mobile.SAMM.R;

public class EarningActivity extends AppCompatActivity {

    private final static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy", Locale.US);

    final Calendar myCalendar = Calendar.getInstance();

    FloatingActionButton back;
    Button save;

    EditText value;
    EditText reason;
    EditText mileage;
    EditText dateText;

    private String intentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning);

        readIntendExtras();
        findViewInLayout();
        setOnClickListeners();
    }

    private void readIntendExtras() {
        Intent intent = getIntent();
        intentId = intent.getExtras().getString("id");
    }

    private void findViewInLayout() {
        value = findViewById(R.id.valueText);
        reason = findViewById(R.id.reasonText);
        mileage = findViewById(R.id.mileageText);
        save = findViewById(R.id.button_save_event);
        back = findViewById(R.id.backButtonCreationV);
        dateText = (EditText) findViewById(R.id.dateText);
        dateText.setText(dateFormat.format(new Date()));
    }

    private void setOnClickListeners() {

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);
                dateText.setText(dateFormat.format(myCalendar.getTime()));
            }
        };

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(EarningActivity.this, date,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // TODO : clean this listener
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"save", Toast.LENGTH_SHORT).show();
                if (!reason.getText().toString().isEmpty() && !value.getText().toString().isEmpty() && !mileage.getText().toString().isEmpty()) {//!nom.getText().toString().isEmpty() && !marque.getText().toString().isEmpty() && !modele.getText().toString().isEmpty() && !numImmat.getText().toString().isEmpty() && !typeCarbu.getText().toString().isEmpty() && !capacite.getText().toString().isEmpty() && !kilometrage.getText().toString().isEmpty()){

                    Float valueValue = Float.valueOf(value.getText().toString());
                    String reasonValue = reason.getText().toString();
                    Integer mileageValue = Integer.valueOf(mileage.getText().toString());

                    Earning earning = new Earning(reasonValue, valueValue, myCalendar.getTime(), mileageValue);

                    earning.save(earning);

                    NetworkManager.createEearning(earning, intentId);
                    Intent returnMenuIntent = new Intent(EarningActivity.this, EventActivity.class);
                    returnMenuIntent.putExtra("id", intentId);
                    startActivity(returnMenuIntent);

                } else {
                    Toast.makeText(EarningActivity.this, "Champ manquant ou mal complété !", Toast.LENGTH_SHORT).show();
                }

//                Intent eventActivityIntent = new Intent(EarningActivity.this, EventActivity.class);
//                startActivity(eventActivityIntent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnMenuIntent = new Intent(EarningActivity.this, EventActivity.class);
                returnMenuIntent.putExtra("id", intentId);
                startActivity(returnMenuIntent);
            }
        });
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