package businessLayer;

import model.Product;

/**
 * Clasa ProductQuantityValidator este utilizata pentru a valida cantitatea unui produs
 * din stoc.
 */

public class ProductQuantityValidator implements Validator<Product>{

    @Override
    public void validate(Product object) {
        if(object.getQuantity()<=0)
        {
            throw new IllegalArgumentException("Quantity is not a positive number!");
        }
    }
}
