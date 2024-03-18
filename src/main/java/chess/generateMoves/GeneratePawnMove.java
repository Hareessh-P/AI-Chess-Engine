package chess.generateMoves;

import chess.pieces.Bitboard;
import chess.pieces.Pawn;

public class GeneratePawnMove implements GenerateMove{
    Pawn pawn;
    Bitboard bbOccupancy;

    public GeneratePawnMove(Pawn pawn, Bitboard bbOccupancy) {
        this.pawn = pawn;
        this.bbOccupancy = bbOccupancy;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

    public Bitboard getBbOccupancy() {
        return bbOccupancy;
    }

    public void setBbOccupancy(Bitboard bbOccupancy) {
        this.bbOccupancy = bbOccupancy;
    }

            //  TODO
//    public Move[] generateLegalPawnMoves(){
//
//    }


}
