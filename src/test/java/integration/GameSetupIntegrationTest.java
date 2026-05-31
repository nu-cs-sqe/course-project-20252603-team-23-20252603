package integration;

import model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameSetupIntegrationTest {

    @Test
    void fullGameSetupInitializesBoardCorrectly() {
        Game game = new Game("Alice", "Bob");
        game.setup();

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

}