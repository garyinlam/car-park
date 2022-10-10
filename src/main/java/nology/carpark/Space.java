package nology.carpark;

public abstract class Space {
    private Vehicle parked;

    public Vehicle getParked() {
        return parked;
    }

    public void setParked(Vehicle parked) {
        this.parked = parked;
    }

    public boolean isOccupied(){
        return parked != null;
    }
}
