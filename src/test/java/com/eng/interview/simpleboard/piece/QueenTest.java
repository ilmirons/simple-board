package com.eng.interview.simpleboard.piece;

import static org.junit.jupiter.api.Assertions.*;
import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.Coords;
import com.eng.interview.simpleboard.impl.BoardImpl;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class QueenTest {

  @Test
  void getLegalMoves() {
    // given
    var queen = new Queen(Color.WHITE, false);
    var board = new BoardImpl();
    var currentPos = new Coords('d', 4);
    board.setPiece(currentPos, queen);

    // when
    var legalMoves = queen.getLegalMoves(board, currentPos);

    // then (central position))
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
                new Coords('h', 4),
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
                new Coords('g', 1))
            .stream()
            .sorted()
            .collect(Collectors.toList()),
        legalMoves.stream().sorted().collect(Collectors.toList()));
  }
}
