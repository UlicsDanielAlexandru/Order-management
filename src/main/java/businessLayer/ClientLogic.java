package businessLayer;

import dataAccesLayer.ClientDAO;
import model.Client;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa ClientLogic este utilizata pentru a implementa logica pentru obiecte de tip
 * Client.
 */

public class ClientLogic {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    public ClientLogic()
    {
        validators=new ArrayList<>();
        validators.add(new NameValidator());
        validators.add(new AddressValidator());
        validators.add(new PhoneNumberValidator());
        validators.add(new EmailValidator());
        clientDAO=new ClientDAO();
    }

    public void clientValidate(Client client)
    {
        for(Validator<Client> validator:validators)
        {
            validator.validate(client);
        }
    }

    public int insertClient(Client client)
    {
        this.clientValidate(client);
        int result=clientDAO.insert(client);
        if(result==0)
            throw new IllegalArgumentException("Client with id:"+client.getId()+" can't be inserted!");
        return result;
    }

    /**
     * Metoda este utilizata pentru a actualiza un client, verificandu-se inainte daca
     * modificarile nu ar incalca corectitudinea datelor.
     * @param field campul care se doreste a fi actualizat
     * @param value valoarea cu care va fi actualizat campul
     * @param id id-ul clientului care se doreste a fi actualizat
     * @return numarul de randuri care au fost afectate de aceasta operatie
     */

    public int updateById(String field,Object value,int id)
    {
        Client client=clientDAO.selectById(id);
        try {
            Field classField=client.getClass().getDeclaredField(field);
            classField.setAccessible(true);
            classField.set(client,value);
            this.clientValidate(client);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        int result=clientDAO.updateById(field,value,id);
        if(result==0)
            throw new IllegalArgumentException("Client with id:"+id+" can't be updated!");
        return result;
    }

    public int deleteById(int id)
    {
        int result=clientDAO.deleteById(id);
        if(result==0)
            throw new IllegalArgumentException("Client with id:"+id+" can't be deleted!");
        return result;
    }

    public Client selectById(int id)
    {
        Client client=clientDAO.selectById(id);
        if(client==null)
            throw new NoSuchElementException("Client with id:"+id+" can't be found!");
        return client;
    }

    public List<Client> selectAll()
    {
        return clientDAO.selectAll();
    }

}
