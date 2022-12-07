public class Card {
    public enum CardSuit {
        CLUBS (1),
        DIAMONDS (2),
        HEARTS (3),
        SPADES (4);

        private int value;
        CardSuit(int value) {
            this.value = value;
        }
    }

    public enum CardValue {
        TWO (2),
        THREE (3),
        FOUR (4),
        FIVE (5),
        SIX (6),
        SEVEN (7),
        EIGHT (8),
        NINE (9),
        TEN (10),
        JACK (11),
        QUEEN (12),
        KING (13),
        ACE (14);

        private int value;
        CardValue(int value) {
            this.value = value;
        }
    }

    private CardSuit suit;
    private CardValue value;


    Card (CardSuit suit, CardValue value){
        this.suit = suit;
        this.value = value;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public void setSuit(CardSuit suit) {
        this.suit = suit;
    }

    public CardValue getValue() {
        return value;
    }

    public void setValue(CardValue value) {
        this.value = value;
    }
}
