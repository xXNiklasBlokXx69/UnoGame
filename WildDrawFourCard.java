public class WildDrawFourCard extends Card implements DrawEffect {
    private static final int DRAW_COUNT = 4;

    public WildDrawFourCard() {
        super(Value.WILD_DRAW_FOUR, Color.WILD);
    }

    @Override
    public int getDrawCount() {
        return DRAW_COUNT;
    }

    @Override
    public boolean canBePlayedOn(Card topCard, Color currentColor) {
        // Wild Draw Four cards can be played on any card
        return true;
    }

    @Override 
    public void applyDrawEffect(GameState gameState){
        Player nextPlayer = gameState.getNextPlayerWithoutAdvancing();
        for(int i = 0; i < this.getDrawCount(); i++){
            Card drawnCard = gameState.drawOneCard();
            nextPlayer.drawCard(drawnCard);
        }
    }

    @Override
    public String toString() {
        return "Wild Draw Four Card";
    }

}
