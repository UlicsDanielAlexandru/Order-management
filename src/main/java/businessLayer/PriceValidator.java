package businessLayer;

import model.Product;

/**
 * Clasa PriceValidator este utilizata pentru a valida pretul unui produs.
 */

public class PriceValidator implements Validator<Product> {

    @Override
    public void validate(Product object) {
        if(object.getPrice()<=0)
        {
            throw new IllegalArgumentException("Price is not a positive number!");
        }
    }
}
