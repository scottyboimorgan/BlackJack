//Scott Morgan
//11/28/2018
import java.util.Scanner;
import java.util.ArrayList;
public class Dealer extends Player{//creates dealer objects that will be controlled by AI to play against the player
  //need to use many of the same methods as players but need to change a couple
  private String name;
  private ArrayList<Card>hand;
  private int roundScore;
  private int numDealt;
  public Dealer(){//creates dealer objects
    this.name = this.setName();
    this.hand = new ArrayList<Card>(); //less fields are needed here than in the player class
    this.roundScore = 0;
    this.numDealt = 0;
  }
  
  @Override protected String setName(){//the dealer's name will always be the dealer
    this.name = "The Dealer";
    return this.name;      
  }
  
  @Override public int getRoundScore(){//the dealer is not allowed to play a "soft" ace, so the ace will always 
    //equal 11
    this.roundScore = 0;
    for(int i = 0; i < this.getNumCards(); i++){
      this.roundScore += this.getHand()[i].getVal();
    }
    return this.roundScore;
  }
}
