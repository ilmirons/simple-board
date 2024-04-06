package com.eng.interview.simpleboard.piece;

import com.eng.interview.simpleboard.api.Board;
import com.eng.interview.simpleboard.api.SimpleBoard;
import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.Coords;
import java.util.Set;

public sealed interface Piece permits King, Queen, Rook, Bishop, Knight, Pawn {

  // SimpleBoard is needed as well as currentPos to check for other piece, checks, etc.

  /**
   * 
   * @param board thw bord the move will be performed on
   * @param currentPos the current position of the piece
   * @return Set of possible position the piece can move to.
   */
  Set<Coords> getLegalMoves(SimpleBoard board, Coords currentPos);

  Color color();
  
  boolean moved();
  
  default boolean sameColor(Piece piece) {
    return this.color() == piece.color();
  }
  
  default boolean differentColor(Piece piece) {
    return !sameColor(piece);
  }

  /**
   * 
   * @param moved should always be set to true as soon as the piece is moved and never set to false
   * @return a new Piece of the same type with the moved status updated
   */
  Piece moved(boolean moved);

  default void move(Coords from, Coords to, Board board) {
    board.setPiece(to, this.moved() ? this : this.moved(true));
    board.removePiece(from);
  }
}
