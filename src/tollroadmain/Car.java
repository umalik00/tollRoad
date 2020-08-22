package tollroadmain;

public class Car extends Vehicle {

    final int numberOfSeats;
//class created to invoke method of parent class "Vehicle"

    public Car(String RegPlate, String Manufacturer, int numberOfSeats) {
        super(RegPlate, Manufacturer);
        this.numberOfSeats = numberOfSeats;
    }
//accessor to retrieve the number of seats value in other classes

    int getNumberOfSeats() {
        return numberOfSeats;
    }

//CalculateBasicTripCost is referenced from Vehicle in order to implement value of cost for number of seats
    @Override
    protected int calculateBasicTripCost() {
        if (numberOfSeats <= 5) {
            return 500;
        } else {
            return 600;
        }
    }
}
