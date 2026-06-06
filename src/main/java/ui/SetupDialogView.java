package ui;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Optional;

public class SetupDialogView {

    public Optional<String[]> showAndWait() {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle("New Game");
        dialog.setHeaderText("Enter player names");

        ButtonType startButton = new ButtonType("Start", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(startButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField whiteName = new TextField("White");
        TextField blackName = new TextField("Black");

        grid.add(new Label("White player:"), 0, 0);
        grid.add(whiteName, 1, 0);
        grid.add(new Label("Black player:"), 0, 1);
        grid.add(blackName, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(buttonType -> {
            if (buttonType == startButton) {
                String w = whiteName.getText().trim();
                String b = blackName.getText().trim();
                if (!w.isEmpty() && !b.isEmpty()) {
                    return new String[]{w, b};
                }
            }
            return null;
        });

        return dialog.showAndWait();
    }
}
