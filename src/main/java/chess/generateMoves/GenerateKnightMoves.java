package chess.generateMoves;

import chess.board.Board;
import chess.game.BitboardGenerator;
import chess.moves.Move;
import chess.pieces.Bitboard;
import chess.pieces.Knight;
import chess.pieces.Piece;
import chess.resources.AttackMasks;

import java.util.ArrayList;

public class GenerateKnightMoves {
    public static ArrayList<Move> generateKnightMoves( Board board, boolean blacksTurn) {
        Bitboard knightOccBb = blacksTurn ? board.getBlackKnights() : board.getWhiteKnights();
        boolean colour = knightOccBb.getColour();
        ArrayList<Move> moves = new ArrayList<>();
        long bb = knightOccBb.getBitboard();
        // here blockers are the same coloured pieces ...
        long blockers = colour ? board.getBlackOccupancyBitboard().getBitboard() : board.getWhiteOccupancyBitboard().getBitboard();
        int boxNo = 0;
        Move move;
        while(bb != 0) {
            if ((bb & 1) == 1) {
                Piece knight = new Knight(boxNo, colour);
                long moveMask = AttackMasks.KNIGHT_MOVES_MASKS[boxNo];
                long pseudoLegalMoves = moveMask & ~ blockers ;
                int curBoxNo = 0;
                while (pseudoLegalMoves != 0) {
                    int shift = Long.numberOfTrailingZeros(pseudoLegalMoves);  //
                    pseudoLegalMoves = pseudoLegalMoves >>> shift;
                    curBoxNo += shift;

                    System.out.println("BoxNo = " + boxNo);
                    System.out.println("CurBoxNo = " + curBoxNo);

                    boolean isOccupied = (BitboardGenerator.generateBitboard(curBoxNo/8, curBoxNo%8) & board.getOccupancyBitboard().getBitboard()) == 1;
                    if (isOccupied) {
                        move = new Move(knight, boxNo, curBoxNo, true, null);

                    }
                    else {
                        move = new Move(knight, boxNo,curBoxNo, false, null);
                    }
                    moves.add(move);
                    //
                    pseudoLegalMoves = pseudoLegalMoves >>> 1;
                    curBoxNo++;
                }
            }
            bb = bb >>> 1;
            boxNo++;
        }
        return moves;
    }

    public static ArrayList<Move> generateKnightAttackMoves( Board board, boolean blacksTurn) {
        Bitboard knightOccBb = blacksTurn ? board.getBlackKnights() : board.getWhiteKnights();
        boolean colour = knightOccBb.getColour();
        ArrayList<Move> moves = new ArrayList<>();
        long bb = knightOccBb.getBitboard();
        // here blockers are the same coloured pieces ...
        long blockers = colour ? board.getBlackOccupancyBitboard().getBitboard() : board.getWhiteOccupancyBitboard().getBitboard();
        int boxNo = 0;
        Move move;
        while(bb != 0) {
            if ((bb & 1) == 1) {
                Piece knight = new Knight(boxNo, colour);
                long moveMask = AttackMasks.KNIGHT_MOVES_MASKS[boxNo];
                long pseudoLegalMoves = moveMask & ~ blockers ;
                int curBoxNo = 0;
                while (pseudoLegalMoves != 0) {
                    int shift = Long.numberOfTrailingZeros(pseudoLegalMoves);  //
                    pseudoLegalMoves = pseudoLegalMoves >>> shift;
                    curBoxNo += shift;

                    System.out.println("BoxNo = " + boxNo);
                    System.out.println("CurBoxNo = " + curBoxNo);

                    boolean isOccupied = (BitboardGenerator.generateBitboard(curBoxNo/8, curBoxNo%8) & board.getOccupancyBitboard().getBitboard()) == 1;
                    if (isOccupied) {
                        move = new Move(knight, boxNo, curBoxNo, true, null);
                        moves.add(move);
                    }
                    //
                    pseudoLegalMoves = pseudoLegalMoves >>> 1;
                    curBoxNo++;
                }
            }
            bb = bb >>> 1;
            boxNo++;
        }
        return moves;
    }
}
