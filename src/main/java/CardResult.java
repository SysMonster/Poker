package main.java;

import java.util.*;
import java.util.stream.Collectors;

public class CardResult {

    public enum ResultValue {
        HIGH_CARD (0),
        PAIR (1),
        TWO_PAIRS (2),
        THREE_OF_A_KIND (3),
        STRAIGHT (4),
        FLUSH (5),
        FULL_HOUSE (6),
        FOUR_OF_A_KIND (7),
        STRAIGHT_FLUSH (8);

        public int value;
        ResultValue(int value) {
            this.value = value;
        }
    }

    public ResultValue getResult (Hand hand){
        if(isStraightFlush(hand))
            return ResultValue.STRAIGHT_FLUSH;

        if(isFourOfAKind(hand))
            return ResultValue.FOUR_OF_A_KIND;

        if(isFullHouse(hand))
            return ResultValue.FULL_HOUSE;

        if(isFlush(hand))
            return ResultValue.FLUSH;

        if(isStraight(hand))
            return ResultValue.STRAIGHT;

        if(isThreeOfAKind(hand))
            return ResultValue.THREE_OF_A_KIND;

        if(isTwoPairs(hand))
            return ResultValue.TWO_PAIRS;

        if(isPair(hand))
            return ResultValue.PAIR;

        return ResultValue.HIGH_CARD;
    }

    /**
     * Compares the both result because they are same and the second condition needs to be checked
     * @param resultOfFirstHand
     * @param firstHand
     * @param secondHand
     * @return 1 for first Hand, 2 for second hand, 0 for draw
     */
    public int compareResults(ResultValue resultOfFirstHand, Hand firstHand, Hand secondHand) {
        if(resultOfFirstHand.equals(ResultValue.STRAIGHT_FLUSH) ||
                resultOfFirstHand.equals(ResultValue.STRAIGHT)){
            if(firstHand.getComparingCards().get(0).getValue().value > secondHand.getComparingCards().get(0).getValue().value){
                return 1;
            }else if(firstHand.getComparingCards().get(0).getValue().value < secondHand.getComparingCards().get(0).getValue().value){
                return 2;
            }
            return 0;
        }

        if(resultOfFirstHand.equals(ResultValue.FOUR_OF_A_KIND) ||
                resultOfFirstHand.equals(ResultValue.FULL_HOUSE) ||
                resultOfFirstHand.equals(ResultValue.FLUSH) ||
                resultOfFirstHand.equals(ResultValue.THREE_OF_A_KIND)){
            if(firstHand.getComparingCards().get(0).getValue().value > secondHand.getComparingCards().get(0).getValue().value){
                return 1;
            }
            return 2;
        }

        if(resultOfFirstHand.equals(ResultValue.TWO_PAIRS)){
            //Check the higher pair
            if(firstHand.getComparingCards().get(0).getValue().value > secondHand.getComparingCards().get(0).getValue().value){
                return 1;
            }else if(firstHand.getComparingCards().get(0).getValue().value < secondHand.getComparingCards().get(0).getValue().value){
                return 2;
            }
            //Check the lower pair
            else{
                if(firstHand.getComparingCards().get(1).getValue().value > secondHand.getComparingCards().get(1).getValue().value){
                    return 1;
                }else if(firstHand.getComparingCards().get(1).getValue().value < secondHand.getComparingCards().get(1).getValue().value){
                    return 2;
                }
                //Check the remaining card
                else {
                    if (firstHand.getComparingCards().get(2).getValue().value > secondHand.getComparingCards().get(2).getValue().value){
                        return 1;
                    }else if(firstHand.getComparingCards().get(2).getValue().value < secondHand.getComparingCards().get(2).getValue().value){
                        return 2;
                    }
                    return 0;
                }
            }
        }

        if(resultOfFirstHand.equals(ResultValue.PAIR)){
            //Check the pair
            if(firstHand.getComparingCards().get(0).getValue().value > secondHand.getComparingCards().get(0).getValue().value){
                return 1;
            }else if(firstHand.getComparingCards().get(0).getValue().value < secondHand.getComparingCards().get(0).getValue().value){
                return 2;
            }
            //Check the other cards in decreasing order
            else{
                if(firstHand.getComparingCards().get(1).getValue().value > secondHand.getComparingCards().get(1).getValue().value){
                    return 1;
                }else if(firstHand.getComparingCards().get(1).getValue().value < secondHand.getComparingCards().get(1).getValue().value){
                    return 2;
                }else {
                    if (firstHand.getComparingCards().get(2).getValue().value > secondHand.getComparingCards().get(2).getValue().value){
                        return 1;
                    }else if(firstHand.getComparingCards().get(2).getValue().value < secondHand.getComparingCards().get(2).getValue().value){
                        return 2;
                    }else{
                        if (firstHand.getComparingCards().get(3).getValue().value > secondHand.getComparingCards().get(3).getValue().value){
                            return 1;
                        }else if(firstHand.getComparingCards().get(3).getValue().value < secondHand.getComparingCards().get(3).getValue().value) {
                            return 2;
                        }
                        return 0;
                    }
                }
            }
        }

        if(resultOfFirstHand.equals(ResultValue.HIGH_CARD)){
            //Check the highest card
            if(firstHand.getHand().get(4).getValue().value > secondHand.getHand().get(4).getValue().value){
                return 1;
            }else if(firstHand.getHand().get(4).getValue().value < secondHand.getHand().get(4).getValue().value){
                return 2;
            }
            //Check the other cards in decreasing order
            else{
                if(firstHand.getHand().get(3).getValue().value > secondHand.getHand().get(3).getValue().value){
                    return 1;
                }else if(firstHand.getHand().get(3).getValue().value < secondHand.getHand().get(3).getValue().value){
                    return 2;
                }else {
                    if (firstHand.getHand().get(2).getValue().value > secondHand.getHand().get(2).getValue().value){
                        return 1;
                    }else if(firstHand.getHand().get(2).getValue().value < secondHand.getHand().get(2).getValue().value){
                        return 2;
                    }else{
                        if (firstHand.getHand().get(1).getValue().value > secondHand.getHand().get(1).getValue().value){
                            return 1;
                        }else if(firstHand.getHand().get(1).getValue().value < secondHand.getHand().get(1).getValue().value) {
                            return 2;
                        }else{
                            if (firstHand.getHand().get(0).getValue().value > secondHand.getHand().get(0).getValue().value){
                                return 1;
                            }else if(firstHand.getHand().get(0).getValue().value < secondHand.getHand().get(0).getValue().value) {
                                return 2;
                            }
                            return 0;
                        }
                    }
                }
            }
        }
        return 4;
    }

