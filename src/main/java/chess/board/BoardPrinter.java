package chess.board;

public class BoardPrinter {
//    private String[][] displayBoard;

    private final Board board;
    public BoardPrinter(Board board) {
        this.board = board;
    }

    public String getElement(int row, int col) {
        int boxNo = getBoxNumber(row,col);
        return this.board.getPieceDisplayString(boxNo);
    }
    public void setElement(int row, int col, String pieceString) {
        int boxNo = getBoxNumber(row, col);
        this.board.setPieceDisplayString(boxNo, pieceString);
    }
    public void printChessboard(boolean whiteView) {
        // Print the chessboard
        for (int i = 0; i < 8; i++) {
            System.out.println("+------------------------------------+");
            System.out.print("|");
            for (int j = 7; j >= 0; j--) {
                int boxNoWhite = getBoxNumber(7-i, j);
                int boxNoBlack = getBoxNumber(i, j);
                String piece = whiteView ?
                        this.board.getPieceDisplayString(boxNoWhite) : this.board.getPieceDisplayString(boxNoBlack);
                System.out.print(" " + piece + " |");
            }
            System.out.print(" " + (whiteView ? 8 - i : i + 1));
            System.out.println(); // Move to the next row
        }
        System.out.println("+------------------------------------+");
        // Print letters 'a' to 'h' at the bottom

        if(whiteView) System.out.print(" a    b    c   d    e    f    g    h");
        else System.out.print(" A    B    C   D    E    F    G    H");
//        for (char c = (whiteView ? 'a' : 'A'); c <= (whiteView ? 'h' : 'H'); c++) {
//            System.out.print(" " + c + "   ");
//        }
        System.out.println();
    }

    public static int getBoxNumber(int row, int col) {
        return row * 8 + col ;
    }
}
