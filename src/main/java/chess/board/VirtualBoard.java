package chess.board;

import chess.game.InputParser;
import chess.game.MoveActuator;
import chess.moves.Move;
import chess.pieces.Bitboard;

public class VirtualBoard implements Board {
//    private final Board board;
    private String[] displayBoard;
    private String space;

    Bitboard occupancyBitboard;        // TODO : See if its occupancy bitboard and change the entire codebase accordingly
    Bitboard whiteOccupancyBitboard;
    Bitboard blackOccupancyBitboard;
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

    final boolean isVirtualBoard = true; ///---> I need this for the moment to allow my minimax algo to undo the pawn moves
    // --> in future try to fix this properly :|


    public VirtualBoard(Board board) {
        //              POTENTIAL PROBLEM  --> Occupancy Bitboard colour is set to black ... :(
        this.occupancyBitboard = board.getOccupancyBitboard();   //  <----------
        this.whiteOccupancyBitboard = board.getWhiteOccupancyBitboard();
        this.blackOccupancyBitboard = board.getBlackOccupancyBitboard();
        this.space = "\u25AD";    //  Unicode escape sequence '\u25AD' can be replaced with '▭'
        this.displayBoard = new String[] {
//              0      1     2     3    4     5     6    7
                "♖", "♘", "♗", "♔", "♕", "♗", "♘", "♖",
                //8     9   10    11    12   13     14  15
                "♙", "♙", "♙", "♙", "♙", "♙", "♙", "♙",
                "▭", "▭", "▭", "▭", "▭", "▭", "▭", "▭",
                "▭", "▭", "▭", "▭", "▭", "▭", "▭", "▭",
                "▭", "▭", "▭", "▭", "▭", "▭", "▭", "▭",
                "▭", "▭", "▭", "▭", "▭", "▭", "▭", "▭",
                "♟", "♟", "♟", "♟", "♟", "♟", "♟", "♟",
                "♜", "♞", "♝", "♚", "♛", "♝", "♞", "♜",

        };
        this.whitePawns = board.getWhitePawns();
        this.whiteRooks = board.getWhiteRooks();
        this.whiteKnights = board.getWhiteKnights();
        this.whiteBishops = board.getWhiteBishops();
        this.whiteKing = board.getWhiteKing();
        this.whiteQueen = board.getWhiteQueen();

        this.blackPawns = board.getBlackPawns();
        this.blackRooks = board.getBlackRooks();
        this.blackKnights = board.getBlackKnights();
        this.blackBishops = board.getBlackBishops();
        this.blackKing = board.getBlackKing();
        this.blackQueen = board.getBlackQueen();

    }

    public Bitboard getWhitePawns() {
        return whitePawns;
    }

    public Bitboard getWhiteRooks() {
        return whiteRooks;
    }

    public Bitboard getWhiteKnights() {
        return whiteKnights;
    }

    public Bitboard getWhiteBishops() {
        return whiteBishops;
    }

    public Bitboard getWhiteKing() {
        return whiteKing;
    }

    public Bitboard getWhiteQueen() {
        return whiteQueen;
    }

    public Bitboard getBlackPawns() {
        return blackPawns;
    }

    public Bitboard getBlackRooks() {
        return blackRooks;
    }

    public Bitboard getBlackKnights() {
        return blackKnights;
    }

    public Bitboard getBlackBishops() {
        return blackBishops;
    }

    public Bitboard getBlackKing() {
        return blackKing;
    }

    public Bitboard getBlackQueen() {
        return blackQueen;
    }

    public Bitboard getOccupancyBitboard() {
        return occupancyBitboard;
    }

