package com.eng.interview.simpleboard.piece;

import static org.junit.jupiter.api.Assertions.*;
import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.Coords;
import com.eng.interview.simpleboard.impl.BoardImpl;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class KingTest {

  @Test
  void getLegalMoves() {

    // given
    var king = new King(Color.WHITE, false);
    var board = new BoardImpl();
    var currentPos = new Coords('d', 4);
    board.setPiece(currentPos, king);

    // when
    var legalMoves = king.getLegalMoves(board, currentPos);

    // then (central position))
    assertEquals(
        Set
            .of(
                new Coords('d', 3),
                new Coords('d', 5),

                new Coords('c', 4),
                new Coords('e', 4),

                new Coords('c', 3),
                new Coords('e', 5),
                
                new Coords('c', 5),
                new Coords('e', 3)
                )
            .stream()
            .sorted()
            .collect(Collectors.toList()),
        legalMoves.stream().sorted().collect(Collectors.toList()));
    
  }
}
