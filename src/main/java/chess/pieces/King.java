package chess.pieces;

import chess.board.Board;
import chess.game.InputParser;
import chess.moves.Move;
import chess.resources.AttackMasks;

public class King extends SpecialChessPiece{
    public King(int boxNo, boolean colour) {
        super(boxNo, colour);
    }

    @Override
    public void movePiece(Move move, Board board) throws InputParser.NoPieceException {
        super.movePiece(move, board);
    }

    @Override
    public boolean isValidPieceMove(int fromBoxNo, int toBoxNo, Board board) {
        return Piece.isNthBitSet(AttackMasks.KING_MOVES_MASKS[fromBoxNo], toBoxNo);
    }

    @Override
    public void placePiece(int boxNo, Board board) {
        super.placePiece(boxNo, board);
        boolean colour = this.getColour();

        if(colour) {
            board.setBlackKingOccupancyBitboard(boxNo);
        }
        else {
            board.setWhiteKingOccupancyBitboard(boxNo);
        }
    }
}
