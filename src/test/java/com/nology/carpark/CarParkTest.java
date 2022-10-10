package com.nology.carpark;

import com.nology.carpark.utils.VehicleFactory;
import com.nology.carpark.utils.VehicleType;
import com.nology.carpark.vehicle.Car;
import com.nology.carpark.vehicle.Motorcycle;
import com.nology.carpark.vehicle.Van;
import com.nology.carpark.vehicle.Vehicle;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class CarParkTest {
    private CarPark target;

    @BeforeEach
    public void setUp(){
        this.target = new CarPark(5,5,10);
    }

    @Test
    void parkVehicle_ValidVan_ReturnsCount(){
        assertEquals(20,target.spacesRemaining());
        target.parkVehicle(new Van("A"));
        assertEquals(17,target.spacesRemaining());
    }

    @Test
    void parkVehicle_ValidCar_ReturnsCount(){
        assertEquals(20,target.spacesRemaining());
        target.parkVehicle(new Car("A"));
        assertEquals(19,target.spacesRemaining());
    }

    @Test
    void parkVehicle_ValidMotorcycle_ReturnsCount(){
        assertEquals(20,target.spacesRemaining());
        target.parkVehicle(new Motorcycle("A"));
        assertEquals(19,target.spacesRemaining());
    }

    @Test
    void totalSpaces_Call_ReturnsCount(){
        assertEquals(20,target.totalSpaces());
    }

    @Test
    void spacesRemaining_Call_ReturnsCount(){
        assertEquals(20,target.spacesRemaining());
        target.parkVehicle(new Car("A"));
        assertEquals(19,target.spacesRemaining());
    }

    @Test
    void regularSpacesRemaining_Call_ReturnsCount(){
        assertEquals(10,target.regularSpacesRemaining());
        target.parkVehicle(new Van("A"));
        assertEquals(7,target.regularSpacesRemaining());
    }

    @Test
    void isFull_NotFull_ReturnsFalse(){
        assertFalse(target.isFull());
    }

    @Test
    void isFull_Full_ReturnsTrue(){
        VehicleFactory vehicleFactory = new VehicleFactory();
        for (int i = 0; i < 20; i++) {
            Vehicle v = vehicleFactory.createVehicle(VehicleType.motorcycle);
            target.parkVehicle(v);
        }
        assertTrue(target.isFull());
    }
    @Test
    void isEmpty_Empty_ReturnsTrue(){
        assertTrue(target.isEmpty());
    }
    @Test
    void isEmpty_NotEmpty_ReturnsFalse(){
        target.parkVehicle(new Car("A"));
        assertFalse(target.isEmpty());
    }

    @Test
    void isMotorcycleFull_NotFull_ReturnsFalse(){
        assertFalse(target.isMotorcycleFull());
    }

    @Test
    void isMotorcycleFull_Full_ReturnsTrue(){
        VehicleFactory vehicleFactory = new VehicleFactory();
        for (int i = 0; i < 5; i++) {
            Vehicle v = vehicleFactory.createVehicle(VehicleType.motorcycle);
            target.parkVehicle(v);
        }
        assertTrue(target.isMotorcycleFull());
    }

    @Test
    void isCompactFull_NotFull_ReturnsFalse(){
        assertFalse(target.isCompactFull());
    }

    @Test
    void isCompactFull_Full_ReturnsTrue(){
        VehicleFactory vehicleFactory = new VehicleFactory();
        for (int i = 0; i < 5; i++) {
            Vehicle v = vehicleFactory.createVehicle(VehicleType.car);
            target.parkVehicle(v);
        }
        assertTrue(target.isCompactFull());
    }

    @Test
    void isRegularFull_NotFull_ReturnsFalse(){
        assertFalse(target.isRegularFull());
    }

    @Test
    void isRegularFull_Full_ReturnsTrue(){
        VehicleFactory vehicleFactory = new VehicleFactory();
        for (int i = 0; i < 15; i++) {
            Vehicle v = vehicleFactory.createVehicle(VehicleType.car);
            target.parkVehicle(v);
        }
        assertTrue(target.isRegularFull());
    }

    @Test
    void vanSpaces_VanParked_ReturnsCount(){
        target.parkVehicle(new Van("A"));
        assertEquals(3,target.vanSpaces());
    }

    @Test
    void removeVehicle_TypeString_RemovesCorrect(){
        Car car = new Car("A");
        target.parkVehicle(car);
        target.removeVehicle("A");
        assertTrue(target.isEmpty());
    }
    @Test
    void removeVehicle_TypeVehicle_RemovesCorrect(){
        Car car = new Car("A");
        target.parkVehicle(car);
        target.removeVehicle(car);
        assertTrue(target.isEmpty());
    }
}