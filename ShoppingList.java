/**
 * This application manages a shopping list
 * 
 * Grading Level: Challenge
 * 
 * @author Sean Bucholtz
 * @version Assignment 7: Shopping List
 */
public class ShoppingList {
   // Scanner object declaration
   private java.util.Scanner scan;
   // declare storage for the list items
   String[] groceryList;
   // couter object for number of items in the list
   int count;
   
   /**
    * zero-parameter constuctor
    * 
    * instantiates field variables
    */
   public ShoppingList() {
      // initialize scanner object
      scan = new java.util.Scanner(System.in);
      // initialize storage for list items
      groceryList = new String[10];
      // initialize counter object
      count = 0;
   }
   
   /**
    * method prints the items on the list.
    */
   public void printList() {
     // item number declaration
     int number;
     System.out.println("Your shopping list:");
     // if list is not empty
     if (this.count != 0) {
       // loops through each item in list
       for (int i = 0; i < this.count; i ++) {
         // instantiates item number to an adjusted index reflecting a numbered position
         number = i + 1;
         // prints item
         System.out.println("  " + number + ". " + this.groceryList[i]);
       }
      // if list is empty 
     }else {
       System.out.println("  * No items on list.");
     }
   }
   
   /**
    * method adds item to the list.
    * 
    * @param item The item to add to the list.
    */
   public void addToList(String item) {
     // if it is not a duplicate
     if (!this.isDuplicate(item)) {
       // add item to the list
       this.groceryList[this.count] = item;
       // increase count
       count += 1;
       // if duplicate
     } else {
       // print error message
       System.out.println("Duplicate item " + item + " not added to list.");
     }
   }
   
   /**
    * removes individual item from list.
    * 
    * @param number The item number to be removed from the list.
    */
   public void removeFromList(int number) {
     // index of item
     int index = number - 1;
     // if item is not last on the list
     if (number != this.count) {
       for (int i = index; i < this.count-1; i ++) {
         // shifts following element down to current index
         this.groceryList[i] = this.groceryList[i+1];
         // clears following index value
         this.groceryList[i+1] = null;
       }
       // if item is last on the list
     } else {
       // no shift required. clears specified element
       this.groceryList[index] = null;
     }
     // decreases list count
     this.count -= 1;
   }
   
   /**
    * method checks item to see if there is a duplicate already in the list.
    * 
    * @param item The item to check the list against.
    * @return Returns true if item is a duplicate, and false if not.
    */
   public boolean isDuplicate(String item) {
     // storage variable for item in list
     String element;
     // loops through all items in list
     for (int i = 0; i < this.count; i ++) {
       // instantiated to item in list at index
       element = groceryList[i];
       // if item equal element in list
       if (item.equals(element)) {
         return true;
       }
     }
     // no duplicates found
     return false;
   }
   
   /**
    * method empties all items in the list.
    */
   public void emptyList() {
      // loops through list
     for (int i = 0; i < this.count; i ++) {
       //empties item
       this.groceryList[i] = null;
     }
     // sets item count back to zero
     this.count = 0;
     // confirmation message
     System.out.println("All items removed from list.");
   }
   
   /**
    * method gathers input from user.
    * 
    * @return The string input.
    */
   public String getInput() {
     // input variable declaration
     String item;
     // sets scanner object to use new-line as the delimiter
     scan.useDelimiter("\n");
     // while input varibale is empty
     do {
       System.out.print("Enter your item or command: ");
       // instantiates input variable to receive input stream token.
       // prompts user for input
       item = scan.next();
       // prevents empty values from being used
       if (item.isEmpty()) {
         // prints error message
         System.out.println("Shopping list items cannot be blank.");
       } 
     }while (item.isEmpty());
     // resets scanner object to use default delimiter
     scan.reset();
     return item; 
   }
   
   /**
    * method checks user input for bad command statements.
    * 
    * @param item The user input.
    * @return Returns true if the user input is a bad command statement, and false if it is not.
    */
   public boolean isBadCommand(String item) {
     // declare and instantiate storage variable
     String element = "";
     // declare and instantiate scanner object whose stream is the item
     java.util.Scanner scan2 = new java.util.Scanner(item);
     // if item begins with command idicator "-"
     if (item.startsWith("-")) {
       // assigned "-*" command token
       element = scan2.next();
       // if input matches known commands
       if (element.equals("-p") || element.equals("-e") || 
    		   element.equals("-x") || element.equals("-h") || element.equals("-r")) {
         // no error
         return false;
       } else {
         // print error message
         System.out.println("Unrecognized command: " + item);
         // print help menu
         printHelp();
         return true;
       }
       // if item does not begin with command indicator "-" it is not a command
     } else {
       // no error
       return false;
     }
   }
   
   /**
    * method displays welcome message.
    */
   public void printWelcome() {
      System.out.println("Welcome to the XYZ Shopping List Program.");
   }
   
   /**
    * methods displays exit message.
    */
   public void printThankYou() {
      System.out.println("Thank you for using the XYZ Shopping List Program.");
   }
   
   /**
    * method displays the help menu.
    */
   public void printHelp() {
      System.out.println("Here are the list of commands:");
      System.out.println("  -p : Print the list");
      System.out.println("  -e : Empty the list");
      System.out.println("  -x : Exit the application");
      System.out.println("  -h : Print this command list");
      System.out.println("  -r : Remove single item");
   }
   
   /**
    * method invokes class properties to create the application enviroment.
    */
   public void go() {
     // declare input storage varibale
     String input;
     // declare and instatiate list item number
     int number = 0;
     // declare scanner object
     java.util.Scanner scan3;
     // welcome user to application
     printWelcome();
     // display menu
     printHelp();
     // instantiates holder variable to user input
     input = getInput();
     // while user does not enter exit command
     while( ! input.equals("-x")) {
       // if user input contains a command to remove
       if (input.startsWith("-r")) {
         // instantiate scanner object whose stream is the user input
         scan3 = new java.util.Scanner(input);
         // removes command token from input stream
         input = scan3.next();
         // removes item number from input stream
         number = scan3.nextInt();
       }
       // if user doe not input a bad command
       if ( ! isBadCommand(input)) {
         switch(input) {
           case "-h":
             // displays help menu
             printHelp();
             break;
           case "-r":
             // removes selected item from list
             removeFromList(number);
             break;
           case "-p":
             printList();
             break;
           case "-x":
             break;
           case "-e":
             // emptys list 
             emptyList();
             break;
           default:
             // if the list is not full
             if (this.count < 10) {
             // add the item to list
             addToList(input);
             // if the list is full
           } else {
             // display error message
             System.out.println("List is full. Item " + input + " not added to list.");
           }
         }
       }
       input = getInput();
     }
     // displays farewell message when exit command is issued
     printThankYou();
   }
   
   /**
   * The application method.
   * It instantiates the class and ivokes the application enviroment
   *
   * @param args The command-line arguments
   */
   public static void main(String[] args) {
      ShoppingList list;

      list = new ShoppingList();
      list.go();
   }
   
}