    /**
     * Returns a set of Cards compare to the given cards value or suit
     * @param hand
     * @param card
     * @param compareToValue
     * @return
     */
    private Set<Card> getSameCards (List<Card> hand, Card card, boolean compareToValue, boolean negate){

        if(compareToValue){
            if(negate){
                return hand.stream()
                        .filter(v -> v.getValue() != card.getValue())
                        .collect(Collectors.toSet());
            }else{
                return hand.stream()
                        .filter(v -> v.getValue() == card.getValue())
                        .collect(Collectors.toSet());
            }
        }else if(negate){
            return hand.stream()
                    .filter(v -> v.getValue() != card.getValue())
                    .collect(Collectors.toSet());
        }
        return hand.stream()
                .filter(v -> v.getSuit() == card.getSuit())
                .collect(Collectors.toSet());
    }

    public boolean isStraightFlush(Hand hand){
        Card firstCard = hand.getHand().get(0);

        //First check if all cards have the same suit
        Set<Card> collect = getSameCards(hand.getHand(),firstCard,false, false);

        if (collect.size() == 5){
            //Then check if cards are straight
            if(isStraight(hand)){
                //
                hand.getComparingCards().add(firstCard);
                return true;
            }
        }

        return false;
    }

    public boolean isFourOfAKind(Hand hand){
        Card firstCard = hand.getHand().get(0);

        //Compare all cards with the first one
        Set<Card> collect = getSameCards(hand.getHand(),firstCard,true,false);

        if(collect.size() == 4) {
            hand.getComparingCards().add(firstCard);
            return true;
        }

        Card secondCard = hand.getHand().get(1);
        //Compare all cards with the second one
        //The first one could be different
        collect = getSameCards(hand.getHand(),secondCard,true,false);

        if(collect.size() == 4) {
            hand.getComparingCards().add(secondCard);
            return true;
        }

        return false;
    }

