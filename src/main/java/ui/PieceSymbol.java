package ui;

import model.Color;
import model.Piece;

final class PieceSymbol {

    private PieceSymbol() {
        throw new UnsupportedOperationException("Utility class");
    }

    static String of(Piece piece) {
        return (piece.getColor() == Color.WHITE) ? white(piece) : black(piece);
    }

    private static String white(Piece piece) {
        switch (piece.getType()) {
            case KING:   return "♔";
            case QUEEN:  return "♕";
            case ROOK:   return "♖";
            case BISHOP: return "♗";
            case KNIGHT: return "♘";
            case PAWN:   return "♙";
            default:     return "?";
        }
    }

    private static String black(Piece piece) {
        switch (piece.getType()) {
            case KING:   return "♚";
            case QUEEN:  return "♛";
            case ROOK:   return "♜";
            case BISHOP: return "♝";
            case KNIGHT: return "♞";
            case PAWN:   return "♟";
            default:     return "?";
        }
    }
}
