package com.eng.interview.simpleboard.piece;

import com.eng.interview.simpleboard.api.SimpleBoard;
import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.Coords;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Pawn(Color color, boolean moved) implements Piece {

  @Override
  @Contract(pure = true)
  public @NotNull Set<Coords> getLegalMoves(@NotNull SimpleBoard board, @NotNull Coords currentPos) {

    if (color == Color.WHITE) {
      return getWhiteMoves(board, currentPos);
    } else {
      return getBlackMoves(board, currentPos);
    }

    // TODO promotion (out of scope)
    // TODO en passant (out of scope)
  }

  private @NotNull Set<Coords> getBlackMoves(SimpleBoard board, @NotNull Coords currentPos) {
    Set<Coords> result = new HashSet<>();

    // move south 1
    currentPos
        .s(1, board)
        .filter(coords -> board.getSquare(coords).isEmpty())
        .ifPresent(result::add);

    // move south 2
    if (!moved && result.size() == 1) { 
      // square in front is empty and we have not moved yet
      // (note this could be buggy: we are assuming !moved <=> in starting position)
      currentPos
          .s(2, board)
          .filter(coords -> board.getSquare(coords).isEmpty())
          .ifPresent(result::add);
    }

    // capture south/est
    currentPos
        .se(1, board)
        .filter(c -> board.getPiece(c).map(p -> p.differentColor(this)).orElse(false))
        .ifPresent(result::add);

    // capture south/west
    currentPos
        .sw(1, board)
        .filter(c -> board.getPiece(c).map(p -> p.differentColor(this)).orElse(false))
        .ifPresent(result::add);
    return result;
  }

  private @NotNull Set<Coords> getWhiteMoves(SimpleBoard board, @NotNull Coords currentPos) {
    Set<Coords> result = new HashSet<>();
    // move north 1
    currentPos
        .n(1, board)
        .filter(coords -> board.getSquare(coords).isEmpty())
        .ifPresent(result::add);

    // move north 2
    if (!moved && result.size() == 1) { 
      // square in front is empty and we have not moved yet 
      // (note this could be buggy: we are assuming !moved <=> in starting position)
      currentPos
          .n(2, board)
          .filter(coords -> board.getSquare(coords).isEmpty())
          .ifPresent(result::add);
    }

    // capture north/est
    currentPos
        .ne(1, board)
        .filter(c -> board.getPiece(c).map(p -> p.differentColor(this)).orElse(false))
        .ifPresent(result::add);

    // capture north/west
    currentPos
        .nw(1, board)
        .filter(c -> board.getPiece(c).map(p -> p.differentColor(this)).orElse(false))
        .ifPresent(result::add);
    return result;
  }

  @Override
  @Contract("_ -> new")
  public @NotNull Piece moved(boolean moved) {
    return new Pawn(color, moved);
  }
}
