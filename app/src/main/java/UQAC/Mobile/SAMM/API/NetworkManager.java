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

package UQAC.Mobile.SAMM.API;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import UQAC.Mobile.SAMM.API.APIPojo.CreateCar;
import UQAC.Mobile.SAMM.API.APIPojo.getAllCars;
import UQAC.Mobile.SAMM.API.APIPojo.CostCreate;
import UQAC.Mobile.SAMM.API.APIPojo.CostGetAll;
import UQAC.Mobile.SAMM.API.APIPojo.DeleteSelector;
import UQAC.Mobile.SAMM.API.APIPojo.EarningCreate;
import UQAC.Mobile.SAMM.API.APIPojo.EarningGetAll;
import UQAC.Mobile.SAMM.API.APIPojo.Login;
import UQAC.Mobile.SAMM.API.APIPojo.RefuelCreate;
import UQAC.Mobile.SAMM.API.APIPojo.RefuelGetAll;
import UQAC.Mobile.SAMM.API.APIPojo.Signup;
import UQAC.Mobile.SAMM.API.APIPojo.TokenCheck;
import UQAC.Mobile.SAMM.API.APIPojo.Online;
import UQAC.Mobile.SAMM.Base.Car;
import UQAC.Mobile.SAMM.Base.Cost;
import UQAC.Mobile.SAMM.Base.Earning;
import UQAC.Mobile.SAMM.Base.Event;
import UQAC.Mobile.SAMM.Base.Refuel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is the public interface for API requests
 */
public class NetworkManager {

    private static final APIInterface API_INTERFACE = getClient().create(APIInterface.class);

    private static final List<Event> events = new ArrayList<>();

    private static String token = null;

