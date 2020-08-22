package tollroadmain;

public class Van extends Vehicle {

    private final int payload;
//class created to invoke method of parent class "Vehicle"

    public Van(String RegPlate, String Manufacturer, int payload) {
        super(RegPlate, Manufacturer);
        this.payload = payload;
    }

    // accessor created to be able to retrieve the payload from another class
    int getpayload() {
        return payload;
    }
//CalculateBasicTripCost is referenced from Vehicle in order to implement value of cost for payload

    @Override
    protected int calculateBasicTripCost() {
        if (payload <= 600) {
            return 500;
        } else if (payload <= 800) {
            return 750;
        } else {
            return 1000;
        }
    }

}
