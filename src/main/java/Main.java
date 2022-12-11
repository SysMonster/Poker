package main.java;

public class Main {

    public static void main(String[] args) {

        System.out.println("Start Spiel");

        CardUtils cardUtils = new CardUtils();
        CardResult cardResult = new CardResult();

        cardUtils.createHands();

        Hand firstHand = new Hand();
        Hand secondHand = new Hand();

        firstHand.setHand(CardUtils.firstHand);
        secondHand.setHand(CardUtils.secondHand);

        CardResult.ResultValue resultOfFirstHand = cardResult.getResult(firstHand);
        CardResult.ResultValue resultOfSecondHand = cardResult.getResult(secondHand);

        System.out.println("Spieler 1 hat die Karten: ");
        int i = 0;
        for(Card card : firstHand.getHand()){
            System.out.println("Die Karte " + ++i + " ist " + card.getSuit() + " " + card.getValue());
        }

        System.out.println("Der Spieler hat: " + resultOfFirstHand);

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("Spieler 2 hat die Karten: ");
        int j = 0;
        for(Card card : secondHand.getHand()){
            System.out.println("Die Karte " + ++j + " ist " + card.getSuit() + " " + card.getValue());
        }

        System.out.println("Der Spieler hat: " + resultOfSecondHand);

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");

        //Check result
        if(resultOfFirstHand.value > resultOfSecondHand.value){
            System.out.println("Gewonnen hat SPIELER 1");
        }else if(resultOfFirstHand.value < resultOfSecondHand.value){
            System.out.println("Gewonnen hat SPIELER 2");
        }
        //If results are same then check second condition
        else{
            int compareResult = cardResult.compareResults(resultOfFirstHand, firstHand, secondHand);
            switch (compareResult){
                case 1:
                    System.out.println("Gewonnen hat SPIELER 1");
                    break;
                case 2:
                    System.out.println("Gewonnen hat SPIELER 2");
                    break;
                case 0:
                    System.out.println("Unentschieden!");
                    break;
                default:
                    System.out.println("Fehler");
            }
        }
    }
}
