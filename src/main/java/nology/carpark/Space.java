package nology.carpark;

public abstract class Space {
    private Vehicle parked;

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
