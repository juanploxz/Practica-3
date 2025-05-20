public class Turn {
    private final int number;
    private final Move white;
    private final Move black;

    public Turn(int number, Move white, Move black) {
        this.number = number;
        this.white = white;
        this.black = black;
    }

    public int number() { return number; }
    public Move white() { return white; }
    public Move black() { return black; }
}