public class Player {
    String name;
    int wins;
    boolean myTurn;
    char shape;

    public Player(String n, boolean m, char s) {
        this.name = n;
        this.wins = 0;
        this.myTurn = m;
        this.shape = s;
    }
}
