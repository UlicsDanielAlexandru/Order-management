package businessLayer;

import model.CompleteOrder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clasa BillMaker este utilizata pentru a crea facturi in format text, in urma
 * realizarii unei comenzi
 */

public class BillMaker {

    /**
     * Metoda face un fisier cu numarul de ordine al comenzi, si va trece in el
     * detaliile din comanda, alaturi de totalul de plata
     * @param completeOrder comanda pentru care este realizata factura
     */

    public static void makeBill(CompleteOrder completeOrder)
    {
        String fileName="src\\main\\resources\\bill";
        fileName=fileName.concat(String.valueOf(completeOrder.getId()));
        fileName=fileName.concat(".txt");
        try
        {
            BufferedWriter writer=new BufferedWriter(new FileWriter(fileName));
            writer.write("Order number:"+completeOrder.getId()+"\n");
            writer.write("Quantity:"+completeOrder.getQuantity()+"\n");
            writer.write("First name:"+completeOrder.getFirstName()+"\n");
            writer.write("Last name:"+completeOrder.getLastName()+"\n");
            writer.write("Product name:"+completeOrder.getName()+"\n");
            writer.write("Brand:"+completeOrder.getBrand()+"\n");
            writer.write("Price:"+completeOrder.getPrice()+"\n");
            writer.write("Total:"+completeOrder.getQuantity()*completeOrder.getPrice());
            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
