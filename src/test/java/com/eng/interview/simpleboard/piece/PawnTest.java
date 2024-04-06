package com.eng.interview.simpleboard.piece;

import static org.junit.jupiter.api.Assertions.*;
import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.Coords;
import com.eng.interview.simpleboard.impl.BoardImpl;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

  @Test
  @DisplayName("Pawn at initial position")
  void getLegalMoves() {
    // Given
    var pawn = new Pawn(Color.WHITE, false);
    var blackPawn = new Pawn(Color.BLACK, false);
    var board = new BoardImpl();
    var currentPos = new Coords('a', 2);
    var blackCurrentPos = new Coords('a', 7);

    // When
    board.setPiece(currentPos, pawn);
    board.setPiece(blackCurrentPos, blackPawn);

    var legalMoves = pawn.getLegalMoves(board, currentPos);
    var blackLegalMoves = blackPawn.getLegalMoves(board, blackCurrentPos);
    
    assertEquals(
        List.of(new Coords('a', 3), new Coords('a', 4)), legalMoves.stream().sorted().toList());

    assertEquals(
        List.of(new Coords('a', 5), new Coords('a', 6)),
        blackLegalMoves.stream().sorted().toList());
  }

  @Test
  @DisplayName("Moved pawn")
  void getLegalMovesMoved() {
    // Given
    var pawn = new Pawn(Color.WHITE, true);
    var blackPawn = new Pawn(Color.BLACK, true);
    var board = new BoardImpl();
    var currentPos = new Coords('a', 3);
    var blackCurrentPos = new Coords('a', 6);

    // When
    board.setPiece(currentPos, pawn);
    board.setPiece(blackCurrentPos, blackPawn);

    var legalMoves = pawn.getLegalMoves(board, currentPos);
    var blackLegalMoves = blackPawn.getLegalMoves(board, blackCurrentPos);

    assertEquals(Set.of(new Coords('a', 4)), legalMoves);

    assertEquals(Set.of(new Coords('a', 5)), blackLegalMoves);
  }

  @Test
  @DisplayName("Blocked pawn")
  void getLegalMovesBlockedPawn() {
    // Given
    var whitePawn = new Pawn(Color.WHITE, true);
    var blackPawn = new Pawn(Color.BLACK, true);
    var board = new BoardImpl();
    var whiteCurrentPos = new Coords('a', 3);
    var blackCurrentPos = new Coords('a', 4);

    // When
    board.setPiece(whiteCurrentPos, whitePawn);
    board.setPiece(blackCurrentPos, blackPawn);

    var legalMoves = whitePawn.getLegalMoves(board, whiteCurrentPos);

    assertEquals(Set.of(), legalMoves);
  }

  @Test
  @DisplayName("Blocked pawn")
  void getLegalMovesCapturePawn() {
    // Given
    var whitePawn = new Pawn(Color.WHITE, true);
    var blackPawn = new Pawn(Color.BLACK, true);
    var board = new BoardImpl();
    var whiteCurrentPos = new Coords('a', 3);
    var blackCurrentPos = new Coords('b', 4);

    // When
    board.setPiece(whiteCurrentPos, whitePawn);
    board.setPiece(blackCurrentPos, blackPawn);

    var whitePawnLegalMoves = whitePawn.getLegalMoves(board, whiteCurrentPos);
    var blackPawnLegalMoves = blackPawn.getLegalMoves(board, blackCurrentPos);

    assertEquals(Set.of(whiteCurrentPos, new Coords('b', 3)), blackPawnLegalMoves);
    assertEquals(Set.of(blackCurrentPos, new Coords('a', 4)), whitePawnLegalMoves);
  }

  @Test
  void moved() {
    assertInstanceOf(Pawn.class, new Pawn(Color.WHITE, false).moved(true));
    assertTrue(new Pawn(Color.WHITE, false).moved(true).moved());
    assertEquals(Color.WHITE, new Pawn(Color.WHITE, false).moved(true).color());
  }
}
