---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# BandBook Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

BandBook is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).
It is a project under the CS2103T module, School of Computing, National University of Singapore.

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S2-CS2103T-T15-3/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S2-CS2103T-T15-3/tp/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574"></puml>

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300"></puml>

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S2-CS2103T-T15-3/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"></puml>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S2-CS2103T-T15-3/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S2-CS2103T-T15-3/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Person` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S2-CS2103T-T15-3/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"></puml>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command"></puml>

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"></puml>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component

**API** : [`Model.java`](https://github.com/AY2324S2-CS2103T-T15-3/tp/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450"></puml>

The `Model` component,

* stores the address book data i.e., all `Person` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Person` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

**Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Person` needing their own `Tag` objects.<br>

<puml src="diagrams/BetterModelClassDiagram.puml" width="450"></puml>

</box>

### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S2-CS2103T-T15-3/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550"></puml>

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Edit feature

#### Our Implementation

The implementation of the edit feature which allows users to change various fields of an existing contact can be seen
in the UML sequence diagram below.

<puml src="diagrams/EditUser.puml"></puml>
Note: The activation bars for :Ui and logicManager:LogicManager are meant to be deactivated after and within the
reference frame respectively. Due to a PlantUML bug, this is unable to be reflected accurately in the diagram.

### Find feature

The find feature mainly allows users to search for contacts based on specific fields. Currently, it only supports
searching by name and instrument. The logic of the find feature is implemented using the `FindCommand` class and the
`FindCommandParser` class.

#### Our Implementation

`FindCommand` is implemented to allow users to search for contacts based on specific fields. Currently, it only
supports searching by name and instrument. It is implemented as such:
1. The `FindCommand` is executed by the `LogicManager`.
2. It calls the `Model` to filter the list of contacts based on the search criteria.
3. The `Model` then returns the filtered list of contacts to the `FindCommand` as a `CommandResult` object.
4. This is passed on to `LogicManager` and then to `UI` to display the filtered list of contacts.

`FindCommandParser` is implemented to parse the user input for the find command. It is implemented as such:
1. The `FindCommandParser` is called by the `AddressBookParser` to parse the user input.
2. With the given string input provided by the user, it undergoes various checks using the `parse` function. 
3. It first checks if the input contains the valid prefixes (i.e. /n and /i with no duplicates).
   1. If there are no valid prefixes, it throws an exception.
4. It then checks the arguments provided to the valid prefixes.
   1. If there are no arguments provided, it throws an exception.
   2. If there are invalid arguments provided (i.e. does not fulfil validation regex of respective fields), it throws
   an exception.
5. If there are no exceptions thrown at this point, it retrieves the arguments provided after each prefix and returns
them as a `FindCommand` object.

#### Design Consideration

**Aspect: How the find feature executes:**
* **Alternative 1 (current choice)**: Filters by name and instrument.
  * Pros: Easy to implement.
  * Cons: Limited search criteria.
* **Alternative 2**: Filters by all possible fields.
  * Pros: More flexible as search criteria is extensive.
  * Cons: More complex to implement.

### Assign feature

The assign feature mainly allows users to assign an instrument to a contact. Currently, it only supports assigning a
single compulsory instrument to one or more contacts. The logic of the assign feature is implemented using the
`InstrumentCommand` class and the `InstrumentCommandParser` class.

#### Our Implementation

`InstrumentCommand` is implemented to allow users to assign an instrument to one or more contacts. Currently, it only
supports assigning a single compulsory instrument to one or more contacts. It is implemented as such:
1. The `InstrumentCommand` is executed by the `LogicManager`.
2. For each index, it calls the `Model` to retrieve the list of contacts to be assigned the instrument.
3. It checks if the indexes provided are valid corresponding to the filtered list.
   1. If there are invalid indexes provided, it throws an exception.
4. A `Person` object is created and the instrument field is updated with the input instrument.
   1. If the instrument provided is the same as the existing instrument assigned to the contact, it throws an exception.
5. The `Model` is updated with the edited `Person` object.
6. Once all valid indexes have been processed, the `Model` returns the updated list of contacts to the `InstrumentCommand`
   as a `CommandResult` object.
6. This is passed on to `LogicManager` and then to `UI` to update the instruments assigned to the specified contacts. 

`InstrumentCommandParser` is implemented to parse the user input for the assign command. It is implemented as such:
1. The `InstrumentCommandParser` is called by the `AddressBookParser` to parse the user input.
2. With the given string input provided by the user, it undergoes various checks using the `parse` function.
3. It first splits the indexes provided and stores them in a set.
   1. If there are no indexes provided, it throws an exception.
   2. If there are invalid indexes provided (i.e. does not exist in the contact list), it throws an exception.
4. It then checks the arguments provided to the instrument prefix.
   1. If there are no arguments provided, it throws an exception.
   2. If there are invalid arguments provided (i.e. does not fulfil validation regex of the instrument field), it throws
      an exception.
5. If there are no exceptions thrown at this point, it retrieves the instrument provided after the instrument prefix and 
   returns them as an `InstrumentCommand` object.

#### Design Consideration

**Aspect: How the assign feature executes:**
* **Alternative 1 (current choice)**: Assigns a single compulsory instrument to one or more contacts.
  * Pros: Easy to implement.
  * Cons: Limited instrument assignments.
* **Alternative 2**: Assigns at least one to possibly multiple instruments to one or more contacts.
  * Pros: More flexible as members may be able to play none or more than one instrument.
  * Cons: More complex to implement.
--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix A: Requirements**

### Product scope

**Target user profile**:

* is the band administrator
* has a need to manage a significant number of band members
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Our app is specifically designed for a band administrator with a seamless and
efficient way to manage and access their members' details. Also, our app is able to extend capabilities that
help make managing a band easier.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority   | As a …​             | I want to …​                            | So that I can…​                                       |
|------------|---------------------|-----------------------------------------|-------------------------------------------------------|
| `* * *`    | band administrator  | create contact information              | keep track of members in the band                     |
| `* * *`    | band administrator  | view contact & address information      | organise transportation by area of residence          |
| `* * *`    | band administrator  | update contact information              | keep the address book current                         |
| `* * *`    | band administrator  | delete contact information              | keep address book updated                             |
| `* *`      | band administrator  | indicate birthday information           | coordinate celebrations for the members               |
| `* *`      | band administrator  | indicate instrument information         | keep track of each member's instrument assignments    |
| `* *`      | band administrator  | indicate matriculation year information | keep track of how long ago the member joined the club |
| `* *`      | band administrator  | view attendance history                 | monitor participation and follow up as necessary      |
| `* *`      | band administrator  | update attendance history               | keep updated attendance records                       |                                         |
| `* *`      | band administrator  | filter contacts by instrument           | better coordinate performance rehearsals              |                                         |

### Use cases

(For all use cases below, the **System** is the `BandBook` and the **Actor** is the `band administrator`, unless specified otherwise)

**Use case: Delete a person**

**MSS**

1.  Band administrator requests to list persons.
2.  BandBook shows a list of persons.
3.  Band administrator requests to delete a specific person in the list.
4.  BandBook deletes the person.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. BandBook shows an error message.

      Use case resumes at step 2.

**Use case: Indicate a birthday of a member**

**MSS**

1.  Band administrator requests to list persons.
2.  BandBook shows a list of persons.
3.  Band administrator requests to add a birthday to a specific person in the list.
4.  BandBook updates the person's info to reflect their birthday.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. BandBook shows an error message.

      Use case resumes at step 2.

**Use case: Indicate matriculation year of a member**

**MSS**

1.  Band administrator requests to list persons.
2.  BandBook shows a list of persons.
3.  Band administrator requests to add a matriculation year to a specific person in the list.
4.  BandBook updates the person's info to reflect their matriculation year.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. BandBook shows an error message.

      Use case resumes at step 2.

**Use case: Delete all members of a specific matriculation year**

**MSS**

1.  Band administrator requests to list persons.
2.  BandBook shows a list of persons.
3.  Band administrator requests to delete all persons in the list who belong to a specific matriculation year.
4.  BandBook deletes all persons who belong to the specific matriculation year.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

**Use case: Mark attendance for a member**

**MSS**

1.  Band administrator requests to list persons.
2.  BandBook shows a list of persons.
3.  Band administrator requests to mark the attendance of specific person(s) in the list.
4.  BandBook updates the person's info to reflect their attendance for a specific day.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given indexes are invalid.

    * 3a1. BandBook shows an error message.

      Use case resumes at step 2.

**Use case: Unmark attendance for a member**

**MSS**

1.  Band administrator requests to list persons.
2.  BandBook shows a list of persons.
3.  Band administrator requests to unmark the attendance of specific person(s) in the list.
4.  BandBook updates the person's info to reflect their attendance for a specific day.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given indexes are invalid.

    * 3a1. BandBook shows an error message.

      Use case resumes at step 2.

**Use case: Assign an instrument to a member**

**MSS**

1.  Band administrator requests to list persons.
2.  BandBook shows a list of persons.
3.  Band administrator requests to assign an instrument to specific person(s) in the list.
4.  BandBook updates the person's info to reflect their instrument.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. BandBook shows an error message.

      Use case resumes at step 2.

**Use case: Find member by name and/or instrument**

**MSS**

1.  Band administrator requests to list persons.
2.  BandBook shows a list of persons.
3.  Band administrator requests to find specific person(s) in the list by name and/or instrument.
4.  BandBook displays a filtered list of persons who match the keywords provided at each prefix.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given keyword cannot be found.

    * 3a1. BandBook shows that 0 persons are listed.

      Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 members without a noticeable sluggishness in performance for typical usage.
3.  Functions should return results within 2 seconds to prevent the app from feeling too slow and irritating to use.
4.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

* **API**: Application Programming Interface, a set of rules and protocols that allows different software applications to communicate with each other.
* **CLI**: Command Line Interface, a text-based interface used to interact with software applications. Users input commands into the CLI to execute functions and operations.
* **Command**: A text input entered by the user into the command input box to instruct the software to perform a specific action.
* **GUI**: Graphical User Interface, a visual interface that allows users to interact with software applications through graphical elements such as windows, buttons, and icons.
* **JSON**: JavaScript Object Notation, a lightweight data-interchange format that is easy for humans to read and write and easy for machines to parse and generate.
* **Mainstream OS**: Windows, Linux, Unix, MacOS
* **MSS**: Main Success Scenario, the main path of a use case that describes the basic, successful flow of events.
* **Parameter**: A value that is passed to a command or function by the user.
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **UI**: User Interface, the visual elements of a software application that users interact with to perform tasks.

--------------------------------------------------------------------------------------------------------------------

## **Appendix B: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

### Deleting a person

1. Deleting a person while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `delete 1`<br>
        Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message.

    1. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

1. _Deleting a person while a filtered list is being shown_

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list. Filter the list using the `find` command.

    1. Test case: `delete 1`<br>
       Expected: First contact in the filtered list is deleted. Details of the deleted contact shown in the status message.

    1. Test case: `delete 0`<br>
       Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the filtered list size)<br>
       Expected: Similar to previous.

