package UQAC.Mobile.SAMM.API;

import java.util.List;

import UQAC.Mobile.SAMM.Base.Car;
import UQAC.Mobile.SAMM.Base.Cost;
import UQAC.Mobile.SAMM.Base.Earning;
import UQAC.Mobile.SAMM.Base.Event;
import UQAC.Mobile.SAMM.Base.Refuel;

public class NetworkCallback {
    public void onActionSuccess(){};
    public void onActionSuccess(Car[] cars){};
    public void onActionSuccess(Refuel[] refuels){};
    public void onActionSuccess(Cost[] costs){};
    public void onActionSuccess(Earning[] earnings){};
    public void onActionSuccess(Event[] events){};
    public void onActionSuccess(List<Event> events){};


    public void onActionFailure(){};
}
