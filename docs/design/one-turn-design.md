# Design: One Turn of the Game

## Overview

This document describes the classes and methods added or updated to support one complete turn of chess. A turn consists of: selecting a piece, computing valid moves, executing a move, and switching the active player.

Special moves (castling, en passant, pawn promotion) and check detection are out of scope for this phase.

---

## New Classes

### 1. `Move`

Represents a single move: a piece moving from one position to another.

```java
class Move {
    Position from;
    Position to;

    Move(Position from, Position to)   // throws IllegalArgumentException if either is null
    Position getFrom()
    Position getTo()
    boolean equals(Object o)
}
```

---

### 2. `MoveValidator`

Stateless utility. Given a board and a position, returns all legal destination squares for the piece at that position.

```java
class MoveValidator {
    // Returns all positions the piece at `from` can legally move to.
    // Returns empty list if the square is empty or the position is invalid.
    List<Position> getValidMoves(Board board, Position from)
}
```

Movement rules delegated per piece type:

| Piece  | Helper method |
|--------|---------------|
| KING   | `kingMoves(board, from, color)` |
| QUEEN  | `queenMoves(board, from, color)` |
| ROOK   | `rookMoves(board, from, color)` |
| BISHOP | `bishopMoves(board, from, color)` |
| KNIGHT | `knightMoves(board, from, color)` |
| PAWN   | `pawnMoves(board, from, color)` |

Sliding pieces (queen, rook, bishop) stop when they hit any piece; they can land on an opponent square (capture) but not on a same-color square.

---

## Updated Classes

### 3. `Board` (new methods)

```java
// Moves the piece from `from` to `to`. Replaces any piece at `to`.
// Throws IllegalArgumentException if either position is invalid or `from` is empty.
void movePiece(Position from, Position to)
```

---

### 4. `Game` (new methods)

```java
// Returns all legal destination squares for the piece at `pos`,
// or an empty list if `pos` is empty or not the current player's piece.
List<Position> getValidMoves(Position pos)

// Executes the move if it is legal for the current player.
// Returns true if the move was made; false if rejected.
// On success: updates board, switches turn.
boolean makeMove(Move move)
```

---

## Class Relationships

```
Game
 ├── Board              ← updated: movePiece()
 ├── MoveValidator      ← new: getValidMoves(board, from)
 └── GameState          ← existing: switchTurn()

Move                    ← new value object (from, to)
```

---

## Package Structure (additions)

```
src/main/java/
└── model/
    ├── Move.java          (new)
    ├── MoveValidator.java (new)
    ├── Board.java         (updated: +movePiece)
    └── Game.java          (updated: +getValidMoves, +makeMove)
```

---

## Design Notes

- `MoveValidator` is a pure function over `Board` — no mutable state — making it straightforward to unit test in isolation.
- `Game.makeMove()` uses `MoveValidator` internally; callers do not need to call the validator separately.
- Pawn starting rank is defined as row 1 for White and row 6 for Black (matching `Board.initialize()`).