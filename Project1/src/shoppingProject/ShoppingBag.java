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
    public int getCapacity() {
        return capacity;
    }

    /**
     * Helper method to find an item in bag. Returns -1 on failure.
     * 
     * @param item - the item to be searched for in bag.
     * @return - returns index of item.
     */
    private int find(GroceryItem item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(bag[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Helper method to grow the capacity of bag.
     */
    private void grow() {
        int bagGrowth = 5; // the the amount by which the bag size will increase
        GroceryItem[] newBag = new GroceryItem[bag.length + bagGrowth];
        for (int i = 0; i < capacity; i++) {
            newBag[i] = bag[i];
        }
        capacity += 5;
        bag = newBag;
    }

    /**
     * Method to add a GroceryItem to bag, increasing bag's size if necessary.
     * 
     * @param item - the item to be added to bag.
     */
    public void add(GroceryItem item) {
        if (size >= capacity) {
            grow();
        }
        bag[size++] = item;
    }

    /**
     * Method removes item from bag.
     * 
     * @param item - item to be removed.
     * @return boolean - Returns true on success, false on failure.
     */
    public boolean remove(GroceryItem item) {
        int positionInBag = find(item);
        if (positionInBag == -1) {
            return false;
        } else {
            if (positionInBag == size - 1)
                bag[positionInBag] = null;
            else {
                bag[positionInBag] = bag[size - 1];
                bag[size - 1] = null;
            }
            size--;

            return true;
        }
    }

    /**
     * Sums price of each item in bag.
     * 
     * @return int - representing the total sales price for bag.
     */
    public double salesPrice() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += bag[i].getPrice();
        }
        return sum;
    }

    /**
     * Method calculates the sales tax owed on the content of bag.
     * 
     * @return double - the sales tax owed upon checkout.
     */
    public double salesTax() {
        double salesTax = 0.06625;
        double taxableSum = 0;
        for (int i = 0; i < size; i++) {
            if (bag[i].getTaxable()) {
                taxableSum += bag[i].getPrice();
            }
        }
        double taxSum = taxableSum * salesTax;
        return ((double) (Math.round(taxSum * 100))) / 100; // rounds taxSum to 2 decimal places
    }

    /**
     * Method which prints the contents of bag
     */
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println("·" + bag[i].toString());
        }
    }

    /**
     * This method create a new, empty GroceryItem array and assigns it to bag.
     */
    public void emptyBag() {
        bag = new GroceryItem[5];
        capacity = 5;
        size = 0;
    }

    /**
     * Helper method for the testRemove() helper method.
     * This method removes a given GroceryItem from bag, prints
     * it's success or failure, and returns the newly altered bag
     * object. 
     * @param bag - ShoppingBag item pre-populated with GroceryItems.
     * @param item - the GroceryItem to be added to bag.
     */
    private static ShoppingBag removeItemTest(ShoppingBag bag, GroceryItem item) {
        if(bag.remove(item))
            System.out.println(item.getName() + " removed.");
        else
            System.out.println("Unable to remove " + item.getName());
        return bag;
    }
    
    /**
     * Helper method for testing the add() method.
     * Method adds several GroceryItems to bag and prints bag's contents.
     * @param itemList - array filled with GroceryItems.
     * @return ShoppingBag item - the altered shopping bag to be used in next test.
     */
    private static ShoppingBag testAdd(GroceryItem[] itemList) {
        ShoppingBag bag = new ShoppingBag();
        System.out.println("Test Case 1: add()");
        bag.add(itemList[0]);
        bag.add(itemList[1]);
        bag.add(itemList[2]);
        bag.add(itemList[3]);
        System.out.println("\nPrinting contents:");
        bag.print();
        return bag;
    }
    
    /**
     * Helper method for testing the remove() method.
     * Method removes several GroceryItems from bag, prints the contents,
     * then attempts to remove GroceryItems not in bag, and then prints the contents.  
     * @param bag - ShoppingBag item pre-populated with GroceryItems.
     * @param itemList - array filled with GroceryItems.
     * @return ShoppingBag item - the altered shopping bag to be used in next test.
     */
    private static ShoppingBag testRemove(ShoppingBag bag, GroceryItem[] itemList) {
        System.out.println("\nTest Case 2.1: remove() with success");
        bag = removeItemTest(bag, itemList[0]);    
        bag = removeItemTest(bag, itemList[2]);
        System.out.println("\nPrinting contents:");
        bag.print();
        System.out.println("\nTest Case 2.2: remove() with failure");
        bag = removeItemTest(bag, itemList[4]);
        bag = removeItemTest(bag, itemList[5]);
        System.out.println("\nPrinting contents:");
        bag.print();
        return bag;        
    }
    
    /**
     * Helper method which tests the grow() method.
     * Method prints the original capacity of bag, adds several GroceryItems to bag,
     * and then prints the new, enlarged capacity of bag.
     * @param bag - ShoppingBag item pre-populated with GroceryItems.
     * @param itemList - array filled with GroceryItems.
     * @return ShoppingBag item - the altered shopping bag to be used in next test.
     */
    private static ShoppingBag testGrow(ShoppingBag bag, GroceryItem[] itemList) {
        System.out.println("\nTest Case 3: grow()");
        System.out.println("Old capacity: " + bag.getCapacity());
        bag.add(itemList[0]);
        bag.add(itemList[1]);
        bag.add(itemList[2]);
        bag.add(itemList[3]);
        System.out.println("New capacity: " + bag.getCapacity());
        System.out.println("\nTest Case 4: salesTax()");
        return bag;
    }
    
    /**
     * Helper method for testing the salesTax() method.
     * Method calls the salesTax() method and prints the result.
     * @param bag - ShoppingBag item pre-populated with GroceryItems.
     */
    private static void testSalesTax(ShoppingBag bag) {
        System.out.println("Sales tax: " + bag.salesTax());
    }

    /**
     * Testbed main method used for running the test 4 different test helper methods.
     * Method creates an array of GroceryItems to be used in several tests, then calls
     * 4 testing helper methods.
     * @param args - convention only, not used.
     */
    public static void main(String[] args) {
        GroceryItem[] itemList = {
                new GroceryItem("Spider_Eye", 0.01, false),
                new GroceryItem("Golden_Apple", 49.99, true),
                new GroceryItem("Rotten_Flesh", 0.03, false),
                new GroceryItem("Enchanted_Golden_Apple", 100.01, true),
                new GroceryItem("Golden_Carrot", 49.99, true),
                new GroceryItem("Poinsonous_Potato", 1.00, false)
        };
        ShoppingBag bag = testAdd(itemList);
        bag = testRemove(bag, itemList);
        bag = testGrow(bag,itemList);
        testSalesTax(bag);        
    }
}
