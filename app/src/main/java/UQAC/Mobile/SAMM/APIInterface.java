package UQAC.Mobile.SAMM;

import UQAC.Mobile.SAMM.APIPojo.CarCreate;
import UQAC.Mobile.SAMM.APIPojo.Login;
import UQAC.Mobile.SAMM.APIPojo.Test;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

interface APIInterface {

        @GET("/online")
        Call<Test.Test2> isOnline();

        @POST("/api/user/login")
        Call<Login.Response> login(@Body Login.Request request);

        @FormUrlEncoded
        @POST("/api/car/create")
        Call<CarCreate> carCreate(@Field("mileage") int mileage, @Field("year") int year);

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