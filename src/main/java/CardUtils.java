package main.java;

import java.util.ArrayList;
import java.util.List;

public class CardUtils {

    public static List<Card> firstHand = new ArrayList<Card>();
    public static List<Card> secondHand = new ArrayList<Card>();


    /**
     * Creates two hands with different cards and returns
     * @return
     */
    public void createHands (){
        for (int i = 0; i<5; i++){

            Card newCardForFirstHand = new Card(Card.CardSuit.values()[getRandomInteger(0, 3)], Card.CardValue.values()[getRandomInteger(0, 12)]);
            while (!checkIfCardIsValid(newCardForFirstHand)){
                newCardForFirstHand = new Card(Card.CardSuit.values()[getRandomInteger(0, 3)], Card.CardValue.values()[getRandomInteger(0, 12)]);
            }
            firstHand.add(newCardForFirstHand);

            Card newCardForSecondHand = new Card(Card.CardSuit.values()[getRandomInteger(0, 3)], Card.CardValue.values()[getRandomInteger(0, 12)]);
            while (!checkIfCardIsValid(newCardForSecondHand)){
                newCardForSecondHand = new Card(Card.CardSuit.values()[getRandomInteger(0, 3)], Card.CardValue.values()[getRandomInteger(0, 12)]);
            }
            secondHand.add(newCardForSecondHand);
        }
    }

    /**
     * A card can only used once with the same value and suit
      */
    public boolean checkIfCardIsValid(final Card card){
        Card possibleCardFirstHand = firstHand.stream()
                .filter(c -> c.getValue() == card.getValue())
                .filter(c -> c.getSuit() == card.getSuit())
                .findAny()
                .orElse(null);

        Card possibleCardSecondHand = secondHand.stream()
                .filter(c -> c.getValue() == card.getValue())
                .filter(c -> c.getSuit() == card.getSuit())
                .findAny()
                .orElse(null);

        // The new card is either found in firstHand nor in secondHand. That's good
        if(possibleCardFirstHand == null && possibleCardSecondHand == null){
            return true;
        }

        //main.java.Card already exists in one hand
        System.out.println("FOUND CARD "  + card.getSuit() + " " + card.getValue() + ". Need another one!");
        return false;
    }

    /**
     * Returns random integer between minimum and maximum range
     */
    public int getRandomInteger(int minimum, int maximum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
}
