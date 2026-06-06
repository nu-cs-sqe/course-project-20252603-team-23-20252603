package ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import model.Piece;

class BoardCell extends StackPane {

    private static final int PIECE_FONT_SIZE = 36;

    private static final String LIGHT_COLOR = "#F0D9B5";
    private static final String DARK_COLOR = "#B58863";
    private static final String SELECTED_COLOR = "#F6F669";

    private final boolean light;

    BoardCell(boolean light) {
        this.light = light;
        setPrefSize(BoardView.CELL_SIZE, BoardView.CELL_SIZE);
        setAlignment(Pos.CENTER);
        setSelected(false);
    }

    void setSelected(boolean selected) {
        String color = selected ? SELECTED_COLOR : (light ? LIGHT_COLOR : DARK_COLOR);
        setStyle("-fx-background-color: " + color + ";");
    }

    void setPiece(Piece piece) {
        getChildren().clear();
        if (piece != null) {
            getChildren().add(labelFor(piece));
        }
    }

    private Label labelFor(Piece piece) {
        Label label = new Label(PieceSymbol.of(piece));
        label.setFont(Font.font(PIECE_FONT_SIZE));
        return label;
    }
}
