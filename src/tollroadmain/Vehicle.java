package tollroadmain;

public abstract class Vehicle {

    private final String RegistrationPlate;
    private final String VehicleManufacturer;
//constructor to set the 2 string arguments with an attribute

    Vehicle(String RegPlate, String Manufacturer) {
        RegistrationPlate = RegPlate;
        VehicleManufacturer = Manufacturer;
    }
//accessors for the strings

    String getRegPlate() {
        return RegistrationPlate;
    }

    String getManufacturer() {
        return VehicleManufacturer;
    }

    protected abstract int calculateBasicTripCost();
}
