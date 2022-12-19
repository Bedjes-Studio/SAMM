package UQAC.Mobile.SAMM;

import java.util.List;

import UQAC.Mobile.SAMM.APIPojo.CarCreate;
import UQAC.Mobile.SAMM.APIPojo.CarGetAll;
import UQAC.Mobile.SAMM.APIPojo.CostCreate;
import UQAC.Mobile.SAMM.APIPojo.CostGetAll;
import UQAC.Mobile.SAMM.APIPojo.Login;
import UQAC.Mobile.SAMM.APIPojo.RefuelCreate;
import UQAC.Mobile.SAMM.APIPojo.RefuelGetAll;
import UQAC.Mobile.SAMM.APIPojo.Signup;
import UQAC.Mobile.SAMM.APIPojo.Test;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

interface APIInterface {

        @GET("/online")
        Call<Test.Test2> isOnline();

        @POST("/api/user/login")
        Call<Login.Response> login(@Body Login.Request request);

        @POST("/api/user/signup")
        Call<Signup.Response> signup(@Body Signup.Request request);

        @POST("/api/car/create")
        Call<CarCreate> carCreate(@Header("Authorization") String token, @Body CarCreate.Request request);

        @GET("/api/car/getAll")
        Call<List<CarGetAll.Response>> carGetAll(@Header("Authorization") String token);

        @POST("/api/event/refuel/create")
        Call<RefuelCreate> refuelCreate(@Header("Authorization") String token, @Body RefuelCreate.Request request);

        @POST("/api/event/refuel/getAll")
        Call<List<RefuelGetAll.Response>> refuelGetAll(@Header("Authorization") String token, @Body RefuelGetAll.Request request);

        @POST("/api/event/cost/create")
        Call<CostCreate> costCreate(@Header("Authorization") String token, @Body CostCreate.Request request);

        @POST("/api/event/cost/getAll")
        Call<List<CostGetAll.Response>> costGetAll(@Header("Authorization") String token, @Body CostGetAll.Request request);

//        @POST("/api/users?")
//        Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);

//        @POST("/api/users")
//        Call<User> createUser(@Body User user);


//        @GET("/demo/countrylist.php") // specify the sub url for our base url
//        public void getVideoList(Callback<List<CountryResponse>> callback);
//
//        @POST("/api/users")
//        Call<User> createUser(@Body User user);
//
//        @GET("/api/users?")
//        Call<UserList> doGetUserList(@Query("page") String page);
//
//        @FormUrlEncoded
//        @POST("/api/users?")
//        Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
}