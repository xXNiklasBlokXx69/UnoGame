import java.util.*;

public class Player {
    private final String name;
    private final List<Card> hand;
    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public List<Card> getHand(){
        return hand;
    }
    public void drawCard(Card card){
        hand.add(card);
    }
    public void removeCard(Card card){
        hand.remove(card);
    }
}
