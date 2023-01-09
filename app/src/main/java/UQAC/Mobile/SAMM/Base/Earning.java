package UQAC.Mobile.SAMM.Base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import UQAC.Mobile.SAMM.API.APIPojo.GetAllEarnings;

public class Earning extends Event {
    private float value;
    private String reason;

    public Earning(String reason, float value, Date date, int mileage) {

        this.value = value;
        this.reason = reason;
        this.date = date;
        this.mileage = mileage;
    }

    public Earning(GetAllEarnings.Response response) {
        this.id = response.id;
        this.value = response.value;
        this.reason = response.reason;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            this.date = format.parse(response.date);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.mileage = response.mileage;
    }


    public boolean save(Earning earning){
        return true;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
