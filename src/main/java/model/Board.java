package model;

public class Board {

    public static final int BOARD_SIZE = 8;

    private static final int WHITE_BACK_ROW = 0;
    private static final int WHITE_PAWN_ROW = 1;
    private static final int BLACK_PAWN_ROW = 6;
    private static final int BLACK_BACK_ROW = 7;

    private static final int ROOK_QUEEN_SIDE   = 0;
    private static final int KNIGHT_QUEEN_SIDE = 1;
    private static final int BISHOP_QUEEN_SIDE = 2;
    private static final int QUEEN_FILE        = 3;
    private static final int KING_FILE         = 4;
    private static final int BISHOP_KING_SIDE  = 5;
    private static final int KNIGHT_KING_SIDE  = 6;
    private static final int ROOK_KING_SIDE    = 7;

    private final Piece[][] grid;

    public Board() {
        this.grid = new Piece[BOARD_SIZE][BOARD_SIZE];
    }

    public Piece getPieceAt(Position pos) {
        validatePosition(pos);
        return grid[pos.getRow()][pos.getCol()];
    }

    public boolean isEmpty(Position pos) {
        validatePosition(pos);
        return grid[pos.getRow()][pos.getCol()] == null;
    }

    public void placePiece(Piece piece, Position pos) {
        if (piece == null) {
            throw new IllegalArgumentException("Piece cannot be null");
        }
        validatePosition(pos);

        grid[pos.getRow()][pos.getCol()] = piece;
    }

    public void initialize() {

        // clear board first (important if reused)
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                grid[r][c] = null;
            }
        }

        // White back row
        grid[WHITE_BACK_ROW][ROOK_QUEEN_SIDE]   = new Piece(PieceType.ROOK,   Color.WHITE);
        grid[WHITE_BACK_ROW][KNIGHT_QUEEN_SIDE]  = new Piece(PieceType.KNIGHT, Color.WHITE);
        grid[WHITE_BACK_ROW][BISHOP_QUEEN_SIDE]  = new Piece(PieceType.BISHOP, Color.WHITE);
        grid[WHITE_BACK_ROW][QUEEN_FILE]         = new Piece(PieceType.QUEEN,  Color.WHITE);
        grid[WHITE_BACK_ROW][KING_FILE]          = new Piece(PieceType.KING,   Color.WHITE);
        grid[WHITE_BACK_ROW][BISHOP_KING_SIDE]   = new Piece(PieceType.BISHOP, Color.WHITE);
        grid[WHITE_BACK_ROW][KNIGHT_KING_SIDE]   = new Piece(PieceType.KNIGHT, Color.WHITE);
        grid[WHITE_BACK_ROW][ROOK_KING_SIDE]     = new Piece(PieceType.ROOK,   Color.WHITE);

        // White pawns
        for (int c = 0; c < BOARD_SIZE; c++) {
            grid[WHITE_PAWN_ROW][c] = new Piece(PieceType.PAWN, Color.WHITE);
        }

        // Black pawns
        for (int c = 0; c < BOARD_SIZE; c++) {
            grid[BLACK_PAWN_ROW][c] = new Piece(PieceType.PAWN, Color.BLACK);
        }

        // Black back row
        grid[BLACK_BACK_ROW][ROOK_QUEEN_SIDE]   = new Piece(PieceType.ROOK,   Color.BLACK);
        grid[BLACK_BACK_ROW][KNIGHT_QUEEN_SIDE]  = new Piece(PieceType.KNIGHT, Color.BLACK);
        grid[BLACK_BACK_ROW][BISHOP_QUEEN_SIDE]  = new Piece(PieceType.BISHOP, Color.BLACK);
        grid[BLACK_BACK_ROW][QUEEN_FILE]         = new Piece(PieceType.QUEEN,  Color.BLACK);
        grid[BLACK_BACK_ROW][KING_FILE]          = new Piece(PieceType.KING,   Color.BLACK);
        grid[BLACK_BACK_ROW][BISHOP_KING_SIDE]   = new Piece(PieceType.BISHOP, Color.BLACK);
        grid[BLACK_BACK_ROW][KNIGHT_KING_SIDE]   = new Piece(PieceType.KNIGHT, Color.BLACK);
        grid[BLACK_BACK_ROW][ROOK_KING_SIDE]     = new Piece(PieceType.ROOK,   Color.BLACK);
    }

    public void movePiece(Position from, Position to) {
        validatePosition(from);
        validatePosition(to);
        Piece piece = grid[from.getRow()][from.getCol()];
        if (piece == null) {
            throw new IllegalArgumentException("No piece at source position");
        }
        grid[to.getRow()][to.getCol()] = piece;
        grid[from.getRow()][from.getCol()] = null;
    }

    private void validatePosition(Position pos) {
        if (pos == null || !pos.isValid()) {
            throw new IllegalArgumentException("Invalid position");
        }
    }
}