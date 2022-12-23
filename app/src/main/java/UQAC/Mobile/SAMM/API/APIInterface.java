package UQAC.Mobile.SAMM.API;

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
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

interface APIInterface {

        @GET("/online")
        Call<Test.Test2> isOnline();

        @POST("/api/user/login")
        Call<Login.Response> login(@Body Login.Request request);

        @GET("/api/user/token")
        Call<TokenCheck> tokenCheck(@Header("Authorization") String token);

        @POST("/api/user/signup")
        Call<Signup.Response> signup(@Body Signup.Request request);

        @POST("/api/car/create")
        Call<CarCreate> carCreate(@Header("Authorization") String token, @Body CarCreate.Request request);

        @GET("/api/car/getAll")
        Call<List<CarGetAll.Response>> carGetAll(@Header("Authorization") String token);

        @POST("/api/car/delete")
        Call<DeleteSelector> carDelete(@Header("Authorization") String token, @Body DeleteSelector.Request request);

        @POST("/api/event/refuel/create")
        Call<RefuelCreate> refuelCreate(@Header("Authorization") String token, @Body RefuelCreate.Request request);

        @POST("/api/event/refuel/getAll")
        Call<List<RefuelGetAll.Response>> refuelGetAll(@Header("Authorization") String token, @Body RefuelGetAll.Request request);

        @POST("/api/refuel/delete")
        Call<DeleteSelector> refuelDelete(@Header("Authorization") String token, @Body DeleteSelector.Request request);

        @POST("/api/event/cost/create")
        Call<CostCreate> costCreate(@Header("Authorization") String token, @Body CostCreate.Request request);

        @POST("/api/event/cost/getAll")
        Call<List<CostGetAll.Response>> costGetAll(@Header("Authorization") String token, @Body CostGetAll.Request request);

        @POST("/api/cost/delete")
        Call<DeleteSelector> costDelete(@Header("Authorization") String token, @Body DeleteSelector.Request request);

        @POST("/api/event/earning/create")
        Call<EarningCreate> earningCreate(@Header("Authorization") String token, @Body EarningCreate.Request request);

        @POST("/api/event/earning/getAll")
        Call<List<EarningGetAll.Response>> earningGetAll(@Header("Authorization") String token, @Body EarningGetAll.Request request);

        @POST("/api/earning/delete")
        Call<DeleteSelector> earningDelete(@Header("Authorization") String token, @Body DeleteSelector.Request request);
}