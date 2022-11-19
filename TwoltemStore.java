import java.util.Scanner;

public class TwoltemStore {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("****** Store Inventory ******");

        System.out.print("Enter the name of product 1: ");
        String product1 = sc.next();

        product1 = product1.substring(0, 1).toUpperCase() + product1.substring(1).toLowerCase();


        System.out.print("Enter the number of " + product1 + " we have: ");
        int amountProduct1 = sc.nextInt();


        if (amountProduct1 < 0) {
            System.out.println("ERROR: Value cannot be negative. Exiting!");
            return;
        }

        System.out.print("Enter the cost of " + product1 + ": ");
        double costProduct1 = sc.nextDouble();

        if (costProduct1 < 0) {
            System.out.println("ERROR: Value cannot be negative. Exiting!");
            return;
        }

        System.out.print("Enter the name of product 2: ");
        String product2 = sc.next();

        product2 = product2.substring(0, 1).toUpperCase() + product2.substring(1).toLowerCase();

        System.out.print("Enter the number of " + product2 + " we have:");
        int amountProduct2 = sc.nextInt();

        if (amountProduct2 < 0) {
            System.out.println("ERROR: Value cannot be negative. Exiting!");
            return;
        }

        System.out.print("Enter the cost of " + product2 + ":");
        double costProduct2 = sc.nextDouble();

        if (costProduct2 < 0) {
            System.out.println("ERROR: Value cannot be negative. Exiting!");
            return;
        }

        System.out.println("****** Customer User Interface ******");
        System.out.println("Welcome to our store. We have the following. Please enter what you would like:" +
                " \n 1-" + product1 + "\n 2-" + product2 + "\n 0- to checkout");
        int choose = sc.nextInt();

        int customerProduct1 = 0;
        int customerProduct2 = 0;
        double totalProduct1 = 0;
        double totalProduct2 = 0;
        double totalProducts = 0;
        while (choose != 0) {

            if (choose < 0 || choose > 2) {
                System.out.println("ERROR");
                return;
            }
            if (choose == 1) {
                System.out.print("How many " + product1 + " would you like?");
                customerProduct1 = sc.nextInt();
                if (customerProduct1 > amountProduct1) {
                    System.out.println("ERROR: We dont have that many remaining.");
                    return;
                }
                amountProduct1 = amountProduct1 - customerProduct1;
                totalProduct1 = customerProduct1 * costProduct1;
            }
            if (choose == 2) {
                System.out.print("How many " + product2 + " would you like?");
                customerProduct2 = sc.nextInt();
                if (customerProduct2 > amountProduct2) {
                    System.out.println("ERROR: We dont have that many remaining.");
                    return;
                }
                amountProduct2 = amountProduct2 - customerProduct2;
                totalProduct2 = customerProduct2 * costProduct2;
            }
            totalProducts = totalProduct1 + totalProduct2;
            System.out.println("Welcome to our store. We have the following. Please enter what you would like:" +
                    " \n 1-" + product1 + "\n 2-" + product2 + "\n 0- to checkout");
            choose = sc.nextInt();
        }


        System.out.println("****** Customer Total ******");
        System.out.println(product1 + " - " + customerProduct1 + " X " + costProduct1 + " = " + totalProduct1);
        System.out.println(product2 + " - " + customerProduct2 + " X " + costProduct2 + " = " + totalProduct2);
        System.out.println("--------------");
        System.out.println("Total due - " + totalProducts);

        System.out.println("****** Final Report ******");
        System.out.println("We now have the remaining amounts of our products :");
        System.out.println(product1 + " - " + amountProduct1);
        System.out.println(product2 + " - " + amountProduct2);
    }
}
