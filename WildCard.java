public class WildCard extends Card {
    public WildCard() {
        super(Value.WILD, Color.WILD);
    }

    @Override
    public boolean canBePlayedOn(Card topCard, Color currentColor) {
        // Wild cards can be played on any card
        return true;
    }
    @Override
    public String toString() {
        return "Wild Card";
    }
}
