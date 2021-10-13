package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.chess.engine.board.BoardUtils;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Knight extends Piece {

    /*
    In a perfect World a knight on the board is going to have a maximum of a possible of 8 legal moves
     */
    private static final int [] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10 , 15, 17 };

    Knight(int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves( final Board board) {


        final List<Move> legalMoves = new ArrayList<>();
        /*
        We want to loop through these candidate offsets and then we want to go ahead and apply the offset to the current position.
        If this is a valid tile then we want to check if the tile is occupied we want to add this to our legal move list
         */
        for(final int currentCandidate: CANDIDATE_MOVE_COORDINATES) {

            final int candidateDestinationCoordinate = this.piecePosition + currentCandidate;

            if (BoardUtils.isValidTileCoordinate(candidateDestinationCoordinate)) {

                if (isFirstColumnExculsion(this.piecePosition, currentCandidate)
                        || isSecondColumnExclusion(this.piecePosition, currentCandidate)
                        || isSeventhColumnExclusion(this.piecePosition, currentCandidate)
                        || isEighthColumnExclusion(this.piecePosition, currentCandidate)) {
                    continue;
                }

                final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                if(!candidateDestinationTile.isTileOccupied()) {

                    legalMoves.add( new Move.MajorMove(candidateDestinationCoordinate, this, board));

                } else {
                    // get the piece at that location
                    final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                    /// get the alliance of that piece.
                    final Alliance pieceAlliance = pieceAtDestination.getPieceAlliance();

                    if (this.pieceAlliance != pieceAlliance) {
                        // if the alliance is not equal to the alliance of the piece then we will add the move to the list of legal moves
                        legalMoves.add(new Move.AttackMove(candidateDestinationCoordinate, this, board, pieceAtDestination));
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

    /*
    An array of booleans that would represent the columns.
     */
    private static boolean isFirstColumnExculsion(final int currentposition, final int candidateOffset) {
         return BoardUtils.FIRST_COLUM[currentposition] && ((candidateOffset == -17) || (candidateOffset == -10) || candidateOffset == 6
                || candidateOffset == 15);
    }

    private static boolean isSecondColumnExclusion (final int currentposition, final int candidateOffset) {
        return BoardUtils.SECOND_COLUM[currentposition] && ((candidateOffset == -10) || candidateOffset == 6);
    }

    private static boolean isSeventhColumnExclusion (final int currentposition, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUM[currentposition] && ((candidateOffset == -6) || candidateOffset == 10);
    }

    private static boolean isEighthColumnExclusion (final int currentposition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUM[currentposition] && ((candidateOffset == -15) || (candidateOffset == -6) || (candidateOffset == 10) || (candidateOffset == 17));
    }




}
