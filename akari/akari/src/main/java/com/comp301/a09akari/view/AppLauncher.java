package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    PuzzleLibrary puzzleLibrary = new PuzzleLibraryImpl();
    Puzzle puzzle1 = new PuzzleImpl(SamplePuzzles.PUZZLE_01);
    Puzzle puzzle2 = new PuzzleImpl(SamplePuzzles.PUZZLE_02);
    Puzzle puzzle3 = new PuzzleImpl(SamplePuzzles.PUZZLE_03);
    Puzzle puzzle4 = new PuzzleImpl(SamplePuzzles.PUZZLE_04);
    Puzzle puzzle5 = new PuzzleImpl(SamplePuzzles.PUZZLE_05);
    puzzleLibrary.addPuzzle(puzzle1);
    puzzleLibrary.addPuzzle(puzzle2);
    puzzleLibrary.addPuzzle(puzzle3);
    puzzleLibrary.addPuzzle(puzzle4);
    puzzleLibrary.addPuzzle(puzzle5);

    Model model = new ModelImpl(puzzleLibrary);
    ControllerImpl controller = new ControllerImpl(model);
    View view = new View(controller);

    Scene scene = new Scene(view.render(), 700, 700);

    stage.setScene(scene);

    Image icon = new Image("light-bulb.png");
    stage.getIcons().add(icon);

    model.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
          stage.sizeToScene();
        });
    stage.setTitle("Play Akari!");
    stage.show();
  }
}
