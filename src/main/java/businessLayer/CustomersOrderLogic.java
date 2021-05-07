package businessLayer;

import dataAccesLayer.ClientDAO;
import dataAccesLayer.CustomersOrderDAO;
import dataAccesLayer.ProductDAO;
import model.CompleteOrder;
import model.CustomersOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa CustomersOrderLogic este utilizata pentru a implementa logica pentru obiecte
 * de tip CustomersOrder.
 */

public class CustomersOrderLogic {
    private Validator<CustomersOrder> validator;
    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private CustomersOrderDAO customersOrderDAO;

    public CustomersOrderLogic()
    {
        this.validator=new OrderQuantityValidator();
        this.clientDAO=new ClientDAO();
        this.productDAO=new ProductDAO();
        this.customersOrderDAO=new CustomersOrderDAO();
    }

    public void orderValidate(CustomersOrder order)
    {
        this.validator.validate(order);
    }

    /**
     * Metoda inseareaza o comanda noua daca stocul de produse este suficiet.
     * @param order comanda care se doreste a fi adaugata
     * @return numarul de randuri adaugate in tabel
     */

    public int insertOrder(CustomersOrder order)
    {
        this.orderValidate(order);
        if(order.getQuantity()>productDAO.selectById(order.getProductId()).getQuantity())
            throw new IllegalArgumentException("Not enough stock!");
        int result=customersOrderDAO.insert(order);
        if(result==0)
            throw new IllegalArgumentException("Order with id:"+order.getId()+" can't be inserted!");
        return result;
    }

    public int updateById(String field,Object value,int id)
    {
        int result=customersOrderDAO.updateById(field,value,id);
        if(result==0)
            throw new IllegalArgumentException("Order with id:"+id+" can't be updates!");
        return result;
    }

    public int deleteById(int id)
    {
        int result=customersOrderDAO.deleteById(id);
        if(result==0)
            throw new IllegalArgumentException("Order with id:"+id+" can't be deleted!");
        return result;
    }

    /**
     * Metoda care returneaza o comanda completa pentru un anume id specificat. Aceasta
     * va selecta un customersOrder, si in cazul in care acesta exista, va crea un
     * obiect de tip CompleteOrder, care va contine si informatiile despre client si
     * produs.
     * @param id id-ul comenzi cautate
     * @return comanda completa cu id-ul specificat, sau null in caz ca nu exista
     */

    public CompleteOrder selectById(int id)
    {
        CustomersOrder customersOrder=customersOrderDAO.selectById(id);
        if(customersOrder==null)
            throw new NoSuchElementException("Order with id:"+id+" can't be found!");
        CompleteOrder completeOrder=new CompleteOrder(customersOrder,clientDAO.selectById(customersOrder.getClientId()),productDAO.selectById(customersOrder.getProductId()));
        return completeOrder;
    }

    /**
     * Metoda selecteaza toate comenziile complete, fiind selectate toate obiectele
     * de tipul CustomersOrder, fiind apoi selectati clientul si produsul.
     * @return lista cu toate comenzile, in format complet
     */

    public List<CompleteOrder> selectAllOrders()
    {
        List<CompleteOrder> completeOrders=new ArrayList<>();
        List<CustomersOrder> customersOrders=customersOrderDAO.selectAll();
        for(CustomersOrder order: customersOrders)
        {
            completeOrders.add(new CompleteOrder(order,clientDAO.selectById(order.getClientId()),productDAO.selectById(order.getProductId())));
        }
        return completeOrders;
    }
}
