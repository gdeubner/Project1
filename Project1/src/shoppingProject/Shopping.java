package shoppingProject;

import java.util.Scanner;

/**
 * This class creates a ShoppingBag instance for the user and accepts commands
 * which manipulate it. If the commands are incorrect, an error message is
 * displayed. If the commands are recognized, then either the bag's add().
 * remove(), quit(), display() or checkout() method is called.
 * 
 * @author Graham Deubner, Sandeep Alankar
 *
 */
public class Shopping {
    
    /**
     * A method which reads inputs from standard I/O.
     */
    public void run() {
        Scanner myScanner = new Scanner(System.in);
        ShoppingBag myBag = new ShoppingBag();
        while (true) {
            String[] input = myScanner.nextLine().split("\s");
            if (input[0].equals("Q") && input.length == 1) { // quit
                quit(myBag);
                break;
            } else if (input[0].equals("P") && input.length == 1) { // display
                display(myBag);
            } else if (input[0].equals("C") && input.length == 1) { // checking out
                checkOut(myBag);
            } else { // A and R commands
                int requiredInputNum = 4; // number of parameters to create a GroceryItem
                if ((!input[0].equals("A") && !input[0].equals("R")) || input.length > requiredInputNum) {
                    System.out.println("Invalid command!");
                } else {
                    String[] commands = getInputs(input, requiredInputNum, myScanner); // holds input parameters
                    GroceryItem item = new GroceryItem(commands[1], Double.parseDouble(commands[2]),
                            Boolean.parseBoolean(commands[3]));
                    if (commands[0].equals("A")) {
                        add(myBag, item);
                    } else if (commands[0].equals("R")) {
                        remove(myBag, item);
                    } else {
                        System.out.println("Invalid command!");
                    }
                }
            }
        }
        myScanner.close();
    }

    /**
     * Helper method for run(). Prompts user for input to fill the "A" and "R"
     * commands
     * 
     * @param input       - user provided input from the command line
     * @param numGIParams - the number of parameters required for a GroceryItem
     * @param myScanner   - the Scanner object used to get user input
     * @return a String array containing the 3 required parameters forGroceryItem
     */
    private static String[] getInputs(String[] input, int requiredInputNum, Scanner myScanner) {
        int inputTokenCounter = 0; // counter for parameters entered by user
        String[] commands = new String[requiredInputNum];
        while (inputTokenCounter < requiredInputNum) {
            for (String s : input) {
                commands[inputTokenCounter] = s;
                inputTokenCounter++;
            }
            if (inputTokenCounter < requiredInputNum)
                input = myScanner.nextLine().split("\s");
        }
        return commands;
    }

    /**
     * A method which displays the contents, sales total, sales tax, and total of a
     * given bag.
     * 
     * @param bag - the back which is being checked out and displayed.
     */
    private void checkOut(ShoppingBag bag) {
        if (bag.getSize() == 0) {
            System.out.println("Unable to checkout, the bag is empty!");
        } else {
            System.out.println("**Checking out " + bag.getSize() + " item(s):");
            bag.print();
            String decimalSalesPrice = String.format("%.2f", bag.salesPrice());
            System.out.println("*Sales total: $" + decimalSalesPrice);
            String decimalSalesTax = String.format("%.2f", bag.salesTax());
            System.out.println("*Sales tax: $" + decimalSalesTax);
            String decimalTotal = String.format("%.2f", bag.salesPrice() + bag.salesTax());
            System.out.println("*Total amount paid: $" + decimalTotal);
            bag.emptyBag();
        }
    }

    /**
     * A method which runs checkout and then quits the program.
     * 
     * @param bag - the ShoppingBag to be checked out
     */
    private void quit(ShoppingBag bag) {
        if (bag.getSize() != 0) {
            checkOut(bag);
        }
        System.out.println("Thanks for shopping with us!");
    }

    /**
     * A method to add a given GroceryItem to a given ShoppingBag.
     * 
     * @param bag  - the bag being added to
     * @param item - the item being added
     */
    private void add(ShoppingBag bag, GroceryItem item) {
        bag.add(item);
        System.out.println(item.getName() + " added to the bag.");
    }

    /**
     * Method for removing a given item from a given bag.
     * 
     * @param bag  - the ShoppingBag to remove from.
     * @param item - the GroceryItem to remove.
     */
    private void remove(ShoppingBag bag, GroceryItem item) {
        if (bag.remove(item)) {
            String decimalPrice = String.format("%.2f", item.getPrice());
            System.out.println(item.getName() + " " + decimalPrice + " removed.");
        } else {
            System.out.println("Unable to remove, this item is not in the bag.");
        }
    }

    /**
     * Method which prints bags number of items in bag, followed by its contents.
     * 
     * @param bag - the ShoppingBag being displayed
     */
    private void display(ShoppingBag bag) {
        if (bag.getSize() == 0) {
            System.out.println("The bag is empty!");
        } else {
            System.out.println("**You have " + bag.getSize() + " item(s) in the bag:");
            bag.print();
            System.out.println("**End of list");
        }
    }
}
