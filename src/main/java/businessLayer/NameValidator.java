package businessLayer;

import model.Client;

import java.util.regex.Pattern;

/**
 * Clasa NameValidator este utilizata pentru a valida numele si prenumele unui client.
 */

public class NameValidator implements Validator<Client> {
    private static final String NAME_PATTERN="[^a-zA-z]";

    @Override
    public void validate(Client object) {
        Pattern pattern=Pattern.compile(NAME_PATTERN);
        if(pattern.matcher(object.getFirstName()).matches() || object.getFirstName().equals(""))
        {
            throw new IllegalArgumentException("First name is not valid!");
        }
        if(pattern.matcher(object.getLastName()).matches() || object.getLastName().equals(""))
        {
            throw new IllegalArgumentException("Last name is not valid!");
        }

    }
}
