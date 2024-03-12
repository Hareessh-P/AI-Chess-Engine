package org.example;

import chess.board.BoardPrinter;
import chess.game.Game;

public class Main {
    public static void main(String[] args) {
//        BoardPrinter boardPrinter = new BoardPrinter();
//        boardPrinter.printChessboard(true);
//        boardPrinter.printChessboard(false);
        Game game = new Game();
        game.startGame();
        System.out.println("Hello world!");
    }
}