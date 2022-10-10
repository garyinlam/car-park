package com.nology.carpark.vehicle;

public class Van extends Vehicle {

    private int payloadCapacity ;
    public Van(String licencePlate) {
        super(licencePlate);
    }

    public Van(String licencePlate, String make, String model, String colour, int payloadCapacity) {
        super(licencePlate, make, model, colour);
        this.payloadCapacity = payloadCapacity;
    }

    public int getPayloadCapacity() {
        return payloadCapacity;
    }
}
