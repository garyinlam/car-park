package com.nology.carpark;

import com.nology.carpark.exceptions.NoSpaceException;
import com.nology.carpark.utils.VehicleFactory;
import com.nology.carpark.utils.VehicleType;
import com.nology.carpark.vehicle.Car;
import com.nology.carpark.vehicle.Motorcycle;
import com.nology.carpark.vehicle.Van;
import com.nology.carpark.vehicle.Vehicle;

import java.util.Random;

public class Main {
    private static final Random RANDOM = new Random();
    public static void main(String[] args) {
        CarPark carPark = new CarPark(30,20,50);

        VehicleFactory vehicleFactory = new VehicleFactory();
        for (int i = 0; i < 30; i++) {
            VehicleType type;
            switch (RANDOM.nextInt(3)){
                case 0:
                    type = VehicleType.car;
                    break;
                case 1:
                    type = VehicleType.van;
                    break;
                case 2:
                    type = VehicleType.motorcycle;
                    break;
                default:
                    type = null;
            }
            Vehicle vehicle = vehicleFactory.createVehicle(type);
            try {
                carPark.parkVehicle(vehicle);
            } catch (NoSpaceException e){
                System.err.println(e.getLocalizedMessage());
            }
        }
        System.out.println(carPark.spacesRemaining());
        System.out.println(carPark.totalSpaces());
        System.out.println(carPark.vanSpaces());
        carPark.removeVehicle("5");
        carPark.parkVehicle(new Car("H3110"));
        carPark.parkVehicle(new Van("W0R1D"));
        carPark.parkVehicle(new Motorcycle("T3ST"));
        carPark.parkVehicle(new Motorcycle("L4ST"),99);
        carPark.printSpaces();
    }
}
