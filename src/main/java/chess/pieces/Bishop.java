package chess.pieces;

import chess.board.Board;
import chess.game.BitboardGenerator;
import chess.resources.AttackMasks;

public class Bishop extends SpecialChessPiece{
    public Bishop(int boxNo, boolean colour) {
        super(boxNo, colour);
    }

    @Override
    public boolean isValidPieceMove(int fromBoxNo, int toBoxNo) {

        Board board = Board.getInstance();
        long rookMask = AttackMasks.BISHOP_ATTACK_MASKS[fromBoxNo];
        long occBitboard = board.getOccupancyBitboard().bitboard;

        int fromRow = fromBoxNo / 8;
        int fromCol = fromBoxNo % 8;

        Bitboard blockers = new Bitboard(
                (rookMask & occBitboard) & ~BitboardGenerator.generateBitboard(fromRow, fromCol),
                this.getColour()
        );

        boolean nwBlockerFound = false,
                neBlockerFound = false,
                seBlockerFound = false,
                swBlockerFound = false;

        int i = 1;
        int posRow = (fromRow + i);
        int posCol =  fromCol - i;

        while(isValidPosition(posRow, posCol)) {
            if (blockers.getBitInOccupancyBitboard(fromRow * 8 + i)) {
                nwBlockerFound = true;
            } else if (posRow * 8 + posCol == toBoxNo) {
                return nwBlockerFound ? false : true;
            }
            i++;
            posRow = (fromRow + i);
            posCol = fromCol - i;
        }

        i = 1;
        posRow = (fromRow + i);
        posCol = fromCol + i;

        while(isValidPosition(posRow, posCol)) {
            if (blockers.getBitInOccupancyBitboard(fromRow * 8 + i)) {
                neBlockerFound = true;
            } else if (posRow * 8 + posCol == toBoxNo) {
                return neBlockerFound ? false : true;
            }
            i++;
            posRow = (fromRow + i);
            posCol = fromCol + i;
        }

        i = 1;
        posRow = (fromRow - i);
        posCol = fromCol + i;

        while(isValidPosition(posRow, posCol)) {
            if (blockers.getBitInOccupancyBitboard(fromRow * 8 + i)) {
                seBlockerFound = true;
            } else if (posRow * 8 + posCol == toBoxNo) {
                return seBlockerFound ? false : true;
            }
            i++;
            posRow = (fromRow - i);
            posCol = fromCol - i;
        }

        i = 1;
        posRow = (fromRow - i);
        posCol = fromCol - i;

        while(isValidPosition(posRow, posCol)) {
            if (blockers.getBitInOccupancyBitboard(fromRow * 8 + i)) {
                swBlockerFound = true;
            } else if (posRow * 8 + posCol == toBoxNo) {
                return swBlockerFound ? false : true;
            }
            i++;
            posRow = (fromRow - i);
            posCol = fromCol - i;
        }

        //  coz there no toBoxNo in the range of valid moves of bishop ...
        return false;
    }

    @Override
    public void placePiece(int boxNo) {
        super.placePiece(boxNo);
        boolean colour = this.getColour();
        Board board = Board.getInstance();

        if(colour) {
            board.setBlackBishopOccupancyBitboard(boxNo);
        }
        else {
            board.setWhiteBishopOccupancyBitboard(boxNo);
        }
    }

    @Override
    public void removePiece(int boxNo) {
        super.removePiece(boxNo);
        boolean colour = this.getColour();
        Board board = Board.getInstance();

        if(colour) {
            board.unsetBlackBishopOccupancyBitboard(boxNo);
        }
        else {
            board.unsetWhiteBishopOccupancyBitboard(boxNo);
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >=0 && row < 8 && col >=0 && col < 8;
    }
}

