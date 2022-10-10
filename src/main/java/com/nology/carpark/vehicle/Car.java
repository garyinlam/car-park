package com.nology.carpark.vehicle;

public class Car extends Vehicle {

    private int doors;
    private int seats;

    public Car(String licencePlate) {
        super(licencePlate);
    }

    public Car(String licencePlate, String make, String model, String colour, int doors, int seats) {
        super(licencePlate, make, model, colour);
        this.doors = doors;
        this.seats = seats;
    }

    public int getDoors() {
        return doors;
    }

    public int getSeats() {
        return seats;
    }
}
