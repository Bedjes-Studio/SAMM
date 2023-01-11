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

package UQAC.Mobile.SAMM.Base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import UQAC.Mobile.SAMM.API.APIPojo.GetAllCosts;

/**
 * Base class for cost events
 */
public class Cost extends Event {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    public static final String EVENT_TYPE = "COST";

    private float value;
    private String reason;
    private String paymentMethod;

    public Cost(float value, String reason, String paymentMethod, Date date, int mileage) {
        this.value = value;
        this.reason = reason;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.mileage = mileage;
    }

    public Cost(GetAllCosts.Response response) {
        this.id = response.id;
        this.value = response.value;
        this.reason = response.reason;
        this.paymentMethod = response.paymentMethod;
        try {
            this.date = dateFormat.parse(response.date);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.mileage = response.mileage;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
