package shoppingProject;

import java.util.Scanner;

/**
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
        System.out.println("Let's start shopping!");
        
        while(true) {
            String line = myScanner.nextLine();
            char firstChar = line.charAt(0);
            if (line.length() == 1) {
                if (firstChar == 'Q') { // quit
                    quit(myBag);
                    break;
                } else if (firstChar == 'P') { // display
                    display(myBag);
                } else if (firstChar == 'C') { // checking out
                    checkOut(myBag);
                } else { // Error
                    System.out.println("Error: Unrecognized command.");
                }
            } else {
                String[] command = line.split("\s", 4);
                if(command.length>4) {
                    System.out.println("Error: too many input tokens.");
                    break;
                }
                GroceryItem item = new GroceryItem(command[1], command[2], command[3]);
                if (command[0] == "A") { // add
                    add(myBag, item);
                } else if (command[0] == "R") { // remove
                    remove(myBag, item);
                }else {
                    System.out.prinln("Error: Unrecognized command.");
                }
            }
        }
        myScanner.close();
    }
    
    /**
     * A method which displays the contents, sales total, sales tax, and total of a given bag.
     * @param bag - the back which is being checked out and displayed.
     */
    private void checkOut(ShoppingBag bag) {
        if(bag.getSize()==0) {
            System.out.println("Unable to check out, the bag is empty!");
        }
        else {
            System.out.println("Checking out " + bag.getSize() + " item(s):");
            bag.print();
            System.out.println("Sales total: $" + bag.salesPrice());
            System.out.println("Sales tax: $" + bag.salesTax());
            System.out.println("Total amount paid: $" + bag.salesPrice() + bag.salesTax());
        }
    }
    
    /**
     * A method which runs checkout and then quits the program.
     * @param bag - the ShoppingBag to be checked out
     */
    private void quit(ShoppingBag bag) {
        if(bag.getSize()!=0) {
            checkOut(bag);
        }
        System.out.println("Thanks for shopping with us!");
    }
    
    
    /**
     * A method to add a given GroceryItem to a given ShoppingBag.
     * @param bag - the bag being added to
     * @param item - the item being added
     */
    private void add(ShoppingBag bag, GroceryItem item) {
        bag.add(item);
        System.out.println(item.getName() + " added to the bag.");
    }
    
    /**
     * Method for removing a given item from a given bag.
     * @param bag - the ShoppingBag to remove from.
     * @param item - the GroceryItem to remove.
     */
    private void remove(ShoppingBag bag, GroceryItem item) {
        if(bag.remove(item)) {
            System.out.println(item.getName() + " " + item.getPrice() + " removed.");
        }else {
            System.out.println("Unable to remove, this item is not in the bag.");
        }
    }
    
    /**
     * Method which prints bags number of items in bag, followed by its contents. 
     * @param bag - the ShoppingBag being displayed
     */
    private void display(ShoppingBag bag) {
        if(bag.getSize()==0) {
            System.out.println("The bag is empty!");
        }else {
            System.out.println("**You have " + bag.getSize() + "item(s) in the bag:");
            bag.print();
            System.out.println("**End of list");
        }
    }

}
