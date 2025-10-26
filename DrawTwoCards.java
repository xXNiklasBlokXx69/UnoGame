public class DrawTwoCards extends Card implements DrawEffect{
    private static final int DRAW_COUNT = 2;

    public DrawTwoCards(Color color) {
        super(Value.DRAW_TWO, color);
    }

    @Override
    public int getDrawCount() {
        return DRAW_COUNT;
    }

    @Override
    public void applyDrawEffect(GameState gameState) {
        // Implement the draw effect logic here
        Player nextPlayer = gameState.getNextPlayerWithoutAdvancing();
        for(int i = 0; i < this.getDrawCount(); i++){
            Card drawnCard = gameState.drawOneCard();
            nextPlayer.drawCard(drawnCard);
        }
    }

    @Override
    public String toString() {
        return "Draw Two " + getColor();
    }
}