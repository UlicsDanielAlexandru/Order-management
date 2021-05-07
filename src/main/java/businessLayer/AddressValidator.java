package businessLayer;

import model.Client;

import java.util.regex.Pattern;


public class AddressValidator implements Validator<Client> {
    private static final String ADDRESS_PATTERN="[^a-zA-Z\\d .-]";

    /**
     * Metoda verifica daca in adresa exista alte caractere decat cifre, litere, punct sau linie.
     * @param object obiectul client care se doreste a fi validat
     */

    @Override
    public void validate(Client object) {
        Pattern pattern=Pattern.compile(ADDRESS_PATTERN);
        if(pattern.matcher(object.getAddress()).matches())
        {
            throw new IllegalArgumentException("Address is not valid!");
        }
    }
}
