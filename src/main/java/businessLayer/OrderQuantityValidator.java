package businessLayer;

import model.CustomersOrder;

/**
 * Clasa OrderQuantityValidator este utilizata pentru a valida cantitea unei comenzi.
 */

public class OrderQuantityValidator implements Validator<CustomersOrder>{

    @Override
    public void validate(CustomersOrder object) {
        if(object.getQuantity()<=0)
        {
            throw new IllegalArgumentException("Quantity is not a positive number!");
        }
    }
}
