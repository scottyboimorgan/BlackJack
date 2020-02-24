//Scott Morgan
//11/28/2018
public class Card{//used to make Card objects which will be placed into "Deck"s
  private int num;
  private Suit s;
  private String name;//cards have a name, value, number and suit
  private int val;
  
  public Card(int num, Suit s){//creates cards
    this.num = num;
    this.nameCard();
    this.s = s;
  }
  
  private void nameCard(){//names cards
    //only used in constructor, why it is private
    //made it a helper method to keep it more organized
    if(this.num == 1){
      this.name = "Ace";
      this.val = 11;
    }
    else if(this.num == 11){
      this.name = "Jack";
      this.val = 10;
    }
    else if(this.num == 12){               //4 special cards that dont have a number
      this.name = "Queen";
      this.val = 10;
    }
    else if(this.num == 13){
      this.name = "King";
      this.val = 10;
    }
    else{
      this.name = "" + this.num; //the name is the number if its not an ace or face card
      this.val = this.num;
    }
  }
  
  public Suit getSuit(){//returns the suit
    return this.s;
  }
  
  public int getVal(){//returns the value of the card
   return this.val; 
  }
  
  public int getNum(){//returns the number of the card
    return this.num;
  }
  
  public String toString(){//[Name] of [Suit]
    return this.name + " of " + this.s;
  }
  
}
