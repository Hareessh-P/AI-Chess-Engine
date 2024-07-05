package chess.board;

import chess.game.InputParser;
import chess.game.MoveActuator;
import chess.moves.Move;
import chess.pieces.*;

public interface Board {
    String getPieceDisplayString(int boxNo);

    void setPieceDisplayString(int boxNo, String pieceString);

    Bitboard getOccupancyBitboard();

    Bitboard getWhiteOccupancyBitboard();

    Bitboard getBlackOccupancyBitboard();

    Bitboard getWhitePawns();

    Bitboard getWhiteRooks();

    Bitboard getWhiteKnights();

    Bitboard getWhiteBishops();

    Bitboard getWhiteKing();

    Bitboard getWhiteQueen();

    Bitboard getBlackPawns();

    Bitboard getBlackRooks();

    Bitboard getBlackKnights();

    Bitboard getBlackBishops();

    Bitboard getBlackKing();

    Bitboard getBlackQueen();

    void setOccupancyBitboard(int toBoxNo);

    void unsetOccupancyBitboard(int fromBoxNo);

    void setBlackOccupancyBitboard(int toBoxNo);

    void unsetBlackOccupancyBitboard(int fromBoxNo);

    void setBlackPawnOccupancyBitboard(int toBoxNo);

    void unsetBlackPawnOccupancyBitboard(int fromBoxNo);

    void setWhiteOccupancyBitboard(int toBoxNo);

    void unsetWhiteOccupancyBitboard(int fromBoxNo);

    void setWhitePawnOccupancyBitboard(int toBoxNo);

    void unsetWhitePawnOccupancyBitboard(int fromBoxNo);

    void movePieceOnDisplayBoard(int fromBoxNo, int toBoxNo);

    void setBlackKingOccupancyBitboard(int boxNo);

    void setWhiteKingOccupancyBitboard(int boxNo);

    void setBlackKnightsOccupancyBitboard(int boxNo);

    void setWhiteKnightsOccupancyBitboard(int boxNo);

    void unsetBlackKnightsOccupancyBitboard(int boxNo);

    void unsetWhiteKnightsOccupancyBitboard(int boxNo);

    void setBlackQueenOccupancyBitboard(int boxNo);

    void setWhiteQueenOccupancyBitboard(int boxNo);

    void unsetBlackQueenOccupancyBitboard(int boxNo);

    void unsetWhiteQueenOccupancyBitboard(int boxNo);

    void setBlackRookOccupancyBitboard(int boxNo);

    void setWhiteRookOccupancyBitboard(int boxNo);

    void unsetBlackRookOccupancyBitboard(int boxNo);

    void unsetWhiteRookOccupancyBitboard(int boxNo);

    void setBlackBishopOccupancyBitboard(int boxNo);

    void setWhiteBishopOccupancyBitboard(int boxNo);

    void unsetBlackBishopOccupancyBitboard(int boxNo);

    void unsetWhiteBishopOccupancyBitboard(int boxNo);

    void makeMove(Move move, Board board) throws InputParser.NoPieceException;

    boolean isVirtualBoard();


//    void makeMove(Move move);
//////////////////////////////////////////////////////

}