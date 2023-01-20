import java.io.*;
import java.util.Scanner;
/*
*Cenker Aydın
* 23.12.2022
 */
public class StoreUsingFiles_20210808002 {

    public static void main(String[] args) throws Exception {
        String baseFileName = args[0];
        String inventory = baseFileName + "_ProductInfo.txt";
        String customerRequest = baseFileName + "_Order.txt";
        String customerReceipt = baseFileName + "_Receipt.txt";
        String logFile = baseFileName + ".log";
        String currentInventory = baseFileName + "_ProductInfoAfterOrder.txt";

        int countLines = countProducts(inventory);
        String[] itemIDs = new String[countLines];
        String[] itemNames = new String[countLines];
        int[] quantities = new int[countLines];
        double[] prices = new double[countLines];
        int[] customerQuantity = new int[countLines];
        double totalPrice = -1;

        getProductInfo(itemIDs, itemNames, quantities, prices, inventory);

        readOrder(customerQuantity, customerRequest, itemNames, itemIDs, quantities, logFile);

        totalPrice = calculateTotal(customerQuantity, prices);

        writeReceipt(itemIDs, itemNames, customerQuantity, prices, totalPrice, customerReceipt);

        writeProductInfo(itemIDs, itemNames, quantities, prices, currentInventory);
    }


    public static int countProducts(String filename) throws Exception {
        File file = new File(filename);
        Scanner input = new Scanner(file);
        int count = 0;
        while (input.hasNextLine()) {
            String s = input.nextLine();
            count++;
        }
        input.close();
        return count;

    }

    public static void getProductInfo(String[] itemID, String[] itemName, int[] quantity, double[] price, String filename) throws Exception {
        File file = new File(filename);
        int counter = 0;
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        while ((line = reader.readLine()) != null) {
            String[] split = line.split(" ");
            itemID[counter] = split[0];
            itemName[counter] = split[1];
            quantity[counter] = Integer.parseInt(split[2]);
            price[counter] = Double.parseDouble(split[3]);
            counter++;
        }
        reader.close();
    }

    static void readOrder(int[] customerQuantity, String filename, String[] name, String[] IdArr, int[] quantity, String logFileName) {

        try (FileInputStream file = new FileInputStream(filename)) {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String data;

            String logFileData = "";

            while ((data = br.readLine()) != null) {
                int fileDataIndex = -1;
                String[] tmp = data.split(" ");    //Split space
                int index = Integer.MAX_VALUE;
                for (String s : tmp) {
                    fileDataIndex++;
                    if (fileDataIndex == 0)
                        index = findIndex(IdArr, s);
                    if (index == -1) {
                        logFileData += "ERROR: Product " + s + " not found!\n";
                        break;
                    }
                    if (fileDataIndex == 1) {
                        int value = Integer.parseInt(s);
                        if (value < 0) {
                            logFileData += "ERROR: Invalid amount requested (" + value + ")\n";
                            break;
                        }
                        if (value > quantity[index]) {
                            logFileData += "ERROR: " + name[index] + " - " + value + " requested but only " + quantity[index] + " remaining\n";
                            break;
                        }
                        customerQuantity[index] += value;
                        quantity[index] -= value;
                        fileDataIndex = -1;
                    }
                }
            }


            writeLog(logFileData, logFileName);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static int findIndex(String[] str, String value) {
        for (int i = 0; i < str.length; i++)
            if (str[i].equals(value))
                return i;
        return -1;
    }

    public static void writeLog(String errorMessage, String filename) {
        writeFile(errorMessage + "\n", filename);
        //System.out.println("ERROR FOUND!");
    }

    static void writeFile(String text, String filename) {
        File tempFile = new File(filename);
        try (PrintWriter pw = new PrintWriter(new File(filename));) {
            pw.print(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void writeReceipt(String[] itemID, String[] name, int[] customQuantity, double[] price, double totalPrice, String filename) throws Exception {
        File file = new File(filename);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.print("*****Customer Receipt*****\n");
        for (int i = 0; i < customQuantity.length; i++) {
            printWriter.print(name[i] + " (");
            printWriter.print(itemID[i] + ") - ");
            printWriter.print(customQuantity[i] + " * ");
            printWriter.print(price[i] + " = ");
            printWriter.print((double) customQuantity[i] * price[i] + "\n");
        }
        printWriter.print("-----Total due " + totalPrice + "-----\n");
        printWriter.close();
    }

    public static double calculateTotal(int[] customerQuantity, double[] prices) {
        double price = 0;
        for (int i = 0; i < customerQuantity.length; i++) {
            if (customerQuantity[i] != 0) {
                price += prices[i] * customerQuantity[i];
            }
        }
        return price;
    }

    public static void writeProductInfo(String[] itemID, String[] itemName, int[] quantity, double[] price, String filename) throws Exception {
        File file = new File(filename);

        PrintWriter printWriter = new PrintWriter(file);
        for (int i = 0; i < itemID.length; i++) {
            printWriter.print(itemID[i] + " ");
            printWriter.print(itemName[i] + " ");
            printWriter.print(quantity[i] + " ");
            printWriter.print(price[i] + "\n");

        }
        printWriter.close();
    }


}

