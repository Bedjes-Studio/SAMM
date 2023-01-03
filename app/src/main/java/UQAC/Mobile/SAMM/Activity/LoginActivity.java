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

/**
 * This activity shows the login screen, this is the default view
 */

public class LoginActivity extends AppCompatActivity {

    private TextView username;
    private TextView password;
    private Button loginButton;

    private boolean isAutologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        readIntendExtras();
        if (isAutologin) {
            loginWithSavedToken();
        }
        findViewInLayout();
        setOnClickListeners();
    }

    private void readIntendExtras() {
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            isAutologin = intent.getExtras().getBoolean("autologin", true);
        }
    }

    private void findViewInLayout() {
        username = findViewById(R.id.loginUsername);
        password = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginButton);
    }

    private void loginWithSavedToken() {
        NetworkManager.tokenCheck(this.getSharedPreferences("Usertoken", Context.MODE_PRIVATE), new NetworkCallback() {
            @Override
            public void onActionSuccess() {
                Toast.makeText(LoginActivity.this, "Connection automatique reussie", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(LoginActivity.this, CarActivity.class);
                startActivity(loginIntent);
            }

            @Override
            public void onActionFailure() {
                Toast.makeText(LoginActivity.this, "Connection automatique échouée", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setOnClickListeners() {

        loginButton.setOnClickListener((View view) -> {

            NetworkCallback callback = new NetworkCallback() {
                @Override
                public void onActionSuccess() {
                    Toast.makeText(LoginActivity.this, "Connection reussie", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(LoginActivity.this, CarActivity.class);
                    startActivity(loginIntent);
                }

                @Override
                public void onActionFailure() {
                    Toast.makeText(LoginActivity.this, "Connection ratee", Toast.LENGTH_SHORT).show();
                }
            };
            NetworkManager.login(LoginActivity.this.getSharedPreferences("Usertoken", Context.MODE_PRIVATE), username.getText().toString(), password.getText().toString(), callback);
        });
    }

    // TODO : onclick event is in layout, move it to java class file
    //  see : https://stackoverflow.com/questions/21319996/android-onclick-in-xml-vs-onclicklistener
    public void setSignIn(View v) {
        Intent signInIntent = new Intent(LoginActivity.this, SignInActivity.class);
        //addNoteIntent.putExtra("title", "Titre de la note");
        startActivity(signInIntent);

    }
}