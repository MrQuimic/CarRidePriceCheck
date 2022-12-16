package pt.isec.gps.team11.model.data;

import java.util.List;
import java.util.Objects;

public class Company {
    private List<Trip> trips;
    private String name;

    public Company(String name, List<Trip> trips){
        this.name = name;
        this.trips = trips;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean addTrip(Trip trip) {
        if(trip == null)
            return false;
        trips.add(trip);
        return true;
    }

    public boolean removeTrip(Trip trip) {
        if(!trips.contains(trip))
            return false;
        trips.remove(trip);
        return true;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(!(obj instanceof Company) )
            return false;

        Company aux = (Company) obj;

        return this.hashCode() == aux.hashCode();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}