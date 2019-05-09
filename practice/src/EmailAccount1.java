import components.map.Map;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Put your name here
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */
    String firstName;
    String lastName;
    String address;
    private Map<String, Integer> map;

    // TODO - declare static and instance data members

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (this.map.hasKey(lastName.toLowerCase())) {
            Map.Pair<String, Integer> pair = this.map
                    .remove(this.lastName.toLowerCase());
            this.map.add(this.lastName.toLowerCase(), pair.value() + 1);
            int dotNumber = pair.value() + 1;
            this.address = this.lastName.toLowerCase() + '.' + dotNumber
                    + "@osu.edu";
        } else {
            this.map.add(lastName.toLowerCase(), 1);
            this.address = this.lastName.toLowerCase() + ".1@osu.edu";
        }
    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String emailAddress() {
        return this.address;
    }

    @Override
    public String toString() {
        return "Name: " + this.firstName + " " + this.lastName + "\nEmail ID: "
                + this.address;
    }

}