    public boolean isFullHouse(Hand hand){
        Card firstCard = hand.getHand().get(0);

        //Compare all cards with the first one
        Set<Card> firstPartOfFullHouse = getSameCards(hand.getHand(),firstCard,true,false);

        if(firstPartOfFullHouse.size() == 3){
            //Collect the 2 other cards and check if they are the same
            Set<Card> secondPartOfFullHouse = getSameCards(hand.getHand(),firstCard,true,true);
            if(secondPartOfFullHouse.size() == 2) {
                List<Card> secondPartOfFullHouseAsList = new ArrayList<>(secondPartOfFullHouse);
                if (secondPartOfFullHouseAsList.get(0).getValue() == secondPartOfFullHouseAsList.get(1).getValue()) {
                    //Set one the card from the 3 paired cards as comparing card
                    hand.getComparingCards().add(firstCard);
                    return true;
                }
            }
            return false;
        }

        Card secondCard = hand.getHand().get(1);

        //Compare all cards with the second one
        firstPartOfFullHouse = getSameCards(hand.getHand(),secondCard,true,false);

        if(firstPartOfFullHouse.size() == 3){
            //Collect the 2 other cards and check if they are the same
            Set<Card> secondPartOfFullHouse = getSameCards(hand.getHand(),secondCard,true,true);
            if(secondPartOfFullHouse.size() == 2) {
                List<Card> secondPartOfFullHouseAsList = new ArrayList<>(secondPartOfFullHouse);
                if (secondPartOfFullHouseAsList.get(0).getValue() == secondPartOfFullHouseAsList.get(1).getValue()) {
                    //Set one the card from the 3 paired cards as comparing card
                    hand.getComparingCards().add(secondCard);
                    return true;
                }
            }
            return false;
        }

        Card thirdCard = hand.getHand().get(2);

        //Compare all cards with the third one
        firstPartOfFullHouse = getSameCards(hand.getHand(),thirdCard,true,false);

        if(firstPartOfFullHouse.size() == 3){
            //Collect the 2 other cards and check if they are the same
            Set<Card> secondPartOfFullHouse = getSameCards(hand.getHand(),thirdCard,true,true);
            if(secondPartOfFullHouse.size() == 2) {
                List<Card> secondPartOfFullHouseAsList = new ArrayList<>(secondPartOfFullHouse);
                if (secondPartOfFullHouseAsList.get(0).getValue() == secondPartOfFullHouseAsList.get(1).getValue()) {
                    //Set one the card from the 3 paired cards as comparing card
                    hand.getComparingCards().add(thirdCard);
                    return true;
                }
            }
            return false;
        }

        return false;
    }

    public boolean isFlush(Hand hand){
        Card firstCard = hand.getHand().get(0);

        //First check if all cards have the same suit
        Set<Card> collect = getSameCards(hand.getHand(),firstCard,false,false);

        if (collect.size() == 5){
            // Set highly valued card as comparing card
            hand.getComparingCards().add(hand.getHand().get(4));
            return true;
        }

        return false;
    }

    public boolean isStraight(Hand hand){
        //Check if cards are straight
        for(int i = 0; i < 4; i++){
            if(hand.getHand().get(i+1).getValue().value - hand.getHand().get(i).getValue().value == 1){
                continue;
            }
            return false;
        }

        //Set the highest valued card as comparing card
        hand.getComparingCards().add(hand.getHand().get(4));
        return true;
    }

    public boolean isThreeOfAKind(Hand hand){
        Card firstCard = hand.getHand().get(0);

        //Compare all cards with the first one
        Set<Card> threeOfAKindHand = getSameCards(hand.getHand(), firstCard, true,false);

        if(threeOfAKindHand.size() == 3) {
            //Set one the card from the 3 paired cards as comparing card
            hand.getComparingCards().add(firstCard);
            return true;
        }

        Card secondCard = hand.getHand().get(1);

        //Compare all cards with the second one
        threeOfAKindHand = getSameCards(hand.getHand(), secondCard, true,false);

        if(threeOfAKindHand.size() == 3) {
            //Set one the card from the 3 paired cards as comparing card
            hand.getComparingCards().add(secondCard);
            return true;
        }

        Card thirdCard = hand.getHand().get(2);

        //Compare all cards with the third one
        threeOfAKindHand = getSameCards(hand.getHand(), thirdCard, true,false);

        if(threeOfAKindHand.size() == 3) {
            //Set one the card from the 3 paired cards as comparing card
            hand.getComparingCards().add(thirdCard);
            return true;
        }
        return false;
    }

