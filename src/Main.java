import java.util.Scanner;

public class Main {


    static void help() {
        System.out.println("\n\n\n\n\n\n\nThis is a game of Tic-Tac-Toe.\nTo win, you need to get 3 in a row before your opponent.\nThe board is set like this:\n1  2  3\n4  5  6\n7  8  9\nThe number you input puts your 'shape' (X or O) into the number slot you chose.");
    }

    public static void main(String[] args) {
        Board board = new Board();
        boolean loop = true;
        char input;
        Scanner sc = new Scanner(System.in);

        while (loop) {
            System.out.println("What do you want to do:\n1) Play (p)\n2) How do I play? (h)\n3) Exit (e)");
            input = sc.next().charAt(0);

            if (input == 'p') board.gameRun(board.current);
            else if (input == 'h') help();
            else if (input == 'e') loop = false;
            else System.out.println("Invalid key, please try again.");
        }
    }
}