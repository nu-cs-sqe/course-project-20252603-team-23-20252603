# Week 3 (04/13/2026-04/19/2026)
**Planning and Progress Tracking**:
1. [done] Xinyuan Liu: build up the team project and set up branch protection rules
2. [done] Julia Li: Read over the game instructions
3. [done] Aidan Mott: Read over chess specifications

# Week 4 (04/20/2026-04/26/2026)
**Planning and Progress Tracking**:
1. [done] All: Decided to use Java Swing as the GUI library
2. [done] Xinyuan Liu: Generate AI-assisted game rules, user story, use cases, and system design for the Game Setup Phase (PR #1)
3. [done] Xinyuan Liu: Implement foundation data layer — `Position`, `Piece`, `Player`, `Color`, `PieceType` (with BVA and unit tests) (PR #1)
4. [In Progress] Aidan : Implement core game logic — `Board`, `Game`, `GameState`, `GameStatus` (with BVA and unit tests)
5. [In Progress] Julia Li: Implement GUI layer — `MainFrame`, `BoardPanel`, `SetupDialog`, `Main` (Java Swing)


# Week 5 (04/27/2026-05/03/2026)
**Planning and Progress Tracking**:
1. [done] Xinyuan Liu: Address week 4 feedback — add branch protection (Gradle required check + applies to admins), populate GitHub Project board with setup-phase work items and assignees (PR #4)
2. [done] Xinyuan Liu: Plan "One Turn of the Game" phase — write requirements (`docs/requirements/one-turn.md`), class design (`docs/design/one-turn-design.md`), and BVA (`docs/bva/one-turn.md`) This might require adjustment based on 3,4 (PR #9)
3. [done] Aidan Mott: Implement core game logic — `Board`, `Game`, `GameState`, `GameStatus` (with BVA and unit tests) (PR #10)
4. [In Progress] Julia Li: Implement GUI layer — `MainFrame`, `BoardPanel`, `SetupDialog` (Java Swing)
5. [done] All: Review teammates' PRs when ready — verify BVA coverage and TDD commit history before approving

# Week 6 (05/04/2026-05/10/2026)
**Planning and Progress Tracking**:
1. [done] Xinyuan Liu: Set up Checkstyle 10.21 and SpotBugs 6.0.9 (PR #12)
2. [done] Xinyuan Liu: Refactor existing codebase to comply with Checkstyle (PR #12)
3. [done] Xinyuan Liu: Implement "Multiple turns of the game" phase with full TDD commit history (PR #12)
4. [Not Started] All: Review teammates' PRs — verify BVA coverage and TDD commit history before approving

# Week 7 (05/11/2026-05/17/2026)
**Planning and Progress Tracking**:
1. [done] Xinyuan Liu: Set up JaCoCo and Pitest in build.gradle.kts (PR #19)
2. [done] Xinyuan Liu: Implement "One win condition" (PR #19)
3. [In Progress] Julia Li: Continue GUI layer implementation — MainFrame, BoardPanel, SetupDialog
4. [done] All: Discuss and document Integration Testing plan on GitHub Project board
5. [done] All: Discuss and document i18n plan on GitHub Project board
6. [Not Started] All: Review teammates' PRs — verify BVA coverage and TDD commit history before approving

# Week 8 (05/18/2026-05/24/2026)
**Planning and Progress Tracking**:
1. [done] Xinyuan Liu: Address week 7 instructor feedback — replace magic numbers in `Board`, `Position`, and `Move` with named constants (`BOARD_SIZE`, `WHITE_BACK_ROW`, `BLACK_BACK_ROW`, `WHITE_PAWN_ROW`, `BLACK_PAWN_ROW`, file/column constants, `MIN_INDEX`, `MAX_INDEX`) and extract hardcoded error strings in `Move` to `static final` fields (PR #22)
2. [done] All: Discuss the Week 7 instructor code review feedback and document follow-up items (PR #16)

# Week 9 (05/25/2026-05/31/2026)
**Planning and Progress Tracking**:
1. [done] Aidan Mott: Document the integration testing plan (`docs/testing/integration-test-plan.md`) and complete the Scenario 1 integration test in `GameIntegrationTest` (PR #23)
2. [done] Aidan Mott: Add passing integration test cases for Scenario 2 and an additional integration test case for Scenario 3 (PR #23)
3. [done] Aidan Mott: Fix imports in `GameIntegrationTest` to comply with Checkstyle style rules (PR #23)
4. [Not Started] All: Review teammates' PRs — verify BVA coverage and TDD commit history before approving

# Week 10 (06/01/2026-06/07/2026)
**Planning and Progress Tracking**:
1. [done] Julia Li: Migrate the GUI layer from Java Swing to JavaFX 17 — set up `ChessApp`, `Main`, `BoardView`, `BoardCell`, `SetupDialogView`, `Utf8ResourceBundle`, and `GameController`, and refactor the UI code into a cleaner package structure with shared helpers (PR #24)
2. [done] Julia Li: Implement i18n support with English and Chinese resource bundles (`labels_en_US.properties`, `labels_zh_CN.properties`) (PR #25)
3. [done] Xinyuan Liu: Write "Multiple turns" and "Win condition" requirements docs, implement `MoveValidator` with BVA-based unit tests, and update `Game` and the JavaFX UI components (`BoardCell`, `BoardView`, `GameController`, `SetupDialogView`) to support win detection and turn management (PR #26)
4. [done] Xinyuan Liu: Address week 10 instructor feedback — add controller-level unit tests for `GameController` (selection, move attempts, turn switching, checkmate/game-over handling) to satisfy the 100% cyclomatic coverage rubric for non-GUI code, and run tests headlessly in CI via Xvfb
5. [done] All: Merge the integration testing plan (#23), JavaFX GUI migration (#24), i18n support (#25), and game requirements/move validator work (#26) into `main`
