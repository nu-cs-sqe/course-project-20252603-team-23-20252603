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
        // TODO: implement full chess setup later
    }

    private void validatePosition(Position pos) {
        if (pos == null || !pos.isValid()) {
            throw new IllegalArgumentException("Invalid position");
        }
    }
}