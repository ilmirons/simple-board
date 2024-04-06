package com.eng.interview.simpleboard.api;

import com.eng.interview.simpleboard.piece.Piece;

public interface Board {

  /**
   * 
   * @return the size of the board (side length) boards can only be square
   */
  int getSize();

  /**
   * 
   * @param coords the coordinates of the square
   * @param piece the piece to place on the square
   */
  void setPiece(Coords coords, Piece piece);

  /**
   * 
   * @param coords the coords of the square to remove the piece from
   */
  void removePiece(Coords coords);

  /**
   * 
   * @param from the coordinates of the piece to move
   * @param to the coordinates to move the piece to
   */
  void movePiece(Coords from, Coords to);

  /**
   * Setup initial positions of pieces on the board
   */
  void setup();
}
