package chess.generateMoves;

import chess.board.Board;
import chess.game.BitboardGenerator;
import chess.moves.Move;
import chess.pieces.Bitboard;
import chess.pieces.King;
import chess.pieces.Piece;
import chess.resources.AttackMasks;

import java.util.ArrayList;

public class GenerateKingMoves {
    public static ArrayList<Move> generateKingMoves( Board board, boolean blacksTurn) {
        ArrayList<Move> moves = new ArrayList<>();
        Bitboard kingOccBb = blacksTurn ? board.getBlackKing() : board.getWhiteKing();
        long bb = kingOccBb.getBitboard();
        boolean colour = kingOccBb.getColour();
        System.out.println(Long.toHexString(bb));
        // here blockers are the same coloured pieces ...
        long blockers = colour ? board.getBlackOccupancyBitboard().getBitboard() : board.getWhiteOccupancyBitboard().getBitboard();
        int boxNo = Long.numberOfTrailingZeros(bb);
        Move move;
        if (((bb >>> boxNo) & 1) == 1) {
            Piece king = new King(boxNo, colour);
            System.out.println("king box no = " + boxNo);
            long moveMask = AttackMasks.KING_MOVES_MASKS[boxNo];
            System.out.println(Long.toHexString(moveMask));
            long pseudoLegalMoves = moveMask & ~ blockers ;
            int curBoxNo = 0;
            System.out.println(Long.toHexString(pseudoLegalMoves));
            System.out.println(Long.toHexString(blockers));
            while (pseudoLegalMoves != 0) {
                int shift = Long.numberOfTrailingZeros(pseudoLegalMoves);  //
                pseudoLegalMoves = pseudoLegalMoves >>> shift;
                curBoxNo += shift;
                boolean isOccupied = (BitboardGenerator.generateBitboard(curBoxNo/8, curBoxNo%8) & board.getOccupancyBitboard().getBitboard()) == 1;
                if (isOccupied) {
                    move = new Move(king, boxNo, curBoxNo, true, null);

                }
                else {
                    move = new Move(king, boxNo,curBoxNo, false, null);
                }
                moves.add(move);
                //
                pseudoLegalMoves = pseudoLegalMoves >>> 1;
                curBoxNo++;
            }
        }
        else {
            System.out.println("TODO : RAISE EXCEPTION - Bug :( ");
        }

        return moves;
    }

    public static ArrayList<Move> generateKingAttackMoves( Board board, boolean blacksTurn) {
        ArrayList<Move> moves = new ArrayList<>();
        Bitboard kingOccBb = blacksTurn ? board.getBlackKing() : board.getWhiteKing();
        long bb = kingOccBb.getBitboard();
        boolean colour = kingOccBb.getColour();
        System.out.println(Long.toHexString(bb));
        // here blockers are the same coloured pieces ...
        long blockers = colour ? board.getBlackOccupancyBitboard().getBitboard() : board.getWhiteOccupancyBitboard().getBitboard();
        int boxNo = Long.numberOfTrailingZeros(bb);
        Move move;
        if (((bb >>> boxNo) & 1) == 1) {
            Piece king = new King(boxNo, colour);
            System.out.println("king box no = " + boxNo);
            long moveMask = AttackMasks.KING_MOVES_MASKS[boxNo];
            System.out.println(Long.toHexString(moveMask));
            long pseudoLegalMoves = moveMask & ~ blockers ;
            int curBoxNo = 0;
            System.out.println(Long.toHexString(pseudoLegalMoves));
            System.out.println(Long.toHexString(blockers));
            while (pseudoLegalMoves != 0) {
                int shift = Long.numberOfTrailingZeros(pseudoLegalMoves);  //
                pseudoLegalMoves = pseudoLegalMoves >>> shift;
                curBoxNo += shift;
                boolean isOccupied = (BitboardGenerator.generateBitboard(curBoxNo/8, curBoxNo%8) & board.getOccupancyBitboard().getBitboard()) == 1;
                if (isOccupied) {
                    move = new Move(king, boxNo, curBoxNo, true, null);
                    moves.add(move);
                }

                //
                pseudoLegalMoves = pseudoLegalMoves >>> 1;
                curBoxNo++;
            }
        }
        else {
            System.out.println("TODO : RAISE EXCEPTION - Bug :( ");
        }

        return moves;
    }

}