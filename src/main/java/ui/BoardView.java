package ui;

import javafx.scene.layout.GridPane;
import model.Board;
import model.Position;

import java.util.function.BiConsumer;

public class BoardView extends GridPane {

    public static final int CELL_SIZE = 70;

    private static final int MAX_INDEX = Board.BOARD_SIZE - 1;

    private final BoardCell[][] cells = new BoardCell[Board.BOARD_SIZE][Board.BOARD_SIZE];
    private BiConsumer<Integer, Integer> clickHandler;

    public BoardView() {
        buildGrid();
    }

    private void buildGrid() {
        for (int gridRow = 0; gridRow < Board.BOARD_SIZE; gridRow++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                addCellAt(gridRow, col);
            }
        }
    }

    private void addCellAt(int gridRow, int col) {
        BoardCell cell = new BoardCell(isLightSquare(gridRow, col));
        cell.setOnMouseClicked(e -> notifyClick(gridRow, col));
        cells[gridRow][col] = cell;
        add(cell, col, gridRow);
    }

    private void notifyClick(int gridRow, int col) {
        if (clickHandler != null) {
            clickHandler.accept(toModelRow(gridRow), col);
        }
    }

    public void refresh(Board board, Position selected) {
        for (int modelRow = 0; modelRow < Board.BOARD_SIZE; modelRow++) {
            for (int col = 0; col < Board.BOARD_SIZE; col++) {
                refreshCell(board, modelRow, col, selected);
            }
        }
    }

    private void refreshCell(Board board, int modelRow, int col, Position selected) {
        Position pos = new Position(modelRow, col);
        BoardCell cell = cells[toGridRow(modelRow)][col];
        cell.setSelected(isSelected(pos, selected));
        cell.setPiece(board.getPieceAt(pos));
    }

    public void setClickHandler(BiConsumer<Integer, Integer> handler) {
        this.clickHandler = handler;
    }

    private int toGridRow(int modelRow) {
        return MAX_INDEX - modelRow;
    }

    private int toModelRow(int gridRow) {
        return MAX_INDEX - gridRow;
    }

    private boolean isLightSquare(int gridRow, int col) {
        return (gridRow + col) % 2 == 0;
    }

    private boolean isSelected(Position pos, Position selected) {
        return selected != null && selected.equals(pos);
    }
}
