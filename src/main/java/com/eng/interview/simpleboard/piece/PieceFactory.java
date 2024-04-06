package com.eng.interview.simpleboard.piece;

import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.PieceType;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

/**
 * Factory class to create pieces.
 */
@UtilityClass
public class PieceFactory {
  
  public static Piece get(@NotNull PieceType type, Color color) {
    return switch (type) {
      case K -> new King(color, false);
      case Q -> new Queen(color, false);
      case R -> new Rook(color, false);
      case B -> new Bishop(color, false);
      case N -> new Knight(color, false);
      case P -> new Pawn(color, false);
    };
  }
}