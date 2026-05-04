# BVA: Core Game Logic

Author: Aidan Mott  
Classes: `GameState`, `Board`, `Game`, `GameStatus`

---

## 1. `GameState` constructor

**Input**: none  
**Output**: initialized `GameState` object

| Test Case  | Scenario                  | Expected                                  |
|------------|---------------------------|-------------------------------------------|
| BVA-GS-01  | new GameState()           | currentTurn = WHITE; status = SETUP       |

---

## 2. `GameState.switchTurn()`

**Input**: none  
**Output**: currentTurn toggled

| Test Case  | Initial Turn | Expected |
|------------|--------------|----------|
| BVA-GS-02  | WHITE        | BLACK    |
| BVA-GS-03  | BLACK        | WHITE    |

---

## 3. `GameState.setStatus(status)`

**Input**: `status` (GameStatus)  
**Output**: updated status or exception

| Test Case  | Input        | Expected                      |
|------------|-------------|-------------------------------|
| BVA-GS-04  | IN_PROGRESS | status updated                |
| BVA-GS-05  | CHECKMATE   | status updated                |
| BVA-GS-06  | null        | IllegalArgumentException      |

---

## 4. `Board` constructor

**Input**: none  
**Output**: empty board

| Test Case  | Scenario      | Expected                      |
|------------|--------------|-------------------------------|
| BVA-BD-01  | new Board()  | all 64 squares are null       |

---

## 5. `Board.placePiece(piece, pos)`

**Input**: `piece` (Piece), `pos` (Position)  
**Output**: piece placed or exception thrown

| Test Case  | piece        | pos                        | Expected                      |
|------------|-------------|----------------------------|-------------------------------|
| BVA-BD-02  | valid piece | valid position             | piece placed                  |
| BVA-BD-03  | null        | valid position             | IllegalArgumentException      |
| BVA-BD-04  | valid piece | null                       | IllegalArgumentException      |
| BVA-BD-05  | valid piece | invalid position           | IllegalArgumentException      |

---

## 6. `Board.getPieceAt(pos)`

**Input**: `pos` (Position)  
**Output**: Piece or null, or exception

| Test Case  | Scenario                  | Expected                      |
|------------|--------------------------|-------------------------------|
| BVA-BD-06  | valid empty square       | null                          |
| BVA-BD-07  | valid occupied square   | piece returned                |
| BVA-BD-08  | pos = null               | IllegalArgumentException      |
| BVA-BD-09  | invalid position         | IllegalArgumentException      |

---

## 7. `Board.isEmpty(pos)`

**Input**: `pos` (Position)  
**Output**: boolean or exception

| Test Case  | Scenario          | Expected                      |
|------------|------------------|-------------------------------|
| BVA-BD-10  | empty square     | true                          |
| BVA-BD-11  | occupied square  | false                         |
| BVA-BD-12  | invalid position | IllegalArgumentException      |

---

## 8. `Board.initialize()`

**Input**: none  
**Output**: board set to standard chess starting position

| Test Case  | Scenario           | Expected                                      |
|------------|------------------|-----------------------------------------------|
| BVA-BD-13  | after initialize | 32 pieces total                               |
| BVA-BD-14  | row 0            | White back row (R N B Q K B N R)              |
| BVA-BD-15  | row 1            | White pawns                                   |
| BVA-BD-16  | row 6            | Black pawns                                   |
| BVA-BD-17  | row 7            | Black back row                                |
| BVA-BD-18  | rows 2–5         | all empty                                     |

---

## 9. `Game` constructor

**Input**: `whiteName` (String), `blackName` (String)  
**Output**: initialized Game or exception

| Test Case  | Scenario             | Expected                      |
|------------|---------------------|-------------------------------|
| BVA-G-01   | valid names         | Game created successfully     |
| BVA-G-02   | null white name     | IllegalArgumentException      |
| BVA-G-03   | blank black name    | IllegalArgumentException      |

---

## 10. `Game.setup()`

**Input**: none  
**Output**: board initialized, status updated

| Test Case  | Scenario            | Expected                                      |
|------------|--------------------|-----------------------------------------------|
| BVA-G-04   | call setup()       | board initialized; status = IN_PROGRESS       |
| BVA-G-05   | call setup() twice | board reinitialized; no exception             |

---

## 11. `Game.getCurrentPlayer()`

**Input**: none  
**Output**: Player

| Test Case  | Scenario       | Expected               |
|------------|---------------|------------------------|
| BVA-G-06   | turn = WHITE  | returns white player   |
| BVA-G-07   | turn = BLACK  | returns black player   |

---

## 12. `Game.getBoard()`

**Input**: none  
**Output**: Board

| Test Case  | Scenario            | Expected         |
|------------|--------------------|------------------|
| BVA-G-08   | after construction | non-null board   |

---

## 13. `Game.getState()`

**Input**: none  
**Output**: GameState

| Test Case  | Scenario            | Expected              |
|------------|--------------------|-----------------------|
| BVA-G-09   | after construction | non-null GameState    |