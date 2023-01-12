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

import UQAC.Mobile.SAMM.API.APIPojo.GetAllRefuels;

/**
 * Base class for refuel events
 */
public class Refuel extends Event {

    // TODO : exports strings
    public static final String[] FUEL_TYPE = {"Gasoline", "Diesel Fuel", "Bio-diesel", "Ethanol"};
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    public static final String EVENT_TYPE = "REFUEL";

    private String fuelType;
    private float litterPrice;
    private float totalCost;
    private float litter;

    public Refuel(String fuelType, float litterPrice, float totalCost, float litter, Date date, int mileage) {
        this.fuelType = fuelType;
        this.litterPrice = litterPrice;
        this.totalCost = totalCost;
        this.litter = litter;
        this.date = date;
        this.mileage = mileage;
    }

    public Refuel(GetAllRefuels.Response response) {
        this.id = response.id;
        this.fuelType = response.fuelType;
        this.litterPrice = response.litterPrice;
        this.totalCost = response.totalCost;
        this.litter = response.litter;
        try {
            this.date = dateFormat.parse(response.date);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.mileage = response.mileage;
    }

    public boolean save(Refuel refuel) {
        return true;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public float getLitterPrice() {
        return litterPrice;
    }

    public void setLitterPrice(float litterPrice) {
        this.litterPrice = litterPrice;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getLitter() {
        return litter;
    }

    public void setLitter(float litter) {
        this.litter = litter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
