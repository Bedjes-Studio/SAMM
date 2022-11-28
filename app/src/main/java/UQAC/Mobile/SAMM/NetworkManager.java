package UQAC.Mobile.SAMM;

import java.util.ArrayList;
import java.util.List;

public class NetworkManager {
    // get voiture
    // ajout voiture
    // ajout refuel
    // get events


    public NetworkManager() {
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