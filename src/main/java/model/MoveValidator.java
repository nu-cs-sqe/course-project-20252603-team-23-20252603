package model;

import java.util.ArrayList;
import java.util.List;

public final class MoveValidator {

    private static final int WHITE_PAWN_START = 1;
    private static final int BLACK_PAWN_START = 6;

    private MoveValidator() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static boolean isMoveLegal(Move move, Board board, Color currentTurn) {
        Position from = move.getFrom();
        Position to   = move.getTo();
        Piece piece   = board.getPieceAt(from);

        if (piece == null || piece.getColor() != currentTurn) {
            return false;
        }
        Piece target = board.getPieceAt(to);
        if (target != null && target.getColor() == currentTurn) {
            return false;
        }
        switch (piece.getType()) {
            case KING:   return isKingMove(from, to);
            case QUEEN:  return isQueenMove(from, to, board);
            case ROOK:   return isRookMove(from, to, board);
            case BISHOP: return isBishopMove(from, to, board);
            case KNIGHT: return isKnightMove(from, to);
            case PAWN:   return isPawnMove(from, to, piece.getColor(), board);
            default:     return false;
        }
    }

    public static List<Position> getLegalDestinations(Position from, Board board, Color currentTurn) {
        List<Position> result = new ArrayList<>();
        for (int row = 0; row < Board.BOARD_SIZE; row++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                Position to = new Position(row, col);
                if (!to.equals(from) && isMoveLegal(new Move(from, to), board, currentTurn)) {
                    result.add(to);
                }
            }
        }
        return result;
    }

    private static boolean isKingMove(Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        return rowDiff <= 1 && colDiff <= 1 && (rowDiff + colDiff) > 0;
    }

    private static boolean isQueenMove(Position from, Position to, Board board) {
        return isRookMove(from, to, board) || isBishopMove(from, to, board);
    }

    private static boolean isRookMove(Position from, Position to, Board board) {
        if (from.equals(to)) {
            return false;
        }
        if (from.getRow() != to.getRow() && from.getCol() != to.getCol()) {
            return false;
        }
        return isPathClear(from, to, board);
    }

    private static boolean isBishopMove(Position from, Position to, Board board) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        if (rowDiff == 0 || rowDiff != colDiff) {
            return false;
        }
        return isPathClear(from, to, board);
    }

    private static boolean isKnightMove(Position from, Position to) {
        int rowDiff = Math.abs(to.getRow() - from.getRow());
        int colDiff = Math.abs(to.getCol() - from.getCol());
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }

    private static boolean isPawnMove(Position from, Position to, Color color, Board board) {
        int direction = (color == Color.WHITE) ? 1 : -1;
        int startRow  = (color == Color.WHITE) ? WHITE_PAWN_START : BLACK_PAWN_START;
        int rowDiff   = to.getRow() - from.getRow();
        int colDiff   = Math.abs(to.getCol() - from.getCol());

        if (rowDiff == direction && colDiff == 0) {
            return board.getPieceAt(to) == null;
        }
        if (rowDiff == 2 * direction && colDiff == 0 && from.getRow() == startRow) {
            Position between = new Position(from.getRow() + direction, from.getCol());
            return board.getPieceAt(between) == null && board.getPieceAt(to) == null;
        }
        if (rowDiff == direction && colDiff == 1) {
            Piece target = board.getPieceAt(to);
            return target != null && target.getColor() != color;
        }
        return false;
    }

    private static boolean isPathClear(Position from, Position to, Board board) {
        int rowStep = Integer.signum(to.getRow() - from.getRow());
        int colStep = Integer.signum(to.getCol() - from.getCol());
        int row = from.getRow() + rowStep;
        int col = from.getCol() + colStep;
        while (row != to.getRow() || col != to.getCol()) {
            if (board.getPieceAt(new Position(row, col)) != null) {
                return false;
            }
            row += rowStep;
            col += colStep;
        }
        return true;
    }
}
