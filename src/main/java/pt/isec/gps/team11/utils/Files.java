package pt.isec.gps.team11.utils;

import javafx.scene.image.Image;
import pt.isec.gps.team11.model.data.*;
import pt.isec.gps.team11.model.data.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Files{
    public static List<List<String>> openFile(String pathFile) {
        List<List<String>> fileTokens = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(pathFile))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                fileTokens.add(Arrays.asList(values));
            }
        } catch (Exception e) {
            return null;
        }

        return fileTokens;
    }


    public static List<Car> dbReadCars(String dbLocation){
        //location = Resources/dbCar.txt
        List<List<String>> list = openFile(dbLocation);
        List<Car> carsList = new ArrayList<>();


        //Requirements : int lotation, int bagageCapacity, String brand, String model, String licensePlate,boolean luxury, Image image


        for(List<String> tempList : list){
            try {
                if(tempList.size() != 7)
                    throw new Exception("Abnormal line in DB");
                carsList.add(new Car(Integer.parseInt(tempList.get(0)), Integer.parseInt(tempList.get(1)), tempList.get(2), tempList.get(3), tempList.get(4), Boolean.parseBoolean(tempList.get(5)) ,new Image(tempList.get(6))));
            }catch (Exception e){
                System.err.println("Line: " + tempList + " with error");
            }
        }
        return carsList;
    }


    public static List<Person> dbReadUsers(String dbLocation){
        //location = Resources/dbAdmin.txt
        //location = Resources/dbDriver.txt
        //location = Resources/dbClient.txt

        List<List<String>> list = openFile(dbLocation);
        List<Person> personsList = new ArrayList<>();

        //String name,  String email, Date birthDate(year,month,day)

        for(List<String> tempList : list){
            try {
                if(tempList.size() != 5)
                    throw new Exception("Abnormal line in DB");
                personsList.add(new Person(tempList.get(0),tempList.get(1),new Date(Integer.parseInt(tempList.get(2)),Integer.parseInt(tempList.get(3)),Integer.parseInt(tempList.get(4)))));
            }catch (Exception e){
                System.err.println("Line: " + tempList + " with error");
            }
        }
        return personsList;
    }


    public static List<Company> dbReadCompany(String dbLocation){
        //location = Resources/dbCompany.txt


        List<List<String>> list = openFile(dbLocation);
        List<Company> companyList = new ArrayList<>();



        for(List<String> tempList : list){
            try {
                if(tempList.size() != 1)
                    throw new Exception("Abnormal line in DB");
                companyList.add(new Company(tempList.get(0)));
            }catch (Exception e){
                System.err.println("Line: " + tempList + " with error");
            }
        }
        return companyList;
    }

    public static List<Trip> dbReadTrip(String dbLocation){
        //location = Resources/dbTrip.txt


        List<List<String>> list = openFile(dbLocation);
        List<Trip> tripList = new ArrayList<>();

//String origin, String destination, boolean oneway, Date date, int extraWaitingTime, int numberOfPassengers, int numberOfLuggage, Date departureTime, boolean highway, String arrival, int price, ArrayList<Car> cars

        for(List<String> tempList : list){
            try {
                if(tempList.size() != 15) //TODO VER NUMERO
                    throw new Exception("Abnormal line in DB");

                tripList.add(new Trip(tempList.get(0),tempList.get(1),Boolean.getBoolean(tempList.get(2)), new Date( Integer.parseInt(tempList.get(3)) ,Integer.parseInt(tempList.get(4)) ,Integer.parseInt(tempList.get(5)) ),Integer.parseInt(tempList.get(6)),
                Integer.parseInt(tempList.get(7)),Integer.parseInt(tempList.get(8)) ,new Date( Integer.parseInt(tempList.get(9)) ,Integer.parseInt(tempList.get(10)) ,Integer.parseInt(tempList.get(11))),Boolean.getBoolean(tempList.get(12)),tempList.get(13),
                Integer.parseInt(tempList.get(14))));

            }catch (Exception e){
                System.err.println("Line: " + tempList + " with error");
            }
        }

        return tripList;
    }




}



