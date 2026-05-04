package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    void constructor_validNames_createsGame() { // BVA-G-01
        Game game = new Game("Alice", "Bob");

        assertNotNull(game.getBoard());
        assertNotNull(game.getState());
    }

    @Test
    void constructor_nullWhiteName_throws() { // BVA-G-02
        assertThrows(IllegalArgumentException.class,
                () -> new Game(null, "Bob"));
    }

    @Test
    void constructor_blankBlackName_throws() { // BVA-G-03
        assertThrows(IllegalArgumentException.class,
                () -> new Game("Alice", " "));
    }

    @Test
    void setup_initializesBoardAndSetsStatus() { // BVA-G-04
        Game game = new Game("Alice", "Bob");

        game.setup();

        assertEquals(GameStatus.IN_PROGRESS, game.getState().getStatus());

        // verify board initialized (32 pieces)
        int count = 0;
        Board board = game.getBoard();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board.getPieceAt(new Position(r, c)) != null) {
                    count++;
                }
            }
        }

        assertEquals(32, count);
    }

    @Test
    void setup_calledTwice_doesNotBreakGame() { // BVA-G-05
        Game game = new Game("Alice", "Bob");

        game.setup();
        game.setup(); // should be safe

        assertEquals(GameStatus.IN_PROGRESS, game.getState().getStatus());
        assertNotNull(game.getBoard());
    }

    @Test
    void getCurrentPlayer_initialIsWhite() { // BVA-G-06
        Game game = new Game("Alice", "Bob");

        Player current = game.getCurrentPlayer();

        assertEquals(Color.WHITE, current.getColor());
        assertEquals("Alice", current.getName());
    }

    @Test
    void getCurrentPlayer_afterTurnSwitch_isBlack() { // BVA-G-07
        Game game = new Game("Alice", "Bob");

        game.getState().switchTurn();

        Player current = game.getCurrentPlayer();

        assertEquals(Color.BLACK, current.getColor());
        assertEquals("Bob", current.getName());
    }

    @Test
    void getBoard_returnsNonNullBoard() { // BVA-G-08
        Game game = new Game("Alice", "Bob");

        assertNotNull(game.getBoard());
    }

    @Test
    void getState_returnsNonNullState() { // BVA-G-09
        Game game = new Game("Alice", "Bob");

        assertNotNull(game.getState());
        assertEquals(GameStatus.SETUP, game.getState().getStatus());
    }
}
