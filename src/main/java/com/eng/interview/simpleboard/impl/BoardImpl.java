package com.eng.interview.simpleboard.impl;

import static com.eng.interview.simpleboard.api.Color.BLACK;
import static com.eng.interview.simpleboard.api.Color.WHITE;
import static com.eng.interview.simpleboard.api.PieceType.*;
import com.eng.interview.simpleboard.api.SimpleBoard;
import com.eng.interview.simpleboard.api.Coords;
import com.eng.interview.simpleboard.api.PieceType;
import com.eng.interview.simpleboard.piece.PieceFactory;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class BoardImpl implements SimpleBoard {
  
  private final List<PieceType> piecesSequence = List.of(
      R, N, B, Q, K, B, N, R
  ); 

  Square[][] square = new Square[getSize()][getSize()];

  public BoardImpl() {
    for (int i = 0; i < getSize(); i++) {
      for (int j = 0; j < getSize(); j++) {
        square[i][j] =
            new Square((i + j) % 2 == 0 ? BLACK : WHITE);
      }
    }
  }
  
  public void setup() {
    for (int j = 0; j < 8; j++) {
      // visualize the way they are printed
      square[getSize() - 1][j].setPiece(PieceFactory.get(piecesSequence.get(j), BLACK));
      square[getSize() - 2][j].setPiece(PieceFactory.get(P, BLACK));
      square[1][j].setPiece(PieceFactory.get(P, WHITE));
      square[0][j].setPiece(PieceFactory.get(piecesSequence.get(j), WHITE));
    }
  }

  @Override
  public Square getSquare(@NotNull Coords coords) {
    if (!isInBounds(coords)) {
      throw new IllegalArgumentException("Coordinates out of bounds");
    }
    return square[coords.toIndexes().i()][coords.toIndexes().j()]; 
  }
}
