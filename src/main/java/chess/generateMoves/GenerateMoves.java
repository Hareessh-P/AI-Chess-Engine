package chess.generateMoves;

import chess.board.Board;
import chess.moves.Move;

import java.util.ArrayList;

public class GenerateMoves {
    public static ArrayList<Move> generateAllMoves(Board board, boolean blacksTurn) {
        ArrayList<Move> moves = new ArrayList<>();
//        moves.addAll(GenerateKingMoves.generateKingMoves(board, blacksTurn));
        moves.addAll(GenerateRookMoves.generateRookMoves(board, blacksTurn, false));
        moves.addAll(GenerateBishopMoves.generateBishopMoves(board, blacksTurn, false));
        moves.addAll(GenerateKnightMoves.generateKnightMoves(board, blacksTurn));
        moves.addAll(GeneratePawnMoves.generatePawnMoves(board, blacksTurn));
        moves.addAll(GenerateQueenMoves.generateQueenMoves(board, blacksTurn));
        return moves;
    }

    public static ArrayList<Move> generateAttackMoves(Board board, boolean blacksTurn) {
        ArrayList<Move> moves = new ArrayList<>();
//        moves.addAll(GenerateKingMoves.generateKingAttackMoves(board, blacksTurn));
        moves.addAll(GenerateRookMoves.generateRookMoves(board, blacksTurn, false));
        moves.addAll(GenerateBishopMoves.generateBishopAttackMoves(board, blacksTurn, false));
        moves.addAll(GenerateKnightMoves.generateKnightAttackMoves(board, blacksTurn));
        moves.addAll(GeneratePawnMoves.generatePawnAttackMoves(board, blacksTurn));
        moves.addAll(GenerateQueenMoves.generateQueenMoves(board, blacksTurn));
        return moves;
    }


}
