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

/**
 * This activity shows the sign up screen
 */

public class SignUpActivity extends AppCompatActivity {

    private FloatingActionButton backButton;
    private TextView username;
    private TextView email;
    private TextView password;
    // TODO : check password equality
    private TextView passwordCheck;
    private Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViewInLayout();
        setOnClickListeners();
    }

    private void findViewInLayout() {
        backButton = findViewById(R.id.backButton);
        username = findViewById(R.id.signUsername);
        email = findViewById(R.id.signEmail);
        password = findViewById(R.id.signPassword);
        passwordCheck = findViewById(R.id.signCheckPassword);
        createButton = findViewById(R.id.signCreate);
    }

    private void setOnClickListeners() {
        createButton.setOnClickListener((View view) -> {
            NetworkCallback callback = new NetworkCallback() {
                @Override
                public void onActionSuccess() {
                    Toast.makeText(SignUpActivity.this, "Création de compte reussie", Toast.LENGTH_SHORT).show();
                    Intent backIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                    backIntent.putExtra("autologin", false);
                    startActivity(backIntent);
                }

                @Override
                public void onActionFailure() {
                    Toast.makeText(SignUpActivity.this, "Création de compte échouée", Toast.LENGTH_SHORT).show();
                }
            };

            NetworkManager.signup(username.getText().toString(), email.getText().toString(), password.getText().toString(), callback);
        });

        backButton.setOnClickListener((View view) -> {
            Intent backIntent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(backIntent);
        });
    }
}