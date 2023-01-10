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

package UQAC.Mobile.SAMM.API.APIPojo;

import com.google.gson.annotations.SerializedName;

import UQAC.Mobile.SAMM.Base.Refuel;

/**
 * POJO class for refuel creation
 */
public class CreateRefuel {
    public static class Request {
        @SerializedName("mileage")
        public int mileage;

        @SerializedName("carId")
        public String carId;

        @SerializedName("fuelType")
        public String fuelType;

        @SerializedName("litterPrice")
        public Float litterPrice;

        @SerializedName("totalCost")
        public Float totalCost;

        @SerializedName("litter")
        public Float litter;

        public Request(Refuel refuel, String carId) {
            this.mileage = refuel.getMileage();
            this.carId = carId;
            this.fuelType = refuel.getFuelType();
            this.litterPrice = refuel.getLitterPrice();
            this.totalCost = refuel.getTotalCost();
            this.litter = refuel.getLitter();
        }
    }
}
