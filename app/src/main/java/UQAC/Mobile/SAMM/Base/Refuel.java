package UQAC.Mobile.SAMM.Base;

import android.content.Context;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import UQAC.Mobile.SAMM.API.APIPojo.RefuelGetAll;

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

    public Refuel(RefuelGetAll.Response response){
        this.id = response.id;
        this.fuelType = response.fuelType;
        this.litterPrice = response.litterPrice;
        this.totalCost = response.totalCost;
        this.litter = response.litter;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            this.date = format.parse(response.date);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }        this.mileage = response.mileage;
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
