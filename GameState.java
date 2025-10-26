import java.util.*;

public class GameState {
    private final List<Player> players;
    private final Deque<Card> drawPile;
    private final Deque<Card> discardPile;
    private int currentPlayerIndex;
    private boolean directionClockwise;
    private Color currentColor;
    private Player winner;

    public GameState(List<Player> players, Deque<Card> drawPile, Deque<Card> discardPile) {
        this.players = players;
        this.drawPile = drawPile;
        this.discardPile = discardPile;
        this.currentPlayerIndex = 0;
        this.directionClockwise = true;
        this.currentColor = discardPile.peek().getColor();
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    public List<Player> getPlayers(){
        return players;
    }
    public int getCurrentPlayerIndex(){
        return currentPlayerIndex;
    }
    public void setCurrentPlayerIndex(int index){
        this.currentPlayerIndex = index;
    }
    public Player getWinner(){
        return winner;
    }

    public void nextPlayer(){
        if(directionClockwise){// Move to next player in clockwise direction
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {// Move to next player in counter-clockwise direction
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();// Ensure non-negative index
        }
    }
    public void reverseDirection(){
        directionClockwise = !directionClockwise;
    }
    public void setCurrentColor(Color color){
        this.currentColor = color;
    }
    public Color getCurrentColor(){
        return currentColor;
    }
    public Card getTopCard(){
        return discardPile.peek();
    }
    public void reshuffleIfNeeded(){
        if(drawPile.isEmpty()){
            Card topCard = discardPile.pop();
            List<Card> reshuffleCard = new ArrayList<>(discardPile);
            Collections.shuffle(reshuffleCard);
            drawPile.addAll(reshuffleCard);
            discardPile.clear();
            discardPile.push(topCard);
        }
    }
    public Player getNextPlayerWithoutAdvancing(){
        int nextIndex;
        if(directionClockwise){
            nextIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            nextIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
        return players.get(nextIndex);
    }

    public Card drawOneCard(){
        reshuffleIfNeeded();
        return drawPile.pop();
    }

    public void advancePlayers(int steps){
        for(int i = 0; i < steps; i++){
            nextPlayer();
        }
    }

    public void playCard(Player player, Card card, Color chosenColorIfWild){
        // Validate if player's turn
        if(player != getCurrentPlayer()){
            throw new IllegalMoveException("Not your turn!");
        }
        // Validate if card in hand
        if(!player.getHand().contains(card)){
            throw new IllegalMoveException("You don't have that card!");
        }
        // Validate canBePlayedOn
        Card topCard = getTopCard();
        Color currentColor = getCurrentColor();
        if(!card.canBePlayedOn(topCard, currentColor)){
            throw new IllegalMoveException("Card cannot be played on the current top card!");
        }
        // Move card from hand to discardPile
        player.removeCard(card);
        discardPile.push(card);
        // Update currentColor if wild
        if(card.getColor().isWild() && chosenColorIfWild != null){
            setCurrentColor(chosenColorIfWild);
        } else {
            setCurrentColor(card.getColor());
        }
        // Apply effects (if any)
        int stepsToAdvance = 1;
        if(card instanceof SkipEffect skipEff){
            skipEff.applySkipEffect(this);
            stepsToAdvance = Math.max(stepsToAdvance, 2); // Skip next player
        } 
        if(card instanceof DrawEffect drawEff){
            drawEff.applyDrawEffect(this);
            stepsToAdvance = Math.max(stepsToAdvance, 2); // Skip next player after draw
        }
        if(card instanceof ReverseEffect revEff){
            revEff.applyReverseEffect(this);
            if(players.size() == 2){
                stepsToAdvance = Math.max(stepsToAdvance, 2); // In 2-player game, reverse acts like skip
            }
        }
        // Check win? 
        if(player.getHand().isEmpty()){
            winner = player;
            return;
        }
        // Move to next player
        advancePlayers(stepsToAdvance);
    }

    public void drawInsteadOfPlaying(Player player){
        // Validate if player's turn
        if(player != getCurrentPlayer()){
            throw new IllegalMoveException("Not your turn!");
        }
        // Draw one card
        Card drawnCard = drawOneCard();
        player.drawCard(drawnCard);
        // Move to next player
        advancePlayers(1);;
    }
}
