package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Puzzle;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PuzzleView implements FXComponent {
  private final ControllerImpl controller;

  public PuzzleView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    Puzzle puzzle = controller.getActivePuzzle();
    GridPane layout = new GridPane();
    layout.setPadding(new Insets(15, 15, 15, 15));
    layout.setGridLinesVisible(true);

    for (int i = 0; i < puzzle.getWidth(); i++) {
      ColumnConstraints cConst = new ColumnConstraints();
      cConst.setMaxWidth(100);
      cConst.setMinWidth(50);
      layout.getColumnConstraints().add(cConst);
    }
    for (int i = 0; i < puzzle.getHeight(); i++) {
      RowConstraints rConst = new RowConstraints();
      rConst.setMaxHeight(100);
      rConst.setMinHeight(50);
      layout.getRowConstraints().add(rConst);
    }
    for (int r = 0; r < puzzle.getHeight(); r++) {
      for (int c = 0; c < puzzle.getWidth(); c++) {
        if (puzzle.getCellType(r, c) == CellType.CLUE) {
          StackPane square = new StackPane();
          square.setStyle("-fx-background-color: black;");
          Text title = new Text(Integer.toString(puzzle.getClue(r, c)));
          title.setFill(Color.WHITE);
          square.getChildren().add(title);
          layout.add(square, c, r);
          layout.setAlignment(Pos.CENTER);
          if (controller.isClueSatisfied(r, c)) {
            square.setStyle("-fx-background-color: green;");
          }
        }
        if (puzzle.getCellType(r, c) == CellType.WALL) {
          StackPane square = new StackPane();
          square.setStyle("-fx-background-color: black;");
          layout.add(square, c, r, 1, 1);
        }
        if (puzzle.getCellType(r, c) == CellType.CORRIDOR) {
          StackPane stack = new StackPane();
          stack.setMaxHeight(50);
          stack.setMaxWidth(50);
          stack.setMinHeight(50);
          stack.setMinWidth(50);
          Image bulb = new Image("light-bulb.png");
          ImageView imgView = new ImageView(bulb);
          imgView.setFitWidth(15);
          imgView.setFitHeight(15);

          int row = r;
          int col = c;

          stack.setOnMouseClicked(
              (event) -> {
                controller.clickCell(row, col);
              });

          if (controller.isLit(r, c)) {
            stack.setStyle("-fx-background-color: yellow;");
          }
          if (controller.isLamp(r, c)) {
            if (controller.getIsIllegal(r, c)) {
              stack.getChildren().add(new Label("\u2717"));
            } else {
              stack.getChildren().add(imgView);
            }
          }
          layout.add(stack, c, r);
        }
      }
    }
    return layout;
  }
}
