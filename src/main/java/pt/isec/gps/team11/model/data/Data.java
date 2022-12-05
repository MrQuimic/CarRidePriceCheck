package pt.isec.gps.team11.model.data;

import pt.isec.gps.team11.utils.Files;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

public class Data {
    public final String USERNAME = "admin@gps";
    public final String PASSWORD = "admin@gps";

    private boolean isLogged;

    private ArrayList<Car> cars;
    private ArrayList<Trip> trips;

    private String distanceOfTrip;
    private String timeOfTrip;
    private String costOfTrip;

    public Data(){
        this.isLogged = false;
        this.cars = new ArrayList<>();
        this.trips = new ArrayList<>();

//        getCarsFromFile();
    }



    public boolean confirmLogin(String username, String password){
        if(username.equals(USERNAME) && password.equals(PASSWORD)){
            this.isLogged = true;
            return true;
        }
        return false;
    }

    public boolean getIsLogged(){
        return this.isLogged;
    }

    public void getCarsFromFile(){
        Path currentRelativePath = Paths.get("");
        String filePath = currentRelativePath.toAbsolutePath().toString()
                    + "\\src\\main\\resources\\dbCar";

        cars = (ArrayList<Car>) Files.dbReadCars(filePath);
    }

    public ArrayList<Car> getSuitableCars(int lotation, int baggage){
        getCarsFromFile();

        ArrayList<Car> suitableCars = new ArrayList<>();

        for(Car car : cars){
            if(car.getLotation() <= lotation && car.getBagageCapacity() <= baggage){
                suitableCars.add(new Car(car));
            }
        }

        return suitableCars;
    }

    public void getTripsFromFile(){
        Path currentRelativePath = Paths.get("");
        String filePath = currentRelativePath.toAbsolutePath().toString()
                + "\\src\\main\\resources\\dbCar";

        trips = (ArrayList<Trip>) Files.dbReadTrip(filePath);
    }

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    public boolean confirmTrip(boolean oneWay, String date, int extraWaitingTime,
                               int numberOfLuggage, int numberOfPassengers, String departureTime, boolean highway){


        return false;
    }

    public void logout(){
        isLogged = false;
    }

    public void saveTripsResult(String result){
        String[] s = result.split("\\|");
        this.distanceOfTrip = s[0];
        this.timeOfTrip = s[1];
        this.costOfTrip = s[2];
    }
}
