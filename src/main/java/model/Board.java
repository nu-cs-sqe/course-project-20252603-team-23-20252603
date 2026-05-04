package model;

public class Board {

    private final Piece[][] grid;

    public Board() {
        this.grid = new Piece[8][8];
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
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                grid[r][c] = null;
            }
        }

        // White back row (row 0)
        grid[0][0] = new Piece(PieceType.ROOK, Color.WHITE);
        grid[0][1] = new Piece(PieceType.KNIGHT, Color.WHITE);
        grid[0][2] = new Piece(PieceType.BISHOP, Color.WHITE);
        grid[0][3] = new Piece(PieceType.QUEEN, Color.WHITE);
        grid[0][4] = new Piece(PieceType.KING, Color.WHITE);
        grid[0][5] = new Piece(PieceType.BISHOP, Color.WHITE);
        grid[0][6] = new Piece(PieceType.KNIGHT, Color.WHITE);
        grid[0][7] = new Piece(PieceType.ROOK, Color.WHITE);

        // White pawns (row 1)
        for (int c = 0; c < 8; c++) {
            grid[1][c] = new Piece(PieceType.PAWN, Color.WHITE);
        }

        // Black pawns (row 6)
        for (int c = 0; c < 8; c++) {
            grid[6][c] = new Piece(PieceType.PAWN, Color.BLACK);
        }

        // Black back row (row 7)
        grid[7][0] = new Piece(PieceType.ROOK, Color.BLACK);
        grid[7][1] = new Piece(PieceType.KNIGHT, Color.BLACK);
        grid[7][2] = new Piece(PieceType.BISHOP, Color.BLACK);
        grid[7][3] = new Piece(PieceType.QUEEN, Color.BLACK);
        grid[7][4] = new Piece(PieceType.KING, Color.BLACK);
        grid[7][5] = new Piece(PieceType.BISHOP, Color.BLACK);
        grid[7][6] = new Piece(PieceType.KNIGHT, Color.BLACK);
        grid[7][7] = new Piece(PieceType.ROOK, Color.BLACK);
    }

    private void validatePosition(Position pos) {
        if (pos == null || !pos.isValid()) {
            throw new IllegalArgumentException("Invalid position");
        }
    }
}