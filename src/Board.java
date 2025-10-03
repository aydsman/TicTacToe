import java.util.Scanner;

public class Board {
    Player p1 = new Player("Ayden", true, 'X');
    Player p2 = new Player("Bob", false, 'O');
    Player empty;
    Player current = returnPlayerTurn(p1, p2, empty); // Current (Pointer to the player who is on their turn).
    char[][] board;

    public Board () {
        this.board = new char[][] {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
    }

    public void clearBoard() { // Clears the board.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[j][i] = ' ';
            }
        }
    }

    public boolean playTurn(Player current, int n) {
        int row, col;

        // Convert input number (1–9) into row/col
        if (n >= 1 && n <= 3) {
            row = n - 1;
            col = 0;
        } else if (n >= 4 && n <= 6) {
            row = n - 4;
            col = 1;
        } else if (n >= 7 && n <= 9) {
            row = n - 7;
            col = 2;
        } else {
            return false; // invalid input
        }

        // ✅ Check before placing
        if (board[row][col] != ' ') {
            return false; // already occupied
        } else {
            board[row][col] = current.shape; // place mark
            return true;
        }
    }


    public void display() { // Prints the board each turn.
        for (int i = 0; i < 3; i++) {
            System.out.print("   -------------------\n" + "   ");
            for (int j = 0; j < 3; j++) {
                System.out.print("|  " + board[j][i] + "  ");
                if (j == 2) System.out.println("|");
            }
        }
        System.out.println("   -------------------");
    }

    public boolean isThereSpace(char [][] b, int n, int m) { // If space key is in array, don't count as a row of 3.
        if (b[n][m] == ' ') return true;
        else return false;
    }

    public boolean checkWin(char[][] b) { // Checks for 3 in a row, returns T, F.

        // Column check
        for (int i = 0; i < 3; i++) {
            if (((b[i][0] == b[i][1]) && (b[i][1] == b[i][2])) && (!isThereSpace(b, i, 0)) && (!isThereSpace(b, i, 1)) && (!isThereSpace(b, i, 2))) {
                //System.out.println("column at: (" + (i+1) + ", 1)" + "(" + (i+1) + ", 2)" + "(" + (i+1) + ", 3)");
                return true;
            }
        }

        // Row check
        for (int i = 0; i < 3; i++) {
            if (((b[0][i] == b[1][i]) && (b[1][i] == b[2][i])) && (!isThereSpace(b, 0, i)) && (!isThereSpace(b, 1, i)) && (!isThereSpace(b, 2, i))) {
                //System.out.println("row at: (1, " + (i+1) + ")" + "(2, " + (i+1) + ")" + "(3, " + (i+1) + ")");
                return true;
            }
        }

        //Diagonal check
        if (((((b[0][0] == b[1][1]) && (b[1][1] == b[2][2]))) && (!isThereSpace(b, 0, 0)) && (!isThereSpace(b, 1, 1)) && (!isThereSpace(b, 2, 2))) || ((b[2][0] == b[1][1]) && (b[1][1] == b[0][2]) && (!isThereSpace(b, 0, 0)) && (!isThereSpace(b, 1, 1)) && (!isThereSpace(b, 2, 2)))) {
            //System.out.println("diagonal");
            return true;
        }

        return false;
    }

    public Player returnPlayerTurn(Player p1, Player p2, Player empty) { // Returns the player if it is his turn.
        Player[] list = {p1, p2};                                        // If it is p1's turn, it returns p1 and makes it p2's turn.

        for (int i = 0; i < 2; i++) {
            if (list[i].myTurn) {
                list[i].myTurn = false;
                empty = list[i];
            }
            else list[i].myTurn = true;
        }
        //System.out.println("p1: " + p1.myTurn + " p2: " + p2.myTurn);
        return empty;
    }

    public void swapTurn(Player current) { // WIP!!!!!
        if (current.myTurn) current.myTurn = false;
        else current.myTurn = true;
    }


    public boolean checkTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isThereSpace(board, i, j)) return false;
            }
        }

        return true;
    }

    public void gameRun(Player current) {
        boolean game = true;
        int input;
        Scanner sc = new Scanner(System.in);

        while (game) {
            display();
            System.out.print(current.name + "'s turn || " + "Enter a number (1-9): ");
            input = sc.nextInt();
            if (!playTurn(current, input)) continue;

            if (checkTie()) {
                display();
                System.out.println("Tie Game.\n");
                game = false;
                clearBoard();
                p1.myTurn = true;
                p2.myTurn = false;
            }

            if (checkWin(board)) {
                current.wins++;
                display();
                System.out.println(current.name + " won! ||" + " Wins: " + current.wins);
                clearBoard();
                game = false;
                p1.myTurn = true;
                p2.myTurn = false;
            }

            current = returnPlayerTurn(p1, p2, empty); // Swap player turn.
        }
    }
}
