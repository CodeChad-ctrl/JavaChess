package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;


public abstract  class Tile {

    /*
    I have declared the two subclasses in the class of tile so I have to make the two sub classes static
     */

    protected final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();

    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {

        /*
        This is from a 3rd Part Library.
        Every possible empty tile that could exist I have created it up front so I never have to construct it again.
        If I ever want to reference an empty tile I would just have to enter emptyTile.get[i]

         */

        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for(int i = 0; i < BoardUtils.NUM_TILES; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece !=null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);

    }


    private Tile(final int tileCoordinate) {
        this.tileCoordinate= tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    private static final class EmptyTile extends Tile {
        EmptyTile(final int coordinate ) {
            super(coordinate);
        }

         @Override
        public boolean isTileOccupied() {
            return false;
        }

         @Override
        public Piece getPiece() {
            return null;
         }

    }

    private static final class OccupiedTile extends Tile {
        // There is no way to reference this varaible outside the without using the getMethod for the piece
        private final Piece pieceOnTile;

        OccupiedTile(int tileCoordinate, final Piece pieceOnTile) {
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;

        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }


    }




}
