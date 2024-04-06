package com.eng.interview.simpleboard.api;

import java.util.Optional;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public record Coords(char file, int rank) implements Comparable<Coords> {

  
  /**
   * Convert this coords to indexes
   * @return an Indexes record (a,1) will be (2,0)
   */
  @Contract(" -> new")
  public @NotNull Indexes toIndexes() {
    return new Indexes(this.rank() - 1, this.file() - 'a');
  }

  /**
   * Return the coords east of this coords or empty if out of bounds
   * @param step number of steps
   * @param board the board
   * @return the new coords or empty if out of bounds
   */
  
  public @NotNull Optional<Coords> e(int step, @NotNull SimpleBoard board) {
    return inBoundsOrEmpty(new Coords((char) (this.file() + step), this.rank()), board);
  }
  
  /**
   * Return the coords west of this coords or empty if out of bounds
   * @param step number of steps
   * @param board the board
   * @return the new coords or empty if out of bounds
   */

  public @NotNull Optional<Coords> w(int step, @NotNull SimpleBoard board) {
    return inBoundsOrEmpty(new Coords((char) (this.file() - step), this.rank()), board);
  }

  /**
   * Return the coords north of this coords or empty if out of bounds
   * @param step number of steps
   * @param board the board
   * @return the new coords or empty if out of bounds
   */
  
  public @NotNull Optional<Coords> n(int step, @NotNull SimpleBoard board) {
    return inBoundsOrEmpty(new Coords(this.file(), this.rank() + step), board);
  }

  /**
   * Return the coords south of this coords or empty if out of bounds
   * @param step number of steps
   * @param board the board
   * @return the new coords or empty if out of bounds
   */
  
  public @NotNull Optional<Coords> s(int step, @NotNull SimpleBoard board) {
    return inBoundsOrEmpty(new Coords(this.file(), this.rank() - step), board);
  }

  /**
   * Return the coords north-east of this coords or empty if out of bounds
   * @param step number of steps
   * @param board the board
   * @return the new coords or empty if out of bounds
   */
  
  public @NotNull Optional<Coords> ne(int step, @NotNull SimpleBoard board) {
    return inBoundsOrEmpty(new Coords((char) (this.file() + step), this.rank() + step), board);
  }
  
  /**
   * Return the coords north-west of this coords or empty if out of bounds
   * @param step number of steps
   * @param board the board
   * @return the new coords or empty if out of bounds
   */

  public @NotNull Optional<Coords> nw(int step, @NotNull SimpleBoard board) {
    return inBoundsOrEmpty(new Coords((char) (this.file() - step), this.rank() + step), board);
  }
  
  /**
   * Return the coords south-east of this coords or empty if out of bounds
   * @param step number of steps
   * @param board the board
   * @return the new coords or empty if out of bounds
   */

  public @NotNull Optional<Coords> se(int step, @NotNull SimpleBoard board) {
    return inBoundsOrEmpty(new Coords((char) (this.file() + step), this.rank() - step), board);
  }
  
  /**
   * Return the coords south-west of this coords or empty if out of bounds
   * @param step number of steps
   * @param board the board
   * @return the new coords or empty if out of bounds
   */

  public @NotNull Optional<Coords> sw(int step, @NotNull SimpleBoard board) {
    return inBoundsOrEmpty(new Coords((char) (this.file() - step), this.rank() - step), board);
  }
  

  @Override
  @Contract(pure = true)
  public int compareTo(@NotNull Coords o) {
    return this.file() == o.file() ? this.rank() - o.rank() : this.file() - o.file();
  }

  private Optional<Coords> inBoundsOrEmpty(Coords coords, @NotNull SimpleBoard board) {
    return Optional.of(coords).filter(board::isInBounds);
  }
  
  /**
   * Record to represent indexes s we usually use them in nested for cycles, 
   * i for the outer for (rows array) and j for the inner for (column index in the row)
   * 
   * @param i the row index
   * @param j the column index
   */

  public record Indexes(int i, int j) {
    @Contract(" -> new")
    @NotNull
    public Coords toCoords() {
      return new Coords((char) (j + 'a'), i + 1);
    }
  }
}
