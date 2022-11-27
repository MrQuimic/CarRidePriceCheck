package pt.isec.gps.team11.model.data;

import java.util.Date;

public class Admin extends Person {

    public Admin(String name, String email, Date birthDate){
        super(name, email, birthDate);
    }

    public boolean insertCar(Car car) {
        return false;
    }

    public boolean removeCar(int idCar){
        return false;
    }

    public boolean insertDriver() {
        return false;
    }

    public boolean removeDriver() {
        return false;
    }

    public void receiveInfo() {

    }

}
