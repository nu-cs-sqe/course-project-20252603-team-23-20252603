package integration;

import model.Game;
import model.Board;
import model.Piece;
import model.Move;
import model.Position;
import model.PieceType;
import model.Color;
import model.GameStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameIntegrationTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game("Alice", "Bob");
        game.setup();
    }

    private void move(int fr, int fc, int tr, int tc) {
        game.makeMove(new Move(
                new Position(fr, fc),
                new Position(tr, tc)
        ));
    }

    private void assertTurn(Color expected) {
        assertEquals(expected, game.getState().getCurrentTurn());
    }

    @Test
    void scenario1_fullGameSetupInitializesBoardCorrectly() {
        assertEquals(GameStatus.IN_PROGRESS, game.getState().getStatus());
        assertEquals(Color.WHITE, game.getState().getCurrentTurn());

        Board board = game.getBoard();

        int pieceCount = 0;

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board.getPieceAt(new Position(row, col)) != null) {
                    pieceCount++;
                }
            }
        }

        assertEquals(32, pieceCount);

        for (int row = 2; row <= 5; row++) {
            for (int col = 0; col < 8; col++) {
                assertNull(board.getPieceAt(new Position(row, col)));
            }
        }

        assertEquals(PieceType.ROOK, board.getPieceAt(new Position(0, 0)).getType());
        assertEquals(PieceType.KING, board.getPieceAt(new Position(0, 4)).getType());
        assertEquals(PieceType.ROOK, board.getPieceAt(new Position(0, 7)).getType());

        assertEquals(PieceType.PAWN, board.getPieceAt(new Position(1, 0)).getType());
        assertEquals(PieceType.PAWN, board.getPieceAt(new Position(1, 7)).getType());

        assertEquals(PieceType.PAWN, board.getPieceAt(new Position(6, 0)).getType());
        assertEquals(PieceType.PAWN, board.getPieceAt(new Position(6, 7)).getType());

        assertEquals(PieceType.ROOK, board.getPieceAt(new Position(7, 0)).getType());
        assertEquals(PieceType.KING, board.getPieceAt(new Position(7, 4)).getType());
        assertEquals(PieceType.ROOK, board.getPieceAt(new Position(7, 7)).getType());

        assertEquals(Color.WHITE, board.getPieceAt(new Position(0, 0)).getColor());
        assertEquals(Color.BLACK, board.getPieceAt(new Position(7, 0)).getColor());
    }

    @Test
    void scenario2_invalidMove_sameSquareRejected() {
        assertThrows(IllegalArgumentException.class, () -> {
            game.makeMove(new Move(
                    new Position(0, 0),
                    new Position(0, 0)
            ));
        });
    }

    @Test
    void scenario2_multiTurnPlay() {
        // --- Initial state (from Scenario 1) ---
        assertTurn(Color.WHITE);
        assertEquals(GameStatus.IN_PROGRESS, game.getState().getStatus());

        // --- Move 1: White pawn ---
        move(1, 0, 2, 0);
        assertTurn(Color.BLACK);

        Piece whitePawn = game.getBoard().getPieceAt(new Position(2, 0));
        assertNotNull(whitePawn);
        assertEquals(Color.WHITE, whitePawn.getColor());

        // --- Move 2: Black pawn ---
        move(6, 0, 5, 0);
        assertTurn(Color.WHITE);

        Piece blackPawn = game.getBoard().getPieceAt(new Position(5, 0));
        assertNotNull(blackPawn);
        assertEquals(Color.BLACK, blackPawn.getColor());

        // --- Move 3: White moves again ---
        move(2, 0, 3, 0);
        assertTurn(Color.BLACK);

        // --- Final sanity checks ---
        assertEquals(GameStatus.IN_PROGRESS, game.getState().getStatus());
    }

    @Test
    void scenario3_winCondition_kingCapture() {
        Board board = game.getBoard();

        Piece whiteQueen = new Piece(PieceType.QUEEN, Color.WHITE);
        Piece blackKing = new Piece(PieceType.KING, Color.BLACK);

        Position attackerPos = new Position(3, 3);
        Position kingPos = new Position(3, 5);

        board.placePiece(whiteQueen, attackerPos);
        board.placePiece(blackKing, kingPos);

        game.makeMove(new Move(attackerPos, kingPos));

        assertEquals(GameStatus.CHECKMATE, game.getState().getStatus());
        assertEquals(Color.WHITE, game.getState().getWinner());
    }

}