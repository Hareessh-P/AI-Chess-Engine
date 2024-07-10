package chess.pieces;

import chess.board.Board;
import chess.board.GameBoard;
import chess.game.BitboardGenerator;
import chess.resources.AttackMasks;

public class Bishop extends SpecialChessPiece{
    public Bishop(int boxNo, boolean colour) {
        super(boxNo, colour);
    }

    @Override
    public boolean isValidPieceMove(int fromBoxNo, int toBoxNo, Board board) {

        long rookMask = AttackMasks.BISHOP_ATTACK_MASKS[fromBoxNo];
        long occBitboard = board.getOccupancyBitboard().bitboard;

        int fromRow = fromBoxNo / 8;
        int fromCol = fromBoxNo % 8;

        Bitboard blockers = new Bitboard(
                (rookMask & occBitboard) & ~BitboardGenerator.generateBitboard(fromRow, fromCol),
                this.getColour()
        );
        System.out.println("blockers (bishop) : " + Long.toHexString(blockers.getBitboard()));
        boolean nwBlockerFound = false,
                neBlockerFound = false,
                seBlockerFound = false,
                swBlockerFound = false;

        int i = 1;
        int posRow = (fromRow + i);
        int posCol =  fromCol + i;

        while(isValidPosition(posRow, posCol)) {
            if (blockers.getBitInOccupancyBitboard(posRow * 8 + posCol)) {
                nwBlockerFound = true;
            } else if (posRow * 8 + posCol == toBoxNo) {
                return nwBlockerFound ? false : true;
            }
            i++;
            posRow = (fromRow + i);
            posCol = fromCol + i;
        }
        System.out.println("no nw blocker");

        i = 1;
        posRow = (fromRow + i);
        posCol = fromCol - i;

        while(isValidPosition(posRow, posCol)) {
            if (blockers.getBitInOccupancyBitboard(posRow * 8 + posCol)) {
                neBlockerFound = true;
            } else if (posRow * 8 + posCol == toBoxNo) {
                return neBlockerFound ? false : true;
            }
            i++;
            posRow = (fromRow + i);
            posCol = fromCol - i;
        }
        System.out.println("no ne blocker");

        i = 1;
        posRow = (fromRow - i);
        posCol = fromCol - i;

        while(isValidPosition(posRow, posCol)) {
            if (blockers.getBitInOccupancyBitboard(posRow * 8 + posCol)) {
                seBlockerFound = true;
            } else if (posRow * 8 + posCol == toBoxNo) {
                return seBlockerFound ? false : true;
            }
            i++;
            posRow = (fromRow - i);
            posCol = fromCol - i;
        }
        System.out.println("no se blocker");

        i = 1;
        posRow = (fromRow - i);
        posCol = fromCol + i;

        while(isValidPosition(posRow, posCol)) {
            if (blockers.getBitInOccupancyBitboard(posRow * 8 + posCol)) {
                swBlockerFound = true;
            } else if (posRow * 8 + posCol == toBoxNo) {
                return swBlockerFound ? false : true;
            }
            i++;
            posRow = (fromRow - i);
            posCol = fromCol + i;
        }
        System.out.println("no sw blocker");
        //  coz there no toBoxNo in the range of valid moves of bishop ...
        return false;
    }

    @Override
    public void placePiece(int boxNo, Board board) {
        super.placePiece(boxNo, board);
        boolean colour = this.getColour();

        if(colour) {
            board.setBlackBishopOccupancyBitboard(boxNo);
        }
        else {
            board.setWhiteBishopOccupancyBitboard(boxNo);
        }
    }

    @Override
    public void removePiece(int boxNo, Board board) {
        super.removePiece(boxNo, board);
        boolean colour = this.getColour();

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

