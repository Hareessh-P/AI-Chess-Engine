package chess.pieces;

public class Pawn extends ChessPiece{


    public Pawn(Bitboard bbPosition) {
        super(bbPosition);
    }

    @Override       // TODO
    public long movePiece(Bitboard from, Bitboard to, long bitOccupancy) {

        this.setBbPosition(to);
        bitOccupancy = bitOccupancy & (~from.getBitboard()); // WTH !! Worst Naming ************
        bitOccupancy = bitOccupancy | to.getBitboard();

        return bitOccupancy;
    }
}
