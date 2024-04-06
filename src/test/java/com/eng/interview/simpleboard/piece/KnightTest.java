package com.eng.interview.simpleboard.piece;

import static org.junit.jupiter.api.Assertions.*;
import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.Coords;
import com.eng.interview.simpleboard.impl.BoardImpl;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {

  @Test
  @DisplayName("Knight at center of the board")
  void getLegalMoves() {

    // Given
    var knight = new Knight(Color.WHITE, false);
    var board = new BoardImpl();
    var currentPos = new Coords('d', 4);

    // When
    board.setPiece(currentPos, knight);
    var legalMoves = knight.getLegalMoves(board, currentPos);

    assertEquals(
        List.of(
            new Coords('b', 3),
            new Coords('b', 5),
            new Coords('c', 2),
            new Coords('c', 6),
            new Coords('e', 2),
            new Coords('e', 6),
            new Coords('f', 3),
            new Coords('f', 5)),
        legalMoves.stream().sorted().toList());
  }

  @Test
  @DisplayName("Knight at initial position")
  void getLegalMoves2() {

    // Given
    var board = new BoardImpl();
    board.setup();
    var currentPos = new Coords('b', 1);
    
    var knight = board.getPiece(currentPos).orElseThrow();
    
    assertInstanceOf(Knight.class,knight);

    // When
    var legalMoves = knight.getLegalMoves(board, currentPos);

    assertEquals(
        List.of(
            new Coords('a', 3),
            new Coords('c', 3)),
        legalMoves.stream().sorted().toList());
  }
}
