import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Start Spiel");
        System.out.println("Generiere Hand 1: ");

        CardUtils cardUtils = new CardUtils();

        cardUtils.createHands();
        final List<Card> firstHand = CardUtils.firstHand;
        final List<Card> secondHand = CardUtils.secondHand;

//        System.out.print("Generiere Hand 2: ");

        int i = 0;
        for(Card card : firstHand){
            System.out.println("Die Karte " + ++i + " hat das Symbol " + card.getSuit());
            System.out.println("Die Karte " + i + " hat den Wert " + card.getValue());
        }
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        int j = 0;
        for(Card card : secondHand){
            System.out.println("Die Karte " + ++j + " hat das Symbol " + card.getSuit());
            System.out.println("Die Karte " + j + " hat den Wert " + card.getValue());
        }
    }
}
