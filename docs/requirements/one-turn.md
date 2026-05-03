# Feature: One Turn of the Game

## User Story

As a chess player, I want to select one of my pieces and move it to a valid square, so that I can take my turn and pass control to my opponent.

### Acceptance Criteria

✅ A player can only move a piece of their own color.

✅ The system must display or compute all legal destination squares for a selected piece.

✅ A move to an occupied square of the same color is rejected.

✅ A move to an occupied square of the opponent's color results in a capture.

✅ Each piece type moves according to standard chess movement rules.

✅ After a valid move is made, the board state is updated and the turn switches to the other player.

✅ An invalid move (wrong color, blocked path, out of bounds) is rejected without changing game state.

---

## Use Case 1: Make a Valid Move

**Actor**: Current player (White or Black)

**Preconditions**: The game is IN_PROGRESS and it is the actor's turn.

**Main Flow**:

1. Player selects a square containing one of their own pieces.
2. System computes all legal destination squares for that piece.
3. Player selects one of the legal destination squares.
4. System moves the piece from the source square to the destination square.
5. If the destination was occupied by an opponent piece, that piece is captured and removed.
6. System switches the current turn to the other player.
7. GUI updates to reflect the new board state and current turn.

**Alternate Flows**:

- 1a. Player selects an empty square → system does nothing (no piece selected).
- 1b. Player selects an opponent's piece → system rejects selection and does nothing.
- 3a. Player selects a square not in the legal move list → system rejects the move and does nothing.

**Postconditions**:

- The piece has moved from its source square to its destination square.
- Any captured piece is removed from the board.
- The turn has switched to the other player.

---

## Use Case 2: Attempt an Invalid Move

**Actor**: Current player

**Preconditions**: The game is IN_PROGRESS and it is the actor's turn.

**Main Flow**:

1. Player selects one of their own pieces.
2. Player selects a destination that is illegal for that piece (blocked, wrong direction, out of bounds, or occupied by own piece).
3. System rejects the move.
4. The board state and current turn remain unchanged.

**Postconditions**: Game state is unchanged; player may try again.

---

## Piece Movement Rules (summary)

| Piece  | Movement Rule |
|--------|---------------|
| King   | One square in any of 8 directions |
| Queen  | Any number of squares in any of 8 directions (blocked by pieces) |
| Rook   | Any number of squares horizontally or vertically (blocked by pieces) |
| Bishop | Any number of squares diagonally (blocked by pieces) |
| Knight | L-shape (±2, ±1) or (±1, ±2); jumps over pieces |
| Pawn   | Forward 1 square (or 2 from starting rank); captures diagonally forward 1 |

> **Scope note**: Special moves (castling, en passant, pawn promotion) and check detection are deferred to later phases.