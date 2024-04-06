package com.eng.interview.simpleboard.piece;

import com.eng.interview.simpleboard.api.SimpleBoard;
import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.Coords;
import java.util.Set;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Bishop(Color color, boolean moved) implements Piece, BishopMoves {

  @Override
  public Set<Coords> getLegalMoves(SimpleBoard board, Coords currentPos) {
    return getBishopMoves(board, currentPos);
  }

  @Override
  @Contract("_ -> new")
  public @NotNull Piece moved(boolean moved) {
    return new Bishop(color, moved);
  }

}
