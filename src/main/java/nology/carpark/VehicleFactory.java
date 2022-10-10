package nology.carpark;

import static nology.carpark.VehicleUtils.*;
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
