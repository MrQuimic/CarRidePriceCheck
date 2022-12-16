package pt.isec.gps.team11.model.data;

import java.util.ArrayList;
import java.util.Date;

public class Driver extends Person {
    public static int idGlobal = 0;
    private ArrayList<String> tripInfo;
    private int id;

    public Driver(String name, String email, Date birthDate,ArrayList<String> tripInfo) {
        super(name, email, birthDate);
        this.tripInfo = new ArrayList<>();
        this.id = ++idGlobal;
        this.tripInfo = tripInfo;
    }

    public void receiveInfo(ArrayList<String> tripInfo) {
        this.tripInfo = tripInfo;
    }

    public int getDriverId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<String> getTripInfo() {
        return tripInfo;
    }
}
