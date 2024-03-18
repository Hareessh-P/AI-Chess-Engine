package chess.pieces;

import chess.board.Board;
import chess.moves.Move;
import chess.resources.AttackMasks;

public class Knight extends SpecialChessPiece{
    public Knight(int boxNo, boolean colour) {
        super(boxNo, colour);
    }

    @Override
    public void movePiece(Move move, Board board) {
        super.movePiece(move, board);
    }

    @Override
    public boolean isValidPieceMove(int fromBoxNo, int toBoxNo) {
        return Piece.isNthBitSet(AttackMasks.KNIGHT_MOVES_MASKS[fromBoxNo], toBoxNo);
    }

    @Override
    public void placePiece(int boxNo) {
        super.placePiece(boxNo);
        boolean colour = this.getColour();
        Board board = Board.getInstance();

        if(colour) {
            board.setBlackKnightsOccupancyBitboard(boxNo);
        }
        else {
            board.setWhiteKnightsOccupancyBitboard(boxNo);
        }
    }

    @Override
    public void removePiece(int boxNo) {
        super.removePiece(boxNo);
        boolean colour = this.getColour();
        Board board = Board.getInstance();

        if(colour) {
            board.unsetBlackKnightsOccupancyBitboard(boxNo);
        }
        else {
            board.unsetWhiteKnightsOccupancyBitboard(boxNo);
        }
    }
}
