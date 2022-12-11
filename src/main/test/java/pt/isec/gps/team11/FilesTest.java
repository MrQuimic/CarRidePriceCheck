package pt.isec.gps.team11;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pt.isec.gps.team11.gui.panes.utils.CSSManager;
import pt.isec.gps.team11.model.CRPCManager;
import pt.isec.gps.team11.model.data.Car;
import pt.isec.gps.team11.model.data.Company;
import pt.isec.gps.team11.model.data.Trip;
import pt.isec.gps.team11.utils.Files;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

class FilesTest {
    private static HashMap<String,Car> supposedCars;
    private static HashMap<Integer,Trip> supposedTrip;
    private static HashMap<String,Company> supposedCompanies;

    private static final Path currentRelativePath = Paths.get("");
    private static final String s = currentRelativePath.toAbsolutePath().toString();
    @BeforeAll
    public static void init() {

        supposedCars = new HashMap<>();
        supposedCars.put("39-AT-39",new Car("39-AT-39",4, 4, "Tesla", "Y",true ,"Resources/image/cars/photo1.png"));
        supposedCars.put("74-00-AC",new Car("74-00-AC",3, 3, "Seat", "Ibiza",false ,"Resources/image/cars/photo2.png"));
        supposedCars.put("45-XD-22",new Car("45-XD-22",4, 4, "Audi", "A3",true ,"Resources/image/cars/photo3.png"));
        supposedCars.put("12-EE-44",new Car("12-EE-44",6, 4, "Renault", "Space",false ,"Resources/image/cars/photo4.png"));

        supposedTrip = new HashMap<>();



        supposedTrip.put(1,new Trip("Coimbra","Porto",true,new Date(2022,12,3),0,2,2,new Date(2022,12,3,12,30),true,"---",1,124,(supposedCars.get("39-AT-39"))));

        supposedTrip.put(2,new Trip("Lisboa","Setubal",false,new Date(2022,12,15),0,4,2,new Date(2022,12,15,15,20),true,"---",45,48,supposedCars.get("74-00-AC")));


        supposedTrip.put(3,new Trip("Algarve","Evora",true,new Date(2023,1,23),0,1,2,new Date(2023,1,23,7,45),false,"---",50,45,supposedCars.get("45-XD-22")));
        Trip.resetId();

        supposedCompanies= new HashMap<>();
        ArrayList<Trip> temp2 = new ArrayList<>();
        temp2.add(supposedTrip.get(1));
        supposedCompanies.put("YourWay",new Company("YourWay",temp2));

        temp2 = new ArrayList<>();
        temp2.add(supposedTrip.get(2));
        supposedCompanies.put("Transportugal",new Company("Transportugal",temp2));

        temp2 = new ArrayList<>();
        temp2.add(supposedTrip.get(3));
        supposedCompanies.put("AtYOURSpeed",new Company("AtYOURSpeed",temp2));
    }


    @Test
    void dbReadCarsTest() {

        String url = s+"\\src\\main\\resources\\dbCar";


        //System.out.println(Files.openFile(s+"\\src\\main\\resources\\dbCar"));
        //System.out.println((url));
        HashMap<String, Car> carsRead = Files.dbReadCars(url);

        Assertions.assertEquals(supposedCars,carsRead,"iguais");
    }

    @Test
    void dbReadTripsTest(){

        String url = s+"\\src\\main\\resources\\dbTrip";
        HashMap<Integer,Trip> tripsRead = Files.dbReadTrip(url, supposedCars);


        Assertions.assertEquals(supposedTrip,tripsRead,"iguais");
    }

    @Test
    void dbReadCompaniesTest(){

        String url = s+"\\src\\main\\resources\\dbCompany";

        HashMap<String, Company> companiesRead = Files.dbReadCompany(url, supposedTrip);
        Assertions.assertEquals(supposedCompanies,companiesRead,"iguais");
    }


}