    public void setOccupancyBitboard(int boxNo) {
        this.occupancyBitboard.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetOccupancyBitboard(int boxNo) {
        this.occupancyBitboard.unsetBitInOccupancyBitboard(boxNo);
    }

    public Bitboard getWhiteOccupancyBitboard() {
        return whiteOccupancyBitboard;
    }

    public void setWhiteOccupancyBitboard(int boxNo) {
        this.whiteOccupancyBitboard.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetWhiteOccupancyBitboard(int boxNo) {
        this.whiteOccupancyBitboard.unsetBitInOccupancyBitboard(boxNo);
    }

    public Bitboard getBlackOccupancyBitboard() {
        return blackOccupancyBitboard;
    }

    public void setBlackOccupancyBitboard(int boxNo) {
        this.blackOccupancyBitboard.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetBlackOccupancyBitboard(int boxNo) {
        this.blackOccupancyBitboard.unsetBitInOccupancyBitboard(boxNo);
    }

    public void movePieceOnDisplayBoard(int fromBoxNumber, int toBoxNumber) {
        System.out.println("\nInside movePieceOnDisplayBoard : ");
        System.out.println("\nfromboxno : "+ fromBoxNumber + "toboxno" + toBoxNumber);
        System.out.println();
        System.out.println();
        this.displayBoard[toBoxNumber] = this.displayBoard[fromBoxNumber];
        this.displayBoard[fromBoxNumber] = this.space;
    }

    public String getPieceDisplayString(int boxNo) {
        return this.displayBoard[boxNo];
    }

    public void setPieceDisplayString(int boxNo, String pieceString) {
        this.displayBoard[boxNo] = pieceString;
    }

    public void setBlackPawnOccupancyBitboard(int boxNo) {
        this.blackPawns.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetBlackPawnOccupancyBitboard(int boxNo) {
        this.blackPawns.unsetBitInOccupancyBitboard(boxNo);
    }

    public void setBlackRookOccupancyBitboard(int boxNo) {
        this.blackRooks.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetBlackRookOccupancyBitboard(int boxNo) {
        this.blackRooks.unsetBitInOccupancyBitboard(boxNo);
    }

    public void setBlackBishopOccupancyBitboard(int boxNo) {
        this.blackBishops.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetBlackBishopOccupancyBitboard(int boxNo) {
        this.blackBishops.unsetBitInOccupancyBitboard(boxNo);
    }

    public void setBlackKnightsOccupancyBitboard(int boxNo) {
        this.blackKnights.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetBlackKnightsOccupancyBitboard(int boxNo) {
        this.blackKnights.unsetBitInOccupancyBitboard(boxNo);
    }

    public void setBlackKingOccupancyBitboard(int boxNo) {
        this.blackKing.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetBlackKingOccupancyBitboard(int boxNo) {
        this.blackKing.unsetBitInOccupancyBitboard(boxNo);
    }

    public void setBlackQueenOccupancyBitboard(int boxNo) {
        this.blackQueen.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetBlackQueenOccupancyBitboard(int boxNo) {
        this.blackQueen.unsetBitInOccupancyBitboard(boxNo);
    }

    public void unsetWhitePawnOccupancyBitboard(int boxNo) {
        this.whitePawns.unsetBitInOccupancyBitboard(boxNo);
    }

    public void setWhitePawnOccupancyBitboard(int boxNo) {
        this.whitePawns.setBitInOccupancyBitboard(boxNo);
    }

    public void setWhiteRookOccupancyBitboard(int boxNo) {
        this.whiteRooks.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetWhiteRookOccupancyBitboard(int boxNo) {
        this.whiteRooks.unsetBitInOccupancyBitboard(boxNo);
    }

    public void setWhiteBishopOccupancyBitboard(int boxNo) {
        this.whiteBishops.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetWhiteBishopOccupancyBitboard(int boxNo) {
        this.whiteBishops.unsetBitInOccupancyBitboard(boxNo);
    }

    @Override
    public void makeMove(Move move, Board board) throws InputParser.NoPieceException {
        MoveActuator moveActuator = new MoveActuator(move, this);
        moveActuator.executeMove(board);
    }

    public void setWhiteKnightsOccupancyBitboard(int boxNo) {
        this.whiteKnights.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetWhiteKnightsOccupancyBitboard(int boxNo) {
        this.whiteKnights.unsetBitInOccupancyBitboard(boxNo);
    }

    public void setWhiteKingOccupancyBitboard(int boxNo) {
        this.whiteKing.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetWhiteKingOccupancyBitboard(int boxNo) {
        this.whiteKing.unsetBitInOccupancyBitboard(boxNo);
    }

    public void setWhiteQueenOccupancyBitboard(int boxNo) {
        this.whiteQueen.setBitInOccupancyBitboard(boxNo);
    }

    public void unsetWhiteQueenOccupancyBitboard(int boxNo) {
        this.whiteQueen.unsetBitInOccupancyBitboard(boxNo);
    }

    public boolean isVirtualBoard() {
        return isVirtualBoard;
    }
}
