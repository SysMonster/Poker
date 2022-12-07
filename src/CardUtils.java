import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardUtils {

    /**
     * Creates two hands with different cards and returns
     * @return
     */
    public Map<String, List<Card>> createHands (){
        List<Card> firstHand = new ArrayList<Card>();
        List<Card> secondHand = new ArrayList<Card>();

        firstHand.add(new Card(Card.CardSuit.values()[getRandomInteger(0,3)], Card.CardValue.values()[getRandomInteger(0,12)]));

        Map<String, List<Card>> returnValue = new HashMap<String, List<Card>>();
        returnValue.put("firstHand", firstHand);
        returnValue.put("secondHand", secondHand);

        return returnValue;
    }

    /**
     * A card can only used once with the same value and suit
      */
    public void checkIfCardIsValid(Card card){

    }

    /**
     * Returns random integer between minimum and maximum range
     */
    public int getRandomInteger(int minimum, int maximum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;
    }
}
