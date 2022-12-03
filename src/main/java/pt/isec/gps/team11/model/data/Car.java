package pt.isec.gps.team11.model.data;

import javafx.scene.image.Image;

public class Car {
    private int lotation;
    private int bagageCapacity;
    private String brand;
    private String model;
    private String licensePlate;
    private boolean luxury;
    private Image image;

    public Car(String licensePlate,int lotation, int bagageCapacity, String brand, String model, boolean luxury, Image image) {
        this.lotation = lotation;
        this.bagageCapacity = bagageCapacity;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.luxury = luxury;
        this.image = image;
    }

    public Car(Car anotherCar){
        this.lotation = anotherCar.getLotation();
        this.bagageCapacity = anotherCar.getBagageCapacity();
        this.brand = anotherCar.getBrand();
        this.model = anotherCar.getModel();
        this.licensePlate = anotherCar.licensePlate;
        this.luxury = anotherCar.getLuxury();
        this.image = anotherCar.getImage();
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

    public boolean getLuxury(){
        return this.luxury;
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
        return getLicensePlate().hashCode();
    }

}
