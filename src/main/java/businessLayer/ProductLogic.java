package businessLayer;

import dataAccesLayer.ProductDAO;
import model.Product;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa ProductLogic este clasa utilizata pentru a implementa logica pentru obiecte de
 * tipul Product.
 */

public class ProductLogic {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    public ProductLogic()
    {
        validators=new ArrayList<>();
        validators.add(new PriceValidator());
        validators.add(new ProductQuantityValidator());
        validators.add(new NameAndBrandValidator());
        productDAO=new ProductDAO();
    }

    public void productValidate(Product product)
    {
        for(Validator<Product> validator:validators)
        {
            validator.validate(product);
        }
    }

    public int insertProduct(Product product)
    {
        this.productValidate(product);
        int result=productDAO.insert(product);
        if(result==0)
            throw new IllegalArgumentException("Product with id:"+product.getId()+" can't be inserted!");
        return result;
    }

    /**
     * Metoda este utilizata pentru a actualiza un produs, verificandu-se inainte daca
     * modificarile nu ar incalca corectitudinea datelor.
     * @param field campul care se doreste a fi actualizat
     * @param value valoarea cu care va fi actualizat campul
     * @param id id-ul produsului care se doreste a fi actualizat
     * @return numarul de randuri care au fost afectate de aceasta operatie
     */

    public int updateById(String field,Object value,int id)
    {
        Product product=productDAO.selectById(id);
        try {
            Field classField=product.getClass().getDeclaredField(field);
            classField.setAccessible(true);
            classField.set(product,value);
            this.productValidate(product);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        int result=productDAO.updateById(field,value,id);
        if(result==0)
            throw new IllegalArgumentException("Product with id:"+id+" can't be updated!");
        return result;
    }

    public int deleteById(int id)
    {
        int result=productDAO.deleteById(id);
        if(result==0)
            throw new IllegalArgumentException("Product with id:"+id+" can't be deleted!");
        return result;
    }

    public Product selectById(int id)
    {
        Product product=productDAO.selectById(id);
        if(product==null)
            throw new NoSuchElementException("Product with id:"+id+" can't be found!");
        return product;
    }

    public List<Product> selectAll()
    {
        return productDAO.selectAll();
    }

}
