package chess.game;

import chess.board.Board;
import chess.board.BoardPrinter;
import chess.moves.Move;
import chess.pieces.Bitboard;
import chess.pieces.ChessPiece;
import chess.pieces.Piece;

// TODO : Ig i wont need MoveActuator ... NOPE idts ...

public class MoveActuator {
    Move move;
    BoardPrinter boardPrinter;
    Board board;

    public MoveActuator(Move move, BoardPrinter boardPrinter, Board board) {
        this.move = move;
        this.boardPrinter = boardPrinter;
        this.board = board;
    }

    public void executeMove() {
        Piece piece = this.move.getMover();
// TODO : I'll proly move this .movePiece to first --> update move object and use boardPrinter to print the board accordingly ..
        piece.movePiece(
                this.move,
                this.board
                );
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
