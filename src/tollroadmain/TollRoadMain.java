package tollroadmain;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class TollRoadMain {

    public static void main(String[] args) throws InsufficientAccountBalanceException {

        try {
            TollRoad tollRoad = initialiseTollRoadFromFile();
            simulateFromFile(tollRoad);
            int moneyMade = tollRoad.getMoneyMade();
            System.out.println("\nTotal");
            System.out.println("       ");
            System.out.println("Profit: $" + moneyMade);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static TollRoad initialiseTollRoadFromFile() throws FileNotFoundException {
        File file = new File("customerData.txt");
        Scanner scanner = new Scanner(file);
        TollRoad tollRoad = new TollRoad();
        scanner.useDelimiter("#");
        System.out.println("Adding Customers");
        System.out.println("          ");
        while (scanner.hasNext()) {
            String[] data = scanner.next().split(",");
            String Vehicle = data[0];
            String RegPlate = data[1];
            String FirstName = data[2];
            String LastName = data[3];
            String VehicleManufacturer = data[4];
            int Balance = Integer.valueOf(data[6]);
            switch (Vehicle) {
                case "Car":
                    int NumberOfSeats = Integer.valueOf(data[5]);
                    tollRoad.addCustomer(new CustomerAccount(FirstName, LastName, new Car(RegPlate, VehicleManufacturer, NumberOfSeats), Balance));
                    break;
                case "Truck":
                    int numTrailers = Integer.valueOf(data[5]);
                    tollRoad.addCustomer(new CustomerAccount(FirstName, LastName, new Truck(RegPlate, VehicleManufacturer, numTrailers), Balance));
                    break;
                case "van":
                    int payload = Integer.valueOf(data[5]);
                    tollRoad.addCustomer(new CustomerAccount(FirstName, LastName, new Van(RegPlate, VehicleManufacturer, payload), Balance));
                    break;
                default:
                    break;

            }
            if (data[7].contains("STAFF")) {
                try {
                    tollRoad.findCustomer(RegPlate).activateStaffDiscount();
                } catch (CustomerNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            } else if (data[7].contains("FRIENDS_AND_FAMILY")) {
                try {
                    tollRoad.findCustomer(RegPlate).activateFriendsAndFamilyDiscount();
                } catch (CustomerNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        return tollRoad;
    }

    private static void simulateFromFile(TollRoad tollRoad) throws InsufficientAccountBalanceException, FileNotFoundException {

        File file = new File("transactions.txt");
        Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\\$");
        System.out.println("Operations");
        System.out.println("        ");
        while (scanner.hasNext()) {
            String data[] = scanner.next().split(",");
            switch (data[0]) {
                case "addFunds":
                    try {
                        tollRoad.findCustomer(data[1]).addFunds(Integer.valueOf(data[2]));
                        System.out.println(data[1] + ": " + data[2] + " added successfully");
                    } catch (CustomerNotFoundException e) {
                        System.out.println(data[1] + ": addFunds failed. CustomerAccount does not exist");
                    }
                    break;
                case "makeTrip":
                    try {
                        tollRoad.chargeCustomer(data[1]);
                    } catch (CustomerNotFoundException e) {
                        System.out.println(data[1] + ": makeTrip failed. CustomerAccount does not exist");

                    }
                    break;
            }
        }

    }

}
