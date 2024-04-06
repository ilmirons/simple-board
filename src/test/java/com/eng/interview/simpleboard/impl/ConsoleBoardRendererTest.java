package com.eng.interview.simpleboard.impl;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ConsoleBoardRendererTest {
  
  @Test
  @DisplayName("Render empty board")
  void renderEmptyBoard() {
    // given
    var board = new BoardImpl();
    var renderer = new ConsoleBoardPrinter();
    
    // when
    var result = renderer.print(board);

    // then
    assertEquals(
        """
          +---+---+---+---+---+---+---+---+
        8 |   |   |   |   |   |   |   |   |\s
          +---+---+---+---+---+---+---+---+
        7 |   |   |   |   |   |   |   |   |\s
          +---+---+---+---+---+---+---+---+
        6 |   |   |   |   |   |   |   |   |\s
          +---+---+---+---+---+---+---+---+
        5 |   |   |   |   |   |   |   |   |\s
          +---+---+---+---+---+---+---+---+
        4 |   |   |   |   |   |   |   |   |\s
          +---+---+---+---+---+---+---+---+
        3 |   |   |   |   |   |   |   |   |\s
          +---+---+---+---+---+---+---+---+
        2 |   |   |   |   |   |   |   |   |\s
          +---+---+---+---+---+---+---+---+
        1 |   |   |   |   |   |   |   |   |\s
          +---+---+---+---+---+---+---+---+
            a   b   c   d   e   f   g   h
        """,
        result);
  }

  @Test
  @DisplayName("Render start position")
  void renderInitialSetup() {
    // given
    var board = new BoardImpl();
    var renderer = new ConsoleBoardPrinter();
    board.setup();

    // when
    var result = renderer.print(board);

    // then
    assertEquals(
        """
          +---+---+---+---+---+---+---+---+
        8 | r | n | b | q | k | b | n | r |\s
          +---+---+---+---+---+---+---+---+
        7 | p | p | p | p | p | p | p | p |\s
          +---+---+---+---+---+---+---+---+
        6 |   |   |   |   |   |   |   |   |\s
          +---+---+---+---+---+---+---+---+
        5 |   |   |   |   |   |   |   |   |\s
          +---+---+---+---+---+---+---+---+
        4 |   |   |   |   |   |   |   |   |\s
          +---+---+---+---+---+---+---+---+
        3 |   |   |   |   |   |   |   |   |\s
          +---+---+---+---+---+---+---+---+
        2 | P | P | P | P | P | P | P | P |\s
          +---+---+---+---+---+---+---+---+
        1 | R | N | B | Q | K | B | N | R |\s
          +---+---+---+---+---+---+---+---+
            a   b   c   d   e   f   g   h
        """,
        result);
  }
  
  
}
