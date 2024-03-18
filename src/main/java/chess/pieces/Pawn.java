package chess.pieces;

import chess.board.Board;
import chess.moves.Move;
import chess.resources.AttackMasks;

public class Pawn extends ChessPiece{


    public Pawn(int boxNo, boolean colour) {
        super(boxNo, colour);
    }

    @Override                                             //  TODO : Refactor this :/
    public void movePiece(Move move, Board board)  {

        System.out.println("we r inside Pawn move piece method ...");

        //  CHECKS VALID COLOUR MOVE ...
        super.movePiece(move, board);

        int fromBoxNo = move.getFromBoxNo();
        int toBoxNo = move.getToBoxNo();

        boolean isNormalMove = Piece.isNthBitSet(
                AttackMasks.getPawnNormalMoves(
                        this.getColour(), fromBoxNo
                ), toBoxNo);

        boolean isAttackMove = Piece.isNthBitSet(
                AttackMasks.getPawnAttackMoves(
                        this.getColour(), fromBoxNo
                ), toBoxNo);

        if(!isNormalMove & !isAttackMove) {
            throw new InvalidMoveException("Invalid move.");
        }

        boolean occupied = Piece.isNthBitSet(
                board.getOccupancyBitboard().bitboard, toBoxNo
        );
        boolean occupiedColour = Piece.isNthBitSet(
                board.getBlackOccupancyBitboard().bitboard, toBoxNo
        );

        Piece pieceInToBox = move.getCaptured();
        System.out.println("hi");
        if(occupied) {
            if(this.getColour() == occupiedColour)
                throw new InvalidMoveException("Invalid move: The box is occupied.");

            else if(isAttackMove){      // TODO --> DONE : ATTACK --> its just a valid move for now until we maintain separate bitboards for each piece type ... :)
                if(pieceInToBox != null) {      // Redundant if ...
                    move.setCaptureMove(true);
                    pieceInToBox.removePiece(toBoxNo);
                }
//                move.setCaptured();       TODO ... setCaptured accepts ChessPiece ... + Unset occupancy bitboard of piece to be removed
            }
            else
                throw new InvalidMoveException("Invalid move: Attack move cannot be done unless an opponent piece is there");
        }
            //  TODO --> DONE : VALID MOVE
        this.setBoxNo(toBoxNo);

        board.setOccupancyBitboard(toBoxNo);
        board.unsetOccupancyBitboard(fromBoxNo);

        if(this.getColour()) {
            board.setBlackOccupancyBitboard(toBoxNo);
            board.unsetBlackOccupancyBitboard(fromBoxNo);

            board.setBlackPawnOccupancyBitboard(toBoxNo);
            board.unsetBlackPawnOccupancyBitboard(fromBoxNo);
            //      TODO : If attacked implicitly we can un-set the occ bit board of the attacked colour (not attacking...) by just --> whitebb = ~blackbb & whitebb
       }
        else {
            board.setWhiteOccupancyBitboard(toBoxNo);
            board.unsetWhiteOccupancyBitboard(fromBoxNo);

            board.setWhitePawnOccupancyBitboard(toBoxNo);
            board.unsetWhitePawnOccupancyBitboard(fromBoxNo);
            //      TODO : If attacked implicitly we can un-set the occ bit board of the attacked colour (not attacking...) by just --> blackbb = ~whitebb & blackbb
        }

        //          WE ARE CHANGING THE DISPLAY AFTER VALIDATING THE MOVE --> GOOD PRACTICE
        board.movePieceOnDisplayBoard(
                fromBoxNo, toBoxNo
        );
    }




}
