package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Birthday birthday;
    private final MatriculationYear matriculationYear;
    private final Instrument instrument;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Attendance> attendances = new HashSet<>();

    /**
     * Constructs a {@code Person} with the specified details. Every field must be present and not null.
     *
     * @param name The name of the person.
     * @param phone The phone number of the person.
     * @param email The email address of the person.
     * @param address The address of the person.
     * @param birthday The birthday of the person.
     * @param matriculationYear The matriculation year of the person.
     * @param instrument The instrument of the person.
     * @param tags The tags of the person.
     * @param attendances The attendances of the person.
     */
    public Person(Name name, Phone phone, Email email, Address address, Birthday birthday,
                MatriculationYear matriculationYear, Instrument instrument, Set<Tag> tags,
                Set<Attendance> attendances) {
        requireAllNonNull(name, phone, email, address, birthday, instrument, tags, attendances);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.matriculationYear = matriculationYear;
        this.instrument = instrument;
        this.tags.addAll(tags);
        this.attendances.addAll(attendances);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public boolean hasBirthdayInfo() {
        return !birthday.hasNoInfo();
    }

    public MatriculationYear getMatriculationYear() {
        return matriculationYear;
    }

    public boolean hasMatriculationYearInfo() {
        return !matriculationYear.hasNoInfo();
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public boolean hasInstrumentInfo() {
        return !instrument.hasNoInfo();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Attendance> getAttendances() {
        return Collections.unmodifiableSet(attendances);
    }


    /**
     * Checks if both persons have the same name. This defines a weaker notion of equality between two persons.
     *
     * @param otherPerson The other person to compare with.
     * @return True if both persons have the same name.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields. This defines a stronger notion
     * of equality between two persons.
     *
     * @param other The other object to compare with.
     * @return True if both persons have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && birthday.equals(otherPerson.birthday)
                && matriculationYear.equals(otherPerson.matriculationYear)
                && instrument.equals(otherPerson.instrument)
                && tags.equals(otherPerson.tags)
                && attendances.equals(otherPerson.attendances);
    }

    /**
     * Returns the hash code of the person.
     *
     * @return Hash code of the person.
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, birthday, matriculationYear, instrument, tags, attendances);
    }

    /**
     * Returns a string representation of the person.
     *
     * @return String representation of the person.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("birthday", birthday)
                .add("matriculation year", matriculationYear)
                .add("instrument", instrument)
                .add("tags", tags)
                .add("attendances", attendances)
                .toString();
    }

}
