package com.eng.interview.simpleboard.piece;

import com.eng.interview.simpleboard.api.Coords;
import com.eng.interview.simpleboard.api.SimpleBoard;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

final
class PieceUtil {

  private PieceUtil() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  /**
   * Utility method useful to generate valid moves for a piece while we generate possible moves in a
   * for loop this method check if the square is empty or occupied. If it is occupied I can break
   * the loop (cannot reach any further square in that direction) According to the color of the
   * piece in the square I know if I can land on that square
   *
   * @param thisPieceCoords starting position of the piece
   * @param coords coords to be added to legal moves
   * @param board the board the move is performed on
   * @param result the set of legal moves
   * @return true if the loop should be stopped, false otherwise
   */
  static boolean checkEmptyAndSignalStop(
      Coords thisPieceCoords, @NotNull Coords coords, SimpleBoard board, Set<Coords> result) {
    if (coords.equals(thisPieceCoords)) {
      return false; // skip starting position in the path of the move
    }
    var thisPiece = board.getPiece(thisPieceCoords).orElseThrow();
    if (board.getSquare(coords).isEmpty()) {
      result.add(coords);
      return false; // instructing for to continue
      // different color piece, we can still land on this coords capturing orElse => square is
      // empty, we already checked.
    } else if (board.getPiece(coords).map(p -> p.differentColor(thisPiece)).orElse(true)) {
      result.add(coords);
      return true; // instructing for to stop
    } else { // same color piece
      return true; // instructing for to stop
    }
  }
}
