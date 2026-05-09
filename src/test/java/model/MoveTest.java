package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {

    @Test
    void constructor_validPositions_createsMoveSuccessfully() { // BVA-MV-01
        Position from = new Position(1, 0);
        Position to = new Position(3, 0);
        Move move = new Move(from, to);
        assertNotNull(move);
    }

    @Test
    void constructor_nullFrom_throwsIllegalArgumentException() { // BVA-MV-02
        assertThrows(IllegalArgumentException.class,
                () -> new Move(null, new Position(3, 0)));
    }

    @Test
    void constructor_nullTo_throwsIllegalArgumentException() { // BVA-MV-03
        assertThrows(IllegalArgumentException.class,
                () -> new Move(new Position(1, 0), null));
    }

    @Test
    void constructor_invalidFromPosition_throwsIllegalArgumentException() { // BVA-MV-04
        assertThrows(IllegalArgumentException.class,
                () -> new Move(new Position(-1, 0), new Position(3, 0)));
    }

    @Test
    void constructor_invalidToPosition_throwsIllegalArgumentException() { // BVA-MV-05
        assertThrows(IllegalArgumentException.class,
                () -> new Move(new Position(1, 0), new Position(0, 8)));
    }

    @Test
    void getFrom_returnsFromPosition() { // BVA-MV-06
        Position from = new Position(1, 0);
        Move move = new Move(from, new Position(3, 0));
        assertEquals(from, move.getFrom());
    }

    @Test
    void getTo_returnsToPosition() { // BVA-MV-07
        Position to = new Position(3, 0);
        Move move = new Move(new Position(1, 0), to);
        assertEquals(to, move.getTo());
    }
}