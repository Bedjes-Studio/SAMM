package UQAC.Mobile.SAMM.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import UQAC.Mobile.SAMM.API.NetworkCallback;
import UQAC.Mobile.SAMM.API.NetworkManager;
import UQAC.Mobile.SAMM.R;

public class LoginActivity extends AppCompatActivity {
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
        boolean autologin = true;
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            autologin = intent.getExtras().getBoolean("autologin", true);
        }
        if (autologin) {
        NetworkManager.tokenCheck(this.getSharedPreferences("Usertoken", Context.MODE_PRIVATE), new NetworkCallback() {
            @Override
            public void onActionSuccess() {
                Toast.makeText(LoginActivity.this, "Connection automatique reussie", Toast.LENGTH_SHORT).show();
                networkManager.createContent();
                Intent loginIntent = new Intent(LoginActivity.this, CarActivity.class);
                startActivity(loginIntent);
            }

            @Override
            public void onActionFailure() {
                Toast.makeText(LoginActivity.this, "Connection automatique échouée", Toast.LENGTH_SHORT).show();
            }
        });}

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // création de la callback
                NetworkCallback callback = new NetworkCallback() {
                    @Override
                    public void onActionSuccess() {
                        Toast.makeText(LoginActivity.this, "Connection reussie", Toast.LENGTH_SHORT).show();
                        networkManager.createContent();
                        Intent loginIntent = new Intent(LoginActivity.this, CarActivity.class);
                        startActivity(loginIntent);
                    }

                    @Override
                    public void onActionFailure() {
                        Toast.makeText(LoginActivity.this, "Connection ratee", Toast.LENGTH_SHORT).show();
                    }
                };

                // appel networkmanager avec callback
                NetworkManager.login(LoginActivity.this.getSharedPreferences("Usertoken", Context.MODE_PRIVATE), username.getText().toString(), password.getText().toString(), callback);
            }
        });
    }

    public void setSignIn(View v) {
        Intent signInIntent = new Intent(LoginActivity.this, SignInActivity.class);
        //addNoteIntent.putExtra("title", "Titre de la note");
        startActivity(signInIntent);

    }
}