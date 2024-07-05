package chess.moves;

import chess.board.BoardPrinter;
import chess.game.InputParser;
import chess.pieces.Piece;

public class Move {
    public float score;
    BoardPrinter boardPrinter;
    Piece mover;
    boolean blackTurn;
    boolean completed;
//    boolean colour;    coz this is redundant and
//    IF THE USER MOVES EMPTY SPACE THE PIECE IS IDENTIFIED AS NULL ,
//    AND HENCE IT(Move obj) CANT BE INSTANTIATED WITH A COLOUR ... AND RETRY SHD BE GIVEN ...
    int fromBoxNo;
    int toBoxNo;
    boolean isCaptureMove;
    //  TODO : CONSIDERING PIECE CAPTURED IS ANY PIECE AND THE CONDITIONS FOR KING WILL BE HANDLED LATER ...
    Piece captured;
    Move nextMove;

    public Move(Piece mover, int fromBoxNo, int toBoxNo, boolean isCaptureMove, Piece captured) {
        this.mover = mover;
        this.blackTurn = this.mover.getColour();
        this.fromBoxNo = fromBoxNo;
        this.toBoxNo = toBoxNo;
        this.isCaptureMove = isCaptureMove;
        this.captured = captured;
//        this.colour = mover.getColour();
        this.completed = true;      //      Just for ease of code ...
    }

    public void setBoardPrinter(BoardPrinter boardPrinter) {
        this.boardPrinter = boardPrinter;
    }

    public Piece getMover() {
        return mover;
    }

    public void setMover(Piece mover) {
        mover = mover;
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

//    public boolean getColour() {
//        return colour;
//    }

//    public void setColour(boolean colour) {
//        this.colour = colour;
//    }
//
//    public boolean getColour() {
//        return colour;
//    }

    public boolean isCaptureMove() {
        return isCaptureMove;
    }

    public void setCaptureMove(boolean captureMove) {
        isCaptureMove = captureMove;
    }

    public Piece getCaptured() throws InputParser.NoPieceException {

        if (this.captured!= null) return this.captured;
        try {
            InputParser ip = new InputParser(this.boardPrinter);
            Piece chessPiece = ip.getChessPiece(this.toBoxNo);
            return chessPiece;
        }
        catch (Exception e) {

        }
        return null;
    }

    public void setCaptured(Piece captured) {
        this.captured = captured;
    }

    public void setBlackTurn(boolean blackTurn) {
        this.blackTurn = blackTurn;
    }

    public boolean getBlackTurn() {
        return this.blackTurn;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public boolean getCompleted() {
        return this.completed;
    }

    public boolean isValidColourMove() {
        return this.getBlackTurn() == this.mover.getColour();
    }
}
