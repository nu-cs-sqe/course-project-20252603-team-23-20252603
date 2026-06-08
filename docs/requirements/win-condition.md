# Feature: Win Condition

## User Story

As a chess player, I want the game to detect when a king has been captured and declare a winner, so that the game ends correctly and the result is recorded.

### Acceptance Criteria

✅ When a player's move captures the opponent's King, the game status is set to CHECKMATE.

✅ The winner is recorded as the player who captured the King.

✅ After the game reaches CHECKMATE, the current turn does NOT switch.

✅ After CHECKMATE, no further moves are permitted.

✅ Capturing any piece that is NOT a King does not end the game; status remains IN_PROGRESS.

✅ Before any win condition is triggered, `getWinner()` returns null.

✅ The GUI displays a "Game Over" alert showing the winner's name when CHECKMATE is reached.

✅ The status bar updates to reflect the checkmate result.

---

## Use Case 1: King Captured — Game Ends

**Actor**: Current player (White or Black)

**Preconditions**: Game is IN_PROGRESS; current player has a piece that can reach the opponent's King in one move.

**Main Flow**:

1. Current player makes a move whose destination contains the opponent's King.
2. System removes the opponent's King from the board.
3. System sets game status to `CHECKMATE`.
4. System records the current player's color as the winner in `GameState`.
5. Turn is NOT switched.
6. GUI displays a "Game Over" alert with the winner's name.
7. Status bar shows the checkmate message.

**Postconditions**:
- `GameState.getStatus()` returns `CHECKMATE`.
- `GameState.getWinner()` returns the color of the capturing player.
- No further moves can be made.

---

## Use Case 2: Attempt to Move After Checkmate

**Actor**: Either player

**Preconditions**: Game status is `CHECKMATE`.

**Main Flow**:

1. A player attempts to make any move.
2. System rejects the move with an `IllegalStateException`.

**Postconditions**: Game state is unchanged.

---

## Use Case 3: Non-King Capture — Game Continues

**Actor**: Current player

**Preconditions**: Game is IN_PROGRESS.

**Main Flow**:

1. Current player makes a move that captures an opponent piece that is NOT the King.
2. System removes the captured piece.
3. System keeps status as `IN_PROGRESS`.
4. Turn switches to the other player normally.

**Postconditions**:
- `GameState.getStatus()` remains `IN_PROGRESS`.
- `GameState.getWinner()` remains null.

---

## Notes on Scope

> **Simplified win condition**: This implementation detects a win by direct King capture rather than traditional checkmate (king in check with no legal escape). Stalemate and draw conditions (`STALEMATE`, `DRAW`) are defined in the `GameStatus` enum but are not triggered by any current game logic. Full checkmate and stalemate detection are deferred to a later phase.
