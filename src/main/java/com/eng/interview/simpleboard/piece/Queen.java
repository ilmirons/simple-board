package com.eng.interview.simpleboard.piece;

import com.eng.interview.simpleboard.api.SimpleBoard;
import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.Coords;
import java.util.Set;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Queen(Color color, boolean moved) implements Piece, RookMoves, BishopMoves {
  
  @Override
  public @NotNull Set<Coords> getLegalMoves(SimpleBoard board, Coords currentPos) {
    var result = getRookMoves(board, currentPos);
    result.addAll(getBishopMoves(board, currentPos));
    return result;
  }

  @Override
  @Contract("_ -> new")
  public @NotNull Piece moved(boolean moved) {
    return new Queen(color, moved);
  }

}
