package com.eng.interview.simpleboard.piece;

import com.eng.interview.simpleboard.api.SimpleBoard;
import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.Coords;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public record King(Color color, boolean moved) implements Piece {

  @Override
  public Set<Coords> getLegalMoves(SimpleBoard board, Coords currentPos) {
    return Stream.of(
            currentPos.n(1, board),
            currentPos.ne(1, board),
            currentPos.e(1, board),
            currentPos.se(1, board),
            currentPos.s(1, board),
            currentPos.sw(1, board),
            currentPos.w(1, board),
            currentPos.nw(1, board)
            // TODO Castling (out of scope)
            //  currentPos.e(2, board),
            //  currentPos.w(2, board)
            // note that castling is the only case where another piece can get in the way
            // and we should check for squares under check too
            ).flatMap(Optional::stream)
        // filter out coords that are occupied by the same color
        .filter(
            coords -> board.getPiece(coords).map(this::differentColor).orElse(true)) // empty square
        // TODO check if king is in check (out of scope)
        // TODO check if king has not moved and rook has not moved to filter castling (out of scope)
        .collect(Collectors.toSet());
  }

  @Override
  public Piece moved(boolean moved) {
    return new King(color, moved);
  }
}
