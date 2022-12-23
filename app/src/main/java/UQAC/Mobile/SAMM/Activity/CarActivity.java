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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import UQAC.Mobile.SAMM.Adapter.CarAdapter;
import UQAC.Mobile.SAMM.Base.Car;
import UQAC.Mobile.SAMM.API.NetworkCallback;
import UQAC.Mobile.SAMM.API.NetworkManager;
import UQAC.Mobile.SAMM.R;

/**
 * This activity shows cars of the user
 */

public class CarActivity extends AppCompatActivity {

    private FloatingActionButton button_add_car;
    private FloatingActionButton logout;
    private FloatingActionButton info;
    private RecyclerView recyclerView;
    private CarAdapter adapter;

    private void updateRecyclerview(Car[] cars) {
        adapter = new CarAdapter(CarActivity.this, cars);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(CarActivity.this));
    }

    private void setOnClickListeners() {

        button_add_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addVehiculeIntent = new Intent(CarActivity.this, CarCreationActivity.class);
                startActivity(addVehiculeIntent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CarActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                NetworkManager.disconnect(CarActivity.this.getSharedPreferences("Usertoken", Context.MODE_PRIVATE), new NetworkCallback() {
                    @Override
                    public void onActionSuccess() {
                        Intent mainActivityIntent = new Intent(CarActivity.this, MainActivity.class);
                        mainActivityIntent.putExtra("autologin", false);
                        startActivity(mainActivityIntent);
                    }
                });
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CarActivity.this, "Info", Toast.LENGTH_SHORT).show();

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.info_vehicles, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(recyclerView, Gravity.CENTER, 0, 0);

//                        // dismiss the popup window when touched
//                        popupView.setOnTouchListener(new View.OnTouchListener() {
//                            @Override
//                            public boolean onTouch(View v, MotionEvent event) {
//                                popupWindow.dismiss();
//                                return true;
//                            }
//                        });
            }
        });

        adapter.setOnItemClickListener(new CarAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String id) {
                Intent eventActivityIntent = new Intent(CarActivity.this, EventActivity.class);
                eventActivityIntent.putExtra("id", id);
                startActivity(eventActivityIntent);
            }

        });

        adapter.setOnItemLongClickListener(new CarAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(String id) {
                Toast.makeText(CarActivity.this, "Delete car", Toast.LENGTH_SHORT).show();

                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.delete_vehicles, null);

                Button confirm = popupView.findViewById(R.id.button_delete);
                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(recyclerView, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        return true;
                    }
                });

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("ALEXIA", "delete car");

                        NetworkCallback callback1 = new NetworkCallback() {
                            @Override
                            public void onActionSuccess() {
                                Log.d("ALEXIA", "succes callback 1");
                                NetworkCallback callback2 = new NetworkCallback() {
                                    @Override
                                    public void onActionSuccess(Car[] cars) {
                                        Log.d("ALEXIA", "succes callback 2");
                                        CarAdapter adapter = new CarAdapter(CarActivity.this, cars);

                                        recyclerView.setAdapter(adapter);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(CarActivity.this));
                                    }

                                };

                                NetworkManager.getAllCar(callback2);
                            }

                        };

                        NetworkManager.deleteCar(id, callback1);
                        popupWindow.dismiss();
                    }

                });

            }
        });

    }

    // création de la callback
    private final NetworkCallback callback = new NetworkCallback() {

        @Override
        public void onActionSuccess(Car[] cars) {
            // Toast.makeText(CarActivity.this, "Get car reussie", Toast.LENGTH_SHORT).show();
            updateRecyclerview(cars);
            setOnClickListeners();
        }

        @Override
        public void onActionFailure() {
            // Toast.makeText(CarActivity.this, "Get car ratee", Toast.LENGTH_SHORT).show();
        }
    };

    private void findViewInLayout() {
        button_add_car = findViewById(R.id.button_add_car);
        logout = findViewById(R.id.logout);
        info = findViewById(R.id.info);
        recyclerView = findViewById(R.id.recycler_view_car);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        findViewInLayout();
        NetworkManager.getAllCar(callback);
    }
}
