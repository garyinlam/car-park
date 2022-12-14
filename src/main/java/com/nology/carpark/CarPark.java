package com.nology.carpark;

import com.nology.carpark.exceptions.NoSpaceException;
import com.nology.carpark.space.CompactSpace;
import com.nology.carpark.space.MotorcycleSpace;
import com.nology.carpark.space.RegularSpace;
import com.nology.carpark.space.Space;
import com.nology.carpark.vehicle.Car;
import com.nology.carpark.vehicle.Motorcycle;
import com.nology.carpark.vehicle.Van;
import com.nology.carpark.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class CarPark {
    private List<Space> spaces;

    public CarPark(int motorcycleSpaces, int compactSpace, int regularSpace) {
        spaces = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < motorcycleSpaces; i++) {
            spaces.add(new MotorcycleSpace(id++));
        }
        for (int i = 0; i < compactSpace; i++) {
            spaces.add(new CompactSpace(id++));
        }
        for (int i = 0; i < regularSpace; i++) {
            spaces.add(new RegularSpace(id++));
        }
    }

    public int totalSpaces() {
        return spaces.size();
    }

    public int spacesRemaining() {
        int spotsRemaining = 0;
        for (Space space: spaces) {
            if (!space.isOccupied()) spotsRemaining++;
        }
        return spotsRemaining;
    }

    public int regularSpacesRemaining() {
        int spotsRemaining = 0;
        for (Space space: spaces) {
            if (!space.isOccupied() && space instanceof RegularSpace) {
                spotsRemaining++;
            }
        }
        return spotsRemaining;
    }

    public boolean isFull(){
        return spacesRemaining() == 0;
    }

    public boolean isEmpty(){
        return spacesRemaining() == totalSpaces();
    }

    public boolean isMotorcycleFull(){
        boolean isFull = true;
        for (Space space: spaces) {
            if (space instanceof MotorcycleSpace && !space.isOccupied()){
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public boolean isCompactFull(){
        boolean isFull = true;
        for (Space space: spaces) {
            if (space instanceof CompactSpace && !space.isOccupied()){
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public boolean isRegularFull(){
        boolean isFull = true;
        for (Space space: spaces) {
            if (space instanceof RegularSpace && !space.isOccupied()){
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public int vanSpaces(){
        int vanSpaces = 0;
        for (Space space: spaces) {
            if (space.getParked() instanceof Van) vanSpaces++;
        }
        return vanSpaces;
    }

    public void parkVehicle(Vehicle vehicle){
        if (vehicle instanceof Van){
            parkVehicle((Van) vehicle);
        } else if (vehicle instanceof Car){
            parkVehicle((Car) vehicle);
        } else if (vehicle instanceof Motorcycle){
            parkVehicle((Motorcycle) vehicle);
        }
    }

    public void parkVehicle(Van van){
        if (regularSpacesRemaining() >= 3 ){
            for (int i = 0; i < spaces.size()-3; i++) {
                if (!(spaces.get(i) instanceof RegularSpace)){
                    continue;
                }
                Space[] temp = {spaces.get(i),spaces.get(i+1),spaces.get(i+2)};
                if(!temp[0].isOccupied() && !temp[1].isOccupied() && !temp[2].isOccupied()){
                    temp[0].setParked(van);
                    temp[1].setParked(van);
                    temp[2].setParked(van);
                    break;
                }
            }
        } else {
            throw new NoSpaceException("No space for van, plate = " + van.getLicencePlate());
        }
    }

    public void parkVehicle(Car car){
        if (!isCompactFull() || !isRegularFull()){
            for (Space space : spaces) {
                if (!space.isOccupied() && !(space instanceof MotorcycleSpace)){
                    space.setParked(car);
                    break;
                }
            }
        } else {
            throw new NoSpaceException("No space for car, plate = " + car.getLicencePlate());
        }
    }

    public void parkVehicle(Motorcycle motorcycle){
        if (!isFull()){
            for (Space space : spaces) {
                if (!space.isOccupied()){
                    space.setParked(motorcycle);
                    break;
                }
            }
        } else {
            throw new NoSpaceException("No space for motorcycle, plate = " + motorcycle.getLicencePlate());
        }
    }

    public void parkVehicle(Motorcycle motorcycle, int spaceId){
        Space space = null;
        for (Space s: spaces) {
            if (s.getId() == spaceId){
                space = s;
            }
        }
        if(space == null){
            throw new IllegalArgumentException("Space with that id does not exist");
        }
        if (space.isOccupied()){
            throw new NoSpaceException("That space is occupied");
        }
        space.setParked(motorcycle);
    }

    public void parkVehicle(Car car, int spaceId){
        Space space = null;
        for (Space s: spaces) {
            if (s.getId() == spaceId){
                space = s;
            }
        }
        if(space == null){
            throw new IllegalArgumentException("Space with that id does not exist");
        }
        if (space.isOccupied()){
           throw new NoSpaceException("That space is occupied");
        }
        if (space instanceof MotorcycleSpace){
           throw new IllegalArgumentException("Cannot park in motorcycle space with a car");
        }
        space.setParked(car);
    }

    public void parkVehicle(Van van, int spaceId){
        List<Space> space = new ArrayList<>();
        for (Space s: spaces) {
            for (int i = 0; i < 3; i++) {
                if (s.getId() == spaceId+i){
                    space.add(s);
                }
            }
        }
        if (space.isEmpty()){
            throw new IllegalArgumentException("Space with that id does not exist");
        }
        if(space.size() != 3){
            throw new IllegalArgumentException("That space cannot fit a van");
        }
        for (Space s: space) {
            if (s.isOccupied()){
                throw new NoSpaceException("That space is occupied");
            }
            if (!(s instanceof RegularSpace)){
                throw new IllegalArgumentException("Cannot park in this space with a van");
            }
        }
        for (Space s: space) {
            s.setParked(van);
        }

    }

    public void removeVehicle(String licencePlate){
        for (Space space : spaces) {
            if(space.getParked() == null){
                continue;
            }
            if (space.getParked().getLicencePlate().equals(licencePlate)){
                space.clearSpace();
            }
        }
    }

    public void removeVehicle(Vehicle vehicle){
        removeVehicle(vehicle.getLicencePlate());
    }

    public void printSpaces(){
        for (Space space : spaces) {
            System.out.println(space.getId() + " " + space.getParked());
        }
    }
}
