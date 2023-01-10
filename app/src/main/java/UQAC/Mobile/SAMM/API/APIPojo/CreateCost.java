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

import UQAC.Mobile.SAMM.Base.Cost;

/**
 * POJO class for cost creation
 */
public class CreateCost {
    public static class Request {

        @SerializedName("mileage")
        public int mileage;

        @SerializedName("carId")
        public String carId;

        @SerializedName("value")
        public float value;

        @SerializedName("reason")
        public String reason;

        @SerializedName("paymentMethod")
        public String paymentMethod;

        public Request(Cost cost, String carId) {
            this.mileage = cost.getMileage();
            this.carId = carId;
            this.value = cost.getValue();
            this.reason = cost.getReason();
            this.paymentMethod = cost.getPaymentMethod();
        }
    }
}
