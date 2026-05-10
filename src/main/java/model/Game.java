package model;

public class Game {

    private final Board board;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private final GameState state;

    public Game(String whiteName, String blackName) {

        if (whiteName == null || whiteName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        if (blackName == null || blackName.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.whitePlayer = new Player(whiteName, Color.WHITE);
        this.blackPlayer = new Player(blackName, Color.BLACK);

        this.board = new Board();
        this.state = new GameState();
    }

    public void setup() {
        board.initialize();
        state.setStatus(GameStatus.IN_PROGRESS);

        // Ensure consistent starting state
        state.switchTurn(); // GameState starts WHITE; optional safety reset below

        // Force correct starting turn (WHITE must start)
        if (state.getCurrentTurn() != Color.WHITE) {
            state.switchTurn();
        }
    }

    public Board getBoard() {
        return board;
    }

    public GameState getState() {
        return state;
    }

    public Player getCurrentPlayer() {
        return (state.getCurrentTurn() == Color.WHITE)
                ? whitePlayer
                : blackPlayer;
    }

    public void makeMove(Move move) {
        validateMove(move);
        board.movePiece(move.getFrom(), move.getTo());
        state.switchTurn();
    }

    private void validateMove(Move move) {
        if (move == null) {
            throw new IllegalArgumentException("Move cannot be null");
        }
        if (state.getStatus() != GameStatus.IN_PROGRESS) {
            throw new IllegalStateException("Game is not in progress");
        }
        Piece piece = board.getPieceAt(move.getFrom());
        if (piece == null) {
            throw new IllegalArgumentException("No piece at source position");
        }
        if (piece.getColor() != state.getCurrentTurn()) {
            throw new IllegalArgumentException("Cannot move opponent's piece");
        }
        Piece target = board.getPieceAt(move.getTo());
        if (target != null && target.getColor() == state.getCurrentTurn()) {
            throw new IllegalArgumentException("Cannot move to square occupied by own piece");
        }
    }
}
