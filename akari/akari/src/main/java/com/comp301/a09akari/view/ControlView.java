package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent {
  private final ControllerImpl controller;

  public ControlView(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();
    layout.setAlignment(Pos.TOP_CENTER);

    Button random = new Button("\uD83C\uDFB0 Random");
    random.setOnAction(
        (javafx.event.ActionEvent event) -> {
          controller.clickRandPuzzle();
        });

    Button prev = new Button("\u2BA8 Prev");
    prev.setOnAction(
        (javafx.event.ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });

    Button next = new Button("Next \u2BA9");
    next.setOnAction(
        (javafx.event.ActionEvent event) -> {
          controller.clickNextPuzzle();
        });

    Button reset = new Button("Reset \u2B6F");
    reset.setOnAction(
        (javafx.event.ActionEvent event) -> {
          controller.clickResetPuzzle();
        });

    layout.getChildren().add(random);
    layout.getChildren().add(prev);
    layout.getChildren().add(next);
    layout.getChildren().add(reset);

    layout.setPadding(new Insets(10, 10, 10, 10));
    layout.setSpacing(20);

    return layout;
  }
}
