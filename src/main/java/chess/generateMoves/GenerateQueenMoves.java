package chess.generateMoves;

import chess.board.Board;
import chess.moves.Move;
import chess.pieces.Bitboard;

import java.util.ArrayList;

public class GenerateQueenMoves {
    public static ArrayList<Move> generateQueenMoves( Board board, boolean blacksTurn) {
        Bitboard queenOccBb = blacksTurn ? board.getBlackQueen() : board.getWhiteQueen();
        ArrayList<Move> moves = new ArrayList<>();
        Bitboard temp = blacksTurn ? board.getBlackRooks() : board.getWhiteRooks();

        moves.addAll(GenerateRookMoves.generateRookMoves(board, blacksTurn, true));
        moves.addAll(GenerateBishopMoves.generateBishopMoves(board, blacksTurn, true));
        return moves;
    }
}
