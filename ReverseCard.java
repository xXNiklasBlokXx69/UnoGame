public class ReverseCard extends Card implements ReverseEffect {
    public ReverseCard(Color color) {
        super(Value.REVERSE, color);
    }

    @Override
    public void applyReverseEffect(GameState gameState) {
        gameState.reverseDirection();
    }
    
    @Override
    public String toString() {
        return "Reverse " + getColor();
    }
}
