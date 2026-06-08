package ui.controller;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.Color;
import model.Game;
import model.GameStatus;
import model.Piece;
import model.PieceType;
import model.Position;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ui.board.BoardView;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameControllerTest {

    private static final int MAX_INDEX = Position.MAX_INDEX;
    private static ResourceBundle bundle;

    @BeforeAll
    static void startToolkit() throws InterruptedException {
        CountDownLatch started = new CountDownLatch(1);
        try {
            Platform.startup(started::countDown);
        } catch (IllegalStateException alreadyRunning) {
            started.countDown();
        }
        assertTrue(started.await(10, TimeUnit.SECONDS), "JavaFX toolkit failed to start");
        bundle = ResourceBundle.getBundle("labels", Locale.US);
    }

    private static Game inProgressGame() {
        Game game = new Game("Alice", "Bob");
        game.setup();
        return game;
    }

    private RecordingGameController buildController(Game game) throws InterruptedException {
        RecordingGameController[] holder = new RecordingGameController[1];
        runOnFx(() -> holder[0] = new RecordingGameController(game, bundle));
        return holder[0];
    }

    private static void runOnFx(Runnable action) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                action.run();
            } finally {
                latch.countDown();
            }
        });
        assertTrue(latch.await(10, TimeUnit.SECONDS), "FX action timed out");
    }

    private static void click(BoardView boardView, Position modelPos) throws InterruptedException {
        int gridRow = MAX_INDEX - modelPos.getRow();
        int col = modelPos.getCol();
        runOnFx(() -> {
            Node cell = boardView.getChildren().stream()
                    .filter(n -> indexEquals(GridPane.getRowIndex(n), gridRow)
                            && indexEquals(GridPane.getColumnIndex(n), col))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No cell at (" + gridRow + "," + col + ")"));
            cell.fireEvent(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0,
                    MouseButton.PRIMARY, 1,
                    false, false, false, false,
                    true, false, false, false, false, false, null));
        });
    }

    private static boolean indexEquals(Integer actual, int expected) {
        return actual != null && actual == expected;
    }

    @SuppressWarnings("unchecked")
    private static List<Position> legalMovesOf(GameController controller) throws ReflectiveOperationException {
        Field field = GameController.class.getDeclaredField("legalMoves");
        field.setAccessible(true);
        return (List<Position>) field.get(controller);
    }

    private static Position selectedPosOf(GameController controller) throws ReflectiveOperationException {
        Field field = GameController.class.getDeclaredField("selectedPos");
        field.setAccessible(true);
        return (Position) field.get(controller);
    }

    @Test
    void constructor_buildsBoardAndShowsInitialTurn() throws Exception {
        Game game = inProgressGame();

        RecordingGameController controller = buildController(game);

        assertNotNull(controller.getBoardView());
        Label statusLabel = controller.getStatusLabel();
        assertEquals(formattedMessage("status.turn", "Alice", "White"), statusLabel.getText());
    }

    @Test
    void onCellClicked_whenGameNotInProgress_isIgnored() throws Exception {
        Game game = new Game("Alice", "Bob"); // status is SETUP, not IN_PROGRESS
        RecordingGameController controller = buildController(game);

        click(controller.getBoardView(), new Position(1, 0));

        assertNull(selectedPosOf(controller));
        assertTrue(legalMovesOf(controller).isEmpty());
    }

    @Test
    void onCellClicked_selectingOwnPiece_recordsSelectionAndLegalMoves() throws Exception {
        Game game = inProgressGame();
        RecordingGameController controller = buildController(game);

        click(controller.getBoardView(), new Position(1, 0));

        assertEquals(new Position(1, 0), selectedPosOf(controller));
        assertEquals(game.getLegalMoves(new Position(1, 0)), legalMovesOf(controller));
        assertFalse(legalMovesOf(controller).isEmpty());
    }

    @Test
    void onCellClicked_clickingEmptySquareWithNoSelection_doesNothing() throws Exception {
        Game game = inProgressGame();
        RecordingGameController controller = buildController(game);

        click(controller.getBoardView(), new Position(4, 4));

        assertNull(selectedPosOf(controller));
        assertTrue(legalMovesOf(controller).isEmpty());
    }

    @Test
    void onCellClicked_clickingSelectedSquareAgain_deselects() throws Exception {
        Game game = inProgressGame();
        RecordingGameController controller = buildController(game);

        click(controller.getBoardView(), new Position(1, 0));
        assertNotNull(selectedPosOf(controller));

        click(controller.getBoardView(), new Position(1, 0));

        assertNull(selectedPosOf(controller));
        assertTrue(legalMovesOf(controller).isEmpty());
    }

    @Test
    void onCellClicked_legalMove_movesPieceAndSwitchesTurn() throws Exception {
        Game game = inProgressGame();
        RecordingGameController controller = buildController(game);

        click(controller.getBoardView(), new Position(1, 0));
        click(controller.getBoardView(), new Position(3, 0));

        assertNull(selectedPosOf(controller));
        assertEquals(Color.WHITE, game.getBoard().getPieceAt(new Position(3, 0)).getColor());
        assertEquals(Color.BLACK, game.getState().getCurrentTurn());
        assertEquals(formattedMessage("status.turn", "Bob", "Black"), controller.getStatusLabel().getText());
    }

    @Test
    void onCellClicked_illegalMove_resetsSelectionAndKeepsTurn() throws Exception {
        Game game = inProgressGame();
        RecordingGameController controller = buildController(game);

        click(controller.getBoardView(), new Position(1, 0));
        click(controller.getBoardView(), new Position(4, 0)); // pawn cannot jump three squares

        assertNull(selectedPosOf(controller));
        assertTrue(legalMovesOf(controller).isEmpty());
        assertEquals(Color.WHITE, game.getState().getCurrentTurn());
        assertNull(game.getBoard().getPieceAt(new Position(4, 0)));
        assertEquals(formattedMessage("status.turn", "Alice", "White"), controller.getStatusLabel().getText());
    }

    @Test
    void onCellClicked_checkmatingMove_announcesWinnerAndShowsAlert() throws Exception {
        Game game = inProgressGame();
        game.getBoard().placePiece(new Piece(PieceType.QUEEN, Color.WHITE), new Position(3, 2));
        game.getBoard().placePiece(new Piece(PieceType.KING, Color.BLACK), new Position(3, 5));
        RecordingGameController controller = buildController(game);

        click(controller.getBoardView(), new Position(3, 2));
        click(controller.getBoardView(), new Position(3, 5));

        assertEquals(GameStatus.CHECKMATE, game.getState().getStatus());
        assertEquals(formattedMessage("status.checkmate", "Alice"), controller.getStatusLabel().getText());

        assertNotNull(controller.lastAlert);
        assertEquals(bundle.getString("alert.gameOver.title"), controller.lastAlert.getTitle());
        assertEquals(bundle.getString("alert.gameOver.header"), controller.lastAlert.getHeaderText());
        assertEquals(formattedMessage("alert.gameOver.content", "Alice"), controller.lastAlert.getContentText());
    }

    private static String formattedMessage(String key, Object... args) {
        return java.text.MessageFormat.format(bundle.getString(key), args);
    }

    /** Subclass that swaps the blocking alert dialog for a recorded value, so tests never hang on showAndWait(). */
    private static final class RecordingGameController extends GameController {

        private Alert lastAlert;

        RecordingGameController(Game game, ResourceBundle bundle) {
            super(game, bundle);
        }

        @Override
        void presentGameOverAlert(Alert alert) {
            lastAlert = alert;
        }
    }
}
