package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.attendance.Attendance;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Instrument;
import seedu.address.model.person.MatriculationYear;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BIRTHDAY = Birthday.DEFAULT_BIRTHDAY;
    public static final String DEFAULT_MATRICULATION_YEAR = MatriculationYear.DEFAULT_MATRICULATION_YEAR;
    public static final String DEFAULT_INSTRUMENT = Instrument.DEFAULT_INSTRUMENT;

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Birthday birthday;
    private MatriculationYear matriculationYear;
    private Instrument instrument;
    private Set<Tag> tags;
    private Set<Attendance> attendances;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthday = new Birthday(DEFAULT_BIRTHDAY);
        matriculationYear = new MatriculationYear(DEFAULT_MATRICULATION_YEAR);
        instrument = new Instrument(DEFAULT_INSTRUMENT);
        tags = new HashSet<>();
        attendances = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        birthday = personToCopy.getBirthday();
        matriculationYear = personToCopy.getMatriculationYear();
        instrument = personToCopy.getInstrument();
        tags = new HashSet<>(personToCopy.getTags());
        attendances = new HashSet<>(personToCopy.getAttendances());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code attendances} of the {@code Person} to no attendances.
     */
    public PersonBuilder withAttendances() {
        this.attendances = new HashSet<>();
        return this;
    }

    /**
     * Parses the {@code attendances} into a {@code Set<Attendance>} and set it to the {@code Person} that we are
     * building.
     */
    public PersonBuilder withAttendances(String ... attendances) {
        this.attendances = SampleDataUtil.getAttendanceSet(attendances);
        return this;
    }

    /**
     * Parses the {@code attendances} into a {@code Set<Attendance>} and adds it to the existing attendances of the
     * {@code Person} that we are building.
     */
    public PersonBuilder addAttendances(String ... attendances) {
        Set<Attendance> attendancesToAdd = SampleDataUtil.getAttendanceSet(attendances);
        this.attendances.addAll(attendancesToAdd);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Person} to the default value.
     */
    public PersonBuilder withBirthday() {
        this.birthday = new Birthday(DEFAULT_BIRTHDAY);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Person} that we are building.
     */
    public PersonBuilder withBirthday(String birthday) {
        this.birthday = new Birthday(birthday);
        return this;
    }

    /**
     * Sets the {@code Birthday} of the {@code Person} that we are building.
     */
    public PersonBuilder withMatriculationYear() {
        this.matriculationYear = new MatriculationYear(DEFAULT_MATRICULATION_YEAR);
        return this;
    }

    /**
     * Sets the {@code MatriculationYear} of the {@code Person} that we are building.
     */
    public PersonBuilder withMatriculationYear(String matriculationYear) {
        this.matriculationYear = new MatriculationYear(matriculationYear);
        return this;
    }

    /**
     * Sets the {@code Instrument} of the {@code Person} that we are building.
     */
    public PersonBuilder withInstrument() {
        this.instrument = new Instrument(DEFAULT_INSTRUMENT);
        return this;
    }

    /**
     * Sets the {@code Instrument} of the {@code Person} that we are building.
     */
    public PersonBuilder withInstrument(String instrument) {
        this.instrument = new Instrument(instrument);
        return this;
    }

    /**
     * Builds a Person object.
     * @return the Person object
     */
    public Person build() {
        return new Person(name, phone, email, address, birthday, matriculationYear, instrument, tags, attendances);
    }

}
