package de.bc.tobias.autodatenbank;

/**
 * Created by Tobias on 27.02.2015.
 */
public class Car {
    private long id;
    private String manufacturer;
    private String model;
    private long constructionyear;
    private long horsepower;

    public Car() {

    }

    public Car(long id, String manufacturer, String model, long constructionyear, long horsepower) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.constructionyear = constructionyear;
        this.horsepower = horsepower;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getConstructionyear() {
        return constructionyear;
    }

    public void setConstructionyear(long constructionyear) {
        this.constructionyear = constructionyear;
    }

    public long getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(long horsepower) {
        this.horsepower = horsepower;
    }
}

