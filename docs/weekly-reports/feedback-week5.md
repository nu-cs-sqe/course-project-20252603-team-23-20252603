# Week 5 Project Feedback by PM/TA

**Dedicated PM/TA**: Jiahao Yu

## How to Read This Feedback
> [!NOTE]
> **Purpose.** This feedback focuses on your team's progress and collaboration. It is meant as guidance, not judgement.

> [!IMPORTANT]
> **Scope.** For the BVA and TDD items, the PM/TA evaluates only the `main` branch. Ongoing work in feature branches will be evaluated after it is merged. If you'd like early feedback on work in progress, please reach out to your PM/TA directly.

> [!TIP]
> **Mistakes are expected :).** As the instructor mentioned in class, early mistakes are part of the learning process. As long as your team addresses the issues after you get the feedback, your grade will not suffer from them.

## Checklist
Status:
- ✅: All done/Good job!
- ⚠️: Attention needed
- ❌: Significant issue found
- ➖: No basis to evaluate

### Past Feedback
| # | Item                                                                                                 | Status | Reviewer Notes | Source Instructions or Resources |
|---|------------------------------------------------------------------------------------------------------|:------:|----------------|----------------------------------|
| 0 | The team has closed and merged the past Feedback PR(s), indicating that they have read the feedback. |   ✅   | Week 4 feedback PR #4 has been merged into `main`, and PR #9/issue #8 show that the team addressed the Week 4 project-board/planning feedback. Nice follow-through. | |

### Software Process Quality
| # | Item                                                                                                                                                         |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|---|--------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 1 | Each active feature branch has an open draft PR against main.                                                                                                |     ⚠️     | I do not see any open PRs right now. The Week 5 report lists GUI work as in progress, so please open a draft PR early for that active work if it is being developed on a branch. | Week 4 Wednesday Lecture (Lecture 08)                                             |
| 2 | The team has a "definition of done" (BVA) fully documented for the part of the system that is done. (needed for Letter Grade D)                              |     ✅     | Good work. `docs/bva/core-game-logic.md` documents GameState, Board, and Game behavior with clear case IDs, inputs, and expected outcomes. | Project grading rubrics                                                           |
| 3 | GitHub commit history demonstrates evidence of a TDD/BDD workflow for all the non-UI code. (needed for Letter Grade C)                                       |     ✅     | PR #10 has strong TDD evidence. | Project grading rubrics                                                           |

### Planning & Progress Evaluation
| # | Item                                                                                                                                                         |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|---|--------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 4 | The team documents every week's planning and progress evaluation professionally. (needed for Letter Grade B)                                                 |     ✅     | Week 5 planning/progress is documented in `docs/weekly-reports/report.md`, including completed feedback/planning work and in-progress implementation areas. | Week 4 Wednesday Lecture (Lecture 08), Project grading rubrics                    |

### Progress & Collaboration
| # | Item                                                                                                                                                                                   |  Status   | Reviewer Notes      | Source Instructions or Resources                 |
|---|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|--------------------------------------------------|
| 5 | Overall development progress (recall the recommended order is: Game Setup Phase -> One turn of the game -> Multiple turns -> One win condition -> Other win conditions (if applicable) |     ✅     | Strong progress this week. The main remaining setup-phase gap appears to be the GUI layer, which is still listed as in progress. | Canvas assignment Project: Week 4 and 5 Guidance |
| 6 | Collaboration: Quality of discussion in PR reviews and work item comments on the board.                                                                                                |     ✅     | PR #10 has a substantive review comment that explicitly checks BVA/TDD evidence and gives design suggestions. That is a very good model for future reviews. | |

### Week 4 Items Rechecked by Request
The team asked for a recheck of the Week 4 items after making updates. I rechecked these items on 2026-05-07.

| #   | Item                                                                                                                                                         |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|-----|--------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 1   | GitHub repository branch protection rules are fully set up so that people cannot push into main without a pull request approval. (needed for Letter Grade C) |     ✅     | Rechecked on 2026-05-07. `main` requires pull request review, requires the `gradle (ubuntu-latest)` status check, uses strict status checks, and applies the rule to admins. Looks good. | Canvas assignment Project: Setup, Project grading rubrics                         |
| 2   | Continuous Integration (CI) is fully set up from the beginning. (needed for Letter Grade B)                                                                  |     ✅     | Rechecked on 2026-05-07. The Gradle Build workflow is present, and the Gradle status check is configured as a required check for `main`. | Canvas assignment Project: Setup, Project grading rubrics                         |
| 3   | The team uses the project management board steadily and frequently, and the description of each task is detailed. (needed for Letter Grade B)                | See below | Rechecked breakdown below. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.1 | Every functionality-related work item on the management board includes a user story, and optionally one or more use cases.                                   |     ✅     | Rechecked on 2026-05-07. The visible setup-phase issues now include user stories and task descriptions, including foundation data, core game logic, and GUI work. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.2 | The design is documented somewhere, either in the work item description, or in a separate design document.                                                   |     ✅     | Rechecked on 2026-05-07. Setup and one-turn design documents are present in `docs/design/`. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.3 | Task assignments are documented clearly in the management board.                                                                                             |     ✅     | Rechecked on 2026-05-07. The visible setup-phase issues have assignees for the major work areas. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |

## Additional Comments
Update after recheck: the Week 4 items look good now. Thank you for following up and making the project tracking/branch protection updates visible.

This was a strong week. The core game logic PR is well structured, the tests pass, and the review on PR #10 shows that the team is starting to review process quality rather than only approving code. For next week, I would suggest you to focus on making the GUI work visible through an early draft PR and keeping the same BVA/TDD/review discipline as you move from setup into one-turn behavior.

## Review Snapshot (Just used for tracking purposes, not for feedback)
- Reviewed latest `main` commit: `ea11ae0`
- Commit summary: Merge pull request #10 from `nu-cs-sqe/wip-core-game-logic`
- Review date: 2026-05-05
- Week 4 item recheck date: 2026-05-07
- Verification: `bash ./gradlew test` passed with Java 11
