package com.nology.carpark.utils;

import com.nology.carpark.vehicle.Car;
import com.nology.carpark.vehicle.Motorcycle;
import com.nology.carpark.vehicle.Van;
import com.nology.carpark.vehicle.Vehicle;

import static com.nology.carpark.utils.VehicleUtils.*;
public class VehicleFactory {
    public Vehicle createVehicle(VehicleType type){
        switch (type){
            case car:
                return new Car(nextUniquePlate());
            case van:
                return new Van(nextUniquePlate());
            case motorcycle:
                return new Motorcycle(nextUniquePlate());
            default:
                throw new IllegalArgumentException("Cannot create vehicle of type: "+type);
        }
    }
}
