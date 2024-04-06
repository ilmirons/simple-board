package com.eng.interview.simpleboard.piece;

import static com.eng.interview.simpleboard.piece.PieceUtil.checkEmptyAndSignalStop;
import com.eng.interview.simpleboard.api.SimpleBoard;
import com.eng.interview.simpleboard.api.Coords;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

sealed interface RookMoves permits Rook, Queen {

  default Set<Coords> getRookMoves(@NotNull SimpleBoard board, @NotNull Coords currentPos) {
    var result = new HashSet<Coords>();
    
    // north moves
    for (var rank = currentPos.rank() + 1; rank <= board.getSize(); rank++) {
      var coords = new Coords(currentPos.file(), rank);
      if (checkEmptyAndSignalStop(currentPos, coords, board, result)) break;
    }

    // south moves
    for (var rank = currentPos.rank() - 1; rank >= 1; rank--) {
      var coords = new Coords(currentPos.file(), rank);
      if (checkEmptyAndSignalStop(currentPos, coords, board, result)) break;
    }
    
    // east moves
    for (var file = (char)(currentPos.file() + 1); file < 'a' + board.getSize(); file++) {
      var coords = new Coords(file, currentPos.rank());
      if (checkEmptyAndSignalStop(currentPos, coords, board, result)) break;
    }

    // west moves
    for (var file = (char)(currentPos.file() - 1); file >= 'a'; file--) {
      var coords = new Coords(file, currentPos.rank());
      if (checkEmptyAndSignalStop(currentPos, coords, board, result)) break;
    }
    
    return result;
  }
}
