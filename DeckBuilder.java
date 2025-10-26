import java.util.*;

public class DeckBuilder {
    public static Deque<Card> buildStandardDeck(){
        List<Card> cards = new ArrayList<>();

        //Add colored cards
        for(Color color : new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW}){
            //One 0 card
            cards.add(new NumberCard(color, 0));

            //Two of each 1-9, Skip, Reverse, Draw Two
            for(int i = 1; i <= 9; i++){
                cards.add(new NumberCard(color, i));
                cards.add(new NumberCard(color, i));
            }
            // Two of each action card
            cards.add(new SkipCard(color));
            cards.add(new SkipCard(color));
            cards.add(new ReverseCard(color));
            cards.add(new ReverseCard(color));
            cards.add(new DrawTwoCards(color));
            cards.add(new DrawTwoCards(color));
        }
        //Add wild cards
        for(int i = 0; i < 4; i++){
            cards.add(new WildCard());
            cards.add(new WildDrawFourCard());
        }

        //Shuffle the deck
        Collections.shuffle(cards);
        //Create draw pile
        Deque<Card> drawPile = new ArrayDeque<>(cards);
        return drawPile;
    }
}
