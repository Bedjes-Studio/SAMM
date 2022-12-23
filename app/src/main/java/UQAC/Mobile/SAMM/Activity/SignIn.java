package UQAC.Mobile.SAMM.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import UQAC.Mobile.SAMM.API.NetworkCallback;
import UQAC.Mobile.SAMM.API.NetworkManager;
import UQAC.Mobile.SAMM.R;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        FloatingActionButton backButton = (FloatingActionButton) findViewById(R.id.backButton);
        TextView username = (TextView) findViewById(R.id.signUsername);
        TextView email = (TextView) findViewById(R.id.signEmail);
        TextView password = (TextView) findViewById(R.id.signPassword);
        TextView passwordCheck = (TextView) findViewById(R.id.signCheckPassword);

        Button createButton = (Button) findViewById(R.id.signCreate);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // création de la callback
                NetworkCallback callback = new NetworkCallback() {
                    @Override
                    public void onActionSuccess() {
                        Toast.makeText(SignIn.this, "Création de compte reussie", Toast.LENGTH_SHORT).show();
                        Intent backIntent = new Intent(SignIn.this, MainActivity.class);
                        startActivity(backIntent);
                    }

                    @Override
                    public void onActionFailure() {
                        Toast.makeText(SignIn.this, "Création de compte échouée", Toast.LENGTH_SHORT).show();
                    }
                };

                // appel networkmanager avec callback
                NetworkManager.signup(username.getText().toString(), email.getText().toString(), password.getText().toString(), callback);

                /*
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(Login.this, "Connection réussie", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Login.this, "Connection ratée", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backIntent = new Intent(SignIn.this, MainActivity.class);
                //addNoteIntent.putExtra("title", "Titre de la note");
                startActivity(backIntent);
            }
        });
    }
}