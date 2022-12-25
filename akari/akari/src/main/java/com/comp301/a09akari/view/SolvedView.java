package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SolvedView implements FXComponent {
  private final ControllerImpl controller;

  public SolvedView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    layout.setAlignment(Pos.TOP_CENTER);

    if (controller.isSolved()) {
      Text title = new Text("\u2713 SOLVED \u2713");
      title.setFill(Color.LIGHTGREEN);
      title.setFont(Font.font("Monospaced", 30));
      title.setUnderline(true);
      layout.getChildren().add(title);
    }
    if (!controller.isSolved()) {
      Text title = new Text("\u2716 UNSOLVED \u2716");
      title.setFill(Color.DARKRED);
      title.setFont(Font.font("Monospaced", 30));
      title.setUnderline(true);
      layout.getChildren().add(title);
    }

    layout.setPadding(new Insets(15, 15, 15, 15));
    return layout;
  }
}
