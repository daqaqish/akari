package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class IndexView implements FXComponent {
  private final ControllerImpl controller;

  public IndexView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    layout.setAlignment(Pos.TOP_CENTER);

    Text puzzle = new Text("Puzzle: ");
    puzzle.setFont(Font.font("Monospaced", 30));
    puzzle.setFill(Color.BLACK);

    Text puzzleIndex = new Text(Integer.toString(controller.getIndex() + 1));
    puzzleIndex.setFont(Font.font("Monospaced", 30));
    puzzleIndex.setFill(Color.BLACK);

    Text of = new Text(" of ");
    of.setFont(Font.font("Monospaced", 30));
    of.setFill(Color.BLACK);

    Text totPuzzles = new Text(Integer.toString(controller.getNumberOfPuzzles()));
    totPuzzles.setFont(Font.font("Monospaced", 30));
    totPuzzles.setFill(Color.BLACK);

    layout.setPadding(new Insets(15, 15, 15, 15));

    layout.getChildren().add(puzzle);
    layout.getChildren().add(puzzleIndex);
    layout.getChildren().add(of);
    layout.getChildren().add(totPuzzles);

    return layout;
  }
}
