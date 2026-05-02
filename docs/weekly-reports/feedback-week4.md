# Week 4 Project Feedback by PM/TA

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

### Software Process Quality
| #   | Item                                                                                                                                                         |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|-----|--------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 1   | GitHub repository branch protection rules are fully set up so that people cannot push into main without a pull request approval. (needed for Letter Grade C) |     ⚠️     | The PR review rule is enabled, which is good. Please also make sure the Gradle check is selected as a required status check and that the rule applies to admins, so the protection cannot be bypassed accidentally. | Canvas assignment Project: Setup, Project grading rubrics                         |
| 2   | Continuous Integration (CI) is fully set up from the beginning. (needed for Letter Grade B)                                                                  |     ✅     | The Gradle Build workflow is configured and the recent GitHub Actions runs are passing. | Canvas assignment Project: Setup, Project grading rubrics                         |
| 3   | The team uses the project management board steadily and frequently, and the description of each task is detailed. (needed for Letter Grade B)                | See below | See breakdown below | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.1 | Every functionality-related work item on the management board includes a user story, and optionally one or more use cases.                                   |     ⚠️     | The setup-phase requirements document has a strong user story, acceptance criteria, and use cases. I could not find matching work items on the GitHub Project board, so please mirror this work there too. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.2 | The design is documented somewhere, either in the work item description, or in a separate design document.                                                   |     ✅     | Nice work on `docs/design/setup-phase-design.md`. The class responsibilities and package structure are clearly documented. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 3.3 | Task assignments are documented clearly in the management board.                                                                                             |     ⚠️     | The weekly report clearly documents who is working on each area. Please also add those task assignments to the GitHub Project board so the project tracking is visible in one place. | Week 4 Wednesday Lecture (Lecture 08), Canvas assignment Project: Week 4 Guidance |
| 4   | Each active feature branch has an open draft PR against main.                                                                                                |     ➖     | I did not find an active unmerged feature branch at the time of review. Keep opening PRs early when new feature work starts. | Week 4 Wednesday Lecture (Lecture 08)                                             |
| 5   | The team has a “definition of done” (BVA) fully documented for the part of the system that is done. (needed for Letter Grade D)                              |     ✅     | Good work. `docs/bva/foundation-data-layer.md` documents BVA for the foundation data layer and the implemented tests map back to those cases. | Project grading rubrics                                                           |
| 6   | GitHub commit history demonstrates evidence of a TDD/BDD workflow for all the non-UI code. (needed for Letter Grade C)                                       |     ✅     | PR #1 includes non-UI model code with BVA and unit tests, and the PR description explicitly calls out passing BVA tests. Nice start. | Project grading rubrics                                                           |

### Planning & Progress Evaluation
| # | Item                                                                                                                                                         |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|---|--------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 7 | The team documents every week’s planning and progress evaluation professionally. (needed for Letter Grade B)                                                 |     ✅     | Good work. The Week 4 report documents the GUI decision, setup-phase planning, completed foundation data layer work, and in-progress Board/Game/GUI tasks. | Week 4 Wednesday Lecture (Lecture 08), Project grading rubrics                    |

### Progress & Collaboration
| #  | Item                                                                                                                                                             |  Status   | Reviewer Notes      | Source Instructions or Resources                                                  |
|----|------------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------:|---------------------|-----------------------------------------------------------------------------------|
| 8  | README is updated properly, including the project name, contributors, and build status badge. In addition, the team should specify the GUI library if appliable. |     ✅     | All good! | Canvas assignment Project: Setup                                                  |
| 9  | Overall progress on "Game Setup Phase"                                                                                                                           |     ✅     | Strong progress. The team has documented setup requirements/design, merged the foundation data layer with BVA and tests, and identified Board/Game/GUI work as in progress. | Canvas assignment Project: Week 4 Guidance                                        |
| 10 | Collaboration: Quality of discussion in PR reviews and work item comments on the board.                                                                          |     ✅     | All good! The merged PRs show review approvals from teammates, including short review comments. |                                                                                   |

## Additional Comments
Nice work this week. The foundation data layer is a strong start.

The main thing I would tighten next is project tracking. The repository documents are in good shape, but the GitHub Project board appears empty right now. Please add the setup-phase tasks there, including ownership and status, so the board reflects the same good planning that already appears in the weekly report.

## Review Snapshot (Just used for tracking purposes, not for feedback)
- Reviewed latest `main` commit: `5630cfba7f9a6bb7b41846c61a80d09c0024bbb4`
- Commit summary: Merge pull request #3 from `nu-cs-sqe/AidanMott-report-week4`
- Review date: 2026-04-28
