public class NumberCard extends Card{
    private final int number;
    
    public NumberCard(Color color, int number) {
        super(Value.values()[number], color);
        this.number = number;
    }
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return getColor() + " " + number;
    }
}
