package chess.pieces;

import chess.board.Board;

public class Queen extends SpecialChessPiece{
    public Queen(int boxNo, boolean colour) {
        super(boxNo, colour);
    }

    @Override
    public boolean isValidPieceMove(int fromBoxNo, int toBoxNo) {
        Bishop bishop = new Bishop(this.getBoxNo(),this.getColour());
        Rook rook = new Rook(this.getBoxNo(),this.getColour());

        return bishop.isValidPieceMove(fromBoxNo,toBoxNo)
                | rook.isValidPieceMove(fromBoxNo, toBoxNo);
    }

    @Override
    public void placePiece(int boxNo) {
        super.placePiece(boxNo);
        boolean colour = this.getColour();
        Board board = Board.getInstance();

        if(colour) {
            board.setBlackQueenOccupancyBitboard(boxNo);
        }
        else {
            board.setWhiteQueenOccupancyBitboard(boxNo);
        }
    }

    @Override
    public void removePiece(int boxNo) {
        super.removePiece(boxNo);
        boolean colour = this.getColour();
        Board board = Board.getInstance();

        if(colour) {
            board.unsetBlackQueenOccupancyBitboard(boxNo);
        }
        else {
            board.unsetWhiteQueenOccupancyBitboard(boxNo);
        }
    }
}
