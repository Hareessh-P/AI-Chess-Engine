package chess.game;

import chess.board.BoardPrinter;
import chess.moves.Move;
import chess.pieces.*;

public class InputParser {
    private BoardPrinter boardPrinter;

    public InputParser(BoardPrinter boardPrinter) {
        this.boardPrinter = boardPrinter;
    }

    public Move parseMove(String from, String to) {
        // Extract the file and rank from the input strings
        char fromFile = from.charAt(0);
        char fromRank = from.charAt(1);
        char toFile = to.charAt(0);
        char toRank = to.charAt(1);

        // Identify the chess piece at the 'from' position
        ChessPiece piece = identifyChessPiece(fromFile, fromRank);

        int fromBoxNo = toBoxNumber(fromFile,fromRank);
        int toBoxNo = toBoxNumber(toFile,toRank);

        //      Hard-coded VALUES !!!   TODO
        Move move = new Move(piece, fromBoxNo, toBoxNo,false, null );

        return move;
    }

    private ChessPiece identifyChessPiece(char file, char rank) {
        // Get the piece from the BoardPrinter object
        int row = getRowIndex(rank);
        int col = getFileIndex(file);
        String pieceUnicode = boardPrinter.getElement(row, col);
        int codePoint = pieceUnicode.codePointAt(0);
        boolean colour = true;
        //      White colour unicode lie between this range ...
        if (codePoint >= 0x2654 && codePoint <= 0x265F) colour = false;  // false --> White ...

        long bitRepresentation = BitboardGenerator.generateBitboard(row, col);
        Bitboard bbPosition = new Bitboard(bitRepresentation, colour);

        if (codePoint >= 0x265A && codePoint <= 0x265F) {
            // Piece is black
            switch (pieceUnicode) {
                case "♟":
                    return new Pawn(bbPosition);
                case "♜":
                    return new Rook(bbPosition);
                case "♞":
                    return new Knight(bbPosition);
                case "♝":
                    return new Bishop(bbPosition);
                case "♛":
                    return new Queen(bbPosition);
                case "♚":
                    return new King(bbPosition);
            }
        }
        //      WHITE PIECES
        if (codePoint >= 0x2654 && codePoint <= 0x265F) {
            // White pieces range: U+2654 to U+265F
            switch (pieceUnicode) {
                case "♙":
                    return new Pawn(bbPosition);
                case "♖":
                    return new Rook(bbPosition);
                case "♘":
                    return new Knight(bbPosition);
                case "♗":
                    return new Bishop(bbPosition);
                case "♕":
                    return new Queen(bbPosition);
                case "♔":
                    return new King(bbPosition);
            }
        }
        return null;
    }


//    private String generateMove(ChessPiece piece, char fromFile, char fromRank, char toFile, char toRank) {
//        // Logic to generate the move based on the piece and positions
//        // You can implement your logic here
//        return "Move " + piece.getName() + " from " + fromFile + fromRank + " to " + toFile + toRank; // Example: Generate a simple move message
//    }

    private int getRowIndex(char rank) {
        // Convert the rank character to the row index
        return 8 - Character.getNumericValue(rank);
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
}