    public boolean isTwoPairs(Hand hand){
        Card firstCard = hand.getHand().get(0);

        //Compare all cards with the first one
        Set<Card> firstPartOfTwoPairsHand = getSameCards(hand.getHand(), firstCard, true,false);

        if(firstPartOfTwoPairsHand.size() == 2){
            //Found one pair. Check the other 3 cards
            Set<Card> secondPartOfTwoPairsHand = getSameCards(hand.getHand(), firstCard, true,true);
            List<Card> secondPartOfTwoPairsHandAsList = new ArrayList<>(secondPartOfTwoPairsHand);

            Card firstCardOfTheSecondPart = secondPartOfTwoPairsHandAsList.get(0);
            secondPartOfTwoPairsHand = getSameCards(hand.getHand(), firstCardOfTheSecondPart, true, false);

            //Found the other pair compare to the first card
            if(secondPartOfTwoPairsHand.size() == 2){
                hand.getComparingCards().add(firstCard);
                hand.getComparingCards().add(firstCardOfTheSecondPart);
                hand.getComparingCards().add(hand.getHand().get(4));
                return true;
            }

            Card secondCardOfTheSecondPart = secondPartOfTwoPairsHandAsList.get(1);
            secondPartOfTwoPairsHand = getSameCards(hand.getHand(), secondCardOfTheSecondPart, true, false);

            //Found the other pair compare to the second card
            if(secondPartOfTwoPairsHand.size() == 2){
                hand.getComparingCards().add(firstCard);
                hand.getComparingCards().add(secondCardOfTheSecondPart);
                hand.getComparingCards().add(firstCardOfTheSecondPart);
                return true;
            }
            return false;
        }

        Card secondCard = hand.getHand().get(1);

        //Compare all cards with the second one
        firstPartOfTwoPairsHand = getSameCards(hand.getHand(), secondCard, true,false);

        if(firstPartOfTwoPairsHand.size() == 2){
            //Found one pair. Check the other 3 cards
            Set<Card> secondPartOfTwoPairsHand = getSameCards(hand.getHand(), secondCard, true,true);
            List<Card> secondPartOfTwoPairsHandAsList = new ArrayList<>(secondPartOfTwoPairsHand);

            Card firstCardOfTheSecondPart = secondPartOfTwoPairsHandAsList.get(0);
            secondPartOfTwoPairsHand = getSameCards(hand.getHand(), firstCardOfTheSecondPart, true, false);

            //Found the other pair compare to the first card
            if(secondPartOfTwoPairsHand.size() == 2){
                hand.getComparingCards().add(secondCard);
                hand.getComparingCards().add(firstCardOfTheSecondPart);
                hand.getComparingCards().add(firstCard);
                return true;
            }

            Card secondCardOfTheSecondPart = secondPartOfTwoPairsHandAsList.get(1);
            secondPartOfTwoPairsHand = getSameCards(hand.getHand(), secondCardOfTheSecondPart, true, false);

            //Found the other pair compare to the second card
            if(secondPartOfTwoPairsHand.size() == 2){
                hand.getComparingCards().add(secondCard);
                hand.getComparingCards().add(secondCardOfTheSecondPart);
                hand.getComparingCards().add(firstCard);
                return true;
            }
            return false;
        }

        return false;
    }

    public boolean isPair(Hand hand){
        Card firstCard = hand.getHand().get(0);

        //Compare all cards with the first one
        Set<Card> pairHand = getSameCards(hand.getHand(), firstCard, true,false);

        if(pairHand.size() == 2) {
            hand.getComparingCards().add(firstCard);
            hand.getComparingCards().add(hand.getHand().get(4));
            hand.getComparingCards().add(hand.getHand().get(3));
            hand.getComparingCards().add(hand.getHand().get(2));
            return true;
        }

        Card secondCard = hand.getHand().get(1);

        //Compare all cards with the second one
        pairHand = getSameCards(hand.getHand(), secondCard, true,false);

        if(pairHand.size() == 2){
            hand.getComparingCards().add(secondCard);
            hand.getComparingCards().add(hand.getHand().get(4));
            hand.getComparingCards().add(hand.getHand().get(3));
            hand.getComparingCards().add(hand.getHand().get(0));
            return true;
        }

        Card thirdCard = hand.getHand().get(2);

        //Compare all cards with the third one
        pairHand = getSameCards(hand.getHand(), thirdCard, true,false);

        if(pairHand.size() == 2) {
            hand.getComparingCards().add(thirdCard);
            hand.getComparingCards().add(hand.getHand().get(4));
            hand.getComparingCards().add(hand.getHand().get(1));
            hand.getComparingCards().add(hand.getHand().get(0));
            return true;
        }

        Card forthCard = hand.getHand().get(3);

        //Compare all cards with the third one
        pairHand = getSameCards(hand.getHand(), forthCard, true,false);

        if(pairHand.size() == 2){
            hand.getComparingCards().add(forthCard);
            hand.getComparingCards().add(hand.getHand().get(2));
            hand.getComparingCards().add(hand.getHand().get(1));
            hand.getComparingCards().add(hand.getHand().get(0));
            return true;
        }
        return false;
    }
}
