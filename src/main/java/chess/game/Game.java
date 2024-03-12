package chess.game;

import chess.board.Board;
import chess.board.BoardPrinter;
import chess.moves.Move;

import java.util.Scanner;

public class Game {
    private boolean turn;   //  0 --> whites turn , 1 --> blacks turn
    private int moveCount;
    private Board board;
    public Game () {
        this.turn = false;
        this.moveCount = 0;
        this.board = Board.getInstance();
    }

    public void startGame() {
        String from;
        String to;
        BoardPrinter boardPrinter = new BoardPrinter();
        InputParser inputParser = new InputParser(boardPrinter);
        Move currentMove;
        long occupancyBit = 0xFFFF00000000FFFFL;

        boolean whitesTurn = true;
        MoveActuator moveActuator;


        boolean keepPlaying = true;
        while (keepPlaying) {
            boardPrinter.printChessboard(whitesTurn);
            Scanner sc = new Scanner(System.in);
            from = sc.nextLine();
            if (from.equals("exit"))
                keepPlaying = false;
            to = sc.nextLine();
            currentMove = inputParser.parseMove(from, to);
            moveActuator = new MoveActuator(currentMove, boardPrinter, occupancyBit);
            moveActuator.executeMove();
            whitesTurn = !whitesTurn;
        }
    }
}
