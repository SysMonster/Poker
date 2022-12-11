package main.java;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    //The actuall hand
    public List<Card> hand;

    //The card which should be compared if the other hand has the same result
    public List<Card> comparingCards = new ArrayList<>();

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public List<Card> getComparingCards() {
        return comparingCards;
    }

    public void setComparingCards(List<Card> comparingCards) {
        this.comparingCards = comparingCards;
    }
}
