package chess.pieces;

import chess.board.Board;

public class Queen extends SpecialChessPiece{
    public Queen(int boxNo, boolean colour) {
        super(boxNo, colour);
    }

    @Override
    public boolean isValidPieceMove(int fromBoxNo, int toBoxNo, Board board) {
        Bishop bishop = new Bishop(this.getBoxNo(),this.getColour());
        Rook rook = new Rook(this.getBoxNo(),this.getColour());

        return bishop.isValidPieceMove(fromBoxNo,toBoxNo,board)
                | rook.isValidPieceMove(fromBoxNo, toBoxNo, board);
    }

    @Override
    public void placePiece(int boxNo, Board board) {
        super.placePiece(boxNo, board);
        boolean colour = this.getColour();

        if(colour) {
            board.setBlackQueenOccupancyBitboard(boxNo);
        }
        else {
            board.setWhiteQueenOccupancyBitboard(boxNo);
        }
    }

    @Override
    public void removePiece(int boxNo, Board board) {
        super.removePiece(boxNo, board);
        boolean colour = this.getColour();

        if(colour) {
            board.unsetBlackQueenOccupancyBitboard(boxNo);
        }
        else {
            board.unsetWhiteQueenOccupancyBitboard(boxNo);
        }
    }
}
