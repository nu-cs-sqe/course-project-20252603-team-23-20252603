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
}
