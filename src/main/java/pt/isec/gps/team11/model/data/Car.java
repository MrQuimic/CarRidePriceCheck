package pt.isec.gps.team11.model.data;


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
    private String imagePath;

    /**
     * Instantiates a new Car.
     *
     * @param licensePlate   the license plate
     * @param lotation       the lotation
     * @param bagageCapacity the bagage capacity
     * @param brand          the brand
     * @param model          the model
     * @param luxury         the luxury
     * @param imagePath          the image
     */
    public Car(String licensePlate,int lotation, int bagageCapacity, String brand, String model, boolean luxury, String imagePath) {
        this.lotation = lotation;
        this.bagageCapacity = bagageCapacity;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.luxury = luxury;
        this.imagePath = imagePath;
    }

    public Car(Car other){
        this.lotation = other.lotation;
        this.bagageCapacity = other.bagageCapacity;
        this.brand = other.brand;
        this.model = other.model;
        this.licensePlate = other.licensePlate;
        this.luxury = other.luxury;
        this.imagePath = other.imagePath;
    }

    public Car(){
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
    public void setImage(String image) {
        this.imagePath = image;
    }

    public String getImage() {
        return imagePath;
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

    @Override
    public String toString() {
        return "Car{" +
                "lotation=" + lotation +
                ", bagageCapacity=" + bagageCapacity +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", luxury=" + luxury +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}

