package UQAC.Mobile.SAMM;

public class Earning extends Event {
    private float value;
    private String reason;

    public Earning(float value, String reason) {
        this.value = value;
        this.reason = reason;
    }
}
