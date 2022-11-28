package UQAC.Mobile.SAMM;

import java.util.Date;

public class Earning extends Event {
    private float value;
    private String reason;

    public Earning(String reason, float value, Date date, int mileage) {

        this.value = value;
        this.reason = reason;
        this.date = date;
        this.mileage = mileage;
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
