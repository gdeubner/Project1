package shoppingProject;

/**
 * This class is meant to emulate a shopping bag. It consists of an array of
 * type GroceryItem which starts at size 5 and increases by 5 when capacity is
 * reached. Getters are provided for a ShoppingBag's size and capacity.The
 * constructor defaults to and empty ShoppingBag. Methods are provided for
 * finding, adding, and removing 'GroceryItem's, as well as calculating to sales
 * total, sales tax, and for the printing of a GroceryBag.
 * 
 * @author Graham Deubner, Sandeep Alankar
 *
 */
public class ShoppingBag {
    private GroceryItem[] bag; // array-based implementation of the bag
    private int size; // number of items currently in the bag
    private int capacity; // current capacity

    /**
     * A constructor which creates a bag of size 0 and with a capacity of 5.
     */
    public ShoppingBag() {
        bag = new GroceryItem[5];
        size = 0;
        capacity = 5;
    }
    
    /**
     * @return int - the size of a ShoppingBag
     */
    public int getSize() {
        return size;
    }
    
    /**
     * @return int - the capacity of a ShoppingBag
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Helper method to find an item in bag. Returns -1 on failure.
     * @param item - the item to be searched for in bag.
     * @return - returns index of item.
     */
    private int find(GroceryItem item) {
        for(int i = 0; i < size; i++) {
            if(item.equals(bag[i])) {
                return i;
            }
        }
        return -1;
    } 
    
    /**
     * Helper method to grow the capacity of bag.
     */
    private void grow() {
        int bagGrowth = 5; //the the amount by which the bag size will increase
        GroceryItem[] newBag = new GroceryItem[bag.length+bagGrowth];
        for(int i = 0; i < capacity; i++){
            newBag[i] = bag[i];
        }
        capacity += 5;
        bag = newBag;
    } 

    /**
     * Method to add a GroceryItem to bag, increasing bag's size if necessary. 
     * @param item - the item to be added to bag.
     */
    public void add(GroceryItem item) {
        if(size >= capacity) {
            grow();
        }
        bag[size++] = item;
            
    }

    /**
     * Method removes item from bag.
     * @param item - item to be removed.
     * @return boolean - Returns true on success, false on failure.
     */
    public boolean remove(GroceryItem item) {
        int positionInBag = find(item);
        if(positionInBag == -1) {
            return false;
        }else {
            bag[positionInBag] = bag[size-1];
            bag[size-1] = null;
            return true;
        }
    }

    /**
     * Sums price of each item in bag.
     * @return int - representing the total sales price for bag.
     */
    public double salesPrice() {
        int sum = 0;
        for(GroceryItem i : bag) {
            sum += i.getPrice();
        }
        return sum;
    }

    /**
     * Method calculates the sales tax owed on the content of bag.
     * @return double - the sales tax owed upon checkout.
     */
    public double salesTax() {
        double salesTax = 0.06625;
        double taxableSum = 0;
        for(GroceryItem i : bag) {
            if(i.getTaxable) {
                taxableSum += i.getPrice();
            }
        }
        double taxSum = taxableSum * salesTax; 
        return ((double)(Math.round(taxSum * 100))) / 100; //rounds taxSum to 2 decimal places
    }

    /**
     * Method which prints the contents of bag
     */
    public void print() {
        for(GroceryItem i : bag) {
            i.toString();
        }
    }
    
    
    
    // Need a 'bed main' or something here to test the functions 
    
    
    
    
    
    
    
}