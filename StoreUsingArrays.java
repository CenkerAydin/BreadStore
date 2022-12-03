import java.util.Scanner;

public class StoreUsingArrays {
    static int menu(String[] item, double[] price, Scanner input){
        System.out.println("Welcome to our store, we have the following.");
        for(int i=0;i<item.length;i++){

            System.out.println(i+1+" - "+item[i] + " ("+ price[i]+")");
        }
        System.out.println("0 - to checkout");
        System.out.print("Please enter what you would like:");
        return input.nextInt();
    }

    static String returnedAmounts(double amount){
        String str="";
        double[] amountArray={0.01,0.10,0.25,0.5 ,1 ,5 ,10 ,20, 50 ,100 ,200};
        int[] amountQuantity=new int[amountArray.length];

        for(int i=amountArray.length-1;i>=0;i--){
            if(((double)amount/amountArray[i])>=1){

                amountQuantity[i]+=((int)(amount/amountArray[i]));
                amount-=amountArray[i]*((int)(amount/amountArray[i]));

            }
        }

        for(int i=0;i<amountQuantity.length;i++){
            if(amountQuantity[i]>0){
                str+=amountQuantity[i] + " - " + amountArray[i] + "\n";
            }
        }

        return str;
    }

    static void storeRun(String[] itemler,int[] quantity,double[] price){
        capitalizeArray(itemler);
        Scanner input = new Scanner(System.in);
        int choice=-1;
        double totalDue=0;
        int[] customerQuantity=new int[itemler.length];

        while (choice!=0){

            choice=menu(itemler, price, input);

            if(choice==0)
                break;
            else if(choice>itemler.length || choice<1){
                System.out.println("ERROR: Invalid choice");
                continue;
            }
            System.out.println("Your choice was "+choice);
            System.out.print("How many "+itemler[choice-1]+" would you like: ");
            int customerWant=input.nextInt();

            if (customerWant>quantity[choice-1]){
                System.out.println("ERROR: Invalid request");
            }else if(customerWant<0){
                System.out.println("ERROR: Invalid request");
                continue;
            }
            customerQuantity[choice-1]+=customerWant;
            quantity[choice-1] -=customerWant;
            totalDue+=price[choice-1]*customerWant;
        }

        System.out.println("-------CHECKOUT-------");

        for(int i=0;i<itemler.length;i++){
            if(customerQuantity[i]>0)
                System.out.println(itemler[i] +" - "+customerQuantity[i]+" x "+price[i]+" = "+customerQuantity[i]*price[i]);

        }
        System.out.println("--------------------");
        System.out.println("Total Due - "+totalDue);
        System.out.println();

        double amount;
        do {
            System.out.println("Please enter amount given: ");
            amount = input.nextDouble();
            if (amount<totalDue){
                System.out.println("Not enough payment given");
            }
        }while (amount<totalDue);

        System.out.println("Thank you for your business.Your change given is:");

        System.out.println(returnedAmounts(amount-totalDue));
    }

    static String capitalizeString(String text){
        String str= text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
        return str;
    }

    static void capitalizeArray(String[] name){
        for(int i=0;i<name.length;i++)
            name[i]=capitalizeString(name[i]);

    }
}
