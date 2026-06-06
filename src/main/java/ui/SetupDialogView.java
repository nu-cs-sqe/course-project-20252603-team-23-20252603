package ui;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Optional;
import java.util.ResourceBundle;

public class SetupDialogView {

    public Optional<String[]> showAndWait(ResourceBundle bundle) {
        Dialog<String[]> dialog = new Dialog<>();
        dialog.setTitle(bundle.getString("setup.title"));
        dialog.setHeaderText(bundle.getString("setup.header"));

        ButtonType startButton = new ButtonType(
                bundle.getString("setup.button.start"), ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(startButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField whiteName = new TextField(bundle.getString("setup.field.white"));
        TextField blackName = new TextField(bundle.getString("setup.field.black"));

        grid.add(new Label(bundle.getString("setup.label.whitePlayer")), 0, 0);
        grid.add(whiteName, 1, 0);
        grid.add(new Label(bundle.getString("setup.label.blackPlayer")), 0, 1);
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
