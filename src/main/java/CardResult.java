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

    public ResultValue getResult (List<Card> hand){
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

    public void compareResults(ResultValue resultOfFirstHand, ResultValue resultOfSecondHand) {
        if(resultOfFirstHand.equals(ResultValue.HIGH_CARD)){

        }
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

    public boolean isStraightFlush(List<Card> hand){
        Card firstCard = hand.get(0);

        //First check if all cards have the same suit
        Set<Card> collect = getSameCards(hand,firstCard,false, false);

        if (collect.size() == 5){
            //Then check if cards are straight
            if(isStraight(hand))
                return true;
        }

        return false;
    }

    public boolean isFourOfAKind(List<Card> hand){
        Card firstCard = hand.get(0);

        //Compare all cards with the first one
        Set<Card> collect = getSameCards(hand,firstCard,true,false);

        if(collect.size() == 4)
            return true;

        Card secondCard = hand.get(1);
        //Compare all cards with the second one
        //The first one could be different
        collect = getSameCards(hand,secondCard,true,false);

        if(collect.size() == 4)
            return true;

        return false;
    }

    public boolean isFullHouse(List<Card> hand){
        Card firstCard = hand.get(0);

        //Compare all cards with the first one
        Set<Card> firstPartOfFullHouse = getSameCards(hand,firstCard,true,false);

        if(firstPartOfFullHouse.size() == 3){
            //Collect the 2 other cards and check if they are the same
            Set<Card> secondPartOfFullHouse = getSameCards(hand,firstCard,true,true);
            if(secondPartOfFullHouse.size() == 2) {
                List<Card> secondPartOfFullHouseAsList = new ArrayList<>(secondPartOfFullHouse);
                if (secondPartOfFullHouseAsList.get(0).getValue() == secondPartOfFullHouseAsList.get(1).getValue()) {
                    return true;
                }
            }
            return false;
        }

        Card secondCard = hand.get(1);

        //Compare all cards with the second one
        firstPartOfFullHouse = getSameCards(hand,secondCard,true,false);

        if(firstPartOfFullHouse.size() == 3){
            //Collect the 2 other cards and check if they are the same
            Set<Card> secondPartOfFullHouse = getSameCards(hand,secondCard,true,true);
            if(secondPartOfFullHouse.size() == 2) {
                List<Card> secondPartOfFullHouseAsList = new ArrayList<>(secondPartOfFullHouse);
                if (secondPartOfFullHouseAsList.get(0).getValue() == secondPartOfFullHouseAsList.get(1).getValue()) {
                    return true;
                }
            }
            return false;
        }

        Card thirdCard = hand.get(2);

        //Compare all cards with the third one
        firstPartOfFullHouse = getSameCards(hand,thirdCard,true,false);

        if(firstPartOfFullHouse.size() == 3){
            //Collect the 2 other cards and check if they are the same
            Set<Card> secondPartOfFullHouse = getSameCards(hand,thirdCard,true,true);
            if(secondPartOfFullHouse.size() == 2) {
                List<Card> secondPartOfFullHouseAsList = new ArrayList<>(secondPartOfFullHouse);
                if (secondPartOfFullHouseAsList.get(0).getValue() == secondPartOfFullHouseAsList.get(1).getValue()) {
                    return true;
                }
            }
            return false;
        }

        return false;
    }

    public boolean isFlush(List<Card> hand){
        Card firstCard = hand.get(0);

        //First check if all cards have the same suit
        Set<Card> collect = getSameCards(hand,firstCard,false,false);

        if (collect.size() == 5){
                return true;
        }

        return false;
    }

    public boolean isStraight(List<Card> hand){
        //Sort cards
        List<Card> sortedList = hand.stream()
                .sorted(Comparator.comparing(Card::getValue))
                .collect(Collectors.toList());

        //Check if cards are straight
        for(int i = 0; i < 4; i++){
            if(sortedList.get(i+1).getValue().value - sortedList.get(i).getValue().value == 1){
                continue;
            }
            return false;
        }

        return true;
    }

    public boolean isThreeOfAKind(List<Card> hand){
        Card firstCard = hand.get(0);

        //Compare all cards with the first one
        Set<Card> threeOfAKindHand = getSameCards(hand, firstCard, true,false);

        if(threeOfAKindHand.size() == 3)
            return true;

        Card secondCard = hand.get(1);

        //Compare all cards with the second one
        threeOfAKindHand = getSameCards(hand, secondCard, true,false);

        if(threeOfAKindHand.size() == 3)
            return true;

        Card thirdCard = hand.get(2);

        //Compare all cards with the third one
        threeOfAKindHand = getSameCards(hand, thirdCard, true,false);

        if(threeOfAKindHand.size() == 3)
            return true;

        return false;
    }

    public boolean isTwoPairs(List<Card> hand){
        Card firstCard = hand.get(0);

        //Compare all cards with the first one
        Set<Card> firstPartOfTwoPairsHand = getSameCards(hand, firstCard, true,false);

        if(firstPartOfTwoPairsHand.size() == 2){
            //Found one pair. Check the other 3 cards
            Set<Card> secondPartOfTwoPairsHand = getSameCards(hand, firstCard, true,true);
            List<Card> secondPartOfTwoPairsHandAsList = new ArrayList<>(secondPartOfTwoPairsHand);

            Card firstCardOfTheSecondPart = secondPartOfTwoPairsHandAsList.get(0);
            secondPartOfTwoPairsHand = getSameCards(hand, firstCardOfTheSecondPart, true, false);

            //Found the other pair compare to the first card
            if(secondPartOfTwoPairsHand.size() == 2){
                return true;
            }

            Card secondCardOfTheSecondPart = secondPartOfTwoPairsHandAsList.get(1);
            secondPartOfTwoPairsHand = getSameCards(hand, secondCardOfTheSecondPart, true, false);

            //Found the other pair compare to the second card
            if(secondPartOfTwoPairsHand.size() == 2){
                return true;
            }
            return false;
        }

        Card secondCard = hand.get(1);

        //Compare all cards with the second one
        firstPartOfTwoPairsHand = getSameCards(hand, secondCard, true,false);

        if(firstPartOfTwoPairsHand.size() == 2){
            //Found one pair. Check the other 3 cards
            Set<Card> secondPartOfTwoPairsHand = getSameCards(hand, secondCard, true,true);
            List<Card> secondPartOfTwoPairsHandAsList = new ArrayList<>(secondPartOfTwoPairsHand);

            Card firstCardOfTheSecondPart = secondPartOfTwoPairsHandAsList.get(0);
            secondPartOfTwoPairsHand = getSameCards(hand, firstCardOfTheSecondPart, true, false);

            //Found the other pair compare to the first card
            if(secondPartOfTwoPairsHand.size() == 2){
                return true;
            }

            Card secondCardOfTheSecondPart = secondPartOfTwoPairsHandAsList.get(1);
            secondPartOfTwoPairsHand = getSameCards(hand, secondCardOfTheSecondPart, true, false);

            //Found the other pair compare to the second card
            if(secondPartOfTwoPairsHand.size() == 2){
                return true;
            }
            return false;
        }

        return false;
    }

    public boolean isPair(List<Card> hand){
        Card firstCard = hand.get(0);

        //Compare all cards with the first one
        Set<Card> pairHand = getSameCards(hand, firstCard, true,false);

        if(pairHand.size() == 2)
            return true;

        Card secondCard = hand.get(1);

        //Compare all cards with the second one
        pairHand = getSameCards(hand, secondCard, true,false);

        if(pairHand.size() == 2)
            return true;

        Card thirdCard = hand.get(2);

        //Compare all cards with the third one
        pairHand = getSameCards(hand, thirdCard, true,false);

        if(pairHand.size() == 2)
            return true;

        Card forthCard = hand.get(3);

        //Compare all cards with the third one
        pairHand = getSameCards(hand, forthCard, true,false);

        if(pairHand.size() == 2)
            return true;


        return false;
    }
}
