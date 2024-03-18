package chess.game;

import chess.board.BoardPrinter;
import chess.moves.Move;
import chess.pieces.*;

//      TODO : I'm going to take out the responsibility of move execution from input-parser


public class InputParser {
    private final BoardPrinter boardPrinter;

    public InputParser(BoardPrinter boardPrinter) {
        this.boardPrinter = boardPrinter;
    }

    public Move parseMove(String from, String to) throws NoPieceException {
        // Extract the file and rank from the input strings
        char fromFile = from.charAt(0);
        char fromRank = from.charAt(1);
        char toFile = to.charAt(0);
        char toRank = to.charAt(1);

        int fromBoxNo = toBoxNumber(fromFile,fromRank);
        int toBoxNo = toBoxNumber(toFile,toRank);
        System.out.print("from box : " + fromBoxNo + " to box : " + toBoxNo);

        boolean isCaptured = true;
        // Identify the chess piece at the 'from' AND 'to' position
        Piece pieceInFromBox = identifyChessPiece(fromFile, fromRank);
        Piece pieceInToBox = null;
        Move move;

        try {
            pieceInToBox = identifyChessPiece(toFile, toRank);
        }
        catch (NoPieceException e) {
            isCaptured = false;

        }

        move = new Move(pieceInFromBox, fromBoxNo, toBoxNo,isCaptured, pieceInToBox );

        return move;
    }

    private Piece identifyChessPiece(char file, char rank) throws NoPieceException {
        // Get the piece from the BoardPrinter object
        int boxNo = toBoxNumber(file, rank);
        int row = getRowIndex(rank);
        int col = getFileIndex(file);
        String pieceUnicode = boardPrinter.getElement(row, col);
        int codePoint = pieceUnicode.codePointAt(0);
        boolean colour = true;
        //      White colour unicode lie between this range ...
        if (codePoint >= 0x2654 && codePoint <= 0x265F) {
            colour = false;
        } // false --> White ...

        long bitRepresentation = BitboardGenerator.generateBitboard(row, col);
        Bitboard bbPosition = new Bitboard(bitRepresentation, colour);

        if (codePoint >= 0x265A && codePoint <= 0x265F) {
            // Piece is black
            switch (pieceUnicode) {
                case "♟":
                    System.out.println("Heyy did u choose ♟ " + "\nrow : " + row + "  col : " + col);
                    return new Pawn(boxNo, true);
                case "♜":
                    System.out.println("Heyy did u choose ♜ " + "\nrow : " + row + "  col : " + col);
                    return new Rook(boxNo, true);
                case "♞":
                    System.out.println("Heyy did u choose ♞" + "\nrow : " + row + "  col : " + col);
                    return new Knight(boxNo, true);
                case "♝":
                    System.out.println("Heyy did u choose ♝" + "\nrow : " + row + "  col : " + col);
                    return new Bishop(boxNo, true);
                case "♛":
                    System.out.println("Heyy did u choose ♛" + "\nrow : " + row + "  col : " + col);
                    return new Queen(boxNo, true);
                case "♚":
                    System.out.println("Heyy did u choose ♚" + "\nrow : " + row + "  col : " + col);
                    return new King(boxNo, true);
            }
        }
        //      WHITE PIECES
        if (codePoint >= 0x2654 && codePoint <= 0x265F) {
            // White pieces range: U+2654 to U+265F
            switch (pieceUnicode) {

                case "♙":
                    System.out.println("Heyy did u choose ♙" + "\nrow : " + row + "  col : " + col);
                    return new Pawn(boxNo, false);
                case "♖":
                    System.out.println("Heyy did u choose ♖" + "\nrow : " + row + "  col : " + col);
                    return new Rook(boxNo, false);
                case "♘":
                    System.out.println("Heyy did u choose ♘" + "\nrow : " + row + "  col : " + col);
                    return new Knight(boxNo, false);
                case "♗":
                    System.out.println("Heyy did u choose ♗" + "\nrow : " + row + "  col : " + col);
                    return new Bishop(boxNo, false);
                case "♕":
                    System.out.println("Heyy did u choose ♕" + "\nrow : " + row + "  col : " + col);
                    return new Queen(boxNo, false);
                case "♔":
                    System.out.println("Heyy did u choose ♔" + "\nrow : " + row + "  col : " + col);
                    return new King(boxNo, false);
            }
        }

        throw new NoPieceException("The chosen box has no piece ...");
    }


//    private String generateMove(ChessPiece piece, char fromFile, char fromRank, char toFile, char toRank) {
//        // Logic to generate the move based on the piece and positions
//        // You can implement your logic here
//        return "Move " + piece.getName() + " from " + fromFile + fromRank + " to " + toFile + toRank; // Example: Generate a simple move message
//    }

    private int getRowIndex(char rank) {
        // Convert the rank character to the row index
        return Character.getNumericValue(rank) - 1;
    }

    private int getFileIndex(char file) {
        // Convert the file character to the column index
        if (Character.isUpperCase(file)) {
            return file - 'A';
        } else {
            return file - 'a';
        }
    }

    private int toBoxNumber(char file, char rank) {
        int fileIndex = getFileIndex(file);
        int rankIndex = getRowIndex(rank);
        return rankIndex * 8 + fileIndex;
    }

    class NoPieceException extends Throwable {
        public NoPieceException(String s) {
        }
    }
}
