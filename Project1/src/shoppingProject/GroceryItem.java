package shoppingProject;

import java.text.DecimalFormat;

/**
 * This class defines the GroceryItem abstract data type with 3 data fields and
 * contains methods to return a string containing the item name, its price, and
 * whether or not it is taxable. It contains a parameterized constructor which
 * defines three data fields for a GroceryItem object.
 * 
 * @author Sandeep Alankar, Graham Deubner
 */

public class GroceryItem {

    private String name;
    private double price;
    private boolean taxable;

    /**
     * This is a getter method that returns the item name.
     * 
     * @return name of item
     */
    public String getName() {
        return name;
    }

    /**
     * This is a getter method that returns the item price.
     * 
     * @return price of item
     */
    public double getPrice() {
        return price;
    }

    /**
     * This is a getter method that returns the taxable boolean variable.
     * 
     * @return taxable, says whether grocery item is taxable or not
     */
    public boolean getTaxable() {
        return taxable;
    }

    /**
     * Parameterized constructor that defines abstract data type GroceryItem as
     * having three elements: name, price, taxable.
     * 
     * @param name     of item
     * @param price    of item
     * @param taxable, true if item is taxable
     */
    GroceryItem(String name, double price, boolean taxable) {
        this.name = name;
        this.price = price;
        this.taxable = taxable;
    }

    /**
     * Method that returns true only if all three data fields of obj are the same as
     * the item calling the method.
     * 
     * @return true if name, price, and taxable are the same for both objects
     */
    public boolean equals(Object obj) {
        if (obj instanceof GroceryItem) {
            GroceryItem item = (GroceryItem) obj;
            if (item.getName().equals(this.name) && item.getPrice() == this.getPrice()
                    && (item.getTaxable() == this.taxable)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * This method is used to return the proper label for an item being taxable or
     * tax free.
     * 
     * @param taxable, boolean that says if grocery item is taxable or not
     * @return "is taxable" if taxable is true, returns "tax free" otherwise
     */
    public String isTaxable(boolean taxable) {
        if (taxable == true) {
            return "is taxable";
        } else {
            return "tax free";
        }
    }

    /**
     * This method returns a String that represents the three data fields of a
     * GroceryItem object. The three elements are the item's name, its price
     * (formatted using the format() method in the DecimalFormat package), and
     * whether or not the item is taxable, which is done by calling the
     * isTaxable(taxable) method.
     */
    public String toString() {
        DecimalFormat df = new DecimalFormat(".##");
        return name + ": $" + df.format(price) + " : " + isTaxable(taxable);
    }
}
