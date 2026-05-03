# BVA: One Turn of the Game

Author: Xinyuan Liu
Classes: `Move`, `MoveValidator`, `Board.movePiece`, `Game.makeMove`, `Game.getValidMoves`

---

## 1. `Move` constructor

**Input**: `from` (Position), `to` (Position)
**Output**: valid `Move` object, or `IllegalArgumentException`

| Test Case  | from       | to         | Expected                    | Reason |
|------------|------------|------------|-----------------------------|--------|
| BVA-MV-01  | (1, 0)     | (3, 0)     | valid Move                  | normal case |
| BVA-MV-02  | null       | (3, 0)     | IllegalArgumentException    | null from |
| BVA-MV-03  | (1, 0)     | null       | IllegalArgumentException    | null to |
| BVA-MV-04  | (0, 0)     | (0, 0)     | IllegalArgumentException    | from == to (no-op move) |

---

## 2. `Board.movePiece(from, to)`

**Input**: `from` (Position), `to` (Position), board state
**Output**: board updated, or exception thrown

| Test Case   | Scenario                              | Expected                        |
|-------------|---------------------------------------|---------------------------------|
| BVA-BP-01   | from has a piece, to is empty         | piece moved; from is now empty  |
| BVA-BP-02   | from has a piece, to has opponent piece| piece moved; opponent removed   |
| BVA-BP-03   | from is empty                         | IllegalArgumentException        |
| BVA-BP-04   | from is invalid position              | IllegalArgumentException        |
| BVA-BP-05   | to is invalid position                | IllegalArgumentException        |

---

## 3. `MoveValidator.getValidMoves` — general rules

**Input**: `board` (Board), `from` (Position)
**Output**: `List<Position>` of legal destinations

| Test Case   | Scenario                              | Expected                          |
|-------------|---------------------------------------|-----------------------------------|
| BVA-VAL-01  | from is empty                         | empty list                        |
| BVA-VAL-02  | from is invalid                       | empty list                        |
| BVA-VAL-03  | piece surrounded by own pieces        | empty list                        |
| BVA-VAL-04  | piece on edge of board                | only in-bounds squares returned   |

---

## 4. `MoveValidator` — ROOK movement

A rook at (r, c) slides along the same row or column. Blocked by any piece; can capture on opponent square.

| Test Case   | Rook at | Board state                       | Expected destinations |
|-------------|---------|-----------------------------------|-----------------------|
| BVA-RK-01   | (3, 3)  | empty board                       | all 14 squares on row 3 and col 3 (excl. (3,3)) |
| BVA-RK-02   | (3, 3)  | own piece at (3, 5)               | (3,4) only in that direction (blocked) |
| BVA-RK-03   | (3, 3)  | opponent at (3, 5)                | (3,4) and (3,5) in that direction (capture) |
| BVA-RK-04   | (0, 0)  | empty board                       | 7 squares right + 7 squares up (corner) |
| BVA-RK-05   | (7, 7)  | empty board                       | 7 squares left + 7 squares down (corner) |

---

## 5. `MoveValidator` — BISHOP movement

A bishop slides diagonally. Blocked by any piece; can capture on opponent square.

| Test Case   | Bishop at | Board state        | Expected destinations |
|-------------|-----------|--------------------|-----------------------|
| BVA-BS-01   | (3, 3)    | empty board        | all diagonal squares: 13 total |
| BVA-BS-02   | (3, 3)    | own piece at (5,5) | (4,4) only on that diagonal |
| BVA-BS-03   | (3, 3)    | opponent at (5,5)  | (4,4) and (5,5) on that diagonal |
| BVA-BS-04   | (0, 0)    | empty board        | 7 squares on one diagonal (corner) |

---

## 6. `MoveValidator` — QUEEN movement

Queen combines rook + bishop.

| Test Case   | Queen at | Board state  | Expected destinations |
|-------------|----------|--------------|-----------------------|
| BVA-QN-01   | (3, 3)   | empty board  | 27 squares (all rook + bishop squares) |
| BVA-QN-02   | (0, 0)   | empty board  | 21 squares (corner) |

---

## 7. `MoveValidator` — KNIGHT movement

Knight jumps in L-shapes. Not blocked by intervening pieces.

| Test Case   | Knight at | Board state             | Expected destinations |
|-------------|-----------|-------------------------|-----------------------|
| BVA-KN-01   | (3, 3)    | empty board             | 8 squares |
| BVA-KN-02   | (0, 0)    | empty board             | 2 squares (corner, most out of bounds) |
| BVA-KN-03   | (0, 1)    | empty board             | 3 squares (edge) |
| BVA-KN-04   | (3, 3)    | own pieces at all 8 destinations | empty list |
| BVA-KN-05   | (3, 3)    | opponent pieces at all 8 destinations | 8 squares (all captures) |

---

## 8. `MoveValidator` — KING movement

King moves one square in any of 8 directions.

| Test Case   | King at | Board state             | Expected destinations |
|-------------|---------|-------------------------|-----------------------|
| BVA-KG-01   | (3, 3)  | empty board             | 8 squares |
| BVA-KG-02   | (0, 0)  | empty board             | 3 squares (corner) |
| BVA-KG-03   | (0, 3)  | empty board             | 5 squares (edge) |
| BVA-KG-04   | (3, 3)  | own piece at (3, 4)     | 7 squares (blocked one) |
| BVA-KG-05   | (3, 3)  | opponent at (3, 4)      | 8 squares (capture allowed) |

---

## 9. `MoveValidator` — PAWN movement

White pawn moves upward (increasing row). Black pawn moves downward (decreasing row).

| Test Case   | Pawn        | Board state                        | Expected destinations |
|-------------|-------------|------------------------------------|-----------------------|
| BVA-PW-01   | White (1,3) | empty forward                      | (2,3) and (3,3) — starting rank, 2-square advance |
| BVA-PW-02   | White (2,3) | empty forward                      | (3,3) only — not on starting rank |
| BVA-PW-03   | White (2,3) | own piece at (3,3)                 | empty list — blocked forward |
| BVA-PW-04   | White (2,3) | opponent at (3,2) and (3,4)        | (3,3), (3,2), (3,4) — forward + diagonal captures |
| BVA-PW-05   | White (2,3) | no pieces diagonally               | (3,3) only — no diagonal captures |
| BVA-PW-06   | Black (6,3) | empty forward (downward)           | (5,3) and (4,3) — starting rank |
| BVA-PW-07   | Black (5,3) | empty forward                      | (4,3) only |
| BVA-PW-08   | White (1,3) | own piece at (2,3)                 | empty list — blocked, no 2-step either |

---

## 10. `Game.makeMove`

**Input**: `Move(from, to)`
**Output**: `boolean` (true = move made, false = rejected)

| Test Case   | Scenario                                           | Expected |
|-------------|----------------------------------------------------|----------|
| BVA-GM-01   | Current player moves own piece to valid empty sq   | true; board updated; turn switched |
| BVA-GM-02   | Current player captures opponent piece             | true; opponent piece removed; turn switched |
| BVA-GM-03   | Move to square not in valid move list              | false; board unchanged |
| BVA-GM-04   | Player tries to move opponent's piece              | false; board unchanged |
| BVA-GM-05   | Move from empty square                             | false; board unchanged |
| BVA-GM-06   | Move with invalid from position                   | false; board unchanged |
| BVA-GM-07   | Move with invalid to position                     | false; board unchanged |