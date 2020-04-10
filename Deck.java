import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Deck.java
 * 
 * @brief A class that represents a deck of playing cards
 * 
 * @details
 * This class is responsible for shuffling and dealing a deck of cards. The deck should
 * contain 52 cards A,2 - 10, J,Q K or four suits, but no jokers.
 */
class Deck 
{
  private ArrayList<Card> all_cards; // ArrayList of all 52 cards

  // Deck constructor which initializes the all_cards list
  public Deck() {
    all_cards = new ArrayList<>();

    // Initializing the all_cards array
    for (int suit = 0; suit < 4; suit++) {
      for (int value = 1; value < 14; value++) { // Excluding the joker as a value
        Card card = new Card();
        card.set_value(value, suit);
        all_cards.add(card);
      }
    }
  }
  
  /**
   * Deals the cards in the given number of sets and cards.
   * If the total number goes beyond the total number of cards (52), it will strat over.
   *
   * @param sets  number of sets
   * @param cards number of cards in each set
   */
  public void deal_hand( int sets, int cards ) {
    int index = 0;
    for (int set = 0; set < sets; set++) {
      System.out.print("Set " + (set + 1) + " ");
      for (int card = 0; card < cards; card++) {
        all_cards.get(index).print();
        index++;

        if (index == all_cards.size()) {
          index = 0;
        }
      }
      System.out.print("\n");
    }
  }
  
  // prints the whole deck in four sets, each with 13 cards
  public void print_deck() {
    for (int i = 0; i < all_cards.size(); i++) {
      if (i % 13 == 0) { // there are 13 cards in each set
        System.out.print("\n");
      }
      all_cards.get(i).print();
    }
  }
  
  // shuffles the list of cards by a given seed value
  //
  // param[in]  seed  seed value for the random function to make it testable and deterministic
  public void shuffle( int seed ) {
    Collections.shuffle(all_cards, new Random(seed));
  }
  
  public void sort() 
  {}
  
}