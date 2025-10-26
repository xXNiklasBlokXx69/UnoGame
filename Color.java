public enum Color {
    RED, BLUE, GREEN, YELLOW, WILD;

    boolean isWild() {
        return this == WILD;
    }
}