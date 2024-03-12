package chess.board;

public class BoardPrinter {
    private String[][] board;

    public BoardPrinter() {
        double spacing = 3;
//        String space = String.format("%" + spacing + "s", "");
        String space = "\u25AD";
        board = new String[][] {
                {"♜", "♞", "♝", "♛", "♚", "♝", "♞", "♜"},
                {"♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟"},
                {space, space, space, space, space, space, space, space},
                {space, space, space, space, space, space, space, space},
                {space, space, space, space, space, space, space, space},
                {space, space, space, space, space, space, space, space},
                {"♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙"},
                {"♖", "♘", "♗", "♕", "♔", "♗", "♘", "♖"}
        };
    }

    public String getElement(int row, int col) {
        return board[row][col];
    }
    public void setElement(int row, int col, String value) {
        board[row][col] = value;
    }
    public void printChessboard(boolean whiteView) {
        // Print the chessboard
        for (int i = 0; i < 8; i++) {

            System.out.println("+------------------------------------+");
            System.out.print("|");
            for (int j = 0; j < 8; j++) {
                String piece = whiteView ? this.board[i][j] : this.board[7 - i][7 - j];
                System.out.print(" " + piece + " |");
            }
            System.out.print(" " + (whiteView ? 8 - i : i + 1));
            System.out.println(); // Move to the next row
        }
        System.out.println("+------------------------------------+");
        // Print letters 'a' to 'h' at the bottom

        if(whiteView) System.out.print(" a    b    c   d    e    f    g    h");
        else System.out.print(" H    G    F   E    D    C    B    A");
//        for (char c = (whiteView ? 'a' : 'A'); c <= (whiteView ? 'h' : 'H'); c++) {
//            System.out.print(" " + c + "   ");
//        }
        System.out.println();
    }
}
