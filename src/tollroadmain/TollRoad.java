package tollroadmain;

import java.util.ArrayList;

public class TollRoad {

    int moneyMade;
    ArrayList<CustomerAccount> CustomerAccounts;

    TollRoad() {
        this.CustomerAccounts = new ArrayList<>();
        this.moneyMade = 0;
    }
//accessors for the variables created

    public int getMoneyMade() {
        return moneyMade;
    }

    public ArrayList<CustomerAccount> getCustomerAccounts() {
        return CustomerAccounts;
    }
//method to add new customers

    void addCustomer(CustomerAccount acc) {
        CustomerAccounts.add(acc);
        System.out.println(acc.getFirstName() + " " + acc.getLastName() + "," + " RegPlate:" + acc.getVehicle().getRegPlate() + "," + acc.getVehicle().getManufacturer() + " - " + " customer added for this toll road");
    }
//method to search the a list of customers associated to a specific road and return a match or throw an exception if unable to

    CustomerAccount findCustomer(String regNum) throws CustomerNotFoundException {
        for (CustomerAccount customerAccounts : CustomerAccounts) {
            if (customerAccounts.getVehicle().getRegPlate().equals(regNum)) {
                return customerAccounts;
            }
        }
        throw new CustomerNotFoundException();
    }
//method to searhc through all accounts for the road to find a match for the customer who's details have just been moved forward into the system for access

    void chargeCustomer(String registrationNumber) throws CustomerNotFoundException, InsufficientAccountBalanceException {
        for (CustomerAccount customerAccounts : CustomerAccounts) {
            if (customerAccounts.getVehicle().getRegPlate().equals(registrationNumber)) {
                try {
                    moneyMade += customerAccounts.makeTrip();
                    System.out.println(customerAccounts.getVehicle().getRegPlate() + ": Trip completed successfully");
                    return;
                } catch (InsufficientAccountBalanceException iae) {
                    System.out.println(customerAccounts.getVehicle().getRegPlate() + ": makeTrip failed. Insufficient funds");
                    return;
                }
            }
        }
        throw new CustomerNotFoundException();
    }
}
