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

/**
 * POJO class for get all cars
 */
public class GetAllCars {
    public static class Response {

        @SerializedName("_id")
        public String id;

        @SerializedName("mileage")
        public int mileage;

        @SerializedName("year")
        public int year;

        @SerializedName("ownerId")
        public String ownerId;

        @SerializedName("guestsId")
        public String guestsId[];

        @SerializedName("specsId")
        public String specsId;

        @SerializedName("fuelType")
        public String fuelType;

        @SerializedName("fuelCapacity")
        public Float fuelCapacity;

        @SerializedName("type")
        public String type;

        @SerializedName("brand")
        public String brand;

        @SerializedName("model")
        public String model;

        @SerializedName("name")
        public String name;

        @SerializedName("__v")
        public String version;
    }
}
