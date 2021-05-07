package businessLayer;

import model.Product;

public class ProductQuantityValidator implements Validator<Product>{

    @Override
    public void validate(Product object) {
        if(object.getQuantity()<=0)
        {
            throw new IllegalArgumentException("Quantity is not a positive number!");
        }
    }
}
