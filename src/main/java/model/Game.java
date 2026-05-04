package model;

public class Game {

    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private GameState state;

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

    public Board getBoard() {
        return board;
    }

    public GameState getState() {
        return state;
    }
}
