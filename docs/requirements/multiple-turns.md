# Feature: Multiple Turns of the Game

## User Story

As a chess player, I want the game to track whose turn it is and enforce that players alternate moves, so that both players take turns fairly throughout the game.

### Acceptance Criteria

✅ After a valid move is made by White, the turn switches to Black.

✅ After a valid move is made by Black, the turn switches back to White.

✅ A player cannot move an opponent's piece on their turn.

✅ A player cannot move to a square occupied by their own piece.

✅ A player cannot make a move from an empty square.

✅ A player cannot make a move if the game is not IN_PROGRESS.

✅ After a valid move, the source square is empty and the destination square holds the moved piece.

✅ If a move captures an opponent's piece, that piece is removed and replaced by the moving piece.

✅ The turn does NOT switch after the game has ended (CHECKMATE).

---

## Use Case 1: White Makes a Valid Move

**Actor**: White player

**Preconditions**: Game is IN_PROGRESS; it is White's turn.

**Main Flow**:

1. White selects a square containing one of their own pieces.
2. White selects a valid destination square.
3. System moves the piece from source to destination.
4. If destination contained a Black piece, it is removed (captured).
5. System switches the turn to Black.

**Postconditions**:
- Source square is empty.
- Destination square holds White's moved piece.
- Current turn is now Black.

---

## Use Case 2: Black Makes a Valid Move

**Actor**: Black player

**Preconditions**: Game is IN_PROGRESS; it is Black's turn.

**Main Flow**:

1. Black selects a square containing one of their own pieces.
2. Black selects a valid destination square.
3. System moves the piece.
4. System switches the turn to White.

**Postconditions**:
- Source square is empty.
- Destination square holds Black's moved piece.
- Current turn is now White.

---

## Use Case 3: Attempt an Invalid Move

**Actor**: Current player

**Preconditions**: Game is IN_PROGRESS.

**Main Flow**:

1. Player attempts one of the following invalid moves:
   - Moving from an empty square
   - Moving an opponent's piece
   - Moving to a square occupied by their own piece
   - Providing a null move
2. System rejects the move with an `IllegalArgumentException`.
3. Board state and current turn remain unchanged.

**Postconditions**: Game state is unchanged; player may try again.

---

## Use Case 4: Attempt a Move When Game Is Not IN_PROGRESS

**Actor**: Either player

**Preconditions**: Game status is SETUP or CHECKMATE.

**Main Flow**:

1. Player attempts to make any move.
2. System rejects the move with an `IllegalStateException`.

**Postconditions**: Game state is unchanged.

---

## Notes on Scope

> **Piece movement rules**: The current implementation does not enforce per-piece legal move geometry (e.g., a rook moving diagonally is accepted). Move legality is limited to: correct turn, non-null move, non-empty source, correct piece color, and non-friendly destination. Full movement rule enforcement is deferred to a later phase.

> **Check detection**: The system does not detect check or prevent moving into check. This is deferred to a later phase.
