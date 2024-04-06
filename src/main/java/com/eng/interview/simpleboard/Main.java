package com.eng.interview.simpleboard;

import com.eng.interview.simpleboard.impl.ConsoleBoardPrinter;
import com.eng.interview.simpleboard.impl.BoardImpl;

public class Main {

  public static void main(String[] args) {
    var board = new BoardImpl();
    var renderer = new ConsoleBoardPrinter();
    board.setup();
    System.out.println(renderer.print(board));
  }
}