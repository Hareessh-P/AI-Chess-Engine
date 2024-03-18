package chess.pieces;

import chess.board.Board;
import chess.game.BitboardGenerator;
import chess.resources.AttackMasks;

public class Rook extends SpecialChessPiece{
    public Rook(int boxNo, boolean fromColour) {
        super(boxNo, fromColour);
    }

    @Override
    public boolean isValidPieceMove(int fromBoxNo, int toBoxNo) {

        Board board = Board.getInstance();
        long rookMask = AttackMasks.ROOK_ATTACK_MASKS[fromBoxNo];
        long occBitboard = board.getOccupancyBitboard().bitboard;

        int fromRow = fromBoxNo / 8;
        int fromCol = fromBoxNo % 8;

        Bitboard blockers = new Bitboard(
                (rookMask & occBitboard) & ~BitboardGenerator.generateBitboard(fromRow, fromCol),
                this.getColour()
        );

        boolean leftBlockerFound = false,
                rightBlockerFound = false,
                upBlockerFound = false,
                downBlockerFound = false;

        for(int i = fromCol-1; i >= 0 ; i--) {
            // const --> fromRow
            if(blockers.getBitInOccupancyBitboard(fromRow*8 + i)){
                leftBlockerFound = true;
            }
            else if (fromRow*8 + i == toBoxNo) {
                return leftBlockerFound ? false : true;
            }
        }
        for(int i = fromCol+1; i < 8 ; i++) {
            // const --> fromRow
            if(blockers.getBitInOccupancyBitboard(fromRow*8 + i)){
                rightBlockerFound = true;
            }
            else if (fromRow*8 + i == toBoxNo) {
                return rightBlockerFound ? false : true;
            }
        }
        for(int i = fromRow-1; i >= 0 ; i--) {
            // const --> fromCol
            if(blockers.getBitInOccupancyBitboard(i*8 + fromCol)){
                downBlockerFound = true;
            }
            else if (i*8 + fromCol == toBoxNo) {
                return downBlockerFound ? false : true;
            }
        }
        for(int i = fromRow+1; i < 8 ; i++) {
            // const --> fromCol
            if(blockers.getBitInOccupancyBitboard(i*8 + fromCol)){
                upBlockerFound = true;
            }
            else if (i*8 + fromCol == toBoxNo) {
                return upBlockerFound ? false : true;
            }
        }

        return false;
    }

    @Override
    public void placePiece(int boxNo) {
        super.placePiece(boxNo);
        boolean colour = this.getColour();
        Board board = Board.getInstance();

        if(colour) {
            board.setBlackRookOccupancyBitboard(boxNo);
        }
        else {
            board.setWhiteRookOccupancyBitboard(boxNo);
        }
    }

    @Override
    public void removePiece(int boxNo) {
        super.removePiece(boxNo);
        boolean colour = this.getColour();
        Board board = Board.getInstance();

        if(colour) {
            board.unsetBlackRookOccupancyBitboard(boxNo);
        }
        else {
            board.unsetWhiteRookOccupancyBitboard(boxNo);
        }
    }
}
