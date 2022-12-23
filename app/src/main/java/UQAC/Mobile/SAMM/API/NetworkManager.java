package UQAC.Mobile.SAMM.API;

import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import UQAC.Mobile.SAMM.API.APIPojo.CarCreate;
import UQAC.Mobile.SAMM.API.APIPojo.CarGetAll;
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
import UQAC.Mobile.SAMM.API.APIPojo.Test;
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

public class NetworkManager {

    // TODO : remove faussaires
    static public List<Event> events = new ArrayList<Event>();

    private static Retrofit retrofit = null;
    static APIInterface apiInterface = getClient().create(APIInterface.class);

    private static String token = null;

    public NetworkManager() {

    }

    static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://34.236.157.55:6422")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }


    /**
     * GET online server status
     **/
    public static void checkConnection() {
        Log.d("API", "get Online status");
        Call<Test.Test2> call = apiInterface.isOnline();
        call.enqueue(new Callback<Test.Test2>() {
            @Override
            public void onResponse(Call<Test.Test2> call, Response<Test.Test2> response) {

                Log.d("API", response.code() + "");
                Test.Test2 resource = response.body();
                String text = resource.message;
            }

            @Override
            public void onFailure(Call<Test.Test2> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void signup(String name, String email, String password, NetworkCallback callback) {
        Log.d("API", "signup");
        Call<Signup.Response> call = apiInterface.signup(new Signup.Request(name, email, password));

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

    public static void tokenCheck( SharedPreferences sharedPref, NetworkCallback callback) {
        Log.d("API", "tokenCheck");
        String userToken =  sharedPref.getString("token", null);
        Log.d("API", "token + " + userToken);
        if (userToken != null) {
            token = userToken;
        }

        Call<TokenCheck> call = apiInterface.tokenCheck(token);

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

        Call<Login.Response> call = apiInterface.login(new Login.Request(email, password));

        call.enqueue(new Callback<Login.Response>() {
            @Override
            public void onResponse(Call<Login.Response> call, Response<Login.Response> response) {
                if (response.code() == 200) {
                    Login.Response data = response.body();
                    token = "Bearer " + data.token;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("token",token);
                    editor.apply();
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
        token = null;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("token");
        editor.apply();
        callback.onActionSuccess();
    }

    public static void createCar(Car car) {
        Log.d("API", "create car");
        Call<CarCreate> call = apiInterface.carCreate(token, new CarCreate.Request(car));
        call.enqueue(new Callback<CarCreate>() {
            @Override
            public void onResponse(Call<CarCreate> call, Response<CarCreate> response) {
                Log.d("API", token);
                if (response.code() == 201) {
                    CarCreate data = response.body();
                }
            }

            @Override
            public void onFailure(Call<CarCreate> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void getAllCar(NetworkCallback callback) {
        Log.d("API", "get all cars");
        Call<List<CarGetAll.Response>> call = apiInterface.carGetAll(token);
        call.enqueue(new Callback<List<CarGetAll.Response>>() {
            @Override
            public void onResponse(Call<List<CarGetAll.Response>> call, Response<List<CarGetAll.Response>> response) {
                if (response.code() == 200) {
                    List<CarGetAll.Response> data = response.body();
                    Car[] cars = new Car[data.size()];
                    for (int i = 0; i < data.size(); ++i) {
                        cars[i] = new Car(data.get(i));
                    }
                    callback.onActionSuccess(cars);
                }
            }

            @Override
            public void onFailure(Call<List<CarGetAll.Response>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void deleteCar(String carId, NetworkCallback callback) {
        Log.d("API", "create car");
        Call<DeleteSelector> call = apiInterface.carDelete(token, new DeleteSelector.Request(carId));
        call.enqueue(new Callback<DeleteSelector>() {
            @Override
            public void onResponse(Call<DeleteSelector> call, Response<DeleteSelector> response) {
                Log.d("API", token);
                if (response.code() == 200) {
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

    public static void getAllEvents(String carId, NetworkCallback callback) {
        // fuel, cost earning
        events.clear();

        NetworkCallback callbackEarning = new NetworkCallback() {
            @Override
            public void onActionSuccess(Earning[] earnings){
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
            public void onActionSuccess(Cost[] costs){
                Collections.addAll(events, costs);
                getAllEarning(carId, callbackEarning);
            }
        };

        NetworkCallback callbackFuel = new NetworkCallback() {
            @Override
            public void onActionSuccess(Refuel[] refuels){
                Collections.addAll(events, refuels);
                getAllCost(carId, callbackCost);
            }
        };

        getAllRefuel(carId, callbackFuel);
    }

    public static void createRefuel(Refuel refuel, String carId) {
        Log.d("API", "create refuel");
        Call<RefuelCreate> call = apiInterface.refuelCreate(token, new RefuelCreate.Request(refuel, carId));
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
        Call<List<RefuelGetAll.Response>> call = apiInterface.refuelGetAll(token, new RefuelGetAll.Request(carId));
        call.enqueue(new Callback<List<RefuelGetAll.Response>>() {
            @Override
            public void onResponse(Call<List<RefuelGetAll.Response>> call, Response<List<RefuelGetAll.Response>> response) {
                if (response.code() == 200) {
                    List<RefuelGetAll.Response> data = response.body();
                    Refuel[] refuels = new Refuel[data.size()];
                    for (int i =0; i < data.size(); ++i) {
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
        Call<DeleteSelector> call = apiInterface.refuelDelete(token, new DeleteSelector.Request(carId));
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
        Call<CostCreate> call = apiInterface.costCreate(token, new CostCreate.Request(cost, carId));
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
        Call<List<CostGetAll.Response>> call = apiInterface.costGetAll(token, new CostGetAll.Request(carId));
        call.enqueue(new Callback<List<CostGetAll.Response>>() {
            @Override
            public void onResponse(Call<List<CostGetAll.Response>> call, Response<List<CostGetAll.Response>> response) {
                if (response.code() == 200) {
                    List<CostGetAll.Response> data = response.body();
                    Cost[] costs = new Cost[data.size()];
                    for (int i =0; i < data.size(); ++i) {
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
        Call<DeleteSelector> call = apiInterface.costDelete(token, new DeleteSelector.Request(carId));
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
        Call<EarningCreate> call = apiInterface.earningCreate(token, new EarningCreate.Request(earning, carId));
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
        Call<List<EarningGetAll.Response>> call = apiInterface.earningGetAll(token, new EarningGetAll.Request(carId));
        call.enqueue(new Callback<List<EarningGetAll.Response>>() {
            @Override
            public void onResponse(Call<List<EarningGetAll.Response>> call, Response<List<EarningGetAll.Response>> response) {
                if (response.code() == 200) {
                    List<EarningGetAll.Response> data = response.body();
                    Earning[] earnings = new Earning[data.size()];
                    for (int i =0; i < data.size(); ++i) {
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
        Call<DeleteSelector> call = apiInterface.earningDelete(token, new DeleteSelector.Request(carId));
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


    // TODO : remove faussaires
    public void createContent() {
//
//        // create events
//        Refuel refuel1 = new Refuel("Essence", 1.6f, 86.64f, 54.15f, Calendar.getInstance().getTime(), 180000);
//        Earning earning = new Earning("Covoiturage", 70, Calendar.getInstance().getTime(), 180000);
//        Refuel refuel2 = new Refuel("Essence", 1.5f, 75f, 50f, Calendar.getInstance().getTime(), 175000);
//
//        events.add(refuel1);
//        events.add(earning);
//        events.add(refuel2);
//
//        // create cars
//
//
//        History history = new History(events);
//        History history1 = new History();
//
//        Car car1 = new Car(history,
//                null,
//                180000,
//                2000,
//                "Essence",
//                23,
//                "voiture",
//                "Chrysler",
//                "300c",
//                "Mon char");
//
//        Car car2 = new Car(history1,
//                null,
//                150000,
//                2000,
//                "Essence",
//                23,
//                "voiture",
//                "Pontiac",
//                "G6",
//                "Auto de ma blonde");
//
//        cars.add(car1);
//        cars.add(car2);
    }
}