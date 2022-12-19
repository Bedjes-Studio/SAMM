package UQAC.Mobile.SAMM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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


        NetworkManager.tokenCheck(this.getSharedPreferences("Usertoken", Context.MODE_PRIVATE), new NetworkCallback() {
            @Override
            public void onActionSuccess() {
                Toast.makeText(MainActivity.this, "Connection automatique reussie", Toast.LENGTH_SHORT).show();
                networkManager.createContent();
                Intent loginIntent = new Intent(MainActivity.this, listVehicules.class);
                startActivity(loginIntent);
            }

            @Override
            public void onActionFailure() {
                Toast.makeText(MainActivity.this, "Connection automatique échouée", Toast.LENGTH_SHORT).show();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // création de la callback
                NetworkCallback callback = new NetworkCallback() {
                    @Override
                    public void onActionSuccess() {
                        Toast.makeText(MainActivity.this, "Connection reussie", Toast.LENGTH_SHORT).show();
                        networkManager.createContent();
                        Intent loginIntent = new Intent(MainActivity.this, listVehicules.class);
                        startActivity(loginIntent);
                    }

                    @Override
                    public void onActionFailure() {
                        Toast.makeText(MainActivity.this, "Connection ratee", Toast.LENGTH_SHORT).show();
                    }
                };

                // appel networkmanager avec callback
                NetworkManager.login(MainActivity.this.getSharedPreferences("Usertoken", Context.MODE_PRIVATE), username.getText().toString(), password.getText().toString(), callback);
            }
        });
    }

    public void setSignIn(View v) {
        Intent signInIntent = new Intent(MainActivity.this, SignIn.class);
        //addNoteIntent.putExtra("title", "Titre de la note");
        startActivity(signInIntent);

    }
}