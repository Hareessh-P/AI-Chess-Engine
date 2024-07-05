package chess.generateMoves;

import chess.board.Board;
import chess.game.BitboardGenerator;
import chess.moves.Move;
import chess.pieces.Bitboard;
import chess.pieces.Pawn;
import chess.resources.AttackMasks;

import javax.imageio.stream.ImageOutputStream;
import java.util.ArrayList;

public class GeneratePawnMoves {
    public static ArrayList<Move> generatePawnMoves( Board board, boolean blacksTurn) {    //  <---- BOARD :___
        Bitboard pawnOccBb = blacksTurn ? board.getBlackPawns() : board.getWhitePawns();
        long bb = pawnOccBb.getBitboard();
        boolean colour = pawnOccBb.getColour();
        int boxNo = 0;
        ArrayList<Move> moves = new ArrayList<>();
        while (bb != 0) {
            if ((bb & 1) == 1) {

                if (colour) {
                    //  black pawn
                    Move move;
                    Pawn p = new Pawn(boxNo, colour);
                    long blockers = board.getOccupancyBitboard().getBitboard();
                    long normalMoveMask = AttackMasks.getPawnNormalMoves(colour, boxNo);
                    long possibleNormalMoves  = ~ blockers & normalMoveMask;
                    int shift = Long.numberOfTrailingZeros(possibleNormalMoves);
                    if (shift > 63) {
                        break;
                    }
                    move = new Move(p,boxNo, shift, false, null);
                    moves.add(move);

                    possibleNormalMoves = possibleNormalMoves >>> shift+1;
                    if (possibleNormalMoves != 0) {
                        shift += Long.numberOfTrailingZeros(possibleNormalMoves) + 1;
                        move = new Move(p,boxNo, shift, false, null);
                        moves.add(move);
                    }

                    long possibleAttackMoves = AttackMasks.getPawnAttackMoves(colour,boxNo) &
                            (colour ?
                                    board.getWhiteOccupancyBitboard().getBitboard()
                                    :
                                    board.getBlackOccupancyBitboard().getBitboard()
                            );
                    System.out.println("possible normal moves = " + Long.toHexString(possibleNormalMoves) );
                    ArrayList<Integer> attackMoveBoxes = getSetBitsIndices(possibleAttackMoves);
                    //  Piece Captured not taken care here ...
                    for (Integer toBoxNo : attackMoveBoxes) {
                        if ((blockers >>> toBoxNo & 1) == 0 ){
                            move = new Move(p, boxNo, toBoxNo, true, null);
                            moves.add(move);
                        }
                    }

                }
                else {
                    Move move;
                    Pawn p = new Pawn(boxNo, colour);
                    long blockers = board.getOccupancyBitboard().getBitboard();
                    long normalMoveMask = AttackMasks.getPawnNormalMoves(colour, boxNo);
                    long possibleNormalMoves  = ~ blockers & normalMoveMask;

                    int shift = Long.numberOfTrailingZeros(possibleNormalMoves);
                    move = new Move(p,boxNo, shift, false, null);
                    moves.add(move);

                    possibleNormalMoves = possibleNormalMoves >>> shift+1 ;
                    if (possibleNormalMoves != 0) {
                        shift += Long.numberOfTrailingZeros(possibleNormalMoves) + 1;
                        move = new Move(p,boxNo, shift, false, null);
                        moves.add(move);
                    }

                    long possibleAttackMoves = AttackMasks.getPawnAttackMoves(colour,boxNo) & (
                            colour ? board.getWhiteOccupancyBitboard().getBitboard()
                                    :
                                    board.getBlackOccupancyBitboard().getBitboard()
                            );
                    ArrayList<Integer> attackMoveBoxes = getSetBitsIndices(possibleAttackMoves);
                    //  Piece Captured not taken care here ...
                    for (Integer toBoxNo : attackMoveBoxes) {
                        move = new Move(p, boxNo, toBoxNo, true, null);
                        moves.add(move);
                    }
                }
            }
            bb = bb >>> 1; // Shift the number to the right by 1 bit
            boxNo++;
        }
        return moves;
    }

    public static ArrayList<Move> generatePawnAttackMoves( Board board, boolean blacksTurn) {    //  <---- BOARD :___
        Bitboard pawnOccBb = blacksTurn ? board.getBlackPawns() : board.getWhitePawns();
        long bb = pawnOccBb.getBitboard();
        boolean colour = pawnOccBb.getColour();
        int boxNo = 0;
        ArrayList<Move> moves = new ArrayList<>();
        while (bb != 0) {
            boxNo += Long.numberOfTrailingZeros(bb);
            bb >>>= Long.numberOfTrailingZeros(bb);
            if ((bb & 1) == 1) {
                if (colour) {
                    //  black pawn
                    Move move;
                    Pawn p = new Pawn(boxNo, colour);
                    long possibleAttackMoves = AttackMasks.getPawnAttackMoves(colour,boxNo) &
                            (colour ?
                                    board.getWhiteOccupancyBitboard().getBitboard()
                                    :
                                    board.getBlackOccupancyBitboard().getBitboard()
                            );
//                    System.out.println("possible normal moves = " + Long.toHexString(possibleNormalMoves) );
                    ArrayList<Integer> attackMoveBoxes = getSetBitsIndices(possibleAttackMoves);
                    //  Piece Captured not taken care here ...
                    for (Integer toBoxNo : attackMoveBoxes) {
                        move = new Move(p, boxNo, toBoxNo, true, null);
                        moves.add(move);

                    }

                }
                else {
                    Move move;
                    Pawn p = new Pawn(boxNo, colour);

                    long possibleAttackMoves = AttackMasks.getPawnAttackMoves(colour,boxNo) & (
                            colour ? board.getWhiteOccupancyBitboard().getBitboard()
                                    :
                                    board.getBlackOccupancyBitboard().getBitboard()
                    );
                    ArrayList<Integer> attackMoveBoxes = getSetBitsIndices(possibleAttackMoves);
                    //  Piece Captured not taken care here ...
                    for (Integer toBoxNo : attackMoveBoxes) {
                        move = new Move(p, boxNo, toBoxNo, true, null);
                        moves.add(move);
                    }
                }
            }
            bb = bb >>> 1; // Shift the number to the right by 1 bit
            boxNo++;
        }
        return moves;
    }

    public static ArrayList<Integer> getSetBitsIndices(long num) {
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < 64; i++) {
            if (((num >>> i) & 1) == 1) {
                indices.add(i);
            }
        }
        return indices;
    }
//    public static int getBoxNumber(long x) {
//        // Finding the position of the set bit
//        int position = 0;
//        while (x != 0) {
//            x = x >> 1;
//            position++;
//        }
//        return position;
//    }
}
