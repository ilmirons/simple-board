package com.eng.interview.simpleboard.api;

@FunctionalInterface
public interface BoardPrinter<T> {
  
  T print(SimpleBoard board);
  
}
