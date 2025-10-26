# UnoGame (OOP assignment)

Purpose
-------
Simple Java implementation of core UNO game mechanics .  
Provides deck creation, basic player and game state management, card types with effects (skip, reverse, draw), and a minimal console-based game loop in `Main.java`.

Quick start
-----------
Compile and run from the project folder:
- javac *.java
- java Main

Project structure
-----------------

Classes
- [`Main`](Main.java) — entry point and minimal console-based game loop.
- [`DeckBuilder`](DeckBuilder.java) — builds and shuffles a standard UNO deck into a draw pile.
- [`GameState`](GameState.java) — manages players, draw/discard piles, current player, direction, current color, applying card effects, reshuffling.
- [`Player`](Player.java) — represents a player and their hand; methods to draw/remove cards.
- [`Card`](Card.java) — abstract base class for all cards with common logic to determine playability.
- [`NumberCard`](NumberCard.java) — number (0–9) cards.
- [`SkipCard`](SkipCard.java) — skip action card.
- [`ReverseCard`](ReverseCard.java) — reverse action card.
- [`DrawTwoCards`](DrawTwoCards.java) — draw two action card.
- [`WildCard`](WildCard.java) — wild card (choose color).
- [`WildDrawFourCard`](WildDrawFourCard.java) — wild draw four card.

Interfaces
- [`SkipEffect`](SkipEffect.java) — marker for skip behavior; implemented by `SkipCard`.
- [`ReverseEffect`](ReverseEffect.java) — reverse behavior; implemented by `ReverseCard`.
- [`DrawEffect`](DrawEffect.java) — draw behavior with `getDrawCount()` and `applyDrawEffect()`; implemented by `DrawTwoCards` and `WildDrawFourCard`.

Enums
- [`Color`](Color.java) — card colors (RED, BLUE, GREEN, YELLOW, WILD).
- [`Value`](Value.java) — card values (ZERO–NINE, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR).

Notable files
- [`IllegalMoveException`](IllegalMoveException.java) — runtime exception thrown on invalid moves.
- [`README.md`](README.md) — this file.

Notes
-----
- Effects (skip/draw/reverse) are applied in `GameState.playCard(...)`.  
- `GameState` handles reshuffling the discard pile into the draw pile when empty.  
- The console loop in `Main` expects numeric input for selecting cards or `-1` to draw.
