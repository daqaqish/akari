package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ControllerImpl;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
  private final ControllerImpl controller;

  public View(ControllerImpl controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();

    PuzzleView puzzleView = new PuzzleView(controller);
    layout.getChildren().add(puzzleView.render());

    SolvedView solvedView = new SolvedView(controller);
    layout.getChildren().add(solvedView.render());

    ControlView controlView = new ControlView(controller);
    layout.getChildren().add(controlView.render());

    IndexView indexView = new IndexView(controller);
    layout.getChildren().add(indexView.render());

    return layout;
  }
}
