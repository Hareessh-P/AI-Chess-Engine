package chess.pieces;

public class Bitboard {
    long bitboard;
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

    public void setBitInOccupancyBitboard(int boxNo) {
        // Calculate the mask to set the bit at the specified boxNo
        long mask = 1L << boxNo;

        // Perform bitwise OR to set the bit
        this.bitboard = this.bitboard | mask;
    }

    public void unsetBitInOccupancyBitboard(int boxNo) {
        // Calculate the mask to set the bit at the specified boxNo
        long mask = 1L << boxNo;

        // Perform bitwise OR to set the bit
        this.bitboard = this.bitboard & ~mask;
    }

    public boolean getBitInOccupancyBitboard(int boxNo) {
        // Calculate the mask to set the bit at the specified boxNo
        long mask = 1L << boxNo;

        return (this.bitboard & mask) == mask;
    }
}
