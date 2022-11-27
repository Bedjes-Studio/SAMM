package UQAC.Mobile.SAMM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button eventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eventButton = findViewById(R.id.buttonEvent);

        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent eventActivityIntent = new Intent(MainActivity.this, EventActivity.class);
                startActivity(eventActivityIntent);
            }
        });
    }
}