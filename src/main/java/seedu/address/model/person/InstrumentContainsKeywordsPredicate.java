package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Instrument} matches any of the keywords given.
 */
public class InstrumentContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public InstrumentContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getInstrument().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InstrumentContainsKeywordsPredicate)) {
            return false;
        }

        InstrumentContainsKeywordsPredicate otherInstrumentContainsKeywordsPredicate =
                (InstrumentContainsKeywordsPredicate) other;
        return keywords.equals(otherInstrumentContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
