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

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class GameController {

    private final Game game;
    private final BoardView boardView;
    private final Label statusLabel;
    private final ResourceBundle bundle;
    private Position selectedPos;
    private List<Position> legalMoves = Collections.emptyList();

    public GameController(Game game, ResourceBundle bundle) {
        this.game = game;
        this.bundle = bundle;
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
                legalMoves = game.getLegalMoves(selectedPos);
                boardView.refresh(game.getBoard(), selectedPos, legalMoves);
            }
        } else if (clicked.equals(selectedPos)) {
            selectedPos = null;
            legalMoves = Collections.emptyList();
            boardView.refresh(game.getBoard(), null);
        } else {
            attemptMove(clicked);
        }
    }

    private void attemptMove(Position target) {
        try {
            game.makeMove(new Move(selectedPos, target));
            selectedPos = null;
            legalMoves = Collections.emptyList();
            refresh();
            checkGameOver();
        } catch (IllegalArgumentException e) {
            selectedPos = null;
            legalMoves = Collections.emptyList();
            boardView.refresh(game.getBoard(), null);
            updateStatus();
        }
    }

    private void checkGameOver() {
        if (game.getState().getStatus() == GameStatus.CHECKMATE) {
            String winnerName = game.getCurrentPlayer().getName();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(bundle.getString("alert.gameOver.title"));
            alert.setHeaderText(bundle.getString("alert.gameOver.header"));
            alert.setContentText(MessageFormat.format(bundle.getString("alert.gameOver.content"), winnerName));
            alert.showAndWait();
        }
    }

    private void refresh() {
        boardView.refresh(game.getBoard(), selectedPos, legalMoves);
        updateStatus();
    }

    private void updateStatus() {
        GameStatus status = game.getState().getStatus();
        if (status == GameStatus.IN_PROGRESS) {
            Player current = game.getCurrentPlayer();
            String text = MessageFormat.format(
                    bundle.getString("status.turn"), current.getName(), colorLabel(current.getColor()));
            statusLabel.setText(text);
        } else if (status == GameStatus.CHECKMATE) {
            String text = MessageFormat.format(
                    bundle.getString("status.checkmate"), game.getCurrentPlayer().getName());
            statusLabel.setText(text);
        }
    }

    private String colorLabel(Color color) {
        return bundle.getString(color == Color.WHITE ? "color.white" : "color.black");
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }
}
