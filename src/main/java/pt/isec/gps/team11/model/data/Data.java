package pt.isec.gps.team11.model.data;

import pt.isec.gps.team11.utils.Files;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Data {
    public final String USERNAME = "admin@gps";
    public final String PASSWORD = "admin@gps";

    public final String USERNAMECLI = "client@gps";
    public final String PASSWORDCLI = "client@gps";

    private boolean isLogged;

    private HashMap<String, Car> cars;
    private HashMap<Integer, Trip> trips;

    private String distanceOfTrip;
    private String timeOfTrip;
    private String costOfTrip;
    private String tripOrigin;
    private String tripDestination;
    private Trip currentTrip;

    private Car currentTripCar;

    public Data(){
        this.isLogged = false;
        this.cars = new HashMap<>();
        this.trips = new HashMap<>();

        getCarsFromFile();
        getTripsFromFile();
    }



    public boolean confirmLogin(String username, String password){
        if((username.equals(USERNAME) && password.equals(PASSWORD)) || (username.equals(USERNAMECLI) && password.equals(PASSWORDCLI))){
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
        cars = Files.dbReadCars(filePath);
    }

    public ArrayList<Car> getSuitableCars(){
        ArrayList<Car> suitableCars = new ArrayList<>();

        for(Map.Entry<String, Car> entry : cars.entrySet()){
            if(entry.getValue().getLotation() >= currentTrip.getNumberOfPassengers()
                    && entry.getValue().getBagageCapacity() >= currentTrip.getNumberOfLuggage()){
                suitableCars.add(new Car(entry.getValue()));
            }
        }

        if(suitableCars.isEmpty()){
            for(Map.Entry<String, Car> entry : cars.entrySet()){
                suitableCars.add(new Car(entry.getValue()));
            }
        }

        return suitableCars;
    }

    public void getTripsFromFile(){
        Path currentRelativePath = Paths.get("");
        String filePath = currentRelativePath.toAbsolutePath() + "\\src\\main\\resources\\dbTrip";

        trips = Files.dbReadTrip(filePath,cars);
    }

    public HashMap<Integer, Trip> getTrips() {
        getTripsFromFile();
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
        String[] s = result.split("\\| ");



            this.distanceOfTrip = s[0];
            this.timeOfTrip = s[1];
            this.costOfTrip = s[2];
    }
    public void saveCurrentTripCar(){

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

    public void saveCurrentCar(Car car){
        this.currentTripCar = new Car(car);
    }

    public Car getCurrentTripCar(){
        return currentTripCar;
    }

    public void confirmTrip(){
        currentTrip.setOrigin(this.tripOrigin);
        currentTrip.setDestination(this.tripDestination);
        currentTrip.setCar(this.currentTripCar);

        String[] date = currentTrip.getStringDate().split("/");
        String[] time = currentTrip.getStringTime().split(":");
        String[] cost = this.costOfTrip.split(" ");
        String[] distance = this.distanceOfTrip.split(" ");

        StringBuilder sb = new StringBuilder();
        sb.append(currentTrip.getOrigin()).append(";").append(currentTrip.getDestination()).append(";")
                .append(currentTrip.isOneWay()).append(";").append(date[2]).append(";")
                .append(date[1]).append(";").append(date[0]).append(";").append(currentTrip.getExtraWaitingTime())
                .append(";").append(currentTrip.getNumberOfPassengers())
                .append(";").append(currentTrip.getNumberOfLuggage()).append(";").append(time[0])
                .append(";").append(time[1]).append(";").append(currentTrip.isHighway()).append(";")
                .append("---").append(";").append(cost[0]).append(";").append(distance[0])
                .append(";").append(currentTrip.getCar().getLicensePlate()).append("\n");



        try{
            Path currentRelativePath = Paths.get("");
            String filePath = currentRelativePath.toAbsolutePath() + "\\src\\main\\resources\\dbTrip";
            File file = new File(filePath);
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            pw.write(sb.toString());
            pw.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        resetTripResults();
        currentTrip = null;
        currentTripCar = null;
    }
}
