package chess.pieces;

public interface Piece {
//    Bitboard bbPosition = null;




    // TODO
    long movePiece(Bitboard from, Bitboard to, long bbOccupancy);
}
