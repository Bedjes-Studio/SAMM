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
import UQAC.Mobile.SAMM.API.APIPojo.GetAllCars;
import UQAC.Mobile.SAMM.API.APIPojo.CreateCost;
import UQAC.Mobile.SAMM.API.APIPojo.GetAllCosts;
import UQAC.Mobile.SAMM.API.APIPojo.DeleteSelector;
import UQAC.Mobile.SAMM.API.APIPojo.CreateEarning;
import UQAC.Mobile.SAMM.API.APIPojo.GetAllEarnings;
import UQAC.Mobile.SAMM.API.APIPojo.Login;
import UQAC.Mobile.SAMM.API.APIPojo.CreateRefuel;
import UQAC.Mobile.SAMM.API.APIPojo.GetAllRefuels;
import UQAC.Mobile.SAMM.API.APIPojo.Signup;
import UQAC.Mobile.SAMM.API.APIPojo.TokenCheck;
import UQAC.Mobile.SAMM.API.APIPojo.ServerStatus;
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

    /**
     * Retrofit client setup
     */
    private static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder().baseUrl("http://34.236.157.55:6422").addConverterFactory(GsonConverterFactory.create()).client(client).build();
    }

    /**
     * Token management utils
     */

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
     * Server status utils
     **/

    public static void checkConnection(NetworkCallback callback) {
        Log.d("API", "get Online status");
        Call<ServerStatus.Response> call = API_INTERFACE.isOnline();

        call.enqueue(new Callback<ServerStatus.Response>() {
            @Override
            public void onResponse(Call<ServerStatus.Response> call, Response<ServerStatus.Response> response) {

                // TODO : remove message from server ?
                if (response.code() == 200) {
                    // Online.Response resource = response.body();
                    // String text = resource.message;
                    callback.onActionSuccess();
                }
            }

            @Override
            public void onFailure(Call<ServerStatus.Response> call, Throwable t) {
                call.cancel();
            }
        });
    }

    /**
     * User connection methods
     **/

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

    /**
     * Car management methods
     **/

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
        Call<List<GetAllCars.Response>> call = API_INTERFACE.carGetAll(token);

        call.enqueue(new Callback<List<GetAllCars.Response>>() {
            @Override
            public void onResponse(Call<List<GetAllCars.Response>> call, Response<List<GetAllCars.Response>> response) {
                if (response.code() == 200) {
                    // TODO : create parser with generic type
                    List<GetAllCars.Response> data = response.body();
                    Car[] cars = new Car[data.size()];
                    for (int i = 0; i < data.size(); ++i) {
                        cars[i] = new Car(data.get(i));
                    }
                    callback.onActionSuccess(cars);
                }
            }

            @Override
            public void onFailure(Call<List<GetAllCars.Response>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    // TODO : implements update method

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

    /**
     * Common Event management methods
     **/

    // TODO : update API to pack this in one request
    public static void getAllEvents(String carId, NetworkCallback callback) {
        // fuel, cost earning
        events.clear();

        NetworkCallback callbackEarning = new NetworkCallback() {
            @Override
            public void onActionSuccess(Earning[] earnings) {
                Collections.addAll(events, earnings);
                Event[] allEvents = new Event[events.size()];
                for (int i = 0; i < events.size(); ++i) {
                    allEvents[i] = events.get(i);
                }
                callback.onActionSuccess(allEvents);
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

        getAllRefuels(carId, callbackFuel);
    }

    /**
     * Refuels management methods
     **/

    // TODO : add callback to popup when refuel is created
    public static void createRefuel(Refuel refuel, String carId) {
        Log.d("API", "create refuel");
        Call<CreateRefuel> call = API_INTERFACE.refuelCreate(token, new CreateRefuel.Request(refuel, carId));

        call.enqueue(new Callback<CreateRefuel>() {
            @Override
            public void onResponse(Call<CreateRefuel> call, Response<CreateRefuel> response) {
                if (response.code() == 201) {
                    // TODO : call callback here
                }
            }

            @Override
            public void onFailure(Call<CreateRefuel> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void getAllRefuels(String carId, NetworkCallback callback) {
        Log.d("API", "get all refuel");
        Call<List<GetAllRefuels.Response>> call = API_INTERFACE.refuelGetAll(token, new GetAllRefuels.Request(carId));

        call.enqueue(new Callback<List<GetAllRefuels.Response>>() {
            @Override
            public void onResponse(Call<List<GetAllRefuels.Response>> call, Response<List<GetAllRefuels.Response>> response) {
                if (response.code() == 200) {
                    // TODO : create parser with generic type
                    List<GetAllRefuels.Response> data = response.body();
                    Refuel[] refuels = new Refuel[data.size()];
                    for (int i = 0; i < data.size(); ++i) {
                        refuels[i] = new Refuel(data.get(i));
                    }
                    callback.onActionSuccess(refuels);
                }
            }

            @Override
            public void onFailure(Call<List<GetAllRefuels.Response>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    // TODO : implements update method

    public static void deleteFuel(String carId, NetworkCallback callback) {
        Log.d("API", "delete fuel");
        Call<DeleteSelector> call = API_INTERFACE.refuelDelete(token, new DeleteSelector.Request(carId));

        call.enqueue(new Callback<DeleteSelector>() {
            @Override
            public void onResponse(Call<DeleteSelector> call, Response<DeleteSelector> response) {
                if (response.code() == 201) {
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

    /**
     * Costs management methods
     **/

    // TODO : add callback to popup when cost is created
    public static void createCost(Cost cost, String carId) {
        Log.d("API", "create cost");
        Call<CreateCost> call = API_INTERFACE.costCreate(token, new CreateCost.Request(cost, carId));

        call.enqueue(new Callback<CreateCost>() {
            @Override
            public void onResponse(Call<CreateCost> call, Response<CreateCost> response) {
                if (response.code() == 201) {
                    // TODO : call callback here
                }
            }

            @Override
            public void onFailure(Call<CreateCost> call, Throwable t) {
                call.cancel();
            }
        });
    }


    public static void getAllCost(String carId, NetworkCallback callback) {
        Log.d("API", "get all costs");
        Call<List<GetAllCosts.Response>> call = API_INTERFACE.costGetAll(token, new GetAllCosts.Request(carId));

        call.enqueue(new Callback<List<GetAllCosts.Response>>() {
            @Override
            public void onResponse(Call<List<GetAllCosts.Response>> call, Response<List<GetAllCosts.Response>> response) {
                if (response.code() == 200) {
                    // TODO : create parser with generic type
                    List<GetAllCosts.Response> data = response.body();
                    Cost[] costs = new Cost[data.size()];
                    for (int i = 0; i < data.size(); ++i) {
                        costs[i] = new Cost(data.get(i));
                    }
                    callback.onActionSuccess(costs);
                }
            }

            @Override
            public void onFailure(Call<List<GetAllCosts.Response>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    // TODO : implements update method

    public static void deleteCost(String carId, NetworkCallback callback) {
        Log.d("API", "delete cost");
        Call<DeleteSelector> call = API_INTERFACE.costDelete(token, new DeleteSelector.Request(carId));

        call.enqueue(new Callback<DeleteSelector>() {
            @Override
            public void onResponse(Call<DeleteSelector> call, Response<DeleteSelector> response) {
                if (response.code() == 201) {
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

    /**
     * Earnings management methods
     **/

    // TODO : add callback to popup when earning is created
    public static void createEarning(Earning earning, String carId) {
        Log.d("API", "create earning");
        Call<CreateEarning> call = API_INTERFACE.earningCreate(token, new CreateEarning.Request(earning, carId));
        call.enqueue(new Callback<CreateEarning>() {
            @Override
            public void onResponse(Call<CreateEarning> call, Response<CreateEarning> response) {
                if (response.code() == 201) {
                    // TODO : call callback here
                }
            }

            @Override
            public void onFailure(Call<CreateEarning> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void getAllEarning(String carId, NetworkCallback callback) {
        Log.d("API", "get all costs");
        Call<List<GetAllEarnings.Response>> call = API_INTERFACE.earningGetAll(token, new GetAllEarnings.Request(carId));

        call.enqueue(new Callback<List<GetAllEarnings.Response>>() {
            @Override
            public void onResponse(Call<List<GetAllEarnings.Response>> call, Response<List<GetAllEarnings.Response>> response) {
                if (response.code() == 200) {
                    // TODO : create parser with generic type
                    List<GetAllEarnings.Response> data = response.body();
                    Earning[] earnings = new Earning[data.size()];
                    for (int i = 0; i < data.size(); ++i) {
                        earnings[i] = new Earning(data.get(i));
                    }
                    callback.onActionSuccess(earnings);
                }
            }

            @Override
            public void onFailure(Call<List<GetAllEarnings.Response>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    // TODO : create update method

    public static void deleteEarning(String carId, NetworkCallback callback) {
        Log.d("API", "delete earning");
        Call<DeleteSelector> call = API_INTERFACE.earningDelete(token, new DeleteSelector.Request(carId));
        call.enqueue(new Callback<DeleteSelector>() {
            @Override
            public void onResponse(Call<DeleteSelector> call, Response<DeleteSelector> response) {
                if (response.code() == 201) {
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

    /**
     * Public utils
     */
    public static int eventSize() {
        return events.size();
    }
}