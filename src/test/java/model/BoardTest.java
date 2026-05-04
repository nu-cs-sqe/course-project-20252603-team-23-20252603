package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    void newBoard_isEmpty() { // BVA-BD-01
        Board board = new Board();

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                assertNull(board.getPieceAt(new Position(r, c)));
            }
        }
    }

    @Test
    void placePiece_validPlacement() { // BVA-BD-02
        Board board = new Board();
        Piece piece = new Piece(PieceType.KING, Color.WHITE);

        board.placePiece(piece, new Position(0, 0));

        assertEquals(piece, board.getPieceAt(new Position(0, 0)));
    }

    @Test
    void placePiece_nullPiece_throws() { // BVA-BD-03
        Board board = new Board();

        assertThrows(IllegalArgumentException.class, () -> {
            board.placePiece(null, new Position(0, 0));
        });
    }

    @Test
    void placePiece_invalidPosition_throws() { // BVA-BD-05
        Board board = new Board();
        Piece piece = new Piece(PieceType.KING, Color.WHITE);

        assertThrows(IllegalArgumentException.class, () -> {
            board.placePiece(piece, new Position(-1, 0));
        });
    }

    @Test
    void initialize_places32Pieces() { // BVA-BD-13
        Board board = new Board();

        board.initialize();

        int count = 0;
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board.getPieceAt(new Position(r, c)) != null) {
                    count++;
                }
            }
        }

        assertEquals(32, count);
    }

}
