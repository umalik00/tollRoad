package tollroadmain;

public class CustomerAccount implements Comparable<CustomerAccount> {
//enum created which include a group of constants which represent the Discount type of an account

    private enum DiscountType {

        NONE, STAFF, FRIENDS_AND_FAMILY;

    }

    private final String FirstName;
    private final String LastName;
    private final Vehicle Vehicle;
    private DiscountType DiscountType;
    private int customerAccountBalance;

    CustomerAccount(String FirstName, String LastName, Vehicle Vehicle, int customerAccountBalance) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Vehicle = Vehicle;
        this.customerAccountBalance = customerAccountBalance;
        this.DiscountType = DiscountType.NONE;//Defaulting the value of Discount type to NONE
    }
//accessors for the variables created

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public Vehicle getVehicle() {
        return Vehicle;
    }

    DiscountType getDiscountType() {
        return DiscountType;
    }

    public int getCustomerAccountBalance() {
        return customerAccountBalance;
    }
//converting the discount type to STAFF from NONE

    void activateStaffDiscount() {
        DiscountType = DiscountType.STAFF;
        System.out.println(Vehicle.getRegPlate() + " activated as STAFF account");
    }
//converting the discount type to FRIENDS_AND_FAMILY if the discount type doesn't have a discount already

    void activateFriendsAndFamilyDiscount() {
        if (DiscountType != DiscountType.STAFF) {
            DiscountType = DiscountType.FRIENDS_AND_FAMILY;
            System.out.println(Vehicle.getRegPlate() + " activated as FRIENDS_AND_FAMILY account");
        } else {
            System.out.println(Vehicle.getRegPlate() + " already activated as STAFF account");
        }
    }
//a method to return the discount to default value in order to remove any activate discounts on the account

    void deactivateDiscount() {
        DiscountType = DiscountType.NONE;
    }
//a method to add more credit to the account balance

    void addFunds(int amount) {

        customerAccountBalance = customerAccountBalance + amount;
    }
//a method to simulate the customer making a trip on the toll road

    int makeTrip() throws InsufficientAccountBalanceException {

        int cost = this.Vehicle.calculateBasicTripCost();//calling calculateBasicTripCost from the Vehicle class
//setting the discount value for the discount types, STAFF being 50%(0.5) and FRIENDS_AND_FAMILY 10%(0.9)
        if (this.DiscountType == DiscountType.STAFF) {
            cost = (int) (cost * 0.5);
        } else if (this.DiscountType == DiscountType.FRIENDS_AND_FAMILY) {
            cost = (int) (cost * 0.9);
        }
        if (cost >= customerAccountBalance) {
            throw new InsufficientAccountBalanceException();//if customer balance is insufficient, throws and exception
        } else {
            customerAccountBalance = (int) (customerAccountBalance - cost);
            return (int) cost;//else it subtracts costs from customer balance
        }
    }
//using the comparable interface, this method is to compare a customer account with another

    @Override
    public int compareTo(CustomerAccount newCustomerAccount) {
        return this.Vehicle.getRegPlate().compareTo(newCustomerAccount.getVehicle().getRegPlate());
    }
}
