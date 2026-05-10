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
    void placePiece_nullPosition_throwsException() { // BVA-BD-04
        Board board = new Board();
        Piece piece = new Piece(PieceType.KING, Color.WHITE);

        assertThrows(IllegalArgumentException.class, () -> {
            board.placePiece(piece, null);
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
    void getPieceAt_emptySquare_returnsNull() { // BVA-BD-06
        Board board = new Board();

        assertNull(board.getPieceAt(new Position(3, 3)));
    }

    @Test
    void getPieceAt_occupiedSquare_returnsPiece() { // BVA-BD-07
        Board board = new Board();
        Piece piece = new Piece(PieceType.QUEEN, Color.BLACK);

        Position pos = new Position(2, 2);
        board.placePiece(piece, pos);

        assertEquals(piece, board.getPieceAt(pos));
    }

    @Test
    void getPieceAt_nullPosition_throwsException() { // BVA-BD-08
        Board board = new Board();

        assertThrows(IllegalArgumentException.class, () -> {
            board.getPieceAt(null);
        });
    }

    @Test
    void getPieceAt_invalidPosition_throwsException() { // BVA-BD-09
        Board board = new Board();

        assertThrows(IllegalArgumentException.class, () -> {
            board.getPieceAt(new Position(9, 9));
        });
    }

    @Test
    void isEmpty_emptySquare_returnsTrue() { // BVA-BD-10
        Board board = new Board();
        assertTrue(board.isEmpty(new Position(4, 4)));
    }

    @Test
    void isEmpty_occupiedSquare_returnsFalse() { // BVA-BD-11
        Board board = new Board();
        Piece piece = new Piece(PieceType.QUEEN, Color.BLACK);

        Position pos = new Position(2, 2);
        board.placePiece(piece, pos);

        assertFalse(board.isEmpty(pos));
    }

    @Test
    void isEmpty_invalidPosition_throwsException() { // BVA-BD-12
        Board board = new Board();

        assertThrows(IllegalArgumentException.class, () -> {
            board.isEmpty(new Position(8, 8));
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

    @Test
    void initialize_whiteBackRow_correctLayout() { // BVA-BD-14
        Board board = new Board();
        board.initialize();

        PieceType[] expected = {
                PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
                PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK
        };

        for (int c = 0; c < 8; c++) {
            Piece p = board.getPieceAt(new Position(0, c));
            assertEquals(expected[c], p.getType());
            assertEquals(Color.WHITE, p.getColor());
        }
    }

    @Test
    void initialize_whitePawns_correctRow() { // BVA-BD-15
        Board board = new Board();
        board.initialize();

        for (int c = 0; c < 8; c++) {
            Piece p = board.getPieceAt(new Position(1, c));
            assertEquals(PieceType.PAWN, p.getType());
            assertEquals(Color.WHITE, p.getColor());
        }
    }

    @Test
    void initialize_blackPawns_correctRow() { // BVA-BD-16
        Board board = new Board();
        board.initialize();

        for (int c = 0; c < 8; c++) {
            Piece p = board.getPieceAt(new Position(6, c));
            assertEquals(PieceType.PAWN, p.getType());
            assertEquals(Color.BLACK, p.getColor());
        }
    }

    @Test
    void initialize_blackBackRow_correctLayout() { // BVA-BD-17
        Board board = new Board();
        board.initialize();

        PieceType[] expected = {
                PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
                PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK
        };

        for (int c = 0; c < 8; c++) {
            Piece p = board.getPieceAt(new Position(7, c));
            assertEquals(expected[c], p.getType());
            assertEquals(Color.BLACK, p.getColor());
        }
    }

    @Test
    void initialize_middleRows_areEmpty() { // BVA-BD-18
        Board board = new Board();
        board.initialize();

        for (int r = 2; r <= 5; r++) {
            for (int c = 0; c < 8; c++) {
                assertNull(board.getPieceAt(new Position(r, c)));
            }
        }
    }

    @Test
    void movePiece_validMove_pieceAtDestination() { // BVA-BD-19
        Board board = new Board();
        Piece piece = new Piece(PieceType.ROOK, Color.WHITE);
        Position from = new Position(0, 0);
        Position to = new Position(4, 0);
        board.placePiece(piece, from);

        board.movePiece(from, to);

        assertEquals(piece, board.getPieceAt(to));
    }

    @Test
    void movePiece_validMove_sourceCleared() { // BVA-BD-20
        Board board = new Board();
        Piece piece = new Piece(PieceType.ROOK, Color.WHITE);
        Position from = new Position(0, 0);
        Position to = new Position(4, 0);
        board.placePiece(piece, from);

        board.movePiece(from, to);

        assertNull(board.getPieceAt(from));
    }

    @Test
    void movePiece_capturesPieceAtDestination() { // BVA-BD-21
        Board board = new Board();
        Piece white = new Piece(PieceType.ROOK, Color.WHITE);
        Piece black = new Piece(PieceType.PAWN, Color.BLACK);
        Position from = new Position(0, 0);
        Position to = new Position(4, 0);
        board.placePiece(white, from);
        board.placePiece(black, to);

        board.movePiece(from, to);

        assertEquals(white, board.getPieceAt(to));
    }

    @Test
    void movePiece_emptySource_throwsIllegalArgumentException() { // BVA-BD-22
        Board board = new Board();

        assertThrows(IllegalArgumentException.class,
                () -> board.movePiece(new Position(3, 3), new Position(4, 3)));
    }

}
