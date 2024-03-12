package chess.board;

import chess.pieces.*;

public class Board {
    private volatile static Board uniqueInstance;

    Bitboard bb;
    Bitboard whitePawns;
    Bitboard whiteRooks;
    Bitboard whiteKnights;
    Bitboard whiteBishops;
    Bitboard whiteKing;
    Bitboard whiteQueen;
    Bitboard blackPawns;
    Bitboard blackRooks;
    Bitboard blackKnights;
    Bitboard blackBishops;
    Bitboard blackKing;
    Bitboard blackQueen;


    private Board() {
        this.whitePawns = new Bitboard(0x000000000000FF00L,false);
        this.whiteRooks = new Bitboard(0x0000000000000081L,false);
        this.whiteKnights = new Bitboard(0x0000000000000042L,false);
        this.whiteBishops = new Bitboard(0x0000000000000024L,false);
        this.whiteKing = new Bitboard(0x0000000000000008L,false);
        this.whiteQueen = new Bitboard(0x0000000000000010L,false);

        this.blackPawns = new Bitboard(0x000000000000FF00L,true);
        this.blackRooks = new Bitboard(0x0000000000000081L,true);
        this.blackKnights = new Bitboard(0x0000000000000042L,true);
        this.blackBishops = new Bitboard(0x0000000000000024L,true);
        this.blackKing = new Bitboard(0x0000000000000008L,true);
        this.blackQueen = new Bitboard(0x0000000000000010L,true);
    }
    public static Board getInstance() {
        if (uniqueInstance == null) {
            synchronized (Board.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Board();
                }
            }
        }
        return uniqueInstance;
    }
}