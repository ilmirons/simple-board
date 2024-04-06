package com.eng.interview.simpleboard.api;

import com.eng.interview.simpleboard.impl.Square;
import com.eng.interview.simpleboard.piece.Piece;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

public interface SimpleBoard extends Board {

  int DEFAULT_SIZE = 8;

  @Override
  default int getSize() {
    return DEFAULT_SIZE;
  }

  Square getSquare(Coords coords);

  default Optional<Piece> getPiece(Coords coords) {
    return getSquare(coords).getPiece();
  }

  @Override
  default void setPiece(Coords coords, Piece piece) {
    getSquare(coords).setPiece(piece);
  }

  @Override
  default void removePiece(Coords coords) {
    getSquare(coords).setPiece(null);
  }

  
  default boolean isInBounds(@NotNull Coords coords) {
    return coords.file() >= 'a'
        && coords.file() < 'a' + getSize()
        && coords.rank() >= 1
        && coords.rank() <= getSize();
  }
  
  default void movePiece(Coords from, Coords to) {
    getPiece(from)
        .ifPresentOrElse(p -> {
          validateMove(p, from, to);
          p.move(from, to, this);
        }, () -> {
          throw new IllegalArgumentException("No piece at " + from);
        });
  }
  
  default void validateMove(Piece piece, @NotNull Coords from, @NotNull Coords to) {
    if (!isInBounds(from) || !isInBounds(to)) {
      throw new IllegalArgumentException("Coordinates out of bounds");
    }
    if (from.equals(to)) {
      throw new IllegalArgumentException("Cannot move piece to the same square");
    }
    if (!piece.getLegalMoves(this, from).contains(to)) {
      throw new IllegalArgumentException("Illegal move from " + from + " to " + to + " for " + piece);
    }
  }
}
