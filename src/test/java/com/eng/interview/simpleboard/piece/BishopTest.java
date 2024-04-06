package com.eng.interview.simpleboard.piece;

import static org.junit.jupiter.api.Assertions.*;
import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.Coords;
import com.eng.interview.simpleboard.impl.BoardImpl;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

  @Test
  @DisplayName("Bishop at center of the board")
  void getLegalMoves() {
    // given
    var bishop = new Bishop(Color.WHITE, false);
    var board = new BoardImpl();
    var currentPos = new Coords('d', 4);
    board.setPiece(currentPos, bishop);

    // when
    var legalMoves = bishop.getLegalMoves(board, currentPos);

    // then (central position, 4 moves in each direction)
    assertEquals(
        Set
            .of(
            new Coords('a', 1),
            new Coords('b', 2),
            new Coords('c', 3),
            new Coords('e', 5),
            new Coords('f', 6),
            new Coords('g', 7),
            new Coords('h', 8),
            new Coords('a', 7),
            new Coords('b', 6),
            new Coords('c', 5),
            new Coords('e', 3),
            new Coords('f', 2),
            new Coords('g', 1)).stream().sorted().collect(Collectors.toList()), 
        legalMoves.stream().sorted().collect(Collectors.toList()));
    
    // left bottom corner position
    board.removePiece(currentPos);
    currentPos = new Coords('a', 1);
    board.setPiece(currentPos, bishop);
    
    assertEquals(
        Set
            .of(
            new Coords('b', 2),
            new Coords('c', 3),
            new Coords('d', 4),
            new Coords('e', 5),
            new Coords('f', 6),
            new Coords('g', 7),
            new Coords('h', 8)).stream().sorted().collect(Collectors.toList()), 
        bishop.getLegalMoves(board, currentPos).stream().sorted().collect(Collectors.toList()));
    
    // right bottom corner position
    
    board.removePiece(currentPos);
    currentPos = new Coords('h', 1);
    board.setPiece(currentPos, bishop);
    
    assertEquals(
        Set
            .of(
            new Coords('g', 2),
            new Coords('f', 3),
            new Coords('e', 4),
            new Coords('d', 5),
            new Coords('c', 6),
            new Coords('b', 7),
            new Coords('a', 8)).stream().sorted().collect(Collectors.toList()), 
        bishop.getLegalMoves(board, currentPos).stream().sorted().collect(Collectors.toList()));
  }
  
@Test
  @DisplayName("Bishops at initial position")
  void getLegalMovesInitialPosition() {
    // given
    var bishop = new Bishop(Color.WHITE, false);
    var blackBishop = new Bishop(Color.BLACK, false);
    var board = new BoardImpl();
    board.setup();
    var currentPos = new Coords('c', 1);
    var blackCurrentPos = new Coords('c', 8);

    // when
    var legalMoves = bishop.getLegalMoves(board, currentPos);
    var blackLegalMoves = blackBishop.getLegalMoves(board, blackCurrentPos);

    // then
    assertEquals(
        Set.of(), legalMoves);
    assertEquals(
        Set.of(), blackLegalMoves);
  }
}
