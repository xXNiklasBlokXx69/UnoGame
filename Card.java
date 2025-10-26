public abstract class Card {
    private final Value value;
    private final Color color;

    public Card(Value value, Color color) {
        this.value = value;
        this.color = color;
    }

    public Color getColor(){
        return color;
    }

    public Value getValue(){
        return value;
    }

    public boolean canBePlayedOn(Card topCard, Color currentColor) {
        // A card can be played on color matching the current color, same value, or wild cards
        return this.color == currentColor ||
               this.value == topCard.getValue() ||
               this.color.isWild();
    }
}
