package chess.agent;

import chess.board.Board;
import chess.board.VirtualBoard;
import chess.evaluation.BoardEvaluator;
import chess.game.InputParser;
import chess.generateMoves.*;
import chess.moves.Move;
import chess.search.Minimax;
import chess.search.MonteCarloTreeSearch;

import java.util.ArrayList;
import java.util.Random;

public class Agent {
    Board board;
    boolean blacksTurn;
    int cp;
    final int depth = 1;
    ArrayList<Move> moves = new ArrayList<>();

    public Agent(Board board, boolean blacksTurn) {
        this.board = board;
        this.blacksTurn = blacksTurn;
        this.moves = GeneratePawnMoves.generatePawnMoves(board, blacksTurn);
        this.cp = this.moves.size();
        this.moves.addAll(GenerateKnightMoves.generateKnightMoves(board, blacksTurn));
        this.moves.addAll(GenerateBishopMoves.generateBishopMoves(board, blacksTurn, false));
        this.moves.addAll(GenerateKingMoves.generateKingMoves(board, blacksTurn));
        this.moves.addAll(GenerateRookMoves.generateRookMoves(board, blacksTurn, false));
        this.moves.addAll(GenerateQueenMoves.generateQueenMoves(board, blacksTurn));
//        for (Move m : this.moves) {
//            System.out.println(m.getFromBoxNo() + " to " + m.getToBoxNo());
//        }
    }
//    public Agent(Board board, boolean blacksTurn) {
//        this.board = board;
//        this.blacksTurn = blacksTurn;
////        this.moves = GenerateMoves.generateAllMoves(board, blacksTurn);
//        this.cp = 0; //     TODO
//    }

    public Move getOptimalMove() throws InputParser.NoPieceException {
        Move optimalMove;
        int depth = 1;
//        optimalMove = Minimax.getBestMove(board, this.depth, this.blacksTurn, depth);
        optimalMove = getOptimalMov();
        return optimalMove;
    }

    public Move getOptimalMov() {
        Random random = new Random();
//        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch();
//        mcts.compute();
//        Minimax.compute(2);
        int n = this.moves.size();
        int j = 0, l = 0;
        if (n > 1) {
            j = random.nextInt(n - 1);
        }
        if (n > 1 + this.cp){
            l = random.nextInt(n - 1 - this.cp) + this.cp;
        }
        int k = 1;
        Move move;
        ArrayList<Move> attackMoves = GenerateMoves.generateAttackMoves(board, blacksTurn);
        int no_attcks = attackMoves.size();
        if (no_attcks > 1) {
            int r = random.nextInt(no_attcks - 1);
            return attackMoves.get(r);
        } else if (no_attcks == 1) {
            return attackMoves.get(0);
        }
        if (j%2 == 0) {
            k = 0;
            move = moves.get(l);
        }
        else {
            move = moves.get(j);
        }

        int randomNumber = random.nextInt(n - 1);
//        int max = -100000;
//        mcts.compute();
//        VirtualBoard virtualBoard = new VirtualBoard(this.board);
//        for (int i=0 ; i<n ; i++) {
//            int individual_score = BoardEvaluator.evaluate(virtualBoard);
//            if (max < individual_score) {
////                move = moves.get(i);
//                max = individual_score;
//            }
//        }
        return this.moves.get(randomNumber);
//        return move;
    }

//    public

}
