package chess.generateMoves;

import chess.board.Board;
import chess.moves.Move;
import chess.pieces.Bishop;
import chess.pieces.Bitboard;
import chess.pieces.Piece;
import chess.resources.AttackMasks;

import java.util.ArrayList;

public class GenerateBishopMoves {
    public static ArrayList<Move> generateBishopMoves( Board board, boolean blacksTurn, boolean isQueen) {
        ArrayList<Move> moves = new ArrayList<>();
        Bitboard bishopOccBb;
        if (isQueen) {
            bishopOccBb = blacksTurn ? board.getBlackQueen() : board.getWhiteQueen();
        }
        else {
            bishopOccBb = blacksTurn ? board.getBlackBishops() : board.getWhiteBishops();
        }
        long bb = bishopOccBb.getBitboard();
        boolean colour = bishopOccBb.getColour();
        int boxNo = 0;
//        Piece bishop = new Bishop(boxNo, colour);
        while (bb != 0) {
            int count = Long.numberOfTrailingZeros(bb); // Count of trailing zeros
            bb >>>= count; // Shift bb to the right by count bits
            boxNo += count; // Increment boxNo by count

            if ((bb & 1) == 1) {
                Piece bishop = new Bishop(boxNo, colour);
                long mask = AttackMasks.BISHOP_ATTACK_MASKS[boxNo];
                long blockers = board.getOccupancyBitboard().getBitboard() & mask;
                ArrayList<Integer> attackBoxNos = getAttackBoxNos(blockers, boxNo);
                //      Appending Attack moves
                for (int i = 0; i < 4 ; i++) {
                    Move move;
                    int toBoxNo = attackBoxNos.get(i);
                    boolean toColour = ((board.getBlackOccupancyBitboard().getBitboard() >>> toBoxNo) & 1) == 1;
                    if (toBoxNo != -1 & colour != toColour) {
                        move = new Move(bishop, boxNo, toBoxNo, true, null);
                        moves.add(move);
                    }
                }
                moves.addAll(generateNormalMoves(bishop, attackBoxNos, boxNo));
            }
            bb >>>= 1; // Shift the number to the right by 1 bit
            boxNo++;
        }
        return moves;
    }

    public static ArrayList<Move> generateBishopAttackMoves( Board board, boolean blacksTurn, boolean isQueen) {
        ArrayList<Move> moves = new ArrayList<>();
        Bitboard bishopOccBb;
        if (isQueen) {
            bishopOccBb = blacksTurn ? board.getBlackQueen() : board.getWhiteQueen();
        }
        else {
            bishopOccBb = blacksTurn ? board.getBlackBishops() : board.getWhiteBishops();
        }
        long bb = bishopOccBb.getBitboard();
        boolean colour = bishopOccBb.getColour();
        int boxNo = 0;
//        Piece bishop = new Bishop(boxNo, colour);
        while (bb != 0) {
            int count = Long.numberOfTrailingZeros(bb); // Count of trailing zeros
            bb >>>= count; // Shift bb to the right by count bits
            boxNo += count; // Increment boxNo by count

            if ((bb & 1) == 1) {
                Piece bishop = new Bishop(boxNo, colour);
                long mask = AttackMasks.BISHOP_ATTACK_MASKS[boxNo];
                long blockers = board.getOccupancyBitboard().getBitboard() & mask;
                ArrayList<Integer> attackBoxNos = getAttackBoxNos(blockers, boxNo);
                //      Appending Attack moves
                for (int i = 0; i < 4 ; i++) {
                    Move move;
                    int toBoxNo = attackBoxNos.get(i);
                    boolean toColour = ((board.getBlackOccupancyBitboard().getBitboard() >>> toBoxNo) & 1) == 1;
                    if (toBoxNo != -1 & colour != toColour) {
                        move = new Move(bishop, boxNo, toBoxNo, true, null);
                        moves.add(move);
                    }
                }
//                moves.addAll(generateNormalMoves(bishop, attackBoxNos, boxNo));
            }
            bb >>>= 1; // Shift the number to the right by 1 bit
            boxNo++;
        }
        return moves;
    }

    static ArrayList<Integer> getAttackBoxNos(long blockers, int boxNo) {
        ArrayList<Integer> attackBoxNos = new ArrayList<>();
        int boardSize = 8; // Size of the chessboard

        // Convert box number to row and column indices
        int row = boxNo / boardSize; // Integer division to get row index
        int col = boxNo % boardSize; // Modulus operation to get column index

        // Iterate diagonally downwards and to the left
        for (int i = 1; row - i >= -1 && col + i <= boardSize; i++) {
            // Process position (row - i, col + i)
            int curBoxNo = (row- i) * 8 + (col + i);

            if (curBoxNo / 8 >= 7 || curBoxNo % 8 >= 7 || curBoxNo / 8 <= 0 || curBoxNo % 8 <= 0) {
                attackBoxNos.add(-1);
                break;
            }
            else if (((blockers >>> curBoxNo) & 1) == 1) {
                attackBoxNos.add(curBoxNo);
                break;
            }
        }

        // Iterate diagonally downwards and to the right
        for (int i = 1; row - i >= -1 && col - i >= -1; i++) {
            // Process position (row - i, col - i)
            int curBoxNo = (row - i) * 8 + (col - i);

            if (curBoxNo / 8 >= 7 || curBoxNo % 8 >= 7 || curBoxNo / 8 <= 0 || curBoxNo % 8 <= 0) {
                attackBoxNos.add(-1);
                break;
            }
            else if (((blockers >>> curBoxNo) & 1) == 1) {
                attackBoxNos.add(curBoxNo);
                break;
            }
        }

        // Iterate diagonally upwards and to the left
        for (int i = 1; row + i <= boardSize && col + i <= boardSize; i++) {
            // Process position (row + i, col + i)
            int curBoxNo = (row + i) * 8 + (col + i);

            if (((blockers >>> curBoxNo) & 1) == 1) {
                attackBoxNos.add(curBoxNo);
                break;
            }
            else if (curBoxNo / 8 >= 7 || curBoxNo % 8 >= 7 || curBoxNo / 8 <= 0 || curBoxNo % 8 <= 0) {
                attackBoxNos.add(-1);
            }
        }

        // Iterate diagonally upwards and to the right
        for (int i = 1; row + i <= boardSize && col - i >= -1; i++) {
            // Process position (row + i, col - i)
            int curBoxNo = (row + i) * 8 + (col - i);

            if (curBoxNo / 8 >= 7 || curBoxNo % 8 >= 7 || curBoxNo / 8 <= 0 || curBoxNo % 8 <= 0) {
                attackBoxNos.add(-1);
            }
            else if (((blockers >>> curBoxNo) & 1) == 1) {
                attackBoxNos.add(curBoxNo);
                break;
            }
        }

        return attackBoxNos;
    }

    static ArrayList<Move> generateNormalMoves(Piece bishop, ArrayList<Integer> attackBoxNos, int boxNo) {
        ArrayList<Move> moves = new ArrayList<>();
        int boardSize = 8; // Size of the chessboard

        // Convert box number to row and column indices
        int row = boxNo / boardSize; // Integer division to get row index
        int col = boxNo % boardSize; // Modulus operation to get column index
        Move move;
        // Iterate diagonally upwards and to the right
        for (int i = 1; row - i >= 0 && col + i < boardSize; i++) {
            // Process position (row - i, col + i)
            int curBoxNo = (row - i) * 8 + (col + i);
            if (curBoxNo == attackBoxNos.get(0)) {
                break;
            }
            move = new Move(bishop, boxNo, curBoxNo, false, null);
            moves.add(move);
        }

        // Iterate diagonally upwards and to the left
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            // Process position (row - i, col - i)
            int curBoxNo = (row - i) * 8 + (col - i);
            if (curBoxNo == attackBoxNos.get(1)) {
                break;
            }
            move = new Move(bishop, boxNo, curBoxNo, false, null);
            moves.add(move);
        }

        // Iterate diagonally downwards and to the right
        for (int i = 1; row + i < boardSize && col + i < boardSize; i++) {
            // Process position (row + i, col + i)
            int curBoxNo = (row + i) * 8 + (col + i);
            if (curBoxNo == attackBoxNos.get(2)) {
                break;
            }
            move = new Move(bishop, boxNo, curBoxNo, false, null);
            moves.add(move);
        }

        // Iterate diagonally downwards and to the left
        for (int i = 1; row + i < boardSize && col - i >= 0; i++) {
            // Process position (row + i, col - i)
            int curBoxNo = (row + i) * 8 + (col - i);
            if (curBoxNo == attackBoxNos.get(3)) {
                break;
            }
            move = new Move(bishop, boxNo, curBoxNo, false, null);
            moves.add(move);
        }
        return moves;
    }
}

