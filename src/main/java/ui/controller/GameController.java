package ui.controller;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import model.Color;
import model.Game;
import model.GameStatus;
import model.Move;
import model.Piece;
import model.Player;
import model.Position;
import ui.board.BoardView;

public class GameController {

    private final Game game;
    private final BoardView boardView;
    private final Label statusLabel;
    private Position selectedPos;

    public GameController(Game game) {
        this.game = game;
        this.boardView = new BoardView();
        this.statusLabel = buildStatusLabel();
        this.selectedPos = null;

        boardView.setClickHandler(this::onCellClicked);
        refresh();
    }

    private Label buildStatusLabel() {
        Label label = new Label();
        label.setPadding(new Insets(8));
        label.setAlignment(Pos.CENTER);
        label.setMaxWidth(Double.MAX_VALUE);
        return label;
    }

    private void onCellClicked(int modelRow, int col) {
        if (game.getState().getStatus() != GameStatus.IN_PROGRESS) {
            return;
        }

        Position clicked = new Position(modelRow, col);
        Piece piece = game.getBoard().getPieceAt(clicked);

        if (selectedPos == null) {
            if (piece != null && piece.getColor() == game.getState().getCurrentTurn()) {
                selectedPos = clicked;
                boardView.refresh(game.getBoard(), selectedPos);
            }
        } else if (clicked.equals(selectedPos)) {
            selectedPos = null;
            boardView.refresh(game.getBoard(), null);
        } else {
            attemptMove(clicked);
        }
    }

    private void attemptMove(Position target) {
        try {
            game.makeMove(new Move(selectedPos, target));
            selectedPos = null;
            refresh();
            checkGameOver();
        } catch (IllegalArgumentException e) {
            selectedPos = null;
            boardView.refresh(game.getBoard(), null);
            updateStatus();
        }
    }

    private void checkGameOver() {
        if (game.getState().getStatus() == GameStatus.CHECKMATE) {
            String winnerName = game.getCurrentPlayer().getName();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Game Over");
            alert.setHeaderText("Checkmate!");
            alert.setContentText(winnerName + " wins!");
            alert.showAndWait();
        }
    }

    private void refresh() {
        boardView.refresh(game.getBoard(), selectedPos);
        updateStatus();
    }

    private void updateStatus() {
        GameStatus status = game.getState().getStatus();
        if (status == GameStatus.IN_PROGRESS) {
            Player current = game.getCurrentPlayer();
            statusLabel.setText(current.getName() + "'s turn (" + colorLabel(current.getColor()) + ")");
        } else if (status == GameStatus.CHECKMATE) {
            statusLabel.setText("Checkmate! " + game.getCurrentPlayer().getName() + " wins!");
        }
    }

    private String colorLabel(Color color) {
        String raw = color.toString();
        return raw.charAt(0) + raw.substring(1).toLowerCase();
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }
}
