package pt.isec.gps.team11.model.data;

import javafx.scene.image.Image;

/**
 * The type Car.
 */
public class Car {
    private int lotation;
    private int bagageCapacity;
    private String brand;
    private String model;
    private String licensePlate;
    private boolean luxury;
    private Image image;

    /**
     * Instantiates a new Car.
     *
     * @param licensePlate   the license plate
     * @param lotation       the lotation
     * @param bagageCapacity the bagage capacity
     * @param brand          the brand
     * @param model          the model
     * @param luxury         the luxury
     * @param image          the image
     */
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

    /**
     * Sets lotation.
     *
     * @param lotation the lotation
     */
    public void setLotation(int lotation) {
        this.lotation = lotation;
    }

    /**
     * Gets lotation.
     *
     * @return the lotation
     */
    public int getLotation() {
        return lotation;
    }

    /**
     * Sets bagage capacity.
     *
     * @param bagageCapacity the bagage capacity
     */
    public void setBagageCapacity(int bagageCapacity) {
        this.bagageCapacity = bagageCapacity;
    }

    /**
     * Gets bagage capacity.
     *
     * @return the bagage capacity
     */
    public int getBagageCapacity() {
        return bagageCapacity;
    }

    /**
     * Sets brand.
     *
     * @param brand the brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Gets brand.
     *
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets model.
     *
     * @param model the model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets model.
     *
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets license plate.
     *
     * @param licensePlate the license plate
     */
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    /**
     * Gets license plate.
     *
     * @return the license plate
     */
    public String getLicensePlate() {
        return licensePlate;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
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
