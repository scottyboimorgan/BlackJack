//Scott Morgan
//11/28/2018
import java.util.Scanner;
import java.util.ArrayList;
public class BlackJack{//the driver class for the BlackJack game
  
  private Scanner sc;
  private Deck deck;
  private Player player;
  private Dealer dealer;
  private boolean playerBust;//used if the player busts
  private boolean playerBlackJack;//used if the player gets a blackjack in their first two cards
  private int doubleGoal;//the player's initial cash, doubled (the amount of cash the player must get to win)
  
  public BlackJack(){//creates an instance of the BlackJack game
    sc = new Scanner(System.in);
    deck = new Deck();
    deck.shuffle();//we need to have a shuffled deck
    System.out.println("Welcome to BlackJack \n");
    player = new Player();
    dealer = new Dealer();
    playerBust = false;
    doubleGoal = player.getCash() * 2;
  }
  public void printCash(){//this is used a few times throughout the , just prints the player's name and howmuch $ they have
    System.out.println(player.getName() + ", you have $" + player.getCash());
  }
  public void playBlackJack() throws NumberFormatException{ //the driver method, has helper methods for different parts
    //of the game
    this.printCash();
    System.out.println("How much do you want to bet?");
    try{//allows the player to enter how much they want to bet
    String ans = sc.nextLine();
    player.setBet(Integer.parseInt(ans));
    }
    catch(NumberFormatException e){//if the player does not enter a number, tell them to and start the method over 
      //until they do
      System.out.println("Please enter an whole dollar amount to bet");
      this.playBlackJack();
    }
    
    this.newRound();
    this.playerPartOfRound();      //the three phases of the round, each with their own method for conciseness
    this.dealerPartOfRound();
    
    if(player.getCash() != 0 && player.getCash() < doubleGoal){//if the player has some money but has
      //yet to double their money
      if((player.getNumDealt() + dealer.getNumDealt()) >= 40){//running low on cards, time to reshuffle
        deck = new Deck();
        deck.shuffle();
        System.out.println("The deck has been re-shuffled");
        player.resetNumDealt();
        dealer.resetNumDealt();
      }
      this.playBlackJack();//start the method over since the game is not over yet
    }
    else if(player.getCash() == 0){//losing condition
      System.out.println("Oops, looks like you're out of money...");
      System.out.println(dealer.getName() + " wins. ");
      return;
    }
    else if(player.getCash() == doubleGoal){//winning condition
      this.printCash();
      System.out.println("You've successfully doubled your money!");
      System.out.println("Congratulations, " + player.getName() + ", you win!");
      return;
    }
  }
  
  public void newRound(){//the first of 3 phases of a complete round/hand of blackjack
    player.resetHand();
    dealer.resetHand();    //have to reset everything to start anew
    playerBust = false;
    playerBlackJack = false;
    player.addToHand(deck.deal());//the player starts with 2 face up cards
    player.addToHand(deck.deal());
    System.out.println();
    System.out.println("You were dealt a " + player.getHand()[0] + " and a " + player.getHand()[1]);
    System.out.println("Your current total is " + player.getRoundScore() + "\n");//print the cards out and their total
    dealer.addToHand(deck.deal());
    dealer.addToHand(deck.deal());//add 2 cards to the dealer's hand
    System.out.println("The Dealer shows a " + dealer.getHand()[1]);//the dealer starts with one card face down
    //so we will not show the player this until their turn is over
    System.out.println("The Dealer's total is " + dealer.getHand()[1].getVal() + " + their face down card \n");
  }
  
