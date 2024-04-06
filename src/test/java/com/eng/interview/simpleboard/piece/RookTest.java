package com.eng.interview.simpleboard.piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.Coords;
import com.eng.interview.simpleboard.impl.BoardImpl;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {

  @Test
  @DisplayName("enumerate all legal moves for a rook, empty board")
  void getLegalMoves() {
    // given
    var rook = new Rook(Color.WHITE, false);
    var board = new BoardImpl();
    var currentPos = new Coords('d', 4);
    board.setPiece(currentPos, rook);

    // when
    var legalMoves = rook.getLegalMoves(board, currentPos);

    // then (central position, 4 moves in each direction)
    assertEquals(
        Set.of(
            new Coords('d', 1),
            new Coords('d', 2),
            new Coords('d', 3),
            new Coords('d', 5),
            new Coords('d', 6),
            new Coords('d', 7),
            new Coords('d', 8),
            new Coords('a', 4),
            new Coords('b', 4),
            new Coords('c', 4),
            new Coords('e', 4),
            new Coords('f', 4),
            new Coords('g', 4),
            new Coords('h', 4)).stream().sorted().collect(Collectors.toList()), 
        legalMoves.stream().sorted().collect(Collectors.toList()));

    // left bottom corner position

    board.removePiece(currentPos);
    currentPos = new Coords('a', 1);
    board.setPiece(currentPos, rook);
    
    legalMoves = rook.getLegalMoves(board, currentPos);
    assertEquals(
        Set.of(
            new Coords('a', 2),
            new Coords('a', 3),
            new Coords('a', 4),
            new Coords('a', 5),
            new Coords('a', 6),
            new Coords('a', 7),
            new Coords('a', 8),
            new Coords('b', 1),
            new Coords('c', 1),
            new Coords('d', 1),
            new Coords('e', 1),
            new Coords('f', 1),
            new Coords('g', 1),
            new Coords('h', 1)),
        legalMoves);
    
    // right bottom corner position
    
    board.removePiece(currentPos);
    currentPos = new Coords('h', 1);
    board.setPiece(currentPos, rook);
    
    legalMoves = rook.getLegalMoves(board, currentPos);
    assertEquals(
        Set.of(
            new Coords('h', 2),
            new Coords('h', 3),
            new Coords('h', 4),
            new Coords('h', 5),
            new Coords('h', 6),
            new Coords('h', 7),
            new Coords('h', 8),
            new Coords('a', 1),
            new Coords('b', 1),
            new Coords('c', 1),
            new Coords('d', 1),
            new Coords('e', 1),
            new Coords('f', 1),
            new Coords('g', 1)),
        legalMoves);
    
    // left top corner position
    
    board.removePiece(currentPos);
    currentPos = new Coords('a', 8);
    board.setPiece(currentPos, rook);
    
    legalMoves = rook.getLegalMoves(board, currentPos);
    assertEquals(
        Set.of(
            new Coords('a', 1),
            new Coords('a', 2),
            new Coords('a', 3),
            new Coords('a', 4),
            new Coords('a', 5),
            new Coords('a', 6),
            new Coords('a', 7),
            new Coords('b', 8),
            new Coords('c', 8),
            new Coords('d', 8),
            new Coords('e', 8),
            new Coords('f', 8),
            new Coords('g', 8),
            new Coords('h', 8)),
        legalMoves);
    
    // right top corner position
    
    board.removePiece(currentPos);
    currentPos = new Coords('h', 8);
    board.setPiece(currentPos, rook);
    
    legalMoves = rook.getLegalMoves(board, currentPos);
    assertEquals(
        Set.of(
            new Coords('h', 1),
            new Coords('h', 2),
            new Coords('h', 3),
            new Coords('h', 4),
            new Coords('h', 5),
            new Coords('h', 6),
            new Coords('h', 7),
            new Coords('a', 8),
            new Coords('b', 8),
            new Coords('c', 8),
            new Coords('d', 8),
            new Coords('e', 8),
            new Coords('f', 8),
            new Coords('g', 8)),
        legalMoves);
  }

  @Test
  @DisplayName("enumerate all legal moves for a rook, pieces in the middle of the way")
  void getLegalMoves2() {
    // given
    var rook = new Rook(Color.WHITE, false);
    var otherRookSameColor = new Rook(Color.WHITE, false);
    var otherRookOtherColor = new Rook(Color.BLACK, false);
    var board = new BoardImpl();
    var currentPos = new Coords('a', 1);
    
    var otherRookSameColorPos = new Coords('d', 1);
    var otherRookOtherColorPos = new Coords('a', 4);
    
    // when
    board.setPiece(currentPos, rook);
    board.setPiece(otherRookSameColorPos, otherRookSameColor);
    board.setPiece(otherRookOtherColorPos, otherRookOtherColor);
    var legalMoves = rook.getLegalMoves(board, currentPos);

    // left bottom corner position

    board.removePiece(currentPos);
    currentPos = new Coords('a', 1);
    board.setPiece(currentPos, rook);

    legalMoves = rook.getLegalMoves(board, currentPos);
    assertEquals(
        Set.of(
            new Coords('a', 2),
            new Coords('a', 3),
            new Coords('a', 4),
            
            new Coords('b', 1),
            new Coords('c', 1)
            ),
        legalMoves);
  }
}
