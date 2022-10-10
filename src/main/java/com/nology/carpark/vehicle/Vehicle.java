package com.nology.carpark.vehicle;

public abstract class Vehicle {
    private String licencePlate;
    private String make;
    private String model;
    private String colour;

    public Vehicle(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Vehicle(String licencePlate, String make, String model, String colour) {
        this.licencePlate = licencePlate;
        this.make = make;
        this.model = model;
        this.colour = colour;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColour() {
        return colour;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vehicle){
            return this.licencePlate.equals(((Vehicle) obj).licencePlate);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " licence plate = "+licencePlate;
    }
}
