package chess.pieces;

import chess.board.Board;
import chess.game.InputParser;
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
    public void movePiece(Move move, Board board) throws InputParser.NoPieceException {

        if(!move.isValidColourMove()) {
            move.setCompleted(false);
            throw new InvalidMoveException("U can't access opponents piece ...");
        }

    }

    @Override
    public void removePiece(int toBoxNo, Board board) {
        board.unsetOccupancyBitboard(toBoxNo);
        if(this.getColour()) {
            board.unsetBlackOccupancyBitboard(toBoxNo);
            //      TODO : If attacked implicitly we can un-set the occ bit board of the attacked colour (not attacking...) by just --> whitebb = ~blackbb & whitebb
        }
        else {
            board.unsetWhiteOccupancyBitboard(toBoxNo);
            //      TODO : If attacked implicitly we can un-set the occ bit board of the attacked colour (not attacking...) by just --> blackbb = ~whitebb & blackbb
        }
    }
}


