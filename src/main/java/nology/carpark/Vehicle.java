package nology.carpark;

public abstract class Vehicle {
    private String licencePlate;

    public Vehicle(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vehicle){
            return this.licencePlate.equals(((Vehicle) obj).licencePlate);
        } else {
            return false;
        }
    }
}
