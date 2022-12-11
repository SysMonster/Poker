package main.java;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("Start Spiel");

        CardUtils cardUtils = new CardUtils();
        CardResult cardResult = new CardResult();

        cardUtils.createHands();
        final List<Card> firstHand = CardUtils.firstHand;
        final List<Card> secondHand = CardUtils.secondHand;

        CardResult.ResultValue resultOfFirstHand = cardResult.getResult(firstHand);
        CardResult.ResultValue resultOfSecondHand = cardResult.getResult(secondHand);

        int i = 0;
        for(Card card : firstHand){
            System.out.println("Die Karte " + ++i + " hat das Symbol " + card.getSuit());
            System.out.println("Die Karte " + i + " hat den Wert " + card.getValue());
        }

        System.out.println("Die erste Hand ist ein " + resultOfFirstHand);

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        int j = 0;
        for(Card card : secondHand){
            System.out.println("Die Karte " + ++j + " hat das Symbol " + card.getSuit());
            System.out.println("Die Karte " + j + " hat den Wert " + card.getValue());
        }

        System.out.println("Die erste Hand ist ein " + resultOfSecondHand);

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
            cardResult.compareResults(resultOfFirstHand,resultOfSecondHand);
        }
    }
}
