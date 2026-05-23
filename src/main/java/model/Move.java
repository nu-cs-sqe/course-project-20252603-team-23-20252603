package model;

public class Move {

    private static final String ERR_FROM_NULL          = "From position cannot be null";
    private static final String ERR_TO_NULL            = "To position cannot be null";
    private static final String ERR_FROM_OUT_OF_BOUNDS = "From position is out of bounds";
    private static final String ERR_TO_OUT_OF_BOUNDS   = "To position is out of bounds";

    private final Position from;
    private final Position to;

    public Move(Position from, Position to) {
        if (from == null) {
            throw new IllegalArgumentException(ERR_FROM_NULL);
        }
        if (to == null) {
            throw new IllegalArgumentException(ERR_TO_NULL);
        }
        if (!from.isValid()) {
            throw new IllegalArgumentException(ERR_FROM_OUT_OF_BOUNDS);
        }
        if (!to.isValid()) {
            throw new IllegalArgumentException(ERR_TO_OUT_OF_BOUNDS);
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