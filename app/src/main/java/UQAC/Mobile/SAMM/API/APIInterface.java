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

/**
 * This interface is used to link api point and methods
 */
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