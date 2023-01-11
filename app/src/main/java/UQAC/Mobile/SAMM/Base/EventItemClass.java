package UQAC.Mobile.SAMM.Base;

public class EventItemClass {

    // --Ce sera inutile à supprimer quand les events seront ok

    // Integers assigned to each layout
    // these are declared static so that they can
    // be accessed from the class name itself
    // And final so that they are not modified later
    public static final int LAYOUT_REFUEL = 0;
    public static final int LAYOUT_REPAIR = 1;
    public static final int LAYOUT_EARNING = 2;

    // This variable ViewType specifies
    // which out of the two layouts
    // is expected in the given item
    private int viewType;

    // Variable for all the layout
    private String mileage, date;

    // Variable for the refuel layout
    private String litter, litterPrice, totalCost;

    // public constructor for the refuel layout
    public  EventItemClass(int viewType, String litter, String litterPrice, String totalCost, String mileage, String date){
        this.viewType = viewType;
        this.litter = litter;
        this.litterPrice = litterPrice;
        this.totalCost = totalCost;
        this.mileage = mileage;
        this.date = date;
    }

    // Variable for the repair and earning layout
    private String value;

    //public constructor for the repair layout
    public EventItemClass(int viewType, String value, String mileage, String date){
        this.viewType = viewType;
        this.value = value;
        this.mileage = mileage;
        this.date = date;
    }

    //getter and setter
    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLitter() {
        return litter;
    }

    public void setLitter(String litter) {
        this.litter = litter;
    }

    public String getLitterPrice() {
        return litterPrice;
    }

    public void setLitterPrice(String litterPrice) {
        this.litterPrice = litterPrice;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
