# Week 3 (04/13/2026-04/19/2026)
**Planning and Progress Tracking**:
1. [done] Xinyuan Liu: build up the team project and set up branch protection rules
2. [done] Julia Li: Read over the game instructions
3. [done] Aidan Mott: Read over chess specifications

# Week 4 (04/20/2026-04/26/2026)
**Planning and Progress Tracking**:
1. [done] All: Decided to use Java Swing as the GUI library
2. [done] Xinyuan Liu: Generate AI-assisted game rules, user story, use cases, and system design for the Game Setup Phase
3. [done] Xinyuan Liu: Implement foundation data layer — `Position`, `Piece`, `Player`, `Color`, `PieceType` (with BVA and unit tests)
4. [In Progress] Aidan : Implement core game logic — `Board`, `Game`, `GameState`, `GameStatus` (with BVA and unit tests)
5. [In Progress] Julia Li: Implement GUI layer — `MainFrame`, `BoardPanel`, `SetupDialog`, `Main` (Java Swing)


# Week 5 (04/27/2026-05/03/2026)
**Planning and Progress Tracking**:
1. [done] Xinyuan Liu: Address week 4 feedback — add branch protection (Gradle required check + applies to admins), populate GitHub Project board with setup-phase work items and assignees
2. [done] Xinyuan Liu: Plan "One Turn of the Game" phase — write requirements (`docs/requirements/one-turn.md`), class design (`docs/design/one-turn-design.md`), and BVA (`docs/bva/one-turn.md`) This might require adjustment based on 3,4
3. [done] Aidan Mott: Implement core game logic — `Board`, `Game`, `GameState`, `GameStatus` (with BVA and unit tests)
4. [In Progress] Julia Li: Implement GUI layer — `MainFrame`, `BoardPanel`, `SetupDialog` (Java Swing)
5. [Not Started] All: Review teammates' PRs when ready — verify BVA coverage and TDD commit history before approving

# Week 6 (05/04/2026-05/10/2026)
**Planning and Progress Tracking**:
1. [done] Xinyuan Liu: Set up Checkstyle 10.21 and SpotBugs 6.0.9 
2. [done] Xinyuan Liu: Refactor existing codebase to comply with Checkstyle
3. [done] Xinyuan Liu: Implement "Multiple turns of the game" phase with full TDD commit history
4. [Not Started] All: Review teammates' PRs — verify BVA coverage and TDD commit history before approving

# Week 7 (05/11/2026-05/17/2026)
**Planning and Progress Tracking**:
1. [done] Xinyuan Liu: Set up JaCoCo (code coverage) and Pitest (mutation testing) in build.gradle.kts
2. [done] Xinyuan Liu: Implement "One win condition" — King-capture checkmate detection with full TDD (BVA-WC-01 to BVA-WC-05)
3. [In Progress] Julia Li: Continue GUI layer implementation — MainFrame, BoardPanel, SetupDialog
4. [Not Started] All: Discuss and document Integration Testing plan on GitHub Project board
5. [Not Started] All: Discuss and document i18n plan on GitHub Project board
6. [Not Started] All: Review teammates' PRs — verify BVA coverage and TDD commit history before approving

**Progress**:
- JaCoCo configured: line coverage 99%, HTML + XML reports generated automatically after each test run
- Pitest configured: 93% mutation score (85 mutations, 79 killed), junit5PluginVersion 1.2.1
- Win condition implemented: capturing the opponent's King sets `GameStatus.CHECKMATE` and records the winner in `GameState`; subsequent moves throw `IllegalStateException`
- All 5 new BVA-WC tests pass; overall test suite green
