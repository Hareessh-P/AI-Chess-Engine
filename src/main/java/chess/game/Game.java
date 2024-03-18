package chess.game;

import chess.board.Board;
import chess.board.BoardPrinter;
import chess.moves.Move;
import chess.pieces.Piece;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Game {
    private boolean turn;   //  0 --> whites turn , 1 --> blacks turn
    private int moveCount;
    private final Board board;
    public Game () {
        this.turn = false;
        this.moveCount = 0;
        this.board = Board.getInstance();
    }

    public void startGame() {

        Scanner sc = new Scanner(System.in);

        String from;
        String to;
        //      IS THIS GOOD PRACTICE ?? idts --> CAN I INSTANTIATE AN OBJ IN A METHOD OF A CLASS
        BoardPrinter boardPrinter = new BoardPrinter();
        InputParser inputParser = new InputParser(boardPrinter);
        Move currentMove;

        boolean whitesTurn = true;
        MoveActuator moveActuator = new MoveActuator(null, boardPrinter, this.board);;


        boolean keepPlaying = true;
//        boolean isCompleted;
//        boolean retry;


        while (keepPlaying) {
            boardPrinter.printChessboard(whitesTurn);
            boolean isCompleted = false;
            boolean retry = false;

            while(!isCompleted){

                try {
                    System.out.print(retry ? "\nPLEASE RETRY !! \n" : "");

                    from = sc.nextLine();
                    if (from.equals("exit"))
                        keepPlaying = false;
                    to = sc.nextLine();
                    System.out.println("hello");
                    currentMove = inputParser.parseMove(from, to);  // throws exception ...
                    currentMove.setBlackTurn(!whitesTurn);

                    //  this retry is for accessing space/empty box ...
                    if(currentMove.getMover() == null) {
                        retry = true;
                        continue;
                    }
                    moveActuator.setMove(currentMove);
                    moveActuator.executeMove();                     // throws exception ...
                    isCompleted = currentMove.getCompleted();
                    //  this retry is for accessing opponents piece ...
                    retry = false;       // Very BAD PRACTICE as of now i'm not able to think of any other option ..:(
                }
                catch (Piece.InvalidMoveException e) {
                    isCompleted = false;
                    retry = true;
                }
                catch (InputParser.NoPieceException e) {
                    isCompleted = false;
                    retry = true;
                }
            }
            whitesTurn = !whitesTurn;
        }
    }
}
