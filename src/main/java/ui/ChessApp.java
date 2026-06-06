package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Game;
import ui.controller.GameController;

public class ChessApp extends Application {

    private static final int SCENE_WIDTH = 560;
    private static final int SCENE_HEIGHT = 620;

    @Override
    public void start(Stage stage) {
        loadGame(stage);
    }

    private void loadGame(Stage stage) {
        String[] names = new SetupDialogView().showAndWait().orElse(null);
        if (names == null) {
            return;
        }

        Game game = new Game(names[0], names[1]);
        game.setup();

        GameController controller = new GameController(game);

        BorderPane root = new BorderPane();
        root.setTop(buildMenuBar(stage));
        root.setCenter(controller.getBoardView());
        root.setBottom(controller.getStatusLabel());

        if (stage.getScene() == null) {
            stage.setTitle("Chess");
            stage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
            stage.setResizable(false);
            stage.show();
        } else {
            stage.getScene().setRoot(root);
        }
    }

    private MenuBar buildMenuBar(Stage stage) {
        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(e -> loadGame(stage));
        Menu gameMenu = new Menu("Game");
        gameMenu.getItems().add(newGame);
        return new MenuBar(gameMenu);
    }
}
