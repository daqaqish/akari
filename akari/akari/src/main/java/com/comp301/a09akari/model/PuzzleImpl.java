package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  private final int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r < 0 || c < 0 || r >= getHeight() || c >= getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    int cell = board[r][c];
    if (cell <= 4) {
      return CellType.CLUE;
    }
    if (cell == 5) {
      return CellType.WALL;
    }
    if (cell == 6) {
      return CellType.CORRIDOR;
    }
    return null;
  }

  @Override
  public int getClue(int r, int c) {
    int cell = board[r][c];
    if (getCellType(r, c) == CellType.CLUE) {
      return cell;
    } else {
      throw new IllegalArgumentException();
    }
  }
}
