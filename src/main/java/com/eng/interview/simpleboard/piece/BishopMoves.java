package com.eng.interview.simpleboard.piece;

import static com.eng.interview.simpleboard.piece.PieceUtil.checkEmptyAndSignalStop;
import com.eng.interview.simpleboard.api.SimpleBoard;
import com.eng.interview.simpleboard.api.Coords;
import java.util.HashSet;
import java.util.Set;

sealed interface BishopMoves permits Bishop, Queen {
  
  default Set<Coords> getBishopMoves(SimpleBoard board, Coords currentPos) {
    var result = new HashSet<Coords>();

    // nord-est moves
    char file = (char)(currentPos.file() + 1);
    int rank = currentPos.rank() + 1;
    while (file < 'a' + board.getSize() && rank <= board.getSize()) {
      var coords = new Coords(file, rank);
      if (checkEmptyAndSignalStop(currentPos, coords, board, result))
        break;
      file++;
      rank++;
    }

    // south-west moves
    file = (char)(currentPos.file() - 1);
    rank = currentPos.rank() - 1;
    while (file >= 'a' && rank > 0) {
      var coords = new Coords(file, rank);
      if (checkEmptyAndSignalStop(currentPos, coords, board, result))
        break;
      file--;
      rank--;
    }

    // north-west moves
    file = (char)(currentPos.file() - 1);
    rank = currentPos.rank() + 1;
    while (file >= 'a' && rank <= board.getSize()) {
      var coords = new Coords(file, rank);
      if (checkEmptyAndSignalStop(currentPos, coords, board, result))
        break;
      file--;
      rank++;
    }

    // south-est moves
    file = (char)(currentPos.file() + 1);
    rank = currentPos.rank() - 1;
    while (file < 'a' + board.getSize() && rank > 0) {
      var coords = new Coords(file, rank);
      if (checkEmptyAndSignalStop(currentPos, coords, board, result))
        break;
      file++;
      rank--;
    }
    return result;
  }
  
}
