package nology.carpark;

import java.util.Random;

public class Main {
    private static final Random RANDOM = new Random();
    public static void main(String[] args) {
        CarPark carPark = new CarPark(30,20,50);

        VehicleFactory vehicleFactory = new VehicleFactory();
        for (int i = 0; i < 70; i++) {
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
            carPark.parkVehicle(vehicle);
        }
        System.out.println(carPark.spacesRemaining());
        System.out.println(carPark.totalSpaces());
        System.out.println(carPark.vanSpaces());
        carPark.printSpaces();
    }
}
