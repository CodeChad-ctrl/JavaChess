package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Rook  extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-8, -1, 1 , 8};


    Rook(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateCoordinatesOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {

            int candidateDestinationCoordinates = this.piecePosition;

            while (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinates)) {
                candidateDestinationCoordinates += candidateCoordinatesOffset;

                if (isFirsTColumnExclusion(candidateDestinationCoordinates, candidateCoordinatesOffset) || (isEigthColumnExclusion(candidateDestinationCoordinates, candidateCoordinatesOffset))) {
                    break;
                }

                if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinates)) {

                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinates);

                    if (!candidateDestinationTile.isTileOccupied()) {

                        legalMoves.add(new Move.MajorMove(candidateDestinationCoordinates, this, board));

                    } else {
                        // get the piece at that location
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        /// get the alliance of that piece.
                        final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                        if (this.pieceAlliance != pieceAlliance) {
                            // if the alliance is not equal to the alliance of the piece then we will add the move to the list of legal moves
                            legalMoves.add(new Move.AttackMove(candidateDestinationCoordinates, this, board, pieceAtDestination));
                        }
                        break;
                    }

                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    private static boolean isFirsTColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUM[currentPosition] && (candidateOffset == -1);

    }

    private static boolean isEigthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUM[currentPosition] && (candidateOffset == 1) ;

    }

}