    private static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder().baseUrl("http://34.236.157.55:6422").addConverterFactory(GsonConverterFactory.create()).client(client).build();
    }


    private static String readSavedToken(SharedPreferences sharedPref) {
        String savedToken = sharedPref.getString("token", null);
        Log.d("API", "token read : " + savedToken);
        return savedToken;
    }

    private static void saveNewToken(SharedPreferences sharedPref, String newToken) {
        token = "Bearer " + newToken;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("token", token);
        editor.apply();
    }

    private static void deleteToken(SharedPreferences sharedPref) {
        token = null;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("token");
        editor.apply();
    }


    /**
     * GET online server status
     **/
    public static void checkConnection(NetworkCallback callback) {
        Log.d("API", "get Online status");
        Call<Online.Response> call = API_INTERFACE.isOnline();

        call.enqueue(new Callback<Online.Response>() {
            @Override
            public void onResponse(Call<Online.Response> call, Response<Online.Response> response) {

                // TODO : remove message from server ?
                if (response.code() == 200) {
                    // Online.Response resource = response.body();
                    // String text = resource.message;
                    callback.onActionSuccess();
                }
            }
            @Override
            public void onFailure(Call<Online.Response> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void signup(String name, String email, String password, NetworkCallback callback) {
        Log.d("API", "signup");
        Call<Signup.Response> call = API_INTERFACE.signup(new Signup.Request(name, email, password));

        call.enqueue(new Callback<Signup.Response>() {
            @Override
            public void onResponse(Call<Signup.Response> call, Response<Signup.Response> response) {
                if (response.code() == 201) {
                    callback.onActionSuccess();
                } else {
                    callback.onActionFailure();
                }
            }

            @Override
            public void onFailure(Call<Signup.Response> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void tokenCheck(SharedPreferences sharedPref, NetworkCallback callback) {
        Log.d("API", "tokenCheck");
        token = readSavedToken(sharedPref);
        Call<TokenCheck> call = API_INTERFACE.tokenCheck(token);

        call.enqueue(new Callback<TokenCheck>() {
            @Override
            public void onResponse(Call<TokenCheck> call, Response<TokenCheck> response) {
                if (response.code() == 200) {
                    callback.onActionSuccess();
                } else {
                    callback.onActionFailure();
                }
            }

            @Override
            public void onFailure(Call<TokenCheck> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void login(SharedPreferences sharedPref, String email, String password, NetworkCallback callback) {
        Log.d("API", "login");
        Call<Login.Response> call = API_INTERFACE.login(new Login.Request(email, password));

        call.enqueue(new Callback<Login.Response>() {
            @Override
            public void onResponse(Call<Login.Response> call, Response<Login.Response> response) {
                if (response.code() == 200) {
                    Login.Response data = response.body();
                    saveNewToken(sharedPref, data.token);
                    callback.onActionSuccess();
                } else {
                    callback.onActionFailure();
                }
            }

            @Override
            public void onFailure(Call<Login.Response> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void disconnect(SharedPreferences sharedPref, NetworkCallback callback) {
        Log.d("API", "disconnect");
        deleteToken(sharedPref);
        callback.onActionSuccess();
    }

    // TODO : update API to link with specs
    // TODO : add callback to popup when car is created
    public static void createCar(Car car) {
        Log.d("API", "create car");
        Call<CreateCar> call = API_INTERFACE.carCreate(token, new CreateCar.Request(car));

        call.enqueue(new Callback<CreateCar>() {
            @Override
            public void onResponse(Call<CreateCar> call, Response<CreateCar> response) {
                if (response.code() == 201) {
                    // TODO : get specs here
                }
            }

            @Override
            public void onFailure(Call<CreateCar> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void getAllCars(NetworkCallback callback) {
        Log.d("API", "get all cars");
        Call<List<getAllCars.Response>> call = API_INTERFACE.carGetAll(token);

        call.enqueue(new Callback<List<getAllCars.Response>>() {
            @Override
            public void onResponse(Call<List<getAllCars.Response>> call, Response<List<getAllCars.Response>> response) {
                if (response.code() == 200) {
                    // TODO : create parser with generic type
                    List<getAllCars.Response> data = response.body();
                    Car[] cars = new Car[data.size()];
                    for (int i = 0; i < data.size(); ++i) {
                        cars[i] = new Car(data.get(i));
                    }
                    callback.onActionSuccess(cars);
                }
            }

            @Override
            public void onFailure(Call<List<getAllCars.Response>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void deleteCar(String carId, NetworkCallback callback) {
        Log.d("API", "create car");
        Call<DeleteSelector> call = API_INTERFACE.carDelete(token, new DeleteSelector.Request(carId));

        call.enqueue(new Callback<DeleteSelector>() {
            @Override
            public void onResponse(Call<DeleteSelector> call, Response<DeleteSelector> response) {
                if (response.code() == 200) {
                    // TODO : parse response to check if delete success
                    callback.onActionSuccess();
                }
            }

            @Override
            public void onFailure(Call<DeleteSelector> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void getAllEvents(String carId, NetworkCallback callback) {
        // fuel, cost earning
        events.clear();

        NetworkCallback callbackEarning = new NetworkCallback() {
            @Override
            public void onActionSuccess(Earning[] earnings) {
                Collections.addAll(events, earnings);
                Event[] allevents = new Event[events.size()];
                for (int i = 0; i < events.size(); ++i) {
                    allevents[i] = events.get(i);
                }
                callback.onActionSuccess(allevents);
            }
        };

        NetworkCallback callbackCost = new NetworkCallback() {
            @Override
            public void onActionSuccess(Cost[] costs) {
                Collections.addAll(events, costs);
                getAllEarning(carId, callbackEarning);
            }
        };

        NetworkCallback callbackFuel = new NetworkCallback() {
            @Override
            public void onActionSuccess(Refuel[] refuels) {
                Collections.addAll(events, refuels);
                getAllCost(carId, callbackCost);
            }
        };

        getAllRefuel(carId, callbackFuel);
    }

    public static void createRefuel(Refuel refuel, String carId) {
        Log.d("API", "create refuel");
        Call<RefuelCreate> call = API_INTERFACE.refuelCreate(token, new RefuelCreate.Request(refuel, carId));
        call.enqueue(new Callback<RefuelCreate>() {
            @Override
            public void onResponse(Call<RefuelCreate> call, Response<RefuelCreate> response) {
                if (response.code() == 201) {
                    RefuelCreate data = response.body();
                }
            }

            @Override
            public void onFailure(Call<RefuelCreate> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void getAllRefuel(String carId, NetworkCallback callback) {
        Log.d("API", "get all refuel");
        Call<List<RefuelGetAll.Response>> call = API_INTERFACE.refuelGetAll(token, new RefuelGetAll.Request(carId));
        call.enqueue(new Callback<List<RefuelGetAll.Response>>() {
            @Override
            public void onResponse(Call<List<RefuelGetAll.Response>> call, Response<List<RefuelGetAll.Response>> response) {
                if (response.code() == 200) {
                    List<RefuelGetAll.Response> data = response.body();
                    Refuel[] refuels = new Refuel[data.size()];
                    for (int i = 0; i < data.size(); ++i) {
                        refuels[i] = new Refuel(data.get(i));
                    }
                    callback.onActionSuccess(refuels);
                }
            }

            @Override
            public void onFailure(Call<List<RefuelGetAll.Response>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void deleteFuel(String carId, NetworkCallback callback) {
        Log.d("API", "delete fuel");
        Call<DeleteSelector> call = API_INTERFACE.refuelDelete(token, new DeleteSelector.Request(carId));
        call.enqueue(new Callback<DeleteSelector>() {
            @Override
            public void onResponse(Call<DeleteSelector> call, Response<DeleteSelector> response) {
                Log.d("API", token);
                if (response.code() == 201) {
                    DeleteSelector data = response.body();
                    callback.onActionSuccess();
                }
            }

            @Override
            public void onFailure(Call<DeleteSelector> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void createCost(Cost cost, String carId) {
        Log.d("API", "create cost");
        Call<CostCreate> call = API_INTERFACE.costCreate(token, new CostCreate.Request(cost, carId));
        call.enqueue(new Callback<CostCreate>() {
            @Override
            public void onResponse(Call<CostCreate> call, Response<CostCreate> response) {
                if (response.code() == 201) {
                    CostCreate data = response.body();
                }
            }

            @Override
            public void onFailure(Call<CostCreate> call, Throwable t) {
                call.cancel();
            }
        });
    }


    public static void getAllCost(String carId, NetworkCallback callback) {
        Log.d("API", "get all costs");
        Call<List<CostGetAll.Response>> call = API_INTERFACE.costGetAll(token, new CostGetAll.Request(carId));
        call.enqueue(new Callback<List<CostGetAll.Response>>() {
            @Override
            public void onResponse(Call<List<CostGetAll.Response>> call, Response<List<CostGetAll.Response>> response) {
                if (response.code() == 200) {
                    List<CostGetAll.Response> data = response.body();
                    Cost[] costs = new Cost[data.size()];
                    for (int i = 0; i < data.size(); ++i) {
                        costs[i] = new Cost(data.get(i));
                    }
                    callback.onActionSuccess(costs);
                }
            }

            @Override
            public void onFailure(Call<List<CostGetAll.Response>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void deleteCost(String carId, NetworkCallback callback) {
        Log.d("API", "delete cost");
        Call<DeleteSelector> call = API_INTERFACE.costDelete(token, new DeleteSelector.Request(carId));
        call.enqueue(new Callback<DeleteSelector>() {
            @Override
            public void onResponse(Call<DeleteSelector> call, Response<DeleteSelector> response) {
                Log.d("API", token);
                if (response.code() == 201) {
                    DeleteSelector data = response.body();
                    callback.onActionSuccess();
                }
            }

            @Override
            public void onFailure(Call<DeleteSelector> call, Throwable t) {
                call.cancel();
            }
        });
    }


    public static void createEearning(Earning earning, String carId) {
        Log.d("API", "create earning");
        Call<EarningCreate> call = API_INTERFACE.earningCreate(token, new EarningCreate.Request(earning, carId));
        call.enqueue(new Callback<EarningCreate>() {
            @Override
            public void onResponse(Call<EarningCreate> call, Response<EarningCreate> response) {
                if (response.code() == 201) {
                    EarningCreate data = response.body();
                }
            }

            @Override
            public void onFailure(Call<EarningCreate> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void getAllEarning(String carId, NetworkCallback callback) {
        Log.d("API", "get all costs");
        Call<List<EarningGetAll.Response>> call = API_INTERFACE.earningGetAll(token, new EarningGetAll.Request(carId));
        call.enqueue(new Callback<List<EarningGetAll.Response>>() {
            @Override
            public void onResponse(Call<List<EarningGetAll.Response>> call, Response<List<EarningGetAll.Response>> response) {
                if (response.code() == 200) {
                    List<EarningGetAll.Response> data = response.body();
                    Earning[] earnings = new Earning[data.size()];
                    for (int i = 0; i < data.size(); ++i) {
                        earnings[i] = new Earning(data.get(i));
                    }
                    callback.onActionSuccess(earnings);
                }
            }

            @Override
            public void onFailure(Call<List<EarningGetAll.Response>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void deleteEarning(String carId, NetworkCallback callback) {
        Log.d("API", "delete earning");
        Call<DeleteSelector> call = API_INTERFACE.earningDelete(token, new DeleteSelector.Request(carId));
        call.enqueue(new Callback<DeleteSelector>() {
            @Override
            public void onResponse(Call<DeleteSelector> call, Response<DeleteSelector> response) {
                Log.d("API", token);
                if (response.code() == 201) {
                    DeleteSelector data = response.body();
                    callback.onActionSuccess();
                }
            }

            @Override
            public void onFailure(Call<DeleteSelector> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static int eventSize() {
        return events.size();
    }
}