package chess.generateMoves;

import chess.board.Board;
import chess.moves.Move;
import chess.pieces.Rook;
import chess.pieces.Bitboard;
import chess.pieces.Piece;
import chess.resources.AttackMasks;

import java.util.ArrayList;

public class GenerateRookMoves {
    public static ArrayList<Move> generateRookMoves( Board board, boolean blacksTurn, boolean isQueen) {
        Bitboard rookOccBb;
        if (isQueen) {
            rookOccBb = blacksTurn ? board.getBlackQueen() : board.getWhiteQueen();
        }
        else {
            rookOccBb = blacksTurn ? board.getBlackRooks() : board.getWhiteRooks();
        }
        ArrayList<Move> moves = new ArrayList<>();
        long bb = rookOccBb.getBitboard();
        int boxNo = 0;
        boolean colour = rookOccBb.getColour();
//        Piece rook = new Rook(boxNo, colour);
        while (bb != 0) {
            int count = Long.numberOfTrailingZeros(bb); // Count of trailing zeros
            bb >>>= count; // Shift bb to the right by count bits
            boxNo += count; // Increment boxNo by count

            if ((bb & 1) == 1) {
                long mask = AttackMasks.ROOK_ATTACK_MASKS[boxNo];
                long blockers = board.getOccupancyBitboard().getBitboard() & mask;
                Piece rook = new Rook(boxNo, colour);
                ArrayList<Integer> attackBoxNos = getAttackBoxNos(blockers, boxNo);
                for (int i = 0; i < 4 ; i++) {
                    Move move;
                    int toBoxNo = attackBoxNos.get(i);
                    boolean toColour = ((board.getBlackOccupancyBitboard().getBitboard() >>> toBoxNo) & 1) == 1;
                    if (toBoxNo != -1 & colour != toColour) {
                        move = new Move(rook, boxNo, toBoxNo, true, null);
                        moves.add(move);
                    }
                }
                moves.addAll(generateNormalMoves(rook, attackBoxNos, boxNo));
            }
            bb = bb >>> 1; // Shift the number to the right by 1 bit
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

        // Iterate downwards in the column
        for (int i = row - 1; i >= -1; i--) {
            int curBoxNo = i * 8 + col;

            if (col < 0 || col > 7 || i < 0 || i > 7 ) {
                attackBoxNos.add(-1);
                break;
            }
            else if (((blockers >>> curBoxNo) & 1) == 1) {
                attackBoxNos.add(curBoxNo);
                break;
            }
        }

        // Iterate upwards in the column
        for (int i = row + 1; i <= boardSize; i++) {
            int curBoxNo = i * 8 + col;

            if (col < 0 || col > 7 || i < 0 || i > 7 ) {
                attackBoxNos.add(-1);
                break;
            }
            else if (((blockers >>> curBoxNo) & 1) == 1) {
                attackBoxNos.add(curBoxNo);
                break;
            }
        }

        // Iterate right in the row
        for (int i = col - 1; i >= -1; i--) {
            int curBoxNo = row * 8 + i;

            if (row > 7 || row < 0 || i > 7 || i < 0 ) {
                attackBoxNos.add(-1);
                break;
            }
            else if (((blockers >>> curBoxNo) & 1) == 1) {
                attackBoxNos.add(curBoxNo);
                break;
            }
        }

        // Iterate left in the row
        for (int i = col + 1; i <= boardSize; i++) {
            int curBoxNo = row * 8 + i;

            if (row > 7 || row < 0 || i > 7 || i < 0  ) {
                attackBoxNos.add(-1);
                break;
            }
            else if (((blockers >>> curBoxNo) & 1) == 1) {
                attackBoxNos.add(curBoxNo);
                break;
            }
        }

        return attackBoxNos;
    }

    static ArrayList<Move> generateNormalMoves(Piece rook, ArrayList<Integer> attackBoxNos, int boxNo) {
        ArrayList<Move> moves = new ArrayList<>();
        int boardSize = 8; // Size of the chessboard

        // Convert box number to row and column indices
        int row = boxNo / boardSize; // Integer division to get row index
        int col = boxNo % boardSize; // Modulus operation to get column index
        Move move;

        // Iterate downwards in the column
        for (int i = row - 1; i >= 0 && attackBoxNos.get(0) != -1 ; i--) {
            int curBoxNo = i * 8 + col;
            if (curBoxNo == attackBoxNos.get(0)) {
                break;
            }
            move = new Move(rook, boxNo, curBoxNo, false, null);
            moves.add(move);
        }

        // Iterate upwards in the column
        for (int i = row + 1; i < boardSize && attackBoxNos.get(1) != -1 ; i++) {
            int curBoxNo = i * 8 + col;
            if (curBoxNo == attackBoxNos.get(1)) {
                break;
            }
            move = new Move(rook, boxNo, curBoxNo, false, null);
            moves.add(move);
        }

        // Iterate right in the row
        for (int i = col - 1; i >= 0 && attackBoxNos.get(2) != -1 ; i--) {
            int curBoxNo = row * 8 + i;
            if (curBoxNo == attackBoxNos.get(2)) {
                break;
            }
            move = new Move(rook, boxNo, curBoxNo, false, null);
            moves.add(move);
        }

        // Iterate left in the row
        for (int i = col + 1; i < boardSize && attackBoxNos.get(3) != -1 ; i++) {
            int curBoxNo = row * 8 + i;
            if (curBoxNo == attackBoxNos.get(3)) {
                break;
            }
            move = new Move(rook, boxNo, curBoxNo, false, null);
            moves.add(move);
        }

        return moves;
    }

}