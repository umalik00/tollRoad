package tollroadmain;

public class Truck extends Vehicle {

    private final int numTrailers;
//class created to invoke method of parent class "Vehicle"

    public Truck(String RegPlate, String Manufacturer, int numTrailers) {
        super(RegPlate, Manufacturer);
        this.numTrailers = numTrailers;
    }
// accessor to retrieve number of trailers from another class

    int getNumTrailers() {
        return numTrailers;
    }
//CalculateBasicTripCost is referenced from Vehicle in order to implement value of cost for the number of trailers

    @Override
    protected int calculateBasicTripCost() {
        if (numTrailers <= 1) {
            return 1250;
        } else {
            return 1500;
        }

    }

}
