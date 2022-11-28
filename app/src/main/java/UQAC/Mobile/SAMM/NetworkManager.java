package UQAC.Mobile.SAMM;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NetworkManager {
    // get voiture
    // ajout voiture
    // ajout refuel
    // get events


    public NetworkManager() {
    }

    static public List<Event> getEvents() {
        List<Event> events = new ArrayList<Event>();

        Refuel refuel1 = new Refuel("Essence", 1.6f, 86.64f, 54.15f, Calendar.getInstance().getTime(), 180000);
        Earning earning = new Earning(70, "Covoiturage");
        Refuel refuel2 = new Refuel("Essence", 1.5f, 75f, 50f, Calendar.getInstance().getTime(), 175000);

        events.add(refuel1);
        events.add(earning);
        events.add(refuel2);
        return events;
    }

    static public List<Car> getCars() {
        List<Car> cars = new ArrayList<Car>();

        History history = new History();

        Car car1 = new Car(history,
                null,
                180000,
                "Essence",
                23,
                "voiture",
                "Chrysler",
                "300c",
                "Mon char");

        Car car2 = new Car(history,
                null,
                150000,
                "Essence",
                23,
                "voiture",
                "Pontiac",
                "G6",
                "Auto de ma blonde");

        cars.add(car1);
        cars.add(car2);
        return cars;
    }
}