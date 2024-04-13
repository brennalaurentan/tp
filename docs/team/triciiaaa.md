---
  layout: default.md
  title: "Tricia Ang's Project Portfolio Page"
---

### Project: BandBook

BandBook is a desktop band contact and attendance management application optimised for CLI users. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 4 kLoC.

Given below are my contributions to the project.

* **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2324s2.github.io/tp-dashboard/?search=t15-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=triciiaaa&tabRepo=AY2324S2-CS2103T-T15-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **New Feature**: Added the ability to assign an instrument to a person.
  * What it does: allows the band administrator to assign an instrument to one or more people in one command. 
  * Justification: This feature improves the product significantly because it allows the band administrator to easily manage the band members' instruments and coordinate performance rehearsals.
  * Highlights: This enhancement also utilises the instrument field `i/` to assign instruments to people.
  * Credits: This feature was built upon the existing `edit` command.


* **Enhancements to Existing Features**:
  * User Interface:
    * Updated GUI color scheme: [\#32](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/32)
  * Logic:
    * Updated `add` and `edit` command to account for the instrument field: [\#46](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/46), [\#58](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/58)
    * Updated `find` command to allow the user to find people by name and/or instrument: [\#51](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/51)
  * Tests:
    * Updated existing test cases to account for the `add`, `edit` and `assign` feature: [\#46](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/46), [\#58](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/58)
    * Updated existing test cases to account for the `find` features: [\#51](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/51)
  * Java Documentation:
    * Added JavaDoc for all relevant classes and methods in codebase: [\#121](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/121)

* **Project Management**:
  * Set up the GitHub product website on MarkBind
  * Set up and maintained the GitHub milestone and issue trackers for [`v1.1`](https://github.com/AY2324S2-CS2103T-T15-3/tp/milestone/2), [`v1.2`](https://github.com/AY2324S2-CS2103T-T15-3/tp/milestone/1), [`v1.3`](https://github.com/AY2324S2-CS2103T-T15-3/tp/milestone/3), [`v1.3b`](https://github.com/AY2324S2-CS2103T-T15-3/tp/milestone/6), [`v1.4`](https://github.com/AY2324S2-CS2103T-T15-3/tp/milestone/4)
  * Managed release [`v1.3.1`](https://github.com/AY2324S2-CS2103T-T15-3/tp/releases/tag/v1.3.1) on GitHub with comprehensive release notes


* **Documentation**:
  * Product Website:
    * Maintained navigation bar links: [\#119](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/119)
  * Index:
    * Updated project name references: [\#47](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/47), [\#53](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/53)
  * About Us:
    * Updated team members' profile images, GitHub links, roles and responsibilities: [\#11](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/11), [\#18](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/18), [\#53](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/53)
  * README
    * Updated latest information about the project: [\#13](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/13), [\#18](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/18), [\#118](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/118)
  * User Guide:
    * Updated project name references: [\#30](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/30), [\#46](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/46)
    * Updated Introduction section: [\#65](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/65)
    * Updated documentation for the `add`, `edit` and `find` feature: [\#46](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/46), [\#53](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/53)
    * Updated FAQ section with more questions: [\#116](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/116)
    * Added documentation for the `assign` feature: [\#46](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/46)
    * Added Parameters Constraints section: [\#103](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/103)
    * Added UI screenshots for various features: [\#59](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/59), [\#116](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/116)
    * Added Acknowledgement section: [\#57](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/57)
    * Maintained the formatting of the User Guide: [\#115](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/115) 
  * Developer Guide:
    * Updated project name references: [\#47](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/47)
    * Updated UML Diagrams: [\#37](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/37), [\#60](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/60)
    * Updated User Stories section: [\#47](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/47)
    * Updated Use Cases section: [\#47](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/47), [\#60](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/60)
    * Updated Non-Functional Requirements: [\#47](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/47)
    * Updated Appendix B: Instructions for Manual Testing: [\#60](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/60)
    * Added Implementation Details section for `assign` and `find` feature: [\#110](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/110)
    * Added Appendix C: Possible Future Enhancements section: [\#110](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/110)


* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#38](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/38), [\#44](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/44), [\#52](https://github.com/AY2324S2-CS2103T-T15-3/tp/pull/52)
