package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.util.SampleDataUtil.getSamplePersons;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Instrument;
import seedu.address.model.person.MatriculationYear;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersons_success() {

        Person[] actualPersonArray = SampleDataUtil.getSamplePersons();
        Person actualFirstPerson = actualPersonArray[0];

        // data copied and pasted from SampleDataUtil
        Person expectedFirstPerson = new Person(new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"), new Address("Blk 30 Geylang Street 29, #06-40"),
                new Birthday("2000-02-02"), new MatriculationYear("2000"), new Instrument("Flute"),
                SampleDataUtil.getTagSet("friends"), SampleDataUtil.getAttendanceSet());

        // verify that first person in both arrays is the same
        assertEquals(actualFirstPerson, expectedFirstPerson);
    }

    @Test
    public void getSampleAddressBook_success() {
        AddressBook sampleAddressBookActual = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAddressBookActual.addPerson(samplePerson);
        }

        ReadOnlyAddressBook sampleAddressBookExpected = SampleDataUtil.getSampleAddressBook();

        assertEquals(sampleAddressBookActual, sampleAddressBookExpected);
    }
}
