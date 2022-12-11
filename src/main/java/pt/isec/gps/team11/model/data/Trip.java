package pt.isec.gps.team11.model.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class Trip {
    private static int idGlobal = 0;
    private int id;
    private String origin;
    private String destination;
    private boolean oneWay;
    private Date date;
    private int extraWaitingTime;
    private int numberOfLuggage;
    private Date departureTime;
    private boolean highway;
    private String arrival;
    private int price;
    private int distance;
    private Car car;

    public Trip(String origin, String destination, boolean oneway, Date date, int extraWaitingTime, int numberOfPassengers, int numberOfLuggage, Date departureTime, boolean highway, String arrival, int price,int distance,Car car) {
        this.id=++idGlobal;
        this.origin = origin;
        this.destination = destination;
        this.oneWay = oneway;
        this.date = date;
        this.extraWaitingTime = extraWaitingTime;
        this.numberOfPassengers = numberOfPassengers;
        this.numberOfLuggage = numberOfLuggage;
        this.departureTime = departureTime;
        this.highway = highway;
        this.arrival = arrival;
        this.price = price;
        this.car = car;
        this.distance = distance;
    }

    public Trip (boolean oneWay, String date, int extraWaitingTime,int numberOfLuggage, int numberOfPassengers, String departureTime, boolean highway) {
        System.out.println("DATE " + date);
        this.id=++idGlobal;
        this.oneWay = oneWay;
        this.date = setStringToDate(date);
        this.extraWaitingTime = extraWaitingTime;
        this.numberOfPassengers = numberOfPassengers;
        this.numberOfLuggage = numberOfLuggage;
        this.departureTime = setStringToTime(departureTime);
        this.highway = highway;

    }

    public Date setStringToTime(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            return formatter.parse(time);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public Date setStringToDate(String date) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public String getStringDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = formatter.format(date);
        return stringDate;
    }

    public String getStringTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        String stringTime = formatter.format(departureTime);
        return stringTime;
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


    public void setOneWay(boolean oneWay) {
        this.oneWay = oneWay;
    }

    public boolean isOneWay() {
        return oneWay;
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

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
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


    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", oneWay=" + oneWay +
                ", date=" + date +
                ", extraWaitingTime=" + extraWaitingTime +
                ", numberOfLuggage=" + numberOfLuggage +
                ", departureTime=" + departureTime +
                ", highway=" + highway +
                ", arrival='" + arrival + '\'' +
                ", price=" + price +
                ", car=" + car +
                ", numberOfPassengers=" + numberOfPassengers +
                '}';
    }

    public static int getNextId(){
        return idGlobal+1;
    }

    public static void resetId(){
        idGlobal = 0;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
