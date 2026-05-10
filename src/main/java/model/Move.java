package model;

public class Move {

    private final Position from;
    private final Position to;

    public Move(Position from, Position to) {
        if (from == null) {
            throw new IllegalArgumentException("From position cannot be null");
        }
        if (to == null) {
            throw new IllegalArgumentException("To position cannot be null");
        }
        if (!from.isValid()) {
            throw new IllegalArgumentException("From position is out of bounds");
        }
        if (!to.isValid()) {
            throw new IllegalArgumentException("To position is out of bounds");
        }
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}