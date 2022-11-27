package pt.isec.gps.team11.model.data;

import java.util.ArrayList;
import java.util.Date;

public class Trip {
    private static int idGlobal = 0;
    private int id;
    private String origin;
    private String destination;
    private boolean oneway;
    private Date date;
    private int extraWaitingTime;
    private int numberOfLuggage;
    private Date departureTime;
    private boolean highway;
    private String arrival;
    private int price;
    private ArrayList<Car> cars;

    public Trip(String origin, String destination, boolean oneway, Date date, int extraWaitingTime, int numberOfPassengers, int numberOfLuggage, Date departureTime, boolean highway, String arrival, int price) {
        this.id=++idGlobal;
        this.origin = origin;
        this.destination = destination;
        this.oneway = oneway;
        this.date = date;
        this.extraWaitingTime = extraWaitingTime;
        this.numberOfPassengers = numberOfPassengers;
        this.numberOfLuggage = numberOfLuggage;
        this.departureTime = departureTime;
        this.highway = highway;
        this.arrival = arrival;
        this.price = price;
        this.cars = new ArrayList<>();
    }

    private int numberOfPassengers;

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }


    public void setOneway(boolean oneway) {
        this.oneway = oneway;
    }

    public boolean isOneway() {
        return oneway;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setExtraWaitingTime(int extraWaitingTime) {
        this.extraWaitingTime = extraWaitingTime;
    }

    public int getExtraWaitingTime() {
        return extraWaitingTime;
    }


    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public int getNumberOfLuggage() {
        return numberOfLuggage;
    }

    public void setNumberOfLuggage(int numberOfLuggage) {
        this.numberOfLuggage = numberOfLuggage;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public boolean isHighway() {
        return highway;
    }

    public void setHighway(boolean highway) {
        this.highway = highway;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(!(obj instanceof Trip) )
            return false;

        Trip aux = (Trip) obj;

        return this.hashCode() == aux.hashCode();
    }

    @Override
    public int hashCode() {
        return getId();
    }

    private boolean addCar(Car car){
        if(cars.contains(car) || car == null)
            return false;

        this.cars.add(car);
        return true;
    }

    private boolean removeCar(Car car){
        if(!cars.contains(car))
            return false;
        cars.remove(car);
        return true;
    }





}
