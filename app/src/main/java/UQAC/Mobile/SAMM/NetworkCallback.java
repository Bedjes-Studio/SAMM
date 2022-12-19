package UQAC.Mobile.SAMM;

import java.util.List;

public class NetworkCallback {
    public void onActionSuccess(){};
    public void onActionSuccess(Car[] cars){};
    public void onActionSuccess(Refuel[] refuels){};
    public void onActionSuccess(Cost[] costs){};
    public void onActionSuccess(Earning[] earnings){};
    public void onActionFailure(){};
}
