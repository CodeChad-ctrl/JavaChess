package com.chess.engine.board;

public class BoardUtils {

    public static final boolean[] FIRST_COLUM = initColumn(0);
    public static final  boolean [] SECOND_COLUM = initColumn(1);
    public static final boolean [] SEVENTH_COLUM = initColumn(6) ;
    public static final boolean [] EIGHTH_COLUM = initColumn(7);

    public static final boolean[] SECOND_ROW = null;
    public static final boolean[] SEVENTH_ROW = null;

    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;

    private BoardUtils() {
        throw new RuntimeException("This class cannot be instantiated!");
    }


    public static boolean isValidTileCoordinate(int coordinate) {

        return coordinate >=0 && coordinate < 64;

    }

    private static boolean[] initColumn( int columnNumber) {
        // declare an array of booleans of size 64
        final boolean[] column = new boolean[64];
        // takes a single parameter
        do {
            column[columnNumber] = true;
            columnNumber +=8;
        } while(columnNumber < NUM_TILES);


        return column;
    }
}
