package seedu.address.logic.parser;

import static seedu.address.model.person.Birthday.DEFAULT_BIRTHDAY;
import static seedu.address.model.person.Instrument.DEFAULT_INSTRUMENT;
import static seedu.address.model.person.MatriculationYear.DEFAULT_MATRICULATION_YEAR;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Stores mapping of prefixes to their respective arguments.
 * Each key may be associated with multiple argument values.
 * Values for a given key are stored in a list, and the insertion ordering is maintained.
 * Keys are unique, but the list of argument values may contain duplicate argument values, i.e. the same argument value
 * can be inserted multiple times for the same prefix.
 */
public class ArgumentMultimap {

    /** Prefixes mapped to their respective arguments**/
    private final Map<Prefix, List<String>> argMultimap = new HashMap<>();

    /**
     * Associates the specified argument value with prefix key in this map.
     * If the map previously contained a mapping for the key, the new value is appended to the list of existing values.
     *
     * @param prefix   Prefix key with which the specified argument value is to be associated.
     * @param argValue Argument value to be associated with the specified prefix key.
     */
    public void put(Prefix prefix, String argValue) {
        List<String> argValues = getAllValues(prefix);
        argValues.add(argValue);
        argMultimap.put(prefix, argValues);
    }

    /**
     * Returns the last value of the given prefix. If the prefix does not exist or has no values, this will return an
     * empty {@code Optional}.
     *
     * @param prefix Prefix key with which the specified argument value is to be associated.
     */
    public Optional<String> getValue(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.empty() : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns the last value  of the given prefix. If the prefix does not exist or has no values, this will return an
     * {@code Optional} with the default birthday value.
     *
     * @param prefix Prefix key with which the specified argument value is to be associated.
     */
    public Optional<String> getOptionalBirthday(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.of(DEFAULT_BIRTHDAY) : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns the last value  of the given prefix. If the prefix does not exist or has no values, this will return an
     * {@code Optional} with the default matriculation year value.
     *
     * @param prefix Prefix key with which the specified argument value is to be associated.
     */
    public Optional<String> getOptionalMatriculationYear(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.of(DEFAULT_MATRICULATION_YEAR)
                : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns the last value  of the given prefix. If the prefix does not exist or has no values, this will return an
     * {@code Optional} with the default instrument value.
     *
     * @param prefix Prefix key with which the specified argument value is to be associated.
     */
    public Optional<String> getOptionalInstrument(Prefix prefix) {
        List<String> values = getAllValues(prefix);
        return values.isEmpty() ? Optional.of(DEFAULT_INSTRUMENT) : Optional.of(values.get(values.size() - 1));
    }

    /**
     * Returns all values of the given prefix.
     * If the prefix does not exist or has no values, this will return an empty list.
     * Modifying the returned list will not affect the underlying data structure of the ArgumentMultimap.
     *
     * @param prefix Prefix key with which the specified argument value is to be associated.
     * @return List of values associated with the specified prefix key.
     */
    public List<String> getAllValues(Prefix prefix) {
        if (!argMultimap.containsKey(prefix)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(argMultimap.get(prefix));
    }

    /**
     * Returns the preamble (text before the first valid prefix). Trims any leading/trailing spaces.
     *
     * @return Preamble text.
     */
    public String getPreamble() {
        return getValue(new Prefix("")).orElse("");
    }

    /**
     * Checks if there are any duplicate prefixes in the ArgumentMultimap.
     *
     * @param prefixes Prefixes to check for duplicates.
     * @throws ParseException If there are duplicate prefixes.
     */
    public void verifyNoDuplicatePrefixesFor(Prefix... prefixes) throws ParseException {
        Prefix[] duplicatedPrefixes = Stream.of(prefixes).distinct()
                .filter(prefix -> argMultimap.containsKey(prefix) && argMultimap.get(prefix).size() > 1)
                .toArray(Prefix[]::new);

        if (duplicatedPrefixes.length > 0) {
            throw new ParseException(Messages.getErrorMessageForDuplicatePrefixes(duplicatedPrefixes));
        }
    }
}
