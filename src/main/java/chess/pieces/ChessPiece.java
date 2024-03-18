package chess.pieces;

import chess.board.Board;
import chess.moves.Move;

//  Initially made this class as abstract and then realized that it isn't needed ...
public class ChessPiece implements Piece {
    private int boxNo;
    private boolean colour;

    public ChessPiece(int boxNo, boolean colour) {
        this.boxNo = boxNo;
        this.colour = colour;
    }

    public void setBoxNo(int boxNo) {
        this.boxNo = boxNo;
    }
    public int getBoxNo() {
        return boxNo;
    }
    public boolean getColour() {
        return colour;
    }
    public void setColour(boolean colour) {
        this.colour = colour;
    }

    @Override       // TODO
    public void movePiece(Move move, Board board)  {

        if(!move.isValidColourMove()) {
            move.setCompleted(false);
            throw new InvalidMoveException("U can't access opponents piece ...");
        }

    }

    @Override
    public void removePiece(int toBoxNo) {

    }
}


