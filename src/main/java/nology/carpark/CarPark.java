package nology.carpark;

import java.util.ArrayList;
import java.util.List;

public class CarPark {
    private List<Space> spaces;

    public CarPark(int motorcycleSpaces, int compactSpace, int regularSpace) {
        spaces = new ArrayList<>();
        for (int i = 0; i < motorcycleSpaces; i++) {
            spaces.add(new MotorcycleSpace());
        }
        for (int i = 0; i < compactSpace; i++) {
            spaces.add(new CompactSpace());
        }
        for (int i = 0; i < regularSpace; i++) {
            spaces.add(new RegularSpace());
        }
    }

    public int totalSpaces() {
        return spaces.size();
    }

    public int spotsRemaining() {
        int spotsRemaining = 0;
        for (Space space: spaces) {
            if (!space.isOccupied()) spotsRemaining++;
        }
        return spotsRemaining;
    }

    public int regularSpotsRemaining() {
        int spotsRemaining = 0;
        for (Space space: spaces) {
            if (!space.isOccupied() && space instanceof RegularSpace) {
                spotsRemaining++;
            }
        }
        return spotsRemaining;
    }

    public boolean isFull(){
        return spotsRemaining() == 0;
    }

    public boolean isEmpty(){
        return spotsRemaining() == totalSpaces();
    }

    public boolean isMotorcycleFull(){
        boolean isFull = true;
        for (Space space: spaces) {
            if (space instanceof MotorcycleSpace && space.isOccupied()){
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public boolean isCompactFull(){
        boolean isFull = true;
        for (Space space: spaces) {
            if (space instanceof CompactSpace && space.isOccupied()){
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    public boolean isRegularFull(){
        boolean isFull = true;
        for (Space space: spaces) {
            if (space instanceof RegularSpace && space.isOccupied()){
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

    public void parkVehicle(Van van){
        if (regularSpotsRemaining() >= 3 ){
            for (int i = 0; i < spaces.size(); i++) {
                if (!spaces.get(i).isOccupied()){
                    spaces.get(i).setParked(van);
                    spaces.get(i+1).setParked(van);
                    spaces.get(i+2).setParked(van);
                    break;
                }
            }
        } else {
            System.out.println("Cannot park, no space");
        }
    }

    public void parkVehicle(Car car){
        if (!isCompactFull() || isRegularFull()){
            for (Space space : spaces) {
                if (!space.isOccupied() && !(space instanceof MotorcycleSpace)){
                    space.setParked(car);
                    break;
                }
            }
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
        }
    }

    public void removeVehicle(String licencePlate){
        for (Space space : spaces) {
            if (space.getParked().getLicencePlate().equals(licencePlate)){
                space.clearSpace();
            }
        }
    }

    public void removeVehicle(Vehicle vehicle){
        removeVehicle(vehicle.getLicencePlate());
    }
}
