package chess.search;

import chess.board.Board;
import chess.board.VirtualBoard;
import chess.evaluation.BoardEvaluator;
import chess.game.InputParser;
import chess.generateMoves.GenerateMoves;
import chess.moves.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonteCarloTreeSearch {

    private static final int SIMULATION_COUNT = 10;
    private static final double T0 = 100.0;
    private static final double T_DECAY = 0.99;
    private static final double ALPHA = 0.8;
    private static final double BETA = 0.1;

    public MonteCarloTreeSearch() {}

    public Move findBestMove(Board board, boolean blacksTurn) throws InputParser.NoPieceException {
        Node root = new Node(board);
        double temperature = T0;

        for (int i = 0; i < SIMULATION_COUNT; i++) {
            Node node = root;
            Board boardCopy = new VirtualBoard(board);

            // Selection phase
            while (!node.getChildren().isEmpty()) {
                node = node.selectChild(temperature);
                boardCopy.makeMove(node.getMove(), boardCopy);
                blacksTurn = !blacksTurn; // Toggle turn after each move
            }

            // Expansion phase
            List<Move> possibleMoves = generateMoves(boardCopy, blacksTurn);
            for (Move move : possibleMoves) {
                Board newBoard = new VirtualBoard(boardCopy);
                newBoard.makeMove(move, newBoard);
                Node newNode = new Node(newBoard, move);
                node.addChild(newNode);
            }

            // Simulation phase
            Node selectedNode = node.getRandomChild();
            Board simulatedBoard = new VirtualBoard(selectedNode.getBoard());
            int result = simulateRandomGame(simulatedBoard, blacksTurn);
            selectedNode.update(result);

            // Backpropagation phase
            while (selectedNode != null) {
                selectedNode.update(result);
                selectedNode = selectedNode.getParent();
            }

            // Update temperature
            temperature = Tdecay(temperature);
        }

        return root.getBestMove();
    }

    private List<Move> generateMoves(Board board, boolean blacksTurn) {
        return GenerateMoves.generateAllMoves(board, blacksTurn);
    }

    private int simulateRandomGame(Board board, boolean blacksTurn) throws InputParser.NoPieceException {
        double temperature = T0;
        double alpha = ALPHA;
        double beta = BETA;

        Random random = new Random();
        int sim_cnt = SIMULATION_COUNT;
        while (sim_cnt > 0) {
            List<Move> potentialActions = generateMoves(board, blacksTurn);

            Move bestAction = null;
            double bestUtility = Double.NEGATIVE_INFINITY;

            for (Move action : potentialActions) {
                Board newBoard = new VirtualBoard(board);
                newBoard.makeMove(action, newBoard);
                int utility = evaluateUtility(newBoard);

                if (utility > bestUtility) {
                    bestAction = action;
                    bestUtility = utility;
                }
            }

            double epsilon = alpha * temperature + beta;

            if (random.nextDouble() < 1 - epsilon) {
                board.makeMove(bestAction, board);
            } else {
                Move randomAction = potentialActions.get(random.nextInt(potentialActions.size()));
                board.makeMove(randomAction, board);
            }

            temperature = Tdecay(temperature);
            sim_cnt -= 1;
        }

        return evaluateFinalResult(board);
    }

    private int evaluateFinalResult(Board board) {
        return BoardEvaluator.evaluate(board);
    }

    private int evaluateUtility(Board board) {
        // This is based on factors like piece values, position, checkmate, etc.
        return BoardEvaluator.evaluate(board);
    }


    private double Tdecay(double T) {
        return T * T_DECAY;
    }

    private static class Node {
        private Board board;
        private Move move;
        private Node parent;
        private List<Node> children;
        private int wins;
        private int visits;

        public Node(Board board) {
            this(board, null);
        }

        public Node(Board board, Move move) {
            this.board = board;
            this.move = move;
            this.children = new ArrayList<>();
            this.wins = 0;
            this.visits = 0;
        }

        public Board getBoard() {
            return board;
        }

        public Move getMove() {
            return move;
        }

        public Node getParent() {
            return parent;
        }

        public List<Node> getChildren() {
            return children;
        }

        public void addChild(Node child) {
            child.parent = this;
            children.add(child);
        }

        public Node selectChild(double temperature) {
            Random random = new Random();
            double epsilon = ALPHA * temperature + BETA;

            if (random.nextDouble() < 1 - epsilon) {
                return getBestChild();
            } else {
                return getRandomChild();
            }
        }

        private Node getBestChild() {
            Node bestChild = null;
            double bestUCB1 = Double.MIN_VALUE;

            for (Node child : children) {
                double ucb1 = child.getUCB1();
                if (ucb1 > bestUCB1) {
                    bestUCB1 = ucb1;
                    bestChild = child;
                }
            }
            return bestChild;
        }

        public Node getRandomChild() {
            Random random = new Random();
            return children.get(random.nextInt(children.size()));
        }

        public void update(int result) {
            visits++;
            wins += result;
        }

        public double getUCB1() {
            if (visits == 0) {
                return Double.MAX_VALUE;
            }
            return (double) wins / visits + Math.sqrt(2 * Math.log(parent.visits) / visits);
        }

        public Move getBestMove() {
            return getBestChild().getMove();
        }
    }
    public void compute(){}
}
