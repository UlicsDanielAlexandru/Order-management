package businessLayer;

import model.Client;

import java.util.regex.Pattern;

/**
 * Clasa PhoneNumberValidator este utilizata pentru a valida numarul de telefon al
 * unui client.
 */

public class PhoneNumberValidator implements Validator<Client> {
    public static final String PHONE_NUMBER_PATTERN="^\\s*(?:(07{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";

    /**
     * Metoda verifica corectitudinea numarului de telefon al unui client, acesta fiind
     * necesar de a contine numai cifre, de a incepe cu 07 si de a avea o lungime de 10
     * caractere.
     * @param object clientul care se doreste a fi validat
     */

    @Override
    public void validate(Client object) {
        Pattern pattern=Pattern.compile(PHONE_NUMBER_PATTERN);
        if(!pattern.matcher(object.getPhoneNumber()).matches())
        {
            throw new IllegalArgumentException("Phone number is not valid!");
        }
    }
}
