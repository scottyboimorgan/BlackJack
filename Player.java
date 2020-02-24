//Scott Morgan
//11/28/2018
import java.util.Scanner;
import java.util.ArrayList;
public class Player{//creates player objects 
  private int cash;//amount of money the player has
  private int bet;//value of the player's bet
  private final int MAX_BET;//the maximum bet allowed at the table
  private ArrayList<Card>hand;//the player's hand of cards
  private String name;//the name of the player
  private int roundScore;//the score of the player's hand for a specific round
  private Scanner sc = new Scanner(System.in);//used to receive user input
  private int numCards;//the number of cards in the player's hand
  private int numDealt;//the number of cards the player has been dealt throughout the game
  
  public Player(){//creates player objects
    this.name = this.setName();
    this.roundScore = 0;
    this.cash = 100;//this could be changed, depending on how long the game is meant to last
    this.hand = new ArrayList<Card>();
    this.numDealt = 0;
    this.bet = 0;
    this.numCards = 0;
    this.MAX_BET = 25;//also subject to change to adjust speed of game
  }
  
  protected String setName(){//sets the name of the player, keeps the constructor clean, is overridden in the dealer class
    System.out.println("What is your name?");
    this.name = sc.nextLine();//gets the player's name from input
    return this.getName();
  }
  
  public void setBet(int x) throws NumberFormatException{//allows the player to choose how much they want to bet
    try{
      if(x > MAX_BET){
        System.out.println("The max bet at this table is $" + this.MAX_BET);
        System.out.println("Please bet an amount less than or equal to $25");
        setBet(Integer.parseInt(sc.nextLine()));//recursive call so player has to enter an integer from 1-25
      }
      else if(x <= 0){
        System.out.println("You have to bet something, enter a positive number");
        setBet(Integer.parseInt(sc.nextLine()));//recursive call so player has to enter an integer from 1-25 
      }
      else{
        this.bet = x;//set the bet if the conditions are satisfied
      }
    }
    catch(NumberFormatException e){
      System.out.println("Money is number based, please enter a whole dollar amount less than or equal to $" + this.MAX_BET);
      setBet(Integer.parseInt(sc.nextLine()));//if the user does not enter a number, call the method
    }
  }
  public int getNumDealt(){//returns the number of cards dealt during the game
    return this.numDealt; 
  }
  public void resetNumDealt(){//resets the amount of cards dealt, used for when to reshuffle the deck
    this.numDealt = 0;
  }
  public int getBet(){//returns the bet of the player
    return this.bet;    
  }
  
  public String getName(){//returns the name of the player
    return this.name; 
  }
  
  public int getCash(){//returns the amount of cash the player has
    return this.cash;
  }
  
  public int getRoundScore(){//gets the score of the player's current hand
    if(this.roundScore > 21){//checks if an ace needs to be "soft" or has to have its value changed from 11 to 1
      this.roundScore = 0;
      for(int i = 0; i < numCards; i++){
        if(this.getHand()[i].getVal() == 11){//if its an ace, only add 1 because the total is greater than 100
          this.roundScore += 1;
        }
        else{
          this.roundScore += this.getHand()[i].getVal();//if it's not an ace, just add the value of the card
        }
      }
    }
    return this.roundScore;//the roundScore is increased for each card added to the hand, so just return that if it is
    //less than 21
    
  }
  
  public void addCash(int n){//adds an amount of cash to the players cash pile
    this.cash += n;
  }
  
  public void subtractCash(int n){//subtracts an amount of cash from the players cash pile
    this.cash -= n;
  }
  
  public void addToHand(Card c){//adds a card to the players hand
    if(this.roundScore > 10 && c.getVal() == 11){
      this.roundScore += 1;//soft ace case
    }
    else{
      this.roundScore += c.getVal();
    }
    this.hand.add(c);//adds to the hand field
    this.numCards++;
    this.numDealt++;
  }
  
  public void resetHand(){//resets the players hand
    this.hand = new ArrayList<Card>();
    this.numCards = 0;
    this.roundScore = 0;
  }
  
  public int getNumCards(){//returns the amount of cards in the players hand
    return this.numCards;
  }
  
  public Card[] getHand(){//returns an array version of the players hand
    Card[]ans = new Card[this.hand.size()];
    for(int i = 0; i < this.hand.size(); i++){
      ans[i] = hand.get(i);
    }
    return ans;
  }
  
}
