package chess.pieces;

import chess.board.Board;
import chess.moves.Move;

public interface Piece {
//    Bitboard bbPosition = null;





    // TODO
    void movePiece(Move move, Board board) ;

    boolean getColour();

    static boolean isNthBitSet(long num, int n) {
        // Shift 1 to the left by n positions and perform bitwise AND with num
        // If the result is non-zero, then the nth bit is set
        return (num & (1L << n)) != 0;
    }

    void removePiece(int toBoxNo);

    public class InvalidMoveException extends RuntimeException {
        public InvalidMoveException(String message) {
            super(message);
        }
    }

}



