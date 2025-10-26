import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Create a deck
        Deque<Card> drawPile = DeckBuilder.buildStandardDeck();

        // Create x amount of players
        System.out.println("How many players?");
        int numPlayers = sc.nextInt();
        sc.nextLine(); // consume newline

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            System.out.println("Enter name for Player " + i + ":");
            String name = sc.nextLine();
            players.add(new Player(name));
        }

        // Deal hands
        for (int i = 0; i < 7; i++) {
            for (Player p : players) {
                p.drawCard(drawPile.pop());
            }
        }

        // Start discard pile and ensure it's not a wild card
        Deque<Card> discardPile = new ArrayDeque<>();
        Card firstCard;
        do {
            firstCard = drawPile.pop();
            if (firstCard.getColor().isWild()) {
                drawPile.addLast(firstCard); // move to bottom
            }
        } while (firstCard.getColor().isWild());
        discardPile.push(firstCard);

        // Create game state
        GameState game = new GameState(players, drawPile, discardPile);

        // Run a simple manual loop
        while (game.getWinner() == null) {
            Player current = game.getCurrentPlayer();
            List<Card> hand = current.getHand();
            System.out.println("\nIt's " + current.getName() + "'s turn.");
            System.out.println("Top card: " + game.getTopCard());
            System.out.println("Current color: " + game.getCurrentColor());
            for (int i = 0; i < hand.size(); i++) {
                System.out.println("[" + i + "] " + hand.get(i));
            }

            System.out.println("Enter card index to play, or -1 to draw:");
            int choice = sc.nextInt();

            try {
                if (choice == -1) {
                    game.drawInsteadOfPlaying(current);
                } else {
                    Card chosen = current.getHand().get(choice);

                    Color chosenColor = null;
                    if (chosen.getColor().isWild()) {
                        System.out.println("Choose a color (RED, GREEN, BLUE, YELLOW): ");
                        String colorInput = sc.next().toUpperCase();
                        chosenColor = Color.valueOf(colorInput);
                    }

                    game.playCard(current, chosen, chosenColor);
                }
            } catch (IllegalMoveException e) {
                System.out.println("Illegal move: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\n" + game.getWinner().getName() + " wins the game!");
        sc.close();
    }
}
