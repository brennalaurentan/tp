package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class InstrumentContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> keywordsOne = new ArrayList<>();
        keywordsOne.add("apple");
        List<String> keywordsTwo = new ArrayList<>();
        keywordsTwo.add("banana");
        InstrumentContainsKeywordsPredicate instrumentContainsKeywordsPredicateOne =
                new InstrumentContainsKeywordsPredicate(keywordsOne);
        InstrumentContainsKeywordsPredicate instrumentContainsKeywordsPredicateTwo =
                new InstrumentContainsKeywordsPredicate(keywordsTwo);

        // same object -> returns true
        assertTrue(instrumentContainsKeywordsPredicateOne.equals(instrumentContainsKeywordsPredicateOne));

        // same values -> returns true
        InstrumentContainsKeywordsPredicate instrumentContainsKeywordsPredicateOneCopy =
                new InstrumentContainsKeywordsPredicate(keywordsOne);
        assertTrue(instrumentContainsKeywordsPredicateOne.equals(instrumentContainsKeywordsPredicateOneCopy));

        // different types -> returns false
        assertFalse(instrumentContainsKeywordsPredicateOne.equals(1));

        // null -> returns false
        assertFalse(instrumentContainsKeywordsPredicateOne.equals(null));

        // different command -> returns false
        assertFalse(instrumentContainsKeywordsPredicateOne.equals(instrumentContainsKeywordsPredicateTwo));
    }
}
