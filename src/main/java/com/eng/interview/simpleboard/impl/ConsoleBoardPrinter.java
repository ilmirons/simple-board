package com.eng.interview.simpleboard.impl;

import com.eng.interview.simpleboard.api.Board;
import com.eng.interview.simpleboard.api.SimpleBoard;
import com.eng.interview.simpleboard.api.BoardPrinter;
import static com.eng.interview.simpleboard.api.Color.*;
import com.eng.interview.simpleboard.api.Coords;
import com.eng.interview.simpleboard.piece.Bishop;
import com.eng.interview.simpleboard.piece.King;
import com.eng.interview.simpleboard.piece.Knight;
import com.eng.interview.simpleboard.piece.Pawn;
import com.eng.interview.simpleboard.piece.Piece;
import com.eng.interview.simpleboard.piece.Queen;
import com.eng.interview.simpleboard.piece.Rook;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ConsoleBoardPrinter implements BoardPrinter<String> {

  private static final String NEW_LINE = "\n";
  private static final String COLUMN_SEPARATOR = " | ";


  /**
   *
   * @param i file index
   * @param j rank index
   * @return the coordinates for the given indexes (0,1 will be a,2)
   */

  @NotNull
  @Contract("_, _ -> new")
  private Coords toCoords(int i, int j) {
    return new Coords((char) (i + 'a'), j + 1);
  }
  
  @Override
  public String print(SimpleBoard board) {
    var sb = new StringBuilder();
    for (int rank = board.getSize() - 1; rank >= 0; rank--) {
      sb.append(rowSeparator(board));
      for (int file = 0; file < board.getSize(); file++) {
        if (file == 0) {
          sb.append(rank + 1);
        }
        var square = board.getSquare(toCoords(file, rank));
        sb.append(COLUMN_SEPARATOR).append(printSquare(square));
        if (file == board.getSize() - 1) {
          sb.append(COLUMN_SEPARATOR).append(NEW_LINE);
        }
      }
    }
    sb.append(rowSeparator(board));
    sb.append("    a   b   c   d   e   f   g   h").append(NEW_LINE);
    return sb.toString();
  }
  
  private String rowSeparator(@NotNull Board board) {
    return "  " + "+---".repeat(board.getSize()) + "+" + NEW_LINE;
  }
  
  private String printSquare(@NotNull Square square) {
    return square.getPiece().map(this::renderPiece).orElse(" ");
  }
  
  private String renderPiece(@NotNull Piece piece) {
    return switch (piece) {
      case King(var color, var unused) -> color == WHITE ? "K" : "k";
      case Queen(var color, var unused) -> color == WHITE ? "Q" : "q";
      case Rook(var color, var unused) -> color == WHITE ? "R" : "r";
      case Bishop(var color, var unused) -> color == WHITE ? "B" : "b";
      case Knight(var color, var unused) -> color == WHITE ? "N" : "n";
      case Pawn(var color, var unused) -> color == WHITE ? "P" : "p";
    };
  }
}
