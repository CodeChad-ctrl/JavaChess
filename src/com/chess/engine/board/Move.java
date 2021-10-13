package com.chess.engine.board;

/*
Incremental build on this class
 */

import com.chess.engine.pieces.Piece;

public abstract class Move {

    final Board board;
    final Piece piece;
    final int destinationCoordinate;

    private Move(final int destinationCoordinate, Piece piece, final Board board) {
        this.board = board;
        this.piece = piece;
        this.destinationCoordinate = destinationCoordinate;

    }

    /*
    Create the relevant subclasses of the parent class Move
     */

    public static final class MajorMove extends Move {

        public MajorMove(final int destinationCoordinate,
                         final Piece piece,
                         final Board board) {
            super(destinationCoordinate, piece, board);
        }
    }

    public static final class AttackMove extends Move {

        final Piece attackedPiece;

        public AttackMove(final int destinationCoordinate,
                          final Piece piece,
                          final Board board,
                          final Piece attackedPiece) {
            super(destinationCoordinate, piece, board);
            this.attackedPiece = attackedPiece;
        }
    }




}
