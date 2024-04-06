package com.eng.interview.simpleboard.impl;

import static com.eng.interview.simpleboard.api.Color.BLACK;
import static com.eng.interview.simpleboard.api.Color.WHITE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.eng.interview.simpleboard.api.Board;
import com.eng.interview.simpleboard.api.Coords;
import com.eng.interview.simpleboard.api.Coords.Indexes;
import com.eng.interview.simpleboard.api.SimpleBoard;
import com.eng.interview.simpleboard.piece.Knight;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

  Board board;

  @BeforeEach
  void setUp() {
    board = new BoardImpl();
    board.setup();
  }

  @Test
  @DisplayName("Square colors are correct")
  void coloring() {
    // given
    var board = new BoardImpl();

    assertSame(BLACK, board.getSquare(new Coords('a', 1)).getColor());

    for (var i = 0; i < 8; i++) {
      for (var j = 0; j < 8; j++) {
        var square = board.getSquare(new Indexes(i, j).toCoords());
        assertEquals((i + j) % 2 == 0 ? BLACK : WHITE, square.getColor());
      }
    }

    // when
    var square = board.getSquare(new Coords('a', 1));

    // then
    assertEquals(BLACK, square.getColor());
  }

  @Test
  @DisplayName("All pieces in first and last rank can't move except knights")
  void blockedMoves1() {
    // given
    var board = new BoardImpl();

    // when
    board.setup();

    // then
    for (var i : List.of(0, board.getSize() - 1)) {
      for (var j = 0; j < board.getSize(); j++) {
        var coords = new Indexes(i, j).toCoords();
        var piece = board.getPiece(coords).orElseThrow();
        var legalMoves = piece.getLegalMoves(board, coords);
        if (piece instanceof Knight) {
          assertEquals(2, legalMoves.size());
        } else {
          assertEquals(0, legalMoves.size(), piece::toString);
        }
      }
    }
  }

  @Test
  @DisplayName("Advancing black and white central pawns blocks their movement")
  void blockedMoves2() {
    // given
    var board = new BoardImpl();

    // when
    board.setup();

    board.movePiece(new Coords('e', 2), new Coords('e', 4)); // white king pawn
    board.movePiece(new Coords('e', 7), new Coords('e', 5)); // black king pawn

    // then
    assertThrows(
        IllegalArgumentException.class,
        () -> board.movePiece(new Coords('e', 4), new Coords('e', 5)));
    assertThrows(
        IllegalArgumentException.class,
        () -> board.movePiece(new Coords('e', 5), new Coords('e', 4)));
  }

  @Test
  @DisplayName("Can't move outside the board")
  void invalidMove1() {
    // given
    var board = new BoardImpl();

    // when
    board.setup();

    // then
    assertThrows(
        IllegalArgumentException.class,
        () -> board.movePiece(new Coords('a', 0), new Coords('a', 1)));
    
    assertThrows(
        IllegalArgumentException.class,
        () -> board.movePiece(new Coords('a', 8), new Coords('a', 9)));
  }

  @Test
  @DisplayName("Can't move from empty square the board")
  void invalidMove2() {
    // given
    var board = new BoardImpl();

    // when
    board.setup();

    // then
    assertThrows(
        IllegalArgumentException.class,
        () -> board.movePiece(new Coords('a', 3), new Coords('a', 4)));
  }

  @Test
  @DisplayName("Can't move from coords to same coords")
  void invalidMove3() {
    // given
    var board = new BoardImpl();

    // when
    board.setup();

    // then
    assertThrows(
        IllegalArgumentException.class,
        () -> board.movePiece(new Coords('a', 2), new Coords('a', 2)));
  }
}
