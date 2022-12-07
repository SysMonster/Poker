import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        System.out.println("Start Spiel");
        System.out.print("Generiere Hand 1: ");

        CardUtils cardUtils = new CardUtils();

        Map<String, List<Card>> hands = cardUtils.createHands();
        final List<Card> firstHand = hands.get("firstHand");
        final List<Card> secondHand = hands.get("secondHand");

        System.out.print("Generiere Hand 2: ");

        System.out.println("Die Karte hat das Symbol " + firstHand.get(0).getSuit());
        System.out.println("Die Karte hat den Wert " + firstHand.get(0).getValue());
    }
}
