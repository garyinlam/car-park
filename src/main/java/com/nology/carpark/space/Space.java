package com.nology.carpark.space;

import com.nology.carpark.vehicle.Vehicle;

public abstract class Space {
    private Vehicle parked;
    private int id;

    public Space(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Vehicle getParked() {
        return parked;
    }

    public void setParked(Vehicle parked) {
        this.parked = parked;
    }

    public void clearSpace(){
        parked = null;
    }

    public boolean isOccupied(){
        return parked != null;
    }
}
