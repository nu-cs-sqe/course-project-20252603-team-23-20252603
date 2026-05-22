package model;

public class GameState {

    private Color currentTurn;
    private GameStatus status;
    private Color winner;

    public GameState() {
        this.currentTurn = Color.WHITE;
        this.status = GameStatus.SETUP;
        this.winner = null;
    }

    public Color getCurrentTurn() {
        return currentTurn;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Color getWinner() {
        return winner;
    }

    public void setWinner(Color winner) {
        this.winner = winner;
    }

    public void switchTurn() {
        currentTurn = (currentTurn == Color.WHITE)
                ? Color.BLACK
                : Color.WHITE;
    }

    public void setStatus(GameStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }
}