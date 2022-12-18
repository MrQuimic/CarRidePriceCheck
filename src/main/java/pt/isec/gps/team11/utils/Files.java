package pt.isec.gps.team11.utils;

import javafx.scene.image.Image;
import pt.isec.gps.team11.model.data.*;
import pt.isec.gps.team11.model.data.Person;

import java.io.*;
import java.util.*;

public class Files {
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

    public static HashMap<String, Car> dbReadCars(String dbLocation) {
        //location = Resources/dbCar.txt
        List<List<String>> list = openFile(dbLocation);
        HashMap<String, Car> carsList = new HashMap<>();


        //Requirements : String licensePlate,int lotation, int bagageCapacity, String brand,
        // String model, boolean luxury, Image image

        //roam all the db file
        for (List<String> tempList : list) {
            try {

                //if dont have the expected size
                if (tempList.size() != 7) {
                    throw new Exception("Abnormal line in DB");
                }
                //System.out.println(tempList);
                carsList.put(tempList.get(0), new Car(tempList.get(0), Integer.parseInt(tempList.get(1)), Integer.parseInt(tempList.get(2)), tempList.get(3), tempList.get(4), Boolean.parseBoolean(tempList.get(5)), tempList.get(6)));
            } catch (Exception e) {
                //System.err.println("Line: " + tempList + " with error" + e);
            }
        }
        return carsList;
    }

    public static List<Person> dbReadUsers(String dbLocation) {
        //location = Resources/dbAdmin.txt
        //location = Resources/dbClient.txt

        List<List<String>> list = openFile(dbLocation);
        List<Person> personsList = new ArrayList<>();

        //Requirements : String name,  String email, Date birthDate(year,month,day)

        //roam all the db file
        for (List<String> tempList : list) {
            try {
                //if dont have the expected size
                if (tempList.size() != 5)
                    throw new Exception("Abnormal line in DB");
                personsList.add(new Person(tempList.get(0), tempList.get(1), new Date(Integer.parseInt(tempList.get(2)), Integer.parseInt(tempList.get(3)), Integer.parseInt(tempList.get(4)))));
            } catch (Exception e) {
                System.err.println("Line: " + tempList + " with error");
            }
        }
        return personsList;
    }

    public static List<Driver> dbReadDrivers(String dbLocation) {
        //location = Resources/dbDriver.txt

        List<List<String>> list = openFile(dbLocation);
        List<Driver> personsList = new ArrayList<>();

        //list of cars associated to one trip
        ArrayList<String> infoList;

        //String name,  String email, Date birthDate(year,month,day),Information List
        for (List<String> tempList : list) {
            try {
                if (tempList.size() < 5)
                    throw new Exception("Abnormal line in DB");

                infoList = new ArrayList<>();

                //getting all the cars associated to the trio
                for (int i = 5; i < tempList.size(); i++)
                    infoList.add(tempList.get(i));

                personsList.add(new Driver(tempList.get(0), tempList.get(1), new Date(Integer.parseInt(tempList.get(2)), Integer.parseInt(tempList.get(3)), Integer.parseInt(tempList.get(4))), infoList));
            } catch (Exception e) {
                System.err.println("Line: " + tempList + " with error");
            }
        }
        return personsList;
    }

    public static HashMap<String, Company> dbReadCompany(String dbLocation, HashMap<Integer, Trip> availableTrips) {
        //location = Resources/dbCompany.txt.txt


        List<List<String>> list = openFile(dbLocation);
        HashMap<String, Company> companyList = new HashMap<>();
        List<Trip> tripList;

        //handle all file
        for (List<String> tempList : list) {
            try {
                //if dont have the expected size
                if (tempList.size() < 2)
                    throw new Exception("Abnormal line in DB");

                tripList = new ArrayList<>();

                //getting all the cars associated to the trip
                for (int i = 1; i < tempList.size(); i++) {
                    tripList.add(availableTrips.get(Integer.parseInt(tempList.get(i))));
                }
                companyList.put(tempList.get(0), new Company(tempList.get(0), tripList));
            } catch (Exception e) {
                System.err.println("Line: " + tempList + " with error");
            }
        }
        return companyList;
    }

    public static HashMap<Integer, Trip> dbReadTrip(String dbLocation, HashMap<String, Car> availableCars) {
        //location = Resources/dbTrip.txt

        //read db
        List<List<String>> list = openFile(dbLocation);


        //the list that will be sent
        HashMap<Integer, Trip> tripList = new HashMap<>();


        //list of cars associated to one trip
        Car tripCar = new Car();


        //roam all the db entities
        assert list != null;
        for (List<String> tempList : list) {
            try {

                //check if have all the needed arguments
                if (tempList.size() != 16)
                    throw new Exception("Abnormal line in DB");


                //getting all the cars associated to the trip
                tripCar = availableCars.get(tempList.get(15));

                //add to the trip list
                Calendar tempCalendar1 = GregorianCalendar.getInstance();
                tempCalendar1.set(Calendar.YEAR, Integer.parseInt(tempList.get(3)));
                tempCalendar1.set(Calendar.MONTH, Integer.parseInt(tempList.get(4)));
                tempCalendar1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(tempList.get(5)));

                Calendar tempCalendar2 = GregorianCalendar.getInstance();
                tempCalendar2.set(Calendar.HOUR, Integer.parseInt(tempList.get(9)));
                tempCalendar2.set(Calendar.MINUTE, Integer.parseInt(tempList.get(10)));



                tripList.put(Trip.getNextId(), new Trip(tempList.get(0), tempList.get(1), Boolean.getBoolean(tempList.get(2)), tempCalendar1.getTime(), Integer.parseInt(tempList.get(6)),
                        Integer.parseInt(tempList.get(7)), Integer.parseInt(tempList.get(8)), tempCalendar2.getTime(), Boolean.getBoolean(tempList.get(11)), tempList.get(12),
                        Double.parseDouble(tempList.get(13)), Integer.parseInt(String.valueOf(Math.round(Double.parseDouble(tempList.get(14))))), tripCar));
            } catch (Exception e) {
                System.err.println("Line: " + tempList + " with error" + e);
            }
        }
        return tripList;
    }

    public static boolean exportPersonDB(ArrayList<Person> personList, String location) {
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
        for (Person person : personList) {
            pWriter.println(person.getName() + ';' + person.getEmail() + ';' + person.getBirthDate().getYear() +
                    person.getBirthDate().getMonth() + person.getBirthDate().getDay());
        }

        pWriter.close();
        return true;
    }

    public static boolean exportTripDB(ArrayList<Trip> personList, String location) {
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
        for (Trip trip : personList) {
            pWriter.print(trip.getOrigin() + ';' + trip.getDestination() + ';' + trip.isOneWay() + ';' +
                    trip.getDate().getYear() + ';' + trip.getDate().getMonth() + ';' +
                    trip.getDate().getDay() + ';' + trip.getExtraWaitingTime() + ';' + trip.getNumberOfPassengers() + ';' +
                    trip.getNumberOfLuggage() + ';' + trip.getDepartureTime().getYear() + ';' + trip.getDepartureTime().getMonth() +
                    ';' + trip.getDepartureTime().getDay() + ';' + trip.isHighway());


            pWriter.print(';' + trip.getCar().getLicensePlate());

            //next line
            pWriter.println();
        }

        pWriter.close();
        return true;
    }

}



