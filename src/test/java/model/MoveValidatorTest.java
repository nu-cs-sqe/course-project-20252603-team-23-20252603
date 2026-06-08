package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MoveValidatorTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(); // empty board
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    private void place(PieceType type, Color color, int row, int col) {
        board.placePiece(new Piece(type, color), new Position(row, col));
    }

    private boolean legal(int fr, int fc, int tr, int tc, Color turn) {
        return MoveValidator.isMoveLegal(
                new Move(new Position(fr, fc), new Position(tr, tc)), board, turn);
    }

    // ── King ─────────────────────────────────────────────────────────────────

    @Test
    void king_oneSquareHorizontal_valid() { // BVA-MV-K01
        place(PieceType.KING, Color.WHITE, 4, 4);
        assertTrue(legal(4, 4, 4, 5, Color.WHITE));
    }

    @Test
    void king_oneSquareDiagonal_valid() { // BVA-MV-K02
        place(PieceType.KING, Color.WHITE, 4, 4);
        assertTrue(legal(4, 4, 5, 5, Color.WHITE));
    }

    @Test
    void king_twoSquares_invalid() { // BVA-MV-K03
        place(PieceType.KING, Color.WHITE, 4, 4);
        assertFalse(legal(4, 4, 4, 6, Color.WHITE));
    }

    @Test
    void king_sameSquare_invalid() { // BVA-MV-K04
        place(PieceType.KING, Color.WHITE, 4, 4);
        assertFalse(legal(4, 4, 4, 4, Color.WHITE));
    }

    @Test
    void king_capturesEnemy_valid() { // BVA-MV-K05
        place(PieceType.KING, Color.WHITE, 4, 4);
        place(PieceType.PAWN, Color.BLACK, 4, 5);
        assertTrue(legal(4, 4, 4, 5, Color.WHITE));
    }

    @Test
    void king_capturesFriendly_invalid() { // BVA-MV-K06
        place(PieceType.KING, Color.WHITE, 4, 4);
        place(PieceType.PAWN, Color.WHITE, 4, 5);
        assertFalse(legal(4, 4, 4, 5, Color.WHITE));
    }

    // ── Rook ─────────────────────────────────────────────────────────────────

    @Test
    void rook_horizontal_valid() { // BVA-MV-R01
        place(PieceType.ROOK, Color.WHITE, 3, 0);
        assertTrue(legal(3, 0, 3, 7, Color.WHITE));
    }

    @Test
    void rook_vertical_valid() { // BVA-MV-R02
        place(PieceType.ROOK, Color.WHITE, 0, 3);
        assertTrue(legal(0, 3, 7, 3, Color.WHITE));
    }

    @Test
    void rook_diagonal_invalid() { // BVA-MV-R03
        place(PieceType.ROOK, Color.WHITE, 3, 3);
        assertFalse(legal(3, 3, 5, 5, Color.WHITE));
    }

    @Test
    void rook_blockedByFriendly_invalid() { // BVA-MV-R04
        place(PieceType.ROOK, Color.WHITE, 3, 0);
        place(PieceType.PAWN, Color.WHITE, 3, 3);
        assertFalse(legal(3, 0, 3, 6, Color.WHITE));
    }

    @Test
    void rook_capturesBlockingEnemy_valid() { // BVA-MV-R05
        place(PieceType.ROOK, Color.WHITE, 3, 0);
        place(PieceType.PAWN, Color.BLACK, 3, 4);
        assertTrue(legal(3, 0, 3, 4, Color.WHITE));
    }

    @Test
    void rook_blockedByEnemy_cannotJumpOver() { // BVA-MV-R06
        place(PieceType.ROOK, Color.WHITE, 3, 0);
        place(PieceType.PAWN, Color.BLACK, 3, 4);
        assertFalse(legal(3, 0, 3, 6, Color.WHITE));
    }

    // ── Bishop ───────────────────────────────────────────────────────────────

    @Test
    void bishop_diagonal_valid() { // BVA-MV-B01
        place(PieceType.BISHOP, Color.WHITE, 3, 3);
        assertTrue(legal(3, 3, 6, 6, Color.WHITE));
    }

    @Test
    void bishop_antiDiagonal_valid() { // BVA-MV-B02
        place(PieceType.BISHOP, Color.WHITE, 3, 3);
        assertTrue(legal(3, 3, 0, 0, Color.WHITE));
    }

    @Test
    void bishop_horizontal_invalid() { // BVA-MV-B03
        place(PieceType.BISHOP, Color.WHITE, 3, 3);
        assertFalse(legal(3, 3, 3, 6, Color.WHITE));
    }

    @Test
    void bishop_blockedOnPath_invalid() { // BVA-MV-B04
        place(PieceType.BISHOP, Color.WHITE, 3, 3);
        place(PieceType.PAWN, Color.BLACK, 4, 4);
        assertFalse(legal(3, 3, 6, 6, Color.WHITE));
    }

    // ── Queen ─────────────────────────────────────────────────────────────────

    @Test
    void queen_horizontal_valid() { // BVA-MV-Q01
        place(PieceType.QUEEN, Color.WHITE, 4, 0);
        assertTrue(legal(4, 0, 4, 7, Color.WHITE));
    }

    @Test
    void queen_vertical_valid() { // BVA-MV-Q02
        place(PieceType.QUEEN, Color.WHITE, 0, 4);
        assertTrue(legal(0, 4, 7, 4, Color.WHITE));
    }

    @Test
    void queen_diagonal_valid() { // BVA-MV-Q03
        place(PieceType.QUEEN, Color.WHITE, 0, 0);
        assertTrue(legal(0, 0, 7, 7, Color.WHITE));
    }

    @Test
    void queen_lShape_invalid() { // BVA-MV-Q04
        place(PieceType.QUEEN, Color.WHITE, 4, 4);
        assertFalse(legal(4, 4, 6, 5, Color.WHITE));
    }

    @Test
    void queen_blockedOnHorizontalPath_invalid() { // BVA-MV-Q05
        place(PieceType.QUEEN, Color.WHITE, 4, 0);
        place(PieceType.PAWN, Color.WHITE, 4, 3);
        assertFalse(legal(4, 0, 4, 7, Color.WHITE));
    }

    // ── Knight ───────────────────────────────────────────────────────────────

    @Test
    void knight_twoOneShape_valid() { // BVA-MV-N01
        place(PieceType.KNIGHT, Color.WHITE, 4, 4);
        assertTrue(legal(4, 4, 6, 5, Color.WHITE));
    }

    @Test
    void knight_oneTwoShape_valid() { // BVA-MV-N02
        place(PieceType.KNIGHT, Color.WHITE, 4, 4);
        assertTrue(legal(4, 4, 5, 6, Color.WHITE));
    }

    @Test
    void knight_allEightJumps_allValid() { // BVA-MV-N03
        place(PieceType.KNIGHT, Color.WHITE, 4, 4);
        int[][] targets = {{6,5},{6,3},{2,5},{2,3},{5,6},{5,2},{3,6},{3,2}};
        for (int[] t : targets) {
            assertTrue(legal(4, 4, t[0], t[1], Color.WHITE),
                    "Expected valid: (" + t[0] + "," + t[1] + ")");
        }
    }

    @Test
    void knight_jumpsOverPiece_valid() { // BVA-MV-N04
        place(PieceType.KNIGHT, Color.WHITE, 4, 4);
        place(PieceType.PAWN, Color.BLACK, 5, 4);
        assertTrue(legal(4, 4, 6, 5, Color.WHITE));
    }

    @Test
    void knight_straightMove_invalid() { // BVA-MV-N05
        place(PieceType.KNIGHT, Color.WHITE, 4, 4);
        assertFalse(legal(4, 4, 4, 6, Color.WHITE));
    }

    // ── Pawn (White) ─────────────────────────────────────────────────────────

    @Test
    void whitePawn_oneForward_valid() { // BVA-MV-PW01
        place(PieceType.PAWN, Color.WHITE, 3, 3);
        assertTrue(legal(3, 3, 4, 3, Color.WHITE));
    }

    @Test
    void whitePawn_twoForwardFromStart_valid() { // BVA-MV-PW02
        place(PieceType.PAWN, Color.WHITE, 1, 3);
        assertTrue(legal(1, 3, 3, 3, Color.WHITE));
    }

    @Test
    void whitePawn_twoForwardNotFromStart_invalid() { // BVA-MV-PW03 (BVA boundary)
        place(PieceType.PAWN, Color.WHITE, 2, 3);
        assertFalse(legal(2, 3, 4, 3, Color.WHITE));
    }

    @Test
    void whitePawn_oneForwardBlocked_invalid() { // BVA-MV-PW04
        place(PieceType.PAWN, Color.WHITE, 3, 3);
        place(PieceType.PAWN, Color.BLACK, 4, 3);
        assertFalse(legal(3, 3, 4, 3, Color.WHITE));
    }

    @Test
    void whitePawn_twoForwardPathBlocked_invalid() { // BVA-MV-PW05
        place(PieceType.PAWN, Color.WHITE, 1, 3);
        place(PieceType.PAWN, Color.BLACK, 2, 3);
        assertFalse(legal(1, 3, 3, 3, Color.WHITE));
    }

    @Test
    void whitePawn_diagonalCapture_valid() { // BVA-MV-PW06
        place(PieceType.PAWN, Color.WHITE, 3, 3);
        place(PieceType.PAWN, Color.BLACK, 4, 4);
        assertTrue(legal(3, 3, 4, 4, Color.WHITE));
    }

    @Test
    void whitePawn_diagonalToEmpty_invalid() { // BVA-MV-PW07
        place(PieceType.PAWN, Color.WHITE, 3, 3);
        assertFalse(legal(3, 3, 4, 4, Color.WHITE));
    }

    @Test
    void whitePawn_backward_invalid() { // BVA-MV-PW08
        place(PieceType.PAWN, Color.WHITE, 3, 3);
        assertFalse(legal(3, 3, 2, 3, Color.WHITE));
    }

    @Test
    void whitePawn_forwardCapture_invalid() { // BVA-MV-PW09
        place(PieceType.PAWN, Color.WHITE, 3, 3);
        place(PieceType.PAWN, Color.BLACK, 4, 3);
        assertFalse(legal(3, 3, 4, 3, Color.WHITE));
    }

    // ── Pawn (Black) ─────────────────────────────────────────────────────────

    @Test
    void blackPawn_oneForward_valid() { // BVA-MV-PB01
        place(PieceType.PAWN, Color.BLACK, 4, 3);
        assertTrue(legal(4, 3, 3, 3, Color.BLACK));
    }

    @Test
    void blackPawn_twoForwardFromStart_valid() { // BVA-MV-PB02
        place(PieceType.PAWN, Color.BLACK, 6, 3);
        assertTrue(legal(6, 3, 4, 3, Color.BLACK));
    }

    @Test
    void blackPawn_twoForwardNotFromStart_invalid() { // BVA-MV-PB03 (BVA boundary)
        place(PieceType.PAWN, Color.BLACK, 5, 3);
        assertFalse(legal(5, 3, 3, 3, Color.BLACK));
    }

    @Test
    void blackPawn_diagonalCapture_valid() { // BVA-MV-PB04
        place(PieceType.PAWN, Color.BLACK, 4, 3);
        place(PieceType.PAWN, Color.WHITE, 3, 2);
        assertTrue(legal(4, 3, 3, 2, Color.BLACK));
    }

    @Test
    void blackPawn_backward_invalid() { // BVA-MV-PB05
        place(PieceType.PAWN, Color.BLACK, 4, 3);
        assertFalse(legal(4, 3, 5, 3, Color.BLACK));
    }

    // ── getLegalDestinations ─────────────────────────────────────────────────

    @Test
    void getLegalDestinations_rookInCenter_hasCorrectCount() { // BVA-MV-LD01
        place(PieceType.ROOK, Color.WHITE, 4, 4);
        List<Position> moves = MoveValidator.getLegalDestinations(
                new Position(4, 4), board, Color.WHITE);
        // 7 horizontal + 7 vertical = 14
        assertEquals(14, moves.size());
    }

    @Test
    void getLegalDestinations_noLegalMoves_empty() { // BVA-MV-LD02
        // King completely surrounded by own pieces
        place(PieceType.KING, Color.WHITE, 0, 0);
        place(PieceType.PAWN, Color.WHITE, 0, 1);
        place(PieceType.PAWN, Color.WHITE, 1, 0);
        place(PieceType.PAWN, Color.WHITE, 1, 1);
        List<Position> moves = MoveValidator.getLegalDestinations(
                new Position(0, 0), board, Color.WHITE);
        assertTrue(moves.isEmpty());
    }
}
