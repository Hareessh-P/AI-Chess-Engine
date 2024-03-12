package chess.generateMoves;

public interface GenerateMove {

    default boolean isLegalBox(int x) {
        return x >= 0 && x < 64;
    }
}
