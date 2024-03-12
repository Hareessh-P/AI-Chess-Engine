package chess.pieces;

import java.util.NoSuchElementException;

public class ChessPiece implements Piece {
    private Bitboard bbPosition;
    private int boxNumber;



    public ChessPiece(Bitboard bbPosition) {
        this.boxNumber = computeBoxNumber(bbPosition);
        this.bbPosition = bbPosition;
    }

    public Bitboard getBbPosition() {
        return bbPosition;
    }

    public void setBbPosition(Bitboard bbPosition) {

        this.bbPosition = bbPosition;
        this.boxNumber = computeBoxNumber(bbPosition);
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }
    public int getBoxNumber() {
        return boxNumber;
    }


    public int computeBoxNumber(Bitboard bbPosition) {
        int position = 0;
        long bit = bbPosition.getBitboard();
        if(bit == 0) return 0;
        while ((bit & 1) != 1) {
            bit >>= 1;
            position++;
        }
        if(position > 63 || position < 0) throw new NoSuchElementException("Box position not found");
        return position;
    }

    @Override       // TODO
    public long movePiece(Bitboard from, Bitboard to, long bitOccupancy) {
        this.bbPosition = to;
        bitOccupancy = bitOccupancy & (~from.getBitboard()); // WTH !! Worst Naming ************
        bitOccupancy = bitOccupancy | to.getBitboard();

        return bitOccupancy;
//        bbOccupancy.andNot(from);
//        bbOccupancy.setBit(to);
    }

    //        String binaryRepOcc = Long.toBinaryString(bbOccupancy.getBitboard());
//        int toBoxNo = computeBoxNumber(to,COLOUR);
//
//        if (binaryRepOcc.charAt(toBoxNo) == '0') {
//            this.setBbPosition(to);
//        } else {
//            throw new IllegalArgumentException("Occupied position detected");
//        }

}
