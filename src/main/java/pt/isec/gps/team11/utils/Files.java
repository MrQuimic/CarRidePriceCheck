package pt.isec.gps.team11.utils;

import javafx.scene.image.Image;
import pt.isec.gps.team11.model.data.*;
import pt.isec.gps.team11.model.data.Person;

import java.io.*;
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


        //Requirements : String licensePlate,int lotation, int bagageCapacity, String brand,
        // String model, boolean luxury, Image image

        //roam all the db file
        for(List<String> tempList : list){
            try {
                //if dont have the expected size
                if(tempList.size() != 7)
                    throw new Exception("Abnormal line in DB");
                carsList.add(new Car(tempList.get(0),Integer.parseInt(tempList.get(1)), Integer.parseInt(tempList.get(2)), tempList.get(3), tempList.get(4), Boolean.parseBoolean(tempList.get(5)) ,new Image(tempList.get(6))));
            }catch (Exception e){
                System.err.println("Line: " + tempList + " with error");
            }
        }
        return carsList;
    }

    public static List<Person> dbReadUsers(String dbLocation){
        //location = Resources/dbAdmin.txt
        //location = Resources/dbClient.txt

        List<List<String>> list = openFile(dbLocation);
        List<Person> personsList = new ArrayList<>();

        //Requirements : String name,  String email, Date birthDate(year,month,day)

        //roam all the db file
        for(List<String> tempList : list){
            try {
                //if dont have the expected size
                if(tempList.size() != 5)
                    throw new Exception("Abnormal line in DB");
                personsList.add(new Person(tempList.get(0),tempList.get(1),new Date(Integer.parseInt(tempList.get(2)),Integer.parseInt(tempList.get(3)),Integer.parseInt(tempList.get(4)))));
            }catch (Exception e){
                System.err.println("Line: " + tempList + " with error");
            }
        }
        return personsList;
    }

    public static List<Driver> dbReadDrivers(String dbLocation){
        //location = Resources/dbDriver.txt

        List<List<String>> list = openFile(dbLocation);
        List<Driver> personsList = new ArrayList<>();

        //list of cars associated to one trip
        ArrayList<String> infoList;

        //String name,  String email, Date birthDate(year,month,day),Information List
        for(List<String> tempList : list){
            try {
                if(tempList.size() < 5)
                    throw new Exception("Abnormal line in DB");

                infoList = new ArrayList<>();

                //getting all the cars associated to the trio
                for(int i = 5; i < tempList.size();i++)
                    infoList.add(tempList.get(i));

                personsList.add(new Driver(tempList.get(0),tempList.get(1),new Date(Integer.parseInt(tempList.get(2)),Integer.parseInt(tempList.get(3)),Integer.parseInt(tempList.get(4))),infoList));
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


        //handle all file
        for(List<String> tempList : list){
            try {
                //if dont have the expected size
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


        //read db
        List<List<String>> list = openFile(dbLocation);


        //the list that will be sent
        List<Trip> tripList = new ArrayList<>();


        //list of cars associated to one trip
        ArrayList<String> carIds;


        //roam all the db entities
        for(List<String> tempList : list){
            try {

                //check if have all the needed arguments
                if(tempList.size() < 18)
                    throw new Exception("Abnormal line in DB");

                carIds = new ArrayList<>();

                //getting all the cars associated to the trip
                for(int i = 17; i < tempList.size();i++)
                    carIds.add(tempList.get(i));

                //add to the trip list
                tripList.add(new Trip(tempList.get(0),tempList.get(1),Boolean.getBoolean(tempList.get(2)), new Date( Integer.parseInt(tempList.get(3)) ,Integer.parseInt(tempList.get(4)) ,Integer.parseInt(tempList.get(5)) ),Integer.parseInt(tempList.get(6)),
                Integer.parseInt(tempList.get(7)),Integer.parseInt(tempList.get(8)) ,new Date( Integer.parseInt(tempList.get(9)) ,Integer.parseInt(tempList.get(10)) ,Integer.parseInt(tempList.get(11)),Integer.parseInt(tempList.get(12)),Integer.parseInt(tempList.get(13)) ),Boolean.getBoolean(tempList.get(14)),tempList.get(15),
                Integer.parseInt(tempList.get(16)),carIds));


            }catch (Exception e){
                System.err.println("Line: " + tempList + " with error");
            }
        }

        return tripList;
    }

    public static boolean exportPersonDB(ArrayList<Person> personList,String location){
        //open the location
        File file = new File(location);
        PrintWriter pWriter = null;

        try {
            //create the file print writer
             pWriter = new PrintWriter(new FileWriter(file));
        } catch (IOException e) {
            System.err.println("Erro: " + e);
            return false;
        }

        //for each person write one line with all the data required
        for(Person person : personList){
            pWriter.println(person.getName() + ';' + person.getEmail() + ';' + person.getBirthDate().getYear()+
                    person.getBirthDate().getMonth()+person.getBirthDate().getDay());
        }

        pWriter.close();
        return true;
    }

    public static boolean exportTripDB(ArrayList<Trip> personList,String location){
        //open the location
        File file = new File(location);
        PrintWriter pWriter = null;

        try {
            //create the file print writer
            pWriter = new PrintWriter(new FileWriter(file));
        } catch (IOException e) {
            System.err.println("Erro: " + e);
            return false;
        }

        //for each trip write one line with all the data required
        for(Trip trip : personList){
            pWriter.print(trip.getOrigin()+';'+trip.getDestination()+';'+trip.isOneWay()+ ';'+
                    trip.getDate().getYear()+';'+trip.getDate().getMonth()+';'+
                    trip.getDate().getDay()+';'+trip.getExtraWaitingTime()+';' +trip.getNumberOfPassengers()+';'+
                    trip.getNumberOfLuggage()+';'+trip.getDepartureTime().getYear()+';'+trip.getDepartureTime().getMonth()+
                    ';'+trip.getDepartureTime().getDay()+';'+trip.isHighway());

            //write all the extra informations
            for(String info : trip.getCarsIds()){
                pWriter.print(';'+info);
            }
            //next line
            pWriter.println();
        }

        pWriter.close();
        return true;
    }

}



