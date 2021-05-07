package businessLayer;

import model.Client;
import model.Product;

import java.util.regex.Pattern;

/**
 * Clasa utilizata pentru a valida numele si firma unui produs.
 */

public class NameAndBrandValidator implements Validator<Product> {

    private static final String NAME_PATTERN="[^a-zA-z]\\d";

    /**
     * Metoda verifica daca produsul are in denumire sau in denumirea firmei altceva
     * decat litere sau cifre.
     * @param object produsul care se doreste a fi validat
     */

    @Override
    public void validate(Product object) {
        Pattern pattern=Pattern.compile(NAME_PATTERN);
        if(pattern.matcher(object.getName()).matches() || object.getName().equals(""))
        {
            throw new IllegalArgumentException("Name is not valid!");
        }
        if(pattern.matcher(object.getBrand()).matches() || object.getBrand().equals(""))
        {
            throw new IllegalArgumentException("Brand is not valid!");
        }

    }
}
