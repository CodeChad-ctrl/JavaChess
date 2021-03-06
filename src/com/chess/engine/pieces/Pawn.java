package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Pawn extends Piece{

    private final static int[] CANDIDATE_MOVE_COORDINATE = {8};

    Pawn( final int piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList();

        /*
        Loop through the Array of Candidate move coordinates
         */

        for(final int currentCandidateOffset : CANDIDATE_MOVE_COORDINATE) {
            int candidateDestinationCoordinates = this.piecePosition + (this.getPieceAlliance().getDirection() * currentCandidateOffset) ;

            if(!BoardUtils.isValidTileCoordinate(candidateDestinationCoordinates)) {
                continue;
            }

            if (currentCandidateOffset == 8 && !board.getTile(candidateDestinationCoordinates).isTileOccupied()) {
                //TODO more work (need to deal with promotions)!!!
                legalMoves.add(new Move.MajorMove(candidateDestinationCoordinates, this, board));

            } else if(currentCandidateOffset == 16 && this.isFirstMove()
                    && (BoardUtils.SECOND_ROW[this.piecePosition]) && this.getPieceAlliance().isBlack()
                    || (BoardUtils.SEVENTH_ROW[this.piecePosition]) && this.getPieceAlliance().isWhite()) {

                final int behindCandidateDestinationCoordinate = this.piecePosition + (this.pieceAlliance.getDirection() * 8);

                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied()
                        && !board.getTile(candidateDestinationCoordinates).isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(candidateDestinationCoordinates, this, board));

                }

            }




        }

        return legalMoves;
    }
}