### Finding a person

1. Finding person(s) by name while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `find n/Alex`<br>
       Expected: The contacts with the name field containing 'Alex' from the list is shown. Number of contacts listed is shown in the status message.

    1. Test case: `find n/Alex Bernice`<br>
       Expected: The contacts with the name field containing 'Alex' or 'Bernice' from the list is shown. Number of contacts listed is shown in the status message.

    1. Other incorrect find commands to try: `find`, `find n/`, `...` (where the string after n/ is not alphanumeric)<br>
       Expected: Error details shown in the status message. Status bar remains the same.

1. Finding person(s) by instrument while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `find i/Clarinet`<br>
       Expected: The contacts with the instrument field containing 'Clarinet' from the list is shown. Number of contacts listed is shown in the status message.

    1. Test case: `find i/Clarinet Oboe`<br>
       Expected: The contacts with the instrument field containing 'Clarinet' or 'Oboe' from the list is shown. Number of contacts listed is shown in the status message.

    1. Other incorrect find commands to try: `find`, `find i/`, `...` (where the string after i/ is not alphanumeric)<br>
       Expected: Error details shown in the status message. Status bar remains the same.

1. Finding person(s) by name and instrument while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `find n/Alex i/Clarinet`<br>
       Expected: The contacts with the name field containing 'Alex' and the instrument field containing 'Clarinet' from the list is shown. Number of contacts listed is shown in the status message.

    1. Test case: `find n/Alex Bernice i/Clarinet Oboe`<br>
       Expected: The contacts with the name field containing 'Alex' or 'Bernice' and the instrument field containing 'Clarinet' or 'Oboe' from the list is shown. Number of contacts listed is shown in the status message.

    1. Other incorrect find commands to try: `find`, `find n/ i/`, `find i/ n/`, `...` (where the string after n/ and i/ is not alphanumeric)<br>
       Expected: Error details shown in the status message. Status bar remains the same.

### Assigning an instrument to person(s)
1. Assigning an instrument to person(s) while all persons are being shown

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

    1. Test case: `assign 1 i/Clarinet`<br>
       Expected: First contact in the list is assigned with the instrument 'Clarinet'. Name of the edited contact shown in the status message.

    1. Test case: `assign 3 4 i/Flute`<br>
       Expected: Third and fourth contact in the list is assigned with the instrument 'Flute'. Names of the edited contacts shown in the status message.

    1. Other incorrect assign commands to try: `assign`, `assign x`, `...` (where x is larger than the list size)<br>
       Expected: Error details shown in the status message. Status bar remains the same.

1. Assigning an instrument to person(s) while a filtered list is being shown_

    1. Prerequisites: List all persons using the `list` command. Multiple persons in the list. Filter the list using the `find` command.

    1. Test case: `assign 1 i/Clarinet`<br>
       Expected: First contact in the filtered list is assigned with the instrument 'Clarinet'. Name of the edited contact shown in the status message.

    1. Test case: `assign 3 4 i/Flute`<br>
       Expected: Third and fourth contact in the filtered list is assigned with the instrument 'Flute'. Names of the edited contacts shown in the status message.

    1. Other incorrect assign commands to try: `assign`, `assign x`, `...` (where x is larger than the filtered list size)<br>
       Expected: Error details shown in the status message. Status bar remains the same.

### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

--------------------------------------------------------------------------------------------------------------------

## **Appendix C: Possible Future Enhancements**

### Display more specific error messages

Currently, BandBook only displays generic error messages. This is not ideal as users will need to scrutinise their
input to figure out what is causing the error. In the future, we hope to enhance BandBook by displaying more specific
error messages to help users understand what went wrong, improving their speed and efficiency when using BandBook.

We can implement this enhancement by improving the respective command parsers to identify which part of the user input
is incorrect, and display an error message that is specific to that part to the user. For example, if the user enters
`assign 1 2 3 i/Flute` when there only exists 2 people in the list, BandBook can display an error message that says
the 3rd index entered is invalid. 

#### Design Consideration
* Craft simple and clear sentences that include the specific aspect that causes the error.
  * **Pros**: Easy to implement.
  * **Cons**: Need to check to ensure the details in the error messages correspond to the actual errors.

### Implement stronger data validation for respective fields

Currently, BandBook only checks if the user input is in the correct format. However, it does not check if the data entered
is valid or not. This is not ideal as users may enter invalid data that may cause potential issues. In the future, we
intend to enhance BandBook by implementing stronger data validation for respective fields to ensure that only valid data
is entered into BandBook.

We can implement this enhancement by retrieving the valid values for various fields such as email domains and instruments
possibly through an API or a reference list and validating the user input against these values. If the user input does
not match any of the valid values, BandBook will display an error message to inform the user that the data entered is
invalid. For example, if the user enters an invalid string `xxx` for the instrument field, BandBook can display an error
message that says the instrument entered is invalid, and display the valid instruments that the user can choose from.

