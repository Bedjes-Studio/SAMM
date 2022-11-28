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
    static public List<Event> events = new ArrayList<Event>();
    static public List<Car> cars = new ArrayList<Car>();

    public void createContent() {

        // create events
        Refuel refuel1 = new Refuel("Essence", 1.6f, 86.64f, 54.15f, Calendar.getInstance().getTime(), 180000);
        Earning earning = new Earning( "Covoiturage", 70, Calendar.getInstance().getTime(), 180000);
        Refuel refuel2 = new Refuel("Essence", 1.5f, 75f, 50f, Calendar.getInstance().getTime(), 175000);

        events.add(refuel1);
        events.add(earning);
        events.add(refuel2);

        // create cars

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
    }
    public NetworkManager() {

    }

    static public List<Event> getEvents() {

        return events;
    }

    static public List<Car> getCars() {

        return cars;
    }
}