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

}
