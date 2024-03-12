package chess.game;

import chess.board.BoardPrinter;
import chess.moves.Move;
import chess.pieces.Bitboard;
import chess.pieces.ChessPiece;

public class MoveActuator {
    Move move;
    BoardPrinter boardPrinter;

    long bitOccupancy;

    public MoveActuator(Move move, BoardPrinter boardPrinter, long bbOccupancy) {
        this.move = move;
        this.boardPrinter = boardPrinter;
        this.bitOccupancy = bbOccupancy;
    }

    public void executeMove() {
        int fromRow = this.move.getFromBoxNo() / 8;
        int fromCol = this.move.getFromBoxNo() % 8;
        int toRow = this.move.getToBoxNo() / 8;
        int toCol = this.move.getToBoxNo() % 8;

        //          Board op Strings swapped ...
        String temp = this.boardPrinter.getElement(fromRow,fromCol);
        this.boardPrinter.setElement(fromRow,fromCol,this.boardPrinter.getElement(toRow,toCol));
        this.boardPrinter.setElement(toRow,toCol,temp);

        ChessPiece piece = this.move.getMover();

        piece.movePiece(
                new Bitboard(BitboardGenerator.generateBitboard(fromRow,fromCol), this.move.getColour()),
                new Bitboard(BitboardGenerator.generateBitboard(toRow,toCol), this.move.getColour()),
                this.bitOccupancy
                );
    }

}
