package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    @Test
    void initialState_isCorrect() { // BVA-GS-01
        GameState state = new GameState();

        assertEquals(Color.WHITE, state.getCurrentTurn());
        assertEquals(GameStatus.SETUP, state.getStatus());
    }

    @Test
    void switchTurn_whiteToBlack() { // BVA-GS-02
        GameState state = new GameState();

        state.switchTurn();

        assertEquals(Color.BLACK, state.getCurrentTurn());
    }

    @Test
    void switchTurn_blackToWhite() { // BVA-GS-03
        GameState state = new GameState();

        state.switchTurn();
        state.switchTurn();

        assertEquals(Color.WHITE, state.getCurrentTurn());
    }

    @Test
    void setStatus_validStatus_updatesState() { // BVA-GS-04
        GameState state = new GameState();

        state.setStatus(GameStatus.IN_PROGRESS);

        assertEquals(GameStatus.IN_PROGRESS, state.getStatus());
    }

    @Test
    void setStatus_checkmate_updatesState() { // BVA-GS-05
        GameState state = new GameState();

        state.setStatus(GameStatus.CHECKMATE);

        assertEquals(GameStatus.CHECKMATE, state.getStatus());
    }

    @Test
    void setStatus_null_throwsException() { // BVA-GS-06
        GameState state = new GameState();

        assertThrows(IllegalArgumentException.class, () -> {
            state.setStatus(null);
        });
    }

}
