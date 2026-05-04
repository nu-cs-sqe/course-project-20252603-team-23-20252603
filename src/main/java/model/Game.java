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
}
