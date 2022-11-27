package pt.isec.gps.team11.model.data;

import javafx.scene.image.Image;

public class Car {
    private static int idGlobal = 0;
    private int id;
    private int lotation;
    private int bagageCapacity;
    private String brand;
    private String model;
    private String licensePlate;
    private boolean luxury;
    private Image image;

    public Car(int lotation, int bagageCapacity, String brand, String model, String licensePlate, boolean luxury, Image image) {
        this.id=++idGlobal;
        this.lotation = lotation;
        this.bagageCapacity = bagageCapacity;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.luxury = luxury;
        this.image = image;
    }


    public void setLotation(int lotation) {
        this.lotation = lotation;
    }

    public int getLotation() {
        return lotation;
    }

    public void setBagageCapacity(int bagageCapacity) {
        this.bagageCapacity = bagageCapacity;
    }

    public int getBagageCapacity() {
        return bagageCapacity;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public int getId(){
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(!(obj instanceof Car) )
            return false;

        Car aux = (Car) obj;

        return this.hashCode() == aux.hashCode();
    }

    @Override
    public int hashCode() {
        return getId();
    }

}
