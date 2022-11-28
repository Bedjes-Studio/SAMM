package UQAC.Mobile.SAMM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //TextView signIn = findViewById(R.id.signIn);
    NetworkManager networkManager = new NetworkManager();

    Button eventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = findViewById(R.id.loginUsername);
        TextView password = findViewById(R.id.loginPassword);

        Button loginButton = findViewById(R.id.loginButton);

//        eventButton = findViewById(R.id.buttonEvent);
//
//        eventButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
//                Intent eventActivityIntent = new Intent(MainActivity.this, EventActivity.class);
//                startActivity(eventActivityIntent);
//            }
//        });

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this, "Connection reussie", Toast.LENGTH_SHORT).show();
                    networkManager.createContent();
                    Intent loginIntent = new Intent(MainActivity.this, listVehicules.class);
                    //addNoteIntent.putExtra("title", "Titre de la note");
                    startActivity(loginIntent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Connection ratee", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setSignIn(View v){
        Intent signInIntent = new Intent(MainActivity.this, SignIn.class);
        //addNoteIntent.putExtra("title", "Titre de la note");
        startActivity(signInIntent);
        
    }
}