package com.eng.interview.simpleboard.piece;

import com.eng.interview.simpleboard.api.SimpleBoard;
import com.eng.interview.simpleboard.api.Color;
import com.eng.interview.simpleboard.api.Coords;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Knight(Color color, boolean moved) implements Piece {

  // this is hardcore
  @Override
  public Set<Coords> getLegalMoves(SimpleBoard board, @NotNull Coords currentPos) {
    return streamConcat(
            currentPos.n(1, board).stream()
                .flatMap(
                    coords ->
                        Stream.concat(coords.ne(1, board).stream(), coords.nw(1, board).stream())),
            currentPos.e(1, board).stream()
                .flatMap(
                    coords ->
                        Stream.concat(coords.ne(1, board).stream(), coords.se(1, board).stream())),
            currentPos.s(1, board).stream()
                .flatMap(
                    coords ->
                        Stream.concat(coords.se(1, board).stream(), coords.sw(1, board).stream())),
            currentPos.w(1, board).stream()
                .flatMap(
                    coords ->
                        Stream.concat(coords.nw(1, board).stream(), coords.sw(1, board).stream())))
        .filter(coords -> board.getPiece(coords).map(this::differentColor).orElse(true))
        .collect(Collectors.toSet());
  }
  
  @SafeVarargs
  private <T> Stream<T> streamConcat(Stream<T> ...streams) {
    return Stream.of(streams).reduce(Stream::concat).orElseGet(Stream::empty);
  }

  @Override
  @Contract("_ -> new")
  public @NotNull Piece moved(boolean moved) {
    return new Knight(color, moved);
  }
}
