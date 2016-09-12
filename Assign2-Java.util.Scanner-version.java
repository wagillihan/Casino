/**
  CST 338 - Assignment 2
  This was a collaboration of the Software Design Team
  Lilia Chakarian, William Gillihan, and Christina Hunter
*/
//imports
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class Assign2
{
   private static Scanner keyboard  = new Scanner(System.in);
   /**
      Method Main
      Where all the action takes place.
   */
   public static void main(String[] args)
   {
      // int counter = 0;
      while (true)// (counter <= TripleString.MAX_PULLS)
      {
         int theBet = (int)getBet();
         if (theBet != 0)
         {
            // counter++;
            TripleString obj = pull();
            int multiplier = getPayMultiplier(obj);
            int won = (theBet * multiplier);
            if (!TripleString.saveWinnings(won))
            {
               TripleString.displayWinnings();
            }
            else
            {
               TripleString.display(obj, won);
            }
            
         }
         else
         {  
            keyboard.close();
            TripleString.displayWinnings();
         }
         
      }   

   }
   
   /**
      Method to help the main().
      This prompts the user for input and 
      returns the bet amount as a functional return.
      It should validate the amount before it returns 
      and insist on a legal bet (0 < bet < 100) until 
      it gets one from the user.  It must return the 
      legal value to the client and not take any other 
      action besides getting the legal amount.
   */
   private static int getBet()
   {
      //input will be 0-100 initialize to a different value
      //Scanner keyboard  = new Scanner(System.in);
      int bet = -1;
      System.out.print("Hello. How much would you like to bet (1 - 100) or 0 to quit? ");
      bet = keyboard.nextInt();
      
      while(bet < 0 || bet > 100)
      {
         System.out.print("Error. How much would you like to bet (1 - 100) or 0 to quit? ");
         bet = keyboard.nextInt();
      }
      return bet;
   }
   
   /**
      Method to help the main().
      This method instantiates and 
      returns a TripleString object to the client.
      The data of theTripleString object has to first 
      be filled with three randomly chosen strings 
      according to the probabilities described in the 
      "Understand the Application" section above.
   */
   public static TripleString pull()
   {
      TripleString pullString = new TripleString();
      String strg1, strg2, strg3;
      strg1 = randString();
      pullString.setString1(strg1);
      strg2 = randString();
      pullString.setString2(strg2);
      strg3 = randString();
      pullString.setString3(strg3);
      return pullString;
   }
   
   /**
      Method to generate random string
      It produces and returns a single random 
      string based on the required probabilities. 
      It does this by calling the java Math.random() 
      function and using the return result of that 
      function as a means of deciding which of the 
      four possible strings to return.  
   */
   private static String randString()
   {
      int start = 0;
      int end = 7;
      String chooseString = "";
      // int random = (int)(Math.random() * ((end - start) + 1 )) + start;
      Random r = new Random();
      int random = r.nextInt((end - start) + 1) + start;
      if(random == 0 || random == 2 || random == 3 || random == 5)
      {
         chooseString = "BAR";
      }
      else if(random == 1 || random == 4)
      {
         chooseString = "cherries";
      }
      else if(random == 6)
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
      Method to get the Pay Multiplier
      Takes the pullString Object as a parameter, and 
      inspects it to determine what its pay multiplier 
      should be:  5x  15x  100x  0x   
      It does this by looking at the three strings inside the
      passed-in TripleString object and using if statements 
      to determine and return the right value
   */
   public static int getPayMultiplier(TripleString thePull) // Bill
   {
      String str1 = thePull.getString1();
      String str2 = thePull.getString2();
      String str3 = thePull.getString3();
      // Checking for cherries
      if (str1.equals("cherries")) // 1st cherries
      {
         if (str2.equals("cherries")) // 2nd cherries so far
         {
            if (str3.equals("cherries")) // cherries cherries cherries pays 30 × bet
            {
               return 30;
            }
            else // cherries cherries [not cherries] pays 15 × bet
            {
               return 15;
            }
         }
         else
         {
            return 5; // cherries [not cherries] [any] pays 5 × bet (5 times the bet)
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
}

/**
   Class TripleString
   Object instance is used in Casino game
*/
class TripleString 
{
   
   /* 
      Object members for the TripleString class.
   */
   public static final int MAX_LEN = 20;
   public static final int MAX_PULLS = 40;
   private String string1, string2, string3;
   private static int numPulls;
   private static int[] pullWinnings = new int[MAX_PULLS];
   
   /**
      Default constructor for TripleString class
      Sets all three string members to empty strings
   */
   public TripleString() 
   {
      string1 = "";
      string2 = "";
      string3 = "";
   }
   /** 
      Private helper method returns true if both 
      the string is not null and its length <= MAX_LEN 
      and false if otherwise
   */
   private boolean validString( String str ) //Bill
      {
         if( str != null && str.length() <= TripleString.MAX_LEN )
         { 
            return true;
         }
         else
         {
            return false;
         }
      }
   /** 
      Mutators for each of the private string members
      Return a boolean according to whether it was 
      successful in updating the private member variable
      Calls the validString() to test for valid incoming data
      update the private member variable with the incoming data if it is good
      @param new string to replace member
   */
   public boolean setString1(String str) //mutator for String1
   {
      if (validString(str)== true)
      {
         this.string1 = str;
         return true;
      }
      else 
      {
         return false;
      }
   }
   
   public boolean setString2(String str) //mutator for String2
   {
      if (validString(str)== true)
      {
         this.string2 = str;
         return true;
      }
      else 
      {
         return false;
      }
   }
 
   public boolean setString3(String str) //mutator for String3 
   {
      if (validString(str)== true)
      {
         this.string3 = str;
         return true;
      }
      else 
      {
         return false;
      }
   }

   /** 
      Accessors for each of the private string members
      allows access to members from outside class
      @return string value
   */
   public String getString1() //accessor for String1
   {
      return this.string1;
   }
 
   
   public String getString2() //accessor for String2 
   {
      return this.string2;
   }
  
   
   public String getString3() //accessor for String3
   {
      return this.string3;
   }
   
   /**
      @overload toString() method
      @return all of the strings as one string.
   */
   public String toString() 
   {
      String toStr;
      toStr = (this.getString1() + " " + this.getString2() + " " + this.getString3());
      return toStr;
   }
   
   /*
      Method for the pullWinnings array.
      Saves the winnings from the round
   */
   public static boolean saveWinnings(int winnings) //Bill
   {
      int pullNum = TripleString.numPulls;
      int max = TripleString.MAX_PULLS;
      if ( pullNum >= max )
      {
         System.out.println("You have reached your maximum amount of pulls");
         return false;
      }
      else
      {
         TripleString.pullWinnings[pullNum] = winnings;
         TripleString.numPulls++;
         return true;
      }
   }
   
   /**
      Method for the pullWinnings array.
      Uses a loop to get the values out 
      of the array and displays a string
   */
   public static void displayWinnings() 
   {
      int totalWin = 0;
      System.out.println("Thanks for playing at the Casino!");
      System.out.println("Your individual winnings were:");
      for (int i = 0; i < numPulls; i++)
      {
         int printWin = 0;
         totalWin += TripleString.pullWinnings[i];
         printWin = TripleString.pullWinnings[i];
         System.out.print(printWin + " ");
      }
      System.out.println('\n' + "Your total winnings were: $" + String.valueOf(totalWin));
      System.exit(0);
   }
   
   /**
      Output method used at the end of each 
      loop-pass when the user needs to see the 
      results of her pull, and receive the news 
      about how much they won (or not):
      This method takes the winnings (a dollar amount) 
      and thePull as parameters and displays the three strings 
      inside thePull along with "  sorry - you lost " or 
      "congrats, you won $X".
   */
   public static void display (TripleString thePull, int winnings) // Bill
   {
   // get the resulting three strings
      String results = thePull.toString();
   // give player game simulation feedback
      System.out.println("Whirrrrrr .... and your pull is ...");
   // show pull results
      System.out.println(results);
   // show winnings
      if (winnings >= 1) // If not zero winnings
      {
         System.out.println("Congratulations, you win: $" + String.valueOf(winnings));
      }
      else 
      {
         System.out.println("Sorry, you lose.");
      }
   }
}
