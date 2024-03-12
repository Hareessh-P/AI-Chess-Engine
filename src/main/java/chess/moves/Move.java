package chess.moves;

import chess.pieces.ChessPiece;

public class Move {
    ChessPiece Mover;
    boolean colour;
    int fromBoxNo;
    int toBoxNo;
    boolean isCaptureMove;
    ChessPiece captured;

    public Move(ChessPiece mover, int fromBoxNo, int toBoxNo, boolean isCaptureMove, ChessPiece captured) {
        Mover = mover;
        this.fromBoxNo = fromBoxNo;
        this.toBoxNo = toBoxNo;
        this.isCaptureMove = isCaptureMove;
        this.captured = captured;
        this.colour = mover.getBbPosition().getColour();
    }

    public ChessPiece getMover() {
        return Mover;
    }

    public void setMover(ChessPiece mover) {
        Mover = mover;
    }

    public int getFromBoxNo() {
        return fromBoxNo;
    }

    public void setFromBoxNo(int fromBoxNo) {
        this.fromBoxNo = fromBoxNo;
    }

    public int getToBoxNo() {
        return toBoxNo;
    }

    public void setToBoxNo(int toBoxNo) {
        this.toBoxNo = toBoxNo;
    }

    public boolean getColour() {
        return colour;
    }

    public void setColour(boolean colour) {
        this.colour = colour;
    }
}