#### Design Consideration
* Maintain a list of valid values for each field.
  * **Pros**: Eliminates the possibility of entering erroneous data.
  * **Cons**: Need to use an appropriate API, otherwise, need to update the list of valid values whenever there are changes.

### Implement stronger check criterion for duplicate contacts

Currently, BandBook only checks if the user is trying to add a duplicate contact based on the name field. This is not
ideal as there can be multiple people with the same name. Additionally, other fields such as email and phone number
should not be the same for contacts with different names. In the future, we hope to enhance BandBook by implementing
a multi-field check criterion for duplicate contacts instead.

We can implement this enhancement by checking if the user is trying to add a contact with the same name, email, and phone
number as an existing contact. If this is the case, BandBook will display an error message to inform the user that the
specified contact details are already in contact list. Otherwise, it will add the new contact to the contact list successfully.

#### Design Consideration
* Use multi-field check criterion such as name, email, and phone number to check for duplicate contacts.
  * **Pros**: More realistic and eliminates the possibility of adding duplicate contacts.
  * **Cons**: Need to identify which field is incorrect and update the UI to display the error message accordingly.

### Allow each person to be assigned at least one to possibly multiple instruments

Currently, BandBook only allows each person to be assigned to one instrument. While the person need not be assigned an
instrument upon the addition of the contact details, it is compulsory for the person has to be assigned an instrument
for subsequent edits once he/she is assigned to the first instrument. This is not ideal as some people may be able to
play multiple instruments within the band while others may not be proficient enough to be assigned an instrument.
In the future, we hope to enhance BandBook by allowing each person to be assigned to at least one to possibly multiple
instruments.

We can implement this enhancement by modifying `Instrument` to be a set of strings instead of a single string. Similar
to `Tag`, this will allows users to store multiple instruments for each person using the command `edit 1 i/Flute i/Clarinet`.
Additionally, we can modify the `Instrument` field to be optional, allowing users to remove any instruments assigned by
using the command `edit 1 i/`.

#### Design Consideration
* Change the `Instrument` field to be a set of strings.
  * **Pros**: Allows users to store multiple instruments for each person.
  * **Cons**: Need to update the UI to display multiple instruments for each person.

### Allow user to copy link to User Guide automatically upon entering help command

Currently, BandBook only displays the help window when the user enters the `help` command. Users will need to click on
the Copy Link button to retrieve the link to our User Guide. This is not ideal as BandBook is optimised for users who
are more proficient in using the Command Line Interface (CLI) and prefer typing to mouse interactions. In the future,
we hope to enhance BandBook by integrating automatic copying of the User Guide link to the user's clipboard upon
entering the `help` command.

We can implement this enhancement by modifying the `HelpCommand` to automatically copy the link to the User Guide
instead of displaying the help window. This will allow users to retrieve the link to the User Guide more efficiently.

#### Design Consideration
* Integrate automatic copying of the link to clipboard.
  * **Pros**: Easy to implement.
  * **Cons**: None.

### Allow user to find person(s) by all other fields

Currently, BandBook only allows users to find person(s) by name and instrument. This is not ideal as users may want to
find person(s) by other fields (i.e. phone, email, address, matriculation year, birthday, tag, and attendance). In the
future, we hope to enhance BandBook by delivering the same implementation of finding person(s) by name and instrument
to other fields.

We can implement this enhancement by modifying the `FindCommand` to allow users to find person(s) by all other fields.
For example, users can find person(s) by tag using the command `find t/friend`, which returns all person(s) who are
given a 'friend' tag. 

#### Design Consideration
* Extend the `FindCommand` to allow users to find person(s) by all other fields.
  * **Pros**: Easy to implement.
  * **Cons**: None.
