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

        Car car = new Car(history,
                null,
                1000,
                "Essence",
                23,
                "voiture",
                "Chrysler",
                "300c",
                "Mon char");
        cars.add(car);
        return cars;
    }
}