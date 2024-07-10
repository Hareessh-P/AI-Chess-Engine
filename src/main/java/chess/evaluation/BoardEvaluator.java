package chess.evaluation;

import chess.board.Board;
import chess.board.VirtualBoard;
import chess.pieces.Bitboard;

public class BoardEvaluator {

    // Material values of pieces
    private static final int PAWN_VALUE = 100;
    private static final int ROOK_VALUE = 500;
    private static final int KNIGHT_VALUE = 300;
    private static final int BISHOP_VALUE = 300;
    private static final int QUEEN_VALUE = 900;
    private static final int KING_VALUE = 35000;

    // Evaluation function
        public static int evaluate(Board board) {
        int whiteScore = 0;
        int blackScore = 0;

        // Evaluate white pieces
        whiteScore += evaluatePieces(board.getWhitePawns(), PAWN_VALUE);
        whiteScore += evaluatePieces(board.getWhiteRooks(), ROOK_VALUE);
        whiteScore += evaluatePieces(board.getWhiteKnights(), KNIGHT_VALUE);
        whiteScore += evaluatePieces(board.getWhiteBishops(), BISHOP_VALUE);
        whiteScore += evaluatePieces(board.getWhiteQueen(), QUEEN_VALUE);
        whiteScore += evaluatePieces(board.getWhiteKing(), KING_VALUE);

        // Evaluate black pieces
        blackScore += evaluatePieces(board.getBlackPawns(), PAWN_VALUE);
        blackScore += evaluatePieces(board.getBlackRooks(), ROOK_VALUE);
        blackScore += evaluatePieces(board.getBlackKnights(), KNIGHT_VALUE);
        blackScore += evaluatePieces(board.getBlackBishops(), BISHOP_VALUE);
        blackScore += evaluatePieces(board.getBlackQueen(), QUEEN_VALUE);
        blackScore += evaluatePieces(board.getBlackKing(), KING_VALUE);

        // Return the difference in scores
            int res = blackScore - whiteScore;
        if (res == 0 ) {
            return -10;
        }
        return res;
    }

    // Helper method to evaluate pieces
    private static int evaluatePieces(Bitboard pieces, int pieceValue) {
        // Count the number of pieces
        int count = Long.bitCount(pieces.getBitboard());
        // Multiply count by piece value
        return count * pieceValue;
    }
    private static final int bishoppos[][] =
            {
                    {-5, -5, -5, -5, -5, -5, -5, -5},
                    {-5, 10,  5,  8,  8,  5, 10, -5},
                    {-5,  5,  3,  8,  8,  3,  5, -5},
                    {-5,  3, 10,  3,  3, 10,  3, -5},
                    {-5,  3, 10,  3,  3, 10,  3, -5},
                    {-5,  5,  3,  8,  8,  3,  5, -5},
                    {-5, 10,  5,  8,  8,  5, 10, -5},
                    {-5, -5, -5, -5, -5, -5, -5, -5}
            };
    private static final int knightpos[][] =
            {
                    {-10, -5, -5, -5, -5, -5, -5,-10},
                    { -8,  0,  0,  3,  3,  0,  0, -8},
                    { -8,  0, 10,  8,  8, 10,  0, -8},
                    { -8,  0,  8, 10, 10,  8,  0, -8},
                    { -8,  0,  8, 10, 10,  8,  0, -8},
                    { -8,  0, 10,  8,  8, 10,  0, -8},
                    { -8,  0,  0,  3,  3,  0,  0, -8},
                    {-10, -5, -5, -5, -5, -5, -5,-10}
            };

    private static final int pawnposWhite[][] =
            {
                    {0,  0,  0,  0,  0,  0,  0,  0},
                    {0,  0,  0, -5, -5,  0,  0,  0},
                    {0,  2,  3,  4,  4,  3,  2,  0},
                    {0,  4,  6, 10, 10,  6,  4,  0},
                    {0,  6,  9, 10, 10,  9,  6,  0},
                    {4,  8, 12, 16, 16, 12,  8,  4},
                    {5, 10, 15, 20, 20, 15, 10,  5},
                    {0,  0,  0,  0,  0,  0,  0,  0}
            };

    // I created this, should be just a flipped
    // version of the white array
    private static final int[][] pawnposBlack =
            {
                    {0,  0,  0,  0,  0,  0,  0,  0},
                    {5, 10, 15, 20, 20, 15, 10,  5},
                    {4,  8, 12, 16, 16, 12,  8,  4},
                    {0,  6,  9, 10, 10,  9,  6,  0},
                    {0,  4,  6, 10, 10,  6,  4,  0},
                    {0,  2,  3,  4,  4,  3,  2,  0},
                    {0,  0,  0, -5, -5,  0,  0,  0},
                    {0,  0,  0,  0,  0,  0,  0,  0}
            };


}