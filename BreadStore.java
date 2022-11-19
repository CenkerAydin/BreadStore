import java.util.Scanner;

public class BreadStore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("******* Bread Store Inventory *******");
        System.out.println("Enter the number of bread we have :");
        int amountBread = sc.nextInt();

        if (amountBread < 0) {
            System.out.println("ERROR: Value cannot be negative. Exiting!");
            return;
        }

        System.out.println("Enter the cost of bread :");
        double costBread = sc.nextDouble();

        if (costBread < 0) {
            System.out.println("ERROR: Value cannot be negative. Exiting!");
            return;
        }
        
        System.out.println("******* Customer User Interface *******");
        System.out.println("We have " + amountBread + " loaves of bread available.How many would you like?");
        int customerBread = sc.nextInt();

        if (customerBread < 0) {
            System.out.println("ERROR: Value cannot be negative. Exiting! ");
            return;
        }

        if (customerBread > amountBread) {
            System.out.println("ERROR: We dont have that many remaining.");
            return;
        }

        double totalCost = costBread * customerBread;
        System.out.println("Your cost is " + totalCost);

        int remain = amountBread - customerBread;
        System.out.println("We now have " + remain + " loaves of bread remaining");

    }
}
