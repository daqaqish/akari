package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private final PuzzleLibrary library;
  private int puzzleIndex;
  private Puzzle puzzle;
  private final List<ModelObserver> observerList;
  private boolean[][] lamps;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }
    this.library = library;
    this.puzzleIndex = 0;
    this.puzzle = library.getPuzzle(0);
    this.observerList = new ArrayList<>();
    this.lamps = new boolean[puzzle.getHeight()][puzzle.getWidth()];
  }

  @Override
  public void addLamp(int r, int c) {
    if (r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamps[r][c] = true;
    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r < 0 || c < 0 || r >= puzzle.getHeight() || c >= puzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (puzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lamps[r][c] = false;
    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r < 0 || c < 0 || r >= puzzle.getHeight() || c >= puzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (puzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    for (int i = c; i < puzzle.getWidth(); i++) {
      if (lamps[r][i]) {
        return true;
      } else if (puzzle.getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int j = c; j >= 0; j--) {
      if (lamps[r][j]) {
        return true;
      } else if (puzzle.getCellType(r, j) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int k = r; k < puzzle.getHeight(); k++) {
      if (lamps[k][c]) {
        return true;
      } else if (puzzle.getCellType(k, c) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int l = r; l >= 0; l--) {
      if (lamps[l][c]) {
        return true;
      } else if (puzzle.getCellType(l, c) != CellType.CORRIDOR) {
        break;
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r < 0 || c < 0 || r >= puzzle.getHeight() || c >= puzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (puzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    return lamps[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r < 0 || c < 0 || r >= puzzle.getHeight() || c >= puzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (!(lamps[r][c])) {
      throw new IllegalArgumentException();
    }
    for (int i = c + 1; i < puzzle.getWidth(); i++) {
      if (lamps[r][i]) {
        return true;
      } else if (puzzle.getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int j = c - 1; j >= 0; j--) {
      if (lamps[r][j]) {
        return true;
      } else if (puzzle.getCellType(r, j) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int k = r + 1; k < puzzle.getHeight(); k++) {
      if (lamps[k][c]) {
        return true;
      } else if (puzzle.getCellType(k, c) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int l = r - 1; l >= 0; l--) {
      if (lamps[l][c]) {
        return true;
      } else if (puzzle.getCellType(l, c) != CellType.CORRIDOR) {
        break;
      }
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return library.getPuzzle(puzzleIndex);
  }

  @Override
  public int getActivePuzzleIndex() {
    return puzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= library.size()) {
      throw new IndexOutOfBoundsException();
    }
    puzzleIndex = index;
    puzzle = library.getPuzzle(puzzleIndex);
    resetPuzzle();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return library.size();
  }

  @Override
  public void resetPuzzle() {
    lamps = new boolean[getActivePuzzle().getHeight()][getActivePuzzle().getWidth()];
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < puzzle.getHeight(); i++) {
      for (int j = 0; j < puzzle.getWidth(); j++) {
        if (puzzle.getCellType(i, j) == CellType.CLUE) {
          if (!isClueSatisfied(i, j)) {
            return false;
          }
        }
        if (puzzle.getCellType(i, j) == CellType.CORRIDOR) {
          if (!isLit(i, j)) {
            return false;
          }
          if (isLamp(i, j) && isLampIllegal(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r < 0 || c < 0 || r >= puzzle.getHeight() || c >= puzzle.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    int n = puzzle.getClue(r, c);
    int adjLamps = 0;
    if (r > 0 && lamps[r - 1][c]) {
      adjLamps++;
    }
    if (c > 0 && lamps[r][c - 1]) {
      adjLamps++;
    }
    if (r < puzzle.getHeight() - 1 && lamps[r + 1][c]) {
      adjLamps++;
    }
    if (c < puzzle.getWidth() - 1 && lamps[r][c + 1]) {
      adjLamps++;
    }
    return (adjLamps == n);
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observerList.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observerList.remove(observer);
  }

  public void notifyObservers() {
    for (ModelObserver observer : observerList) {
      observer.update(this);
    }
  }
}
