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

import java.util.List;

import UQAC.Mobile.SAMM.API.APIPojo.GetAllCars;

/**
 * This class is the base class for a car
 */
public class Car {
    // TODO : use later for vehicule adapter
    public static final String VEHICLE_TYPE = "CAR";

    private String id;
    private int mileage;
    private int year;
    private String name;
    private List<User> owners;
    private History history;

    // TODO : move these variable to specs
    private String brand;
    private String model;
    private String fuelType;
    private float fuelCapacity;

    // TODO : delete this variable
    private String type;


    public Car(History history, List<User> owners, int mileage, int year, String fuelType, float fuelCapacity, String type, String brand, String model, String name) {
        this.history = history;
        this.owners = owners;
        this.mileage = mileage;
        this.year = year;
        this.fuelType = fuelType;
        this.fuelCapacity = fuelCapacity;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.name = name;
    }

    public Car(String id) {
        this.id = id;
    }

    public Car(GetAllCars.Response response) {
        this.id = response.id;
        this.mileage = response.mileage;
        this.year = response.year;
        this.fuelType = response.fuelType;
        this.fuelCapacity = response.fuelCapacity;
        this.type = response.type;
        this.brand = response.brand;
        this.model = response.model;
        this.name = response.name;
        // this.owner = get owners from id ?
        // this.guests = get guests from id ?
        // this.specs = not used anymore
    }

    public Car() {
    }

    //Getters
    public String getId() {
        return id;
    }

    public History getHistory() {
        return history;
    }

    public List<User> getOwners() {
        return owners;
    }

    public int getMileage() {
        return mileage;
    }

    public int getYear() {
        return year;
    }

    public String getType() {
        return type;
    }

    public String getFuelType() {
        return fuelType;
    }

    public float getFuelCapacity() {
        return fuelCapacity;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getName() {
        return name;
    }

    //
}
