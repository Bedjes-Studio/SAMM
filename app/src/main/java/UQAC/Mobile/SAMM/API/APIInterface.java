package UQAC.Mobile.SAMM.API;

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
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

interface APIInterface {

        @GET("/online")
        Call<ServerStatus.Response> isOnline();

        @POST("/api/user/login")
        Call<Login.Response> login(@Body Login.Request request);

        @GET("/api/user/token")
        Call<TokenCheck> tokenCheck(@Header("Authorization") String token);

        @POST("/api/user/signup")
        Call<Signup.Response> signup(@Body Signup.Request request);

        @POST("/api/car/create")
        Call<CreateCar> carCreate(@Header("Authorization") String token, @Body CreateCar.Request request);

        @GET("/api/car/getAll")
        Call<List<GetAllCars.Response>> carGetAll(@Header("Authorization") String token);

        @POST("/api/car/delete")
        Call<DeleteSelector> carDelete(@Header("Authorization") String token, @Body DeleteSelector.Request request);

        @POST("/api/event/refuel/create")
        Call<CreateRefuel> refuelCreate(@Header("Authorization") String token, @Body CreateRefuel.Request request);

        @POST("/api/event/refuel/getAll")
        Call<List<GetAllRefuels.Response>> refuelGetAll(@Header("Authorization") String token, @Body GetAllRefuels.Request request);

        @POST("/api/refuel/delete")
        Call<DeleteSelector> refuelDelete(@Header("Authorization") String token, @Body DeleteSelector.Request request);

        @POST("/api/event/cost/create")
        Call<CreateCost> costCreate(@Header("Authorization") String token, @Body CreateCost.Request request);

        @POST("/api/event/cost/getAll")
        Call<List<GetAllCosts.Response>> costGetAll(@Header("Authorization") String token, @Body GetAllCosts.Request request);

        @POST("/api/cost/delete")
        Call<DeleteSelector> costDelete(@Header("Authorization") String token, @Body DeleteSelector.Request request);

        @POST("/api/event/earning/create")
        Call<CreateEarning> earningCreate(@Header("Authorization") String token, @Body CreateEarning.Request request);

        @POST("/api/event/earning/getAll")
        Call<List<GetAllEarnings.Response>> earningGetAll(@Header("Authorization") String token, @Body GetAllEarnings.Request request);

        @POST("/api/earning/delete")
        Call<DeleteSelector> earningDelete(@Header("Authorization") String token, @Body DeleteSelector.Request request);
}