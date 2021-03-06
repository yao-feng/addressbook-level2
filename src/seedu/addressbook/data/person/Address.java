package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address extends Contact implements Printable{

    public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    private final Block block;
    private final Street street;
    private final Unit unit;
    private final PostalCode postalCode;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        super(address, isPrivate);
        if (!isValidContact(value)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        String[] details = value.split(", ");
        if (details.length == 4) {
            block = new Block(details[0]);
            street = new Street(details[1]);
            unit = new Unit(details[2]);
            postalCode = new PostalCode(details[3]);
        }
        else {
            block = null;
            street = null;
            unit = null;
            postalCode = null;
        }
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    @Override
    public boolean isValidContact(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public String getPrintableString() {
        return "Address: " + value;
    }
}