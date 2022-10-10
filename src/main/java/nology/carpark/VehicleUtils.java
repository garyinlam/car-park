package nology.carpark;

public class VehicleUtils {
    private static int nextId = 0;

    public static String nextUniquePlate() {
        return ++nextId + "";
    }
}
