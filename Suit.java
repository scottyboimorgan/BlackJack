//Scott Morgan
//11/28/2018
public enum Suit{//there can only be 4 suits, so we might as well make that part of the card into an enum
  DIAMOND("Diamonds"),HEART("Hearts"),SPADE("Spades"),CLUB("Clubs");
  private String name;
 
  Suit(String name){//creates suit enum objects
    this.name = name;
  }

  public String toString(){//the name of the suit is returned
    return this.name;
  }
}
