package chess.game;

public class BitboardGenerator {
    public static long generateBitboard(int row, int col) {
        // Ensure row and col are within bounds (0 to 7)
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            throw new IllegalArgumentException("Row and column must be between 0 and 7");
        }

        // Calculate the index of the bit (0 to 63)
        int index = row * 8 + col;

        // Generate the bitboard with a single "1" bit at the specified index
        return 1L << index;
    }
}



