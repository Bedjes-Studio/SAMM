package UQAC.Mobile.SAMM;

import android.content.Context;
import android.widget.Toast;

import java.util.Date;

public class Refuel extends Event {

    private String fuelType;
    private float litterPrice;
    private float totalCost;
    private float litter;

    public Refuel(String fuelType, float litterPrice, float totalCost, float litter, Date date, int mileage){
        this.fuelType = fuelType;
        this.litterPrice = litterPrice;
        this.totalCost = totalCost;
        this.litter = litter;
        this.date = date;
        this.mileage = mileage;
    }

    public boolean save(Refuel refuel){
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