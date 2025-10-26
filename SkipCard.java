public class SkipCard extends Card implements SkipEffect {
    public SkipCard(Color color) {
        super(Value.SKIP, color);
    }

    @Override
    public void applySkipEffect(GameState gameState) {
        //Keep empty,as the skipping logic is handled in GameState
    }

    @Override
    public String toString() {
        return "Skip " + getColor();
    }
}
