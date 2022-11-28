package UQAC.Mobile.SAMM;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        FloatingActionButton backButton= (FloatingActionButton) findViewById(R.id.backButton);
        TextView username = (TextView) findViewById(R.id.signUsername);
        TextView email = (TextView) findViewById(R.id.signEmail);
        TextView password = (TextView) findViewById(R.id.signPassword);
        TextView passwordCheck = (TextView) findViewById(R.id.signCheckPassword);

        Button createButton = (Button) findViewById(R.id.signCreate);
        /*
        createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(Login.this, "Connection réussie", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Login.this, "Connection ratée", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent backIntent = new Intent(SignIn.this, MainActivity.class);
                //addNoteIntent.putExtra("title", "Titre de la note");
                startActivity(backIntent);
            }
        });
    }
}