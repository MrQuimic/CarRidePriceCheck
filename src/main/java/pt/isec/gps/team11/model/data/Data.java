package pt.isec.gps.team11.model.data;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Data {
    public final String USERNAME = "admin@gps";
    public final String PASSWORD = "admin@gps";

    private boolean isLogged;

    private ArrayList<Car> cars;
    private ArrayList<Trip> trips;

    private String distanceOfTrip;
    private String timeOfTrip;
    private String costOfTrip;
    private String tripOrigin;
    private String tripDestination;
    private Trip currentTrip;

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

    public void setIsLogged(){
        this.isLogged = true;
    }

    public boolean getIsLogged(){
        return this.isLogged;
    }

    public void getCarsFromFile(){
        Path currentRelativePath = Paths.get("");
        String filePath = currentRelativePath.toAbsolutePath().toString()
                    + "\\src\\main\\resources\\dbCar";

        //cars = (ArrayList<Car>) Files.dbReadCars(filePath);
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

        //trips = (ArrayList<Trip>) Files.dbReadTrip(filePath);
    }

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    public boolean book(boolean oneWay, String date, int extraWaitingTime, int numberOfLuggage, int numberOfPassengers, String departureTime, boolean highway){
        currentTrip = new Trip(oneWay, date, extraWaitingTime, numberOfLuggage, numberOfPassengers, departureTime, highway);

        return true;
    }

    public Trip getCurrentTrip(){
        return this.currentTrip;
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

    public void resetTripResults(){
        this.distanceOfTrip = null;
        this.timeOfTrip = null;
        this.costOfTrip = null;
    }

    public String getCostOfTrip() {
        return costOfTrip;
    }

    public String getTimeOfTrip() {
        return timeOfTrip;
    }

    public String getDistanceOfTrip() {
        return distanceOfTrip;
    }

    public void setTripOrigin(String tripOrigin) {
        this.tripOrigin = tripOrigin;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public String getTripOrigin() {
        return tripOrigin;
    }
}
