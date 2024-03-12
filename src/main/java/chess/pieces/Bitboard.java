package chess.pieces;

public class Bitboard {
    long bitboard = 0;
    private boolean colour; // 0 --> White , 1 --> Black

    public Bitboard(long bitboard, boolean colour) {
        this.bitboard = bitboard;
        this.colour = colour;
    }

    public long getBitboard() {
        return bitboard;
    }

    public void setBitboard(long bitboard) {
        this.bitboard = bitboard;
    }

    public boolean getColour() {
        return colour;
    }

    public void setColour(boolean colour) {
        this.colour = colour;
    }
}
