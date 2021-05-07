package businessLayer;

/**
 * Interfata Validator este utilizata pentru a crea mai multi validat-ori pentru date.
 * @param <T> una din clasele Client, Product sau CustomersOrder
 */

public interface Validator<T> {
    public void validate(T object);
}
