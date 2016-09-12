/**
CST 338 - Assignment 2
Lilia Chakarian, William Gillihan, and Christina Hunter
Simulate a casino slot machine according to the
specifications outlined in the assignment 2 instructions 
https://ilearn.csumb.edu/mod/assign/view.php?id=239405
*/
import java.util.Scanner;
import java.lang.Math;
/**
Assign2 Class
*/
public class Assign2
{
   public static Scanner keyboard  = new Scanner(System.in);
   /**
   Name: main
   Description: main calls other methods within the Assign2
   and TripleString classes to simulate a casino slot machine
   */
   public static void main(String[] args) // Christina & Bill
   {
      // while loop continues play simulation
      // until bet is 0 or max pulls is reached
      while (true)
      {
         int theBet = getBet();
         if (theBet != 0)
         {
            TripleString obj = pull();
            int multiplier = getPayMultiplier(obj);
            int won = (theBet * multiplier);
            if (!TripleString.saveWinnings(won))
            {
               TripleString.displayWinnings();
            }
            else
            {
               display(obj, won);
            }
         }
         // if bet is 0 ends game
         else
         {
            String done = TripleString.displayWinnings();
            if (done == "EXIT")
            {
               keyboard.close();
               System.exit(0);
            }
            else
            {
               keyboard.close();
               System.exit(0);
            }
         }
      }
   }
   /**
   Name: getBet
   Return: return an int
   Purpose of return: to allow calling method to get and
   check an int type user input for a bet 
   Description: to allow the calling method to calculate 
   winnings based on the bet
   */
   public static int getBet() // Christina
   {
      // input will be 0-100 initialize int bet to a different value
      // gets bet from user and checks for valid range 1-100
      // and also checks for valid input (int)
      int bet = -1 ;
      while ( bet < 0 || bet > 100 ) 
      {
         System.out.print("Hello. How much would you like to bet (1 - 100)"
            + " or 0 to quit? ");
         while(!keyboard.hasNextInt()) // Bill
         {
            keyboard.next();
            System.out.print("Numbers only. How much would you like to bet (1 - 100)"
               + " or 0 to quit? ");
         }
         bet = keyboard.nextInt();
      }
      return bet;
   }
   /**
   Name: pull, member of TripleString class
   Return: returns an object of the TripleString class
   Purpose of return: to allow the calling method
   to receive an object of the TripleString class
   and to perform actions on the object
   Description: instantiates an object of the
   TripleString class and fills the three private 
   strings of that class with a random string returned
   by calling the randString() method
   */
   public static TripleString pull() // Christina
   {
      //calls randString() to get a specific string
      //at random and sets to TripleString strings
      TripleString pullString = new TripleString();
      String str1, str2, str3;
      str1 = randString();
      pullString.setString1(str1);
      str2 = randString();
      pullString.setString2(str2);
      str3 = randString();
      pullString.setString3(str3);
      return pullString;
   }
   /**
   Name: randString
   Return: returns a string based on a random number
   Purpose of return: to allow the calling method to get a 
   specific string chosen at random 
   Description: uses Math.random() transformed to 
   get a random number between 1-8 to choose 
   a string at random according to the probabilities as listed:
   BAR      1/2   (50%)
   cherries 1/4  (25%)
   space    1/8  (12.5%)
   7        1/8  (12.5%)
   */
   private static String randString() // Christina
   {
      // uses Math.random() to get a random
      // number in a specific to select a string
      int start = 1;
      int end = 8;
      String chooseString;
      int random = (int)(Math.random() * end) + start;
      if (random == 1 || random == 2 || random == 3 || random == 4)
      {
         chooseString = "BAR";
      }
      else if (random == 5 || random == 6)
      {
         chooseString = "cherries";
      }
      else if (random == 7)
      {
         chooseString = "(space)";
      }
      else
      {
         chooseString = "7";
      }
      return chooseString;
   }
   /**
   Name: getPayMultiplier
   Parameter(s): TripleString class object
   Return: returns the multiplier for a bet
   based on the order and content of strings
   contained in the TripleString class object
   Purpose of return: to allow the calling method
   to get the multiplier to determine the winnings
   of this slot pull based on the resulting strings
   Description: returns a multiplier based on the 
   TripleString class object strings with the listed 
   combinations:
   cherries  [not cherries]  [any] pays 5 × bet (5 times the bet)
   cherries  cherries  [not cherries] pays 15 × bet
   cherries  cherries  cherries pays 30 × bet
   BAR  BAR  BARpays 50 × bet
   7  7  7 pays 100 × bet
   */
   public static int getPayMultiplier(TripleString thePull) // Bill
   {
      // gets the current object strings and test them paying attention 
      // to string content and order to see how much the pull is worth
      String str1 = thePull.getString1();
      String str2 = thePull.getString2();
      String str3 = thePull.getString3();
      // Checking for cherries
      if (str1.equals("cherries")) // 1st cherries
      {
         if (str2.equals("cherries")) // 2nd cherries so far
         {
            if (str3.equals("cherries"))
            {
               return 30;  // triple cherries pays 30 × bet
            }
            else
            {
               return 15; // cherries cherries [not cherries] pays 15 × bet
            }
         }
         else
         {
            return 5; // single cherries in 1st slot pays 5 × bet
         }
      }
      else
      {
         if (str1.equals("BAR") && str2.equals("BAR") && str3.equals("BAR"))
         {
            return 50; // BAR BAR BAR pays 50 × bet
         }
         else if (str1.equals("7") && str2.equals("7") && str3.equals("7"))
         {
            return 100; // 7 7 7 pays 100 × bet
         }
         else
         {
            return 0; // No Matches
         }
      }      
   }
   /**
   Name: display
   Parameter(s): TripleString object, int winnings
   Description: displays the strings that simulate the 
   casino slot machine and shows the winnings from
   the bet * multiplier and lets the user know
   if they won or not
   */
   public static void display (TripleString thePull, int winnings) // Bill
   {
      // display slot pull, result, and winnings
      String results = thePull.toString();
      System.out.println("Whirrrrrr .... and your pull is ...");
      System.out.println(results);
      if (winnings >= 1) // If not zero winnings
      {
         System.out.println("Congratulations, you win: $" + 
      String.valueOf(winnings));
      }
      else 
      {
         System.out.println("Sorry, you lose.");
      }
   }   
}
/**
TripleString Class
*/
class TripleString 
{
   public static final int MAX_LEN = 20;
   public static final int MAX_PULLS = 40;
   private String string1, string2, string3;
   private static int numPulls;
   private static int[] pullWinnings = new int[MAX_PULLS];
   /**
   Name: TripleString
   Description: default constructor for the TripleString class
   sets strings 1, 2, and 3 to a space
   */
   public TripleString() // Lilia
   {
      string1 = " ";
      string2 = " ";
      string3 = " ";
   }
   /**
   Name: validString
   Parameter(s): String str
   Return: true or false (boolean)
   Purpose of return: to allow the calling method
   to determine if a string is not null and its length <= MAX_LEN
   Description: Private helper method returns true if both 
   the string is not null and its length <= MAX_LEN
   and false if otherwise
   */
   private boolean validString(String str) // Bill
      {
         if (str != null && str.length() <= TripleString.MAX_LEN)
         { 
            return true;
         }
         else
         {
            return false;
         }
      }
   /**
   Name: setString1
   Parameter(s): String str
   Return: true or false (boolean)
   Purpose of return: to allow the calling method
   to determine if the string was actually passed
   to the TripleString class and calls 
   validString to check for a valid string
   */
   public boolean setString1(String str) // Lilia
   {
      if (validString(str) == true)
      {
          string1 = str;
          return true;
      }
      else 
      {
          return false;
      }
   }
   /**
   Name: setString2
   Parameter(s): String str
   Return: true or false (boolean)
   Purpose of return: to allow the calling method
   to determine if the string was actually passed
   to the TripleString class and calls 
   validString to check for a valid string
   */
   public boolean setString2(String str) // Lilia
   {
      if (validString(str) == true)
      {
          string2 = str;
          return true;
      }
      else 
      {
          return false;
      }
   }
   /**
   Name: setString3
   Parameter(s): String str
   Return: true or false (boolean)
   Purpose of return: to allow the calling method
   to determine if the string was actually passed
   to the TripleString class and calls 
   validString to check for a valid string
   */
   public boolean setString3(String str) // Lilia
   {
      if (validString(str) == true)
      {
          string3 = str;
          return true;
      }
      else 
      {
          return false;
      }
   }
   /**
   Name: getString1
   Return: string1
   Purpose of return: to allow the calling method
   to access the private string1
   */
   public String getString1() // Lilia
   {
      return string1;
   }
   /**
   Name: getString2
   Return: string2
   Purpose of return: to allow the calling method
   to access the private string2
   */
   public String getString2() // Lilia
   {
      return string2;
   }
   /**
   Name: getString3
   Return: string3
   Purpose of return: to allow the calling method
   to access the private string3
   */
   public String getString3() // Lilia
   {
      return string3;
   }
   /**
   Name: displayWinnings
   Return: as specified in the instructions
   for assignment 2 displayWinnings returns a string
   Purpose of return: to fulfill the assignment requirements
   and let the calling method know that the method has executed
   Description: method displays several user messages and
   the winnings by calling the pullWinnings array and 
   displays all the individual wins and total of all wins
   it also provides a message when the maximum number of pulls
   has been reached
   */
   public static String displayWinnings() // Christina
   {
      // displays winnings and total winnings using
      // pullWinnings array
      int totalWin = 0;
      String end = "EXIT";
      System.out.println("Thanks for playing at the Casino!");
      System.out.println("Your individual winnings were:");
      for (int i = 0; i < numPulls; i++)
      {
         int printWin = 0;
         totalWin += TripleString.pullWinnings[i];
         printWin = TripleString.pullWinnings[i];
         System.out.print(printWin + " ");
      }
      System.out.println('\n' + "Your total winnings were: $" + 
      String.valueOf(totalWin));
      if (numPulls == MAX_PULLS)
      {
         System.out.println("Sorry, you have reached the maximum number "
               + "of pulls.");
      }
      return end;
   }
   /**
   Name: toString
   Return: a string contains all three private strings
   Purpose of return: to return a single string 
   so the calling method can display it as a casino slot pull
   Description: concatente string1, 2, and 3 into a single string
   */
   public String toString() // Christina
   {
      // concatente string1, 2, and 3 into a single string
      String toStr;
      toStr = (this.getString1() + " " + this.getString2() + " " + 
      this.getString3());
      return toStr;
   }   
   /**
   Name: saveWinnings
   Parameter(s): int winnings
   Return: true or false (boolean)
   Purpose of return: to allow the calling method to 
   ensure that the winnings have been inserted into the
   pullWinnings array
   Description: takes the winnings and stores them
   into the pullWinnings array
   */
   public static boolean saveWinnings(int winnings) // Bill
   {
      // stores winnings into the pullWinnings array
      int pullNum = TripleString.numPulls;
      int max = TripleString.MAX_PULLS;
      if (pullNum >= max)
      {
         return false;
      }
      else
      {
         TripleString.pullWinnings[pullNum] = winnings;
         TripleString.numPulls++;
         return true;
      }
   }
}
