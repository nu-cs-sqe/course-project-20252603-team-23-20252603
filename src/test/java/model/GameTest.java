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

    @Test
    void makeMove_validMove_pieceAtDestination() { // BVA-MT-01
        Game game = new Game("Alice", "Bob");
        game.setup();

        game.makeMove(new Move(new Position(1, 0), new Position(3, 0)));

        assertNotNull(game.getBoard().getPieceAt(new Position(3, 0)));
    }

    @Test
    void makeMove_validMove_sourceCleared() { // BVA-MT-02
        Game game = new Game("Alice", "Bob");
        game.setup();

        game.makeMove(new Move(new Position(1, 0), new Position(3, 0)));

        assertNull(game.getBoard().getPieceAt(new Position(1, 0)));
    }

    @Test
    void makeMove_validMove_switchesTurnToBlack() { // BVA-MT-03
        Game game = new Game("Alice", "Bob");
        game.setup();
        assertEquals(Color.WHITE, game.getState().getCurrentTurn());

        game.makeMove(new Move(new Position(1, 0), new Position(3, 0)));

        assertEquals(Color.BLACK, game.getState().getCurrentTurn());
    }

    @Test
    void makeMove_nullMove_throwsIllegalArgumentException() { // BVA-MT-04
        Game game = new Game("Alice", "Bob");
        game.setup();

        assertThrows(IllegalArgumentException.class, () -> game.makeMove(null));
    }

    @Test
    void makeMove_gameNotInProgress_throwsIllegalStateException() { // BVA-MT-05
        Game game = new Game("Alice", "Bob");
        // game is in SETUP state — not IN_PROGRESS

        assertThrows(IllegalStateException.class,
                () -> game.makeMove(new Move(new Position(1, 0), new Position(3, 0))));
    }

    @Test
    void makeMove_emptySourceSquare_throwsIllegalArgumentException() { // BVA-MT-06
        Game game = new Game("Alice", "Bob");
        game.setup();
        // rows 2–5 are empty after setup

        assertThrows(IllegalArgumentException.class,
                () -> game.makeMove(new Move(new Position(4, 0), new Position(5, 0))));
    }

    @Test
    void makeMove_opponentPiece_throwsIllegalArgumentException() { // BVA-MT-07
        Game game = new Game("Alice", "Bob");
        game.setup();
        // White's turn; black pawns are at row 6

        assertThrows(IllegalArgumentException.class,
                () -> game.makeMove(new Move(new Position(6, 0), new Position(5, 0))));
    }

    @Test
    void makeMove_toOwnPiece_throwsIllegalArgumentException() { // BVA-MT-08
        Game game = new Game("Alice", "Bob");
        game.setup();
        // White pawn at (1,0) → white rook at (0,0): destination is own piece

        assertThrows(IllegalArgumentException.class,
                () -> game.makeMove(new Move(new Position(1, 0), new Position(0, 0))));
    }

    @Test
    void makeMove_capturesOpponentPiece() { // BVA-MT-09
        Game game = new Game("Alice", "Bob");
        game.setup();
        // Manually place a black pawn in an empty square reachable by white
        game.getBoard().placePiece(new Piece(PieceType.PAWN, Color.BLACK), new Position(3, 0));

        game.makeMove(new Move(new Position(1, 0), new Position(3, 0)));

        assertEquals(Color.WHITE, game.getBoard().getPieceAt(new Position(3, 0)).getColor());
    }

    @Test
    void makeMove_twoConsecutiveMoves_alternatesTurns() { // BVA-MT-10
        Game game = new Game("Alice", "Bob");
        game.setup();

        game.makeMove(new Move(new Position(1, 0), new Position(3, 0))); // white
        assertEquals(Color.BLACK, game.getState().getCurrentTurn());

        game.makeMove(new Move(new Position(6, 0), new Position(4, 0))); // black
        assertEquals(Color.WHITE, game.getState().getCurrentTurn());
    }
}
