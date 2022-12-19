package UQAC.Mobile.SAMM;

import java.util.Date;

import UQAC.Mobile.SAMM.APIPojo.CostGetAll;
import UQAC.Mobile.SAMM.APIPojo.EarningGetAll;

public class Earning extends Event {
    private float value;
    private String reason;

    public Earning(String reason, float value, Date date, int mileage) {

        this.value = value;
        this.reason = reason;
        this.date = date;
        this.mileage = mileage;
    }

    public Earning(EarningGetAll.Response response) {
        this.value = response.value;
        this.reason = response.reason;
//        this.date = response.date;
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
