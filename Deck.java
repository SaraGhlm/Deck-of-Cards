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
  
  // Sorts the list of cards by using merge sort algorithm
  public void sort() {
    merge_sort(0, all_cards.size() - 1);
  }

  /**
   * Implements the merge sort algorithm for sorting all_cards list
   *
   * @param start_index   index of the beginning of the range
   * @param end_index     index of the end of the range
   */
  private void merge_sort(int start_index, int end_index) {
    if (start_index < end_index) {

      int middle_index = (start_index + end_index) / 2; // finds the middle of the given range

      // sorts the first half of the array
      merge_sort(start_index, middle_index);

      // sorts the second half of the array
      merge_sort(middle_index + 1, end_index);

      // merge both halves back
      merge(start_index, middle_index, end_index);
    }
  }

  /**
   * merges two halves of an all_cards arraylist
   *
   * @param start_index   index of the beginning of the range
   * @param middle_index  index of the middle of the range
   * @param end_index     index of the end of the range
   */
  private void merge(int start_index, int middle_index, int end_index) {
    // the temp arraylist which will be the sorted list
    ArrayList<Card> sorted_list = new ArrayList<>();

    int first_index = start_index;
    int second_index = middle_index + 1;

    // loop over first and second halves
    // it will stop when either the first half or second half is finished
    while (first_index <= middle_index && second_index <= end_index) {
      if (all_cards.get(first_index).compare_to(all_cards.get(second_index)) == -1) { 
        // first card is smaller than the second card
        sorted_list.add(all_cards.get(first_index));
        first_index++;
      } else { 
        // second card is smaller than the first card
        sorted_list.add(all_cards.get(second_index));
        second_index++;
      }
    }

    // This loop will add the remaining elements in the first half of the list (if there is any!)
    while (first_index <= middle_index) {
      sorted_list.add(all_cards.get(first_index));
      first_index++;
    }

    // This loop will add the remaining elements in the second half of the list (if there is any!)
    while (second_index <= end_index) {
      sorted_list.add(all_cards.get(second_index));
      second_index++;
    }

    // Writing the sorted arraylist over the all_cards arraylist
    int i = 0;
    int j = start_index;
    while (i < sorted_list.size()) {
      all_cards.set(j, sorted_list.get(i++));
      j++;
    }
  }
  
}