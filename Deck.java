//Scott Morgan
//11/28/2018
import java.util.Random;
public class Deck{//creates deck objects
  private Card[]cards = new Card[52];
  //decks are always 52 cards, this version of blackjack will be a 1 deck table
  public Deck(){
    Suit[]suits = new Suit[4];
    suits[0] = Suit.HEART;
    suits[1] = Suit.DIAMOND;  //easier access for the suits
    suits[2] = Suit.SPADE;
    suits[3] = Suit.CLUB;
    int x = 0;//number of the deck
    int j = 0;//access for suit
    while(j < 4){//gets every suit
      for(int i = 1; i < 14; i++){ //4*13 = 52
        this.cards[x] = new Card(i, suits[j]);//sets the card into the deck
        x++;
      }
      j++;
    }
  }
  
  public void shuffle(){//shuffles the deck into a random order
    Random r = new Random();
    for(int i = 0; i < 50; i++){//uses a random object to switch the order of cards
      int x = i + r.nextInt(cards.length-i);
      Card temp = this.cards[x];//basic temp switch
      this.cards[x] = this.cards[i];
      this.cards[i] = temp;
    }
  }
  
  public Card deal(){//deals a card from the deck
    Card[]newArr = new Card[cards.length-1];//were losing a card so we need a new, shortened array
    Card temp = cards[cards.length-1];//gets the last card of the array
    for(int i = 0; i < cards.length - 1; i++){
        newArr[i] = cards[i]; //puts the cards into the newArr
    }
    cards = newArr;//reassigns the new array to the cards field
    return temp;//returns the removed card
  }
  
  public String toString(){//returns all of the cards in the deck
    String ans = "";
    for(int i = 0; i < cards.length; i++){
      ans = ans + this.cards[i] + "\n";
    }
    return ans;
  }

}