  public void playerPartOfRound(){//the player's turn in the current hand/round
    boolean wantsToPlay = true;//used to see if the player wants more cards
    while(wantsToPlay){
      if(player.getRoundScore() == 21){
        System.out.println("BlackJack! :^D");//the players first two cards = 21 aka blackjack
        //the player automatically wins this hand
        player.addCash(player.getBet());//the player gets to add the amount that they bet to their total cash
        playerBlackJack = true;//used in the dealer's portion of the round
        System.out.println();
        wantsToPlay = false;
        return;
      }
      else{//the player did not get blackjack, time to see if they want more cards
        System.out.println("Would you like another card? (type \"y\" for yes or \"n\" for no)");
        String ans = sc.nextLine();
        ans = ans.toLowerCase();//allows players to use capital letters if they please
        if(ans.equals("y")){//the player wants another card
          player.addToHand(deck.deal());//gives them one
          System.out.println("You've drawn a " + player.getHand()[player.getNumCards() - 1]);
          System.out.println("Your total is now " + player.getRoundScore());//tells them what card they were dealt as 
          //well as their new total
          if(player.getRoundScore() == 21){
            System.out.println("You have 21 :^D");//the player has 21, they dont automatically win tho
            wantsToPlay = false;
            return;
          }
          else if(player.getRoundScore() > 21){//the player busts, aka goes over 21
            System.out.println("You busted :^(\n");
            player.subtractCash(player.getBet());//automatically lose their bet
            playerBust = true;//used in dealer portion of the round
            wantsToPlay = false;
            return;
          }
        }
        else if(ans.equals("n")){//the player does not want another card
          wantsToPlay = false;
          System.out.println("You stand at " + player.getRoundScore() + " :^)\n");
          return;
        }
      }
    }
  }
  
  public void dealerPartOfRound(){
    boolean wantsToPlay = true;//same idea as player part of round
    if(playerBust){//if the player busts, the dealer does not get to play
      return;
    }
    else if(playerBlackJack){//if the player has blackjack, the dealer does not get to play
      return;
    }
    else{//the player stands at a certain value
      System.out.println("The Dealer's face down card is a(n) " + dealer.getHand()[0]);//reveal the dealer's face 
      //down card
      System.out.println("The Dealer's total is " + dealer.getRoundScore());//print out the dealer's total score for
      //this round
      while(wantsToPlay){
        if(dealer.getRoundScore() == 21){//if the dealer has 21
          if(player.getRoundScore() == 21){//both dealer and player have 21
            System.out.println("Stand-off, you get your bet back, " + player.getName() + "\n");
            wantsToPlay = false;
          }
          else if(player.getRoundScore() < 21){//dealer wins because player has less "points"
            System.out.println("The Dealer wins this hand, tough luck.\n");
            player.subtractCash(player.getBet());//player loses their bet
            wantsToPlay = false;
          }
        }
        
        else if(dealer.getRoundScore() < 21){//the AI for the dealer
          if(dealer.getRoundScore()> player.getRoundScore()){//no need to get another card if the dealer already has
            //a higher hand than the player
            System.out.println("The Dealer stands at " + dealer.getRoundScore());
            System.out.println("The Dealer wins this hand, tough luck.\n");
            player.subtractCash(player.getBet());//player loses their bet
            wantsToPlay = false;
          }
          else if(dealer.getRoundScore() < 17){//a situation where the dealer is likely to draw a card, assuming
            //that the player's score is high than their's
            if(dealer.getRoundScore() <= player.getRoundScore()){
              dealer.addToHand(deck.deal());//give the dealer a new card and show the player
              System.out.println("The Dealer has drawn a " + dealer.getHand()[dealer.getNumCards() - 1]);
              System.out.println("The Dealer's total is " + dealer.getRoundScore());
            }
            continue;
          }
          else if((dealer.getRoundScore() >= 17) && (dealer.getRoundScore() < player.getRoundScore())){
            dealer.addToHand(deck.deal());//the dealer will draw if their hand is less than the player's
            //because this dealer is only playing against 1 player, not an entire table of them
            System.out.println("The Dealer has drawn a " + dealer.getHand()[dealer.getNumCards() - 1]);
            System.out.println("The Dealer's total is " + dealer.getRoundScore());
            continue;
          }
          else if((dealer.getRoundScore() >= 17) && (dealer.getRoundScore() == player.getRoundScore())){
            //if the dealer has 17 or higher, and the player has the same amount, stand at that amount
            //the risk is too high to hit again
            System.out.println("The Dealer stands at " + dealer.getRoundScore());
            System.out.println("Stand-off, you get your bet back, " + player.getName() + "\n");
            wantsToPlay = false;
          }
        }
        else if(dealer.getRoundScore() > 21){//the dealer busts
          System.out.println("Dealer busts");
          System.out.println("You've won this hand, " + player.getName() + "\n");
          player.addCash(player.getBet());//the player wins their bet back
          wantsToPlay = false;
        }
      }
    }
  }
  
  public static void main(String[]args){//main method
    BlackJack game = new BlackJack();//creates a BlackJack object
    game.playBlackJack();//play the game
  }
}
