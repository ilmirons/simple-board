package com.eng.interview.simpleboard.impl;

import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.piece.Piece;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@RequiredArgsConstructor
public class Square {

  @Getter @NotNull
  private final Color
      color; // knowing the color could be useful for a better visualization of the board, depending
             // on BoardPrinter implementation

  @Setter private Piece piece;
  
  /**
   * Get the piece that is currently on this square, if any.
   * @return the piece that is currently on this square, or empty if there is none
   */

  // Using an Optional makes generating valid moves easier for most pieces.
  public Optional<Piece> getPiece() {
    return Optional.ofNullable(piece);
  }

  public boolean isEmpty() {
    return piece == null;
  }
}
