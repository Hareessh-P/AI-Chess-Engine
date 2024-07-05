package chess.pieces;

import chess.board.Board;
import chess.game.InputParser;
import chess.moves.Move;

abstract public class SpecialChessPiece implements  Piece{
    private int boxNo;
    private boolean colour;

    public SpecialChessPiece(int boxNo, boolean colour) {
        this.boxNo = boxNo;
        this.colour = colour;
    }

    public void setBoxNo(int boxNo) {
        this.boxNo = boxNo;
    }
    public int getBoxNo() {
        return boxNo;
    }
    public boolean getColour() {
        return colour;
    }
    public void setColour(boolean colour) {
        this.colour = colour;
    }


    @Override
    public void movePiece(Move move, Board board) throws InputParser.NoPieceException {

        if(!move.isValidColourMove()) {
            move.setCompleted(false);
            return;
        }

        int fromBoxNo = move.getFromBoxNo();
        int toBoxNo = move.getToBoxNo();

        if(!this.isValidPieceMove(fromBoxNo, toBoxNo, board))
            throw new InvalidMoveException("Invalid move.");

        boolean occupied = Piece.isNthBitSet(
                board.getOccupancyBitboard().bitboard, toBoxNo
        );
        boolean occupiedColour = Piece.isNthBitSet(
                board.getBlackOccupancyBitboard().bitboard, toBoxNo
        );

        Piece pieceInToBox = move.getCaptured();

        if(occupied) {
            if(this.getColour() == occupiedColour) {
                throw new InvalidMoveException("Invalid move: \nThe box is occupied bhy home piece.");
            }
            else {
//                 TODO --> DONE : ATTACK --> its just a valid move for now until we maintain separate bitboards for each piece type ... :)
                move.setCaptureMove(true);
//                move.setCaptured(pieceInToBox);  //    We are getting the piece from move only lol.
            }
        }

        this.removePiece(fromBoxNo, board);
        this.placePiece(toBoxNo, board);

        //          WE ARE CHANGING THE DISPLAY AFTER VALIDATING THE MOVE --> GOOD PRACTICE
        board.movePieceOnDisplayBoard(
                fromBoxNo, toBoxNo
        );
    }

    public void placePiece(int toBoxNo, Board board) {
        board.setOccupancyBitboard(toBoxNo);
        this.setBoxNo(toBoxNo);
        if(this.getColour()) {
            board.setBlackOccupancyBitboard(toBoxNo);
            //      TODO : If attacked implicitly we can un-set the occ bit board of the attacked colour (not attacking...) by just --> whitebb = ~blackbb & whitebb
        }
        else {
            board.setWhiteOccupancyBitboard(toBoxNo);
            //      TODO : If attacked implicitly we can un-set the occ bit board of the attacked colour (not attacking...) by just --> blackbb = ~whitebb & blackbb
        }
    }

    public void removePiece(int boxNo, Board board) {        //  TODO AFTER NAP OOooozzzzzz...
        board.unsetOccupancyBitboard(boxNo);
        if(this.getColour()) {
            board.unsetBlackOccupancyBitboard(boxNo);
            //      TODO : If attacked implicitly we can un-set the occ bit board of the attacked colour (not attacking...) by just --> whitebb = ~blackbb & whitebb
        }
        else {
            board.unsetWhiteOccupancyBitboard(boxNo);
            //      TODO : If attacked implicitly we can un-set the occ bit board of the attacked colour (not attacking...) by just --> blackbb = ~whitebb & blackbb
        }
    }

    public abstract  boolean isValidPieceMove(int fromBoxNo, int toBoxNo, Board board);

}


