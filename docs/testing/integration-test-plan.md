# Integration Test Plan

Author: Aidan Mott

## Purpose

This document defines integration tests for the chess game system focusing on end-to-end behavior across:

* `Game`
* `Board`
* `GameState`
* `Move`
* `Piece`

The goal is to verify that core gameplay flows work correctly when classes interact together.

### Scope

✔ Included:

* Game setup flow
* Multi-turn gameplay
* Win condition (king capture → checkmate)

✘ Excluded:

* GUI layer (`BoardPanel`, `MainFrame`, `SetupDialog`)
* Mocking frameworks (not allowed)

Tests are written using **JUnit 5** and placed in:

```
src/test/java/integration/
```

---

# Scenario 1: Full Game Setup

## Objective

Verify that `Game.setup()` correctly initializes the board and game state.

## Classes Involved

* Game
* Board
* GameState
* Player

## Test Flow

1. Create a new `Game`
2. Call `setup()`
3. Retrieve board and state
4. Verify initial conditions

## Expected State After Setup

* Board contains 32 pieces
* White pieces on rows 0–1
* Black pieces on rows 6–7
* Rows 2–5 are empty
* Game status = `IN_PROGRESS`
* Current turn = `WHITE`

## Key Assertions

```java
assertEquals(GameStatus.IN_PROGRESS, game.getState().getStatus());
assertEquals(Color.WHITE, game.getState().getCurrentTurn());
```

---

# Scenario 2: Multi-Turn Gameplay

## Objective

Verify that players can alternate moves and that the board updates correctly.

## Classes Involved

* Game
* Board
* GameState
* Move

## Test Flow

1. Initialize game and setup
2. White makes a valid move
3. Verify board update + turn switch
4. Black makes a valid move
5. Verify board update + turn switch back

## Sample Moves

White:

```
(1,0) → (3,0)
```

Black:

```
(6,0) → (4,0)
```

## Expected Behavior

* Pieces move correctly on board
* Source squares become empty
* Turns alternate correctly:

    * WHITE → BLACK → WHITE

## Key Assertions

```java
assertTrue(game.getState().getCurrentTurn() == Color.BLACK);
assertTrue(game.getState().getCurrentTurn() == Color.WHITE);
```

---

# Scenario 3: Win Condition (King Capture → Checkmate)

## Objective

Verify that capturing a king ends the game and updates game state to CHECKMATE.

## Classes Involved

* Game
* Board
* GameState
* Piece

## Important Note

Win condition is implemented in `Game.makeMove()`:

```java
if (captured != null && captured.getType() == PieceType.KING)
```

This triggers:

* `GameStatus.CHECKMATE`
* Winner assignment
* No further turn switching

## Test Flow

1. Create a simplified board state where a king can be captured
2. Execute a move that captures the king
3. Verify game state updates

## Expected Results

* Game status becomes `CHECKMATE`
* Winner is set in `GameState`
* Turn does NOT switch after checkmate
* Game is effectively ended

## Key Assertions

```java
assertEquals(GameStatus.CHECKMATE, game.getState().getStatus());
assertEquals(Color.WHITE, game.getState().getWinner());
```

# Notes on Test Design

### 1. No mocking

All tests use real `Game`, `Board`, and `GameState` objects.

### 2. Board state manipulation

Win condition tests may require manual board setup (placing kings directly).

### 3. Critical dependency

These integration tests assume `Board.movePiece()` correctly updates state.

---

# Success Criteria

Integration testing is complete when:

* Game setup initializes correct board state
* Multi-turn gameplay alternates correctly
* Moves update board and switch turns
* King capture triggers CHECKMATE
* GameState reflects correct end condition
