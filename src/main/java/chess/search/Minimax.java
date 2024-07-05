package chess.search;

import chess.board.Board;
import chess.board.BoardPrinter;
import chess.board.VirtualBoard;
import chess.evaluation.BoardEvaluator;
import chess.game.InputParser;
import chess.generateMoves.GenerateMoves;
import chess.moves.Move;

import java.util.ArrayList;

public class Minimax {
    public static Move getBestMove(Board board, int depth, boolean maximizingPlayer, int de) throws InputParser.NoPieceException {
        Move bestMove = null;
        Board virtualBoard = new VirtualBoard(board); // Create a copy of the board
        BoardPrinter bp = new BoardPrinter(virtualBoard);
        float bestScore = maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        compute(de);
        ArrayList<Move> moves = GenerateMoves.generateAllMoves(board, maximizingPlayer);
        int n = moves.size();
        for (int i=0 ; i<n ; i++) {
            Move move = moves.get(i);
            move.setBoardPrinter(bp);

            virtualBoard.makeMove(move, virtualBoard); // Make the move on the cloned board

            float score = minimax(virtualBoard, depth - 1, !maximizingPlayer, bp);
            if ((maximizingPlayer && score > bestScore) || (!maximizingPlayer && score < bestScore)) {
                bestScore = score;
                bestMove = move;
            }

            Move undoMove = null;
            try {
                undoMove = new Move(move.getMover(), move.getToBoxNo(), move.getFromBoxNo(), move.isCaptureMove(), move.getCaptured());
                undoMove.setBoardPrinter(bp);
            } catch (InputParser.NoPieceException e) {
                throw new RuntimeException(e);
            }
            virtualBoard.makeMove(undoMove, virtualBoard);
        }

        return bestMove;
    }
    public static float minimax(Board virtualBoard, int depth, boolean maximizingPlayer, BoardPrinter bp) throws InputParser.NoPieceException {
        if (depth == 0 ) {  //  || board.isGameOver() ...
            return BoardEvaluator.evaluate(virtualBoard);
        }
        if (maximizingPlayer) {
            float maxScore = Integer.MIN_VALUE;
            ArrayList<Move> moves = GenerateMoves.generateAllMoves(virtualBoard, true);
            int n = moves.size();
            for (int i=0 ; i<n ; i++) {
                Move move = moves.get(i);
                move.setBoardPrinter(bp);

                virtualBoard.makeMove(move, virtualBoard);

                float score = minimax(virtualBoard, depth - 1, false, bp);
                maxScore = Math.max(maxScore, score);

                Move undoMove = null;
                try {
                    undoMove = new Move(move.getMover(), move.getToBoxNo(), move.getFromBoxNo(), move.isCaptureMove(), move.getCaptured());
                    undoMove.setBoardPrinter(bp);
                } catch (InputParser.NoPieceException e) {
                    throw new RuntimeException(e);
                }
                virtualBoard.makeMove(undoMove, virtualBoard);
            }
            return maxScore;
        } else {
            float minScore = Integer.MAX_VALUE;
            ArrayList<Move> moves = GenerateMoves.generateAllMoves(virtualBoard, false);
            int n = moves.size();
            for (int i=0 ; i<n ; i++) {
                Move move = moves.get(i);
                move.setBoardPrinter(bp);
                virtualBoard.makeMove(move, virtualBoard); // Make the move on the cloned board

                float score = minimax(virtualBoard, depth - 1, true, bp);
                minScore = Math.min(minScore, score);

                Move undoMove = null;
                try {
                    undoMove = new Move(move.getMover(), move.getToBoxNo(), move.getFromBoxNo(), move.isCaptureMove(), move.getCaptured());
                    undoMove.setBoardPrinter(bp);
                } catch (InputParser.NoPieceException e) {
                    throw new RuntimeException(e);
                }
                virtualBoard.makeMove(undoMove, virtualBoard);
            }
            return minScore;
        }
    }
    public static void compute(int de) {
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(160*de*de);
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
