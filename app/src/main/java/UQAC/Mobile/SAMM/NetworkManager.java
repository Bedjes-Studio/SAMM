package UQAC.Mobile.SAMM;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import UQAC.Mobile.SAMM.APIPojo.CarCreate;
import UQAC.Mobile.SAMM.APIPojo.Login;
import UQAC.Mobile.SAMM.APIPojo.Test;
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
    static public List<Car> cars = new ArrayList<Car>();

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

    public static void login(String email, String password, Context context) {
        Log.d("API", "login");
        Call<Login.Response> call = apiInterface.login(new Login.Request(email,password));

        call.enqueue(new Callback<Login.Response>() {
            @Override
            public void onResponse(Call<Login.Response> call, Response<Login.Response> response) {
                Log.d("API", response.code() + "");
                if (response.code() == 200) {
                    Toast.makeText(context, "Connection reussie", Toast.LENGTH_SHORT).show();
                    //createContent();
                    Intent loginIntent = new Intent(context, listVehicules.class);
                    loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(loginIntent);
                } else {
                    Toast.makeText(context, "Connection refusée", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login.Response> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public static void login2(String email, String password, NetworkCallback callback) {
        Log.d("API", "login");
        Call<Login.Response> call = apiInterface.login(new Login.Request(email,password));

        call.enqueue(new Callback<Login.Response>() {
            @Override
            public void onResponse(Call<Login.Response> call, Response<Login.Response> response) {
                Log.d("API", response.code() + "");
                if (response.code() == 200) {
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

    public static void createCar() {
        Log.d("API", "create car");
        Call<CarCreate> call = apiInterface.carCreate(1000, 0);
        call.enqueue(new Callback<CarCreate>() {
            @Override
            public void onResponse(Call<CarCreate> call, Response<CarCreate> response) {

                Log.d("API", response.code() + "");
                CarCreate resource = response.body();
                String text = resource.message;
            }

            @Override
            public void onFailure(Call<CarCreate> call, Throwable t) {
                call.cancel();
            }
        });
    }

    // TODO : remove faussaires
    public void createContent() {

        // create events
        Refuel refuel1 = new Refuel("Essence", 1.6f, 86.64f, 54.15f, Calendar.getInstance().getTime(), 180000);
        Earning earning = new Earning("Covoiturage", 70, Calendar.getInstance().getTime(), 180000);
        Refuel refuel2 = new Refuel("Essence", 1.5f, 75f, 50f, Calendar.getInstance().getTime(), 175000);

        events.add(refuel1);
        events.add(earning);
        events.add(refuel2);

        // create cars


        History history = new History(events);
        History history1 = new History();

        Car car1 = new Car(history,
                null,
                180000,
                "Essence",
                23,
                "voiture",
                "Chrysler",
                "300c",
                "Mon char");

        Car car2 = new Car(history1,
                null,
                150000,
                "Essence",
                23,
                "voiture",
                "Pontiac",
                "G6",
                "Auto de ma blonde");

        cars.add(car1);
        cars.add(car2);
    }

    // TODO : remove faussaires
    static public List<Event> getEvents() {
        return events;
    }

    static public List<Car> getCars() {

        return cars;
    }
}