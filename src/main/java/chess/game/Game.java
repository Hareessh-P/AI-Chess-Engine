package chess.game;

import chess.agent.Agent;
import chess.board.Board;
import chess.board.BoardPrinter;
import chess.board.GameBoard;
import chess.moves.Move;
import chess.pieces.Piece;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.logging.LogManager;

public class Game {
    private boolean turn;   //  0 --> whites turn , 1 --> blacks turn
    private int moveCount;
    private final Board gameBoard;
    public Game () {
        this.turn = false;
        this.moveCount = 0;
        this.gameBoard = GameBoard.getInstance();
    }

//    public void startGame() {
//
//        Scanner sc = new Scanner(System.in);
//
//        String from;
//        String to;
//        //      IS THIS GOOD PRACTICE ?? idts --> CAN I INSTANTIATE AN OBJ IN A METHOD OF A CLASS
//        BoardPrinter boardPrinter = new BoardPrinter();
//        InputParser inputParser = new InputParser(boardPrinter);
//        Move currentMove;
//
//        boolean whitesTurn = true;
//        MoveActuator moveActuator = new MoveActuator(null, boardPrinter, this.board);;
//
//
//        boolean keepPlaying = true;
////        boolean isCompleted;
////        boolean retry;
//
//
//        while (keepPlaying) {
//            boardPrinter.printChessboard(whitesTurn);
//            boolean isCompleted = false;
//            boolean retry = false;
//
//            while(!isCompleted){
//
//                try {
//                    System.out.print(retry ? "\nPLEASE RETRY !! \n" : "");
//
//                    from = sc.nextLine();
//                    if (from.equals("exit"))
//                        keepPlaying = false;
//                    to = sc.nextLine();
//                    System.out.println("hello");
//                    currentMove = inputParser.parseMove(from, to);  // throws exception ...
//                    currentMove.setBlackTurn(!whitesTurn);
//
//                    //  this retry is for accessing space/empty box ...
//                    if(currentMove.getMover() == null) {
//                        retry = true;
//                        continue;
//                    }
//                    moveActuator.setMove(currentMove);
//                    moveActuator.executeMove();                     // throws exception ...
//                    isCompleted = currentMove.getCompleted();
//                    //  this retry is for accessing opponents piece ...
//                    retry = false;       // Very BAD PRACTICE as of now i'm not able to think of any other option ..:(
//                }
//                catch (Piece.InvalidMoveException e) {
//                    isCompleted = false;
//                    retry = true;
//                }
//                catch (InputParser.NoPieceException e) {
//                    isCompleted = false;
//                    retry = true;
//                }
//            }
//            whitesTurn = !whitesTurn;
//        }
//    }
    public void startGame() {

        GameBoard gameBoard = GameBoard.getInstance();
        Scanner sc = new Scanner(System.in);

        String from;
        String to;
        //      IS THIS GOOD PRACTICE ?? idts --> CAN I INSTANTIATE AN OBJ IN A METHOD OF A CLASS
        BoardPrinter boardPrinter = new BoardPrinter(gameBoard);
        InputParser inputParser = new InputParser(boardPrinter);
        Move currentMove;

        boolean whitesTurn = true;
        MoveActuator moveActuator = new MoveActuator(null, this.gameBoard);;
//        MoveActuator moveActuator = new MoveActuator(null, boardPrinter, this.gameBoard);;


        boolean keepPlaying = true;
//        boolean isCompleted;
//        boolean retry;


        while (keepPlaying) {
            boardPrinter.printChessboard(whitesTurn);
            boolean isCompleted = false;
            boolean retry = false;

            while(!isCompleted){

                try {
                    System.out.print(retry ? "\nPLEASE ENTER !! \n" : "");

                    from = sc.nextLine();
                    if (from.equals("exit"))
                        keepPlaying = false;
                    to = sc.nextLine();
                    System.out.println("hello");
                    if (from.equals("*")) {
                        Agent agent = new Agent(gameBoard, !whitesTurn);
                        currentMove = agent.getOptimalMove();
                    }
                    else {
                        currentMove = inputParser.parseMove(from, to);  // throws exception ...
                        currentMove.setBlackTurn(!whitesTurn);
                    }

                    //  this retry is for accessing space/empty box ...
                    if(currentMove.getMover() == null) {
                        retry = true;
                        continue;
                    }
                    currentMove.setBoardPrinter(boardPrinter);
                    moveActuator.setMove(currentMove);
                    moveActuator.executeMove(gameBoard);                     // throws exception ...
                    isCompleted = currentMove.getCompleted();
                    //  this retry is for accessing opponents piece ...
                    retry = false;       // Very BAD PRACTICE as of now i'm not able to think of any other option ..:(

                    System.out.println(":(((( : " + Long.toHexString(gameBoard.getBlackPawns().getBitboard()));
                }
                catch (Piece.InvalidMoveException e) {
                    isCompleted = false;
                    retry = true;
                    System.out.println("");
                }
                catch (InputParser.NoPieceException e) {
                    isCompleted = false;
                    retry = true;
                }
            }
            whitesTurn = !whitesTurn;
        }
    }
//    public void startGame() {
//        Scanner sc = new Scanner(System.in);
//
//        String from;
//        String to;
//
//        BoardPrinter boardPrinter = new BoardPrinter();
//        InputParser inputParser = new InputParser(boardPrinter);
//        Move currentMove;
//
//        boolean whitesTurn = true;
//        MoveActuator moveActuator = new MoveActuator(null, boardPrinter, this.board);
//
//        boolean keepPlaying = true;
//
//        int cnt = 0;
//
//        while (keepPlaying) {
//            boardPrinter.printChessboard(whitesTurn);
//            boolean isCompleted = false;
//            boolean retry = false;
//
//            if (whitesTurn) {
//                // White's turn
//                while (!isCompleted) {
//                    try {
//                        System.out.print(retry ? "\nPLEASE RETRY !! \n" : "");
//
//                        from = sc.nextLine();
//                        if (from.equals("exit")) {
//                            keepPlaying = false;
//                            break;
//                        }
//                        to = sc.nextLine();
//                        currentMove = inputParser.parseMove(from, to);
//                        currentMove.setBlackTurn(false);
//
//                        if (currentMove.getMover() == null) {
//                            retry = true;
//                            continue;
//                        }
//                        moveActuator.setMove(currentMove);
//                        moveActuator.executeMove();
//                        isCompleted = currentMove.getCompleted();
//                        retry = false;
//                    } catch (Piece.InvalidMoveException | InputParser.NoPieceException e) {
//                        isCompleted = false;
//                        retry = true;
//                    }
//                }
//            } else {
//                // Black's turn
//                System.out.println("WHISTLE PODU !!");
//                Agent agent = new Agent(board);
//            }
//            whitesTurn = !whitesTurn;
//        }
//    }

}
