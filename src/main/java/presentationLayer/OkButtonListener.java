package presentationLayer;

import businessLayer.*;
import model.CustomersOrder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

/**
 * Clasa OkButtonListener este utilizate pentru a implementa functionalitatea
 * butonului de ok.
 */
public class OkButtonListener implements ActionListener {
    private OrderView orderView;
    private View view;
    private ClientLogic clientLogic;
    private ProductLogic productLogic;
    private CustomersOrderLogic customersOrderLogic;

    public OkButtonListener(OrderView orderView,View view,ClientLogic clientLogic,ProductLogic productLogic,CustomersOrderLogic customersOrderLogic)
    {
        this.orderView=orderView;
        this.view=view;
        this.clientLogic=clientLogic;
        this.productLogic=productLogic;
        this.customersOrderLogic=customersOrderLogic;
    }
    /**
     * Cand butonul este apasat se verifica daca au fost selectati un client si un
     * produs, iar cantitatea de produse este introdusa, urmand ca apoi o comanda sa
     * fie adaugata, tabelul de comenzi sa fie reactualizat si o factura sa fie
     * generata. In caz de eroare se afiseaza un mesaj.
     * @param e evenimentul de apasare al butonului din interfata
     */
    public void actionPerformed(ActionEvent e) {
        try {
            if(orderView.clientsTable.getSelectedRow()==-1)
                throw new IllegalArgumentException("No client selected!");
            int clientId = (Integer) orderView.clientsTable.getValueAt(orderView.clientsTable.getSelectedRow(), 0);
            if(orderView.productsTable.getSelectedRow()==-1)
                throw new IllegalArgumentException("No product selected!");
            int productId = (Integer) orderView.productsTable.getValueAt(orderView.productsTable.getSelectedRow(), 0);
            if(orderView.quantityTextField.getText().equals(""))
                throw new IllegalArgumentException("No quantity entered!");
            customersOrderLogic.insertOrder(new CustomersOrder(Integer.parseInt(orderView.quantityTextField.getText()), clientId, productId));
            ((DefaultTableModel) view.ordersTable.getModel()).setColumnCount(0);
            ((DefaultTableModel) view.ordersTable.getModel()).setRowCount(0);
            TablePopulator.populateTable(view.ordersTable, customersOrderLogic.selectAllOrders().parallelStream().map(x -> (Object) x).collect(Collectors.toList()));
            productLogic.updateById("quantity",productLogic.selectById(productId).getQuantity()-Integer.parseInt(orderView.quantityTextField.getText()),productId);
            ((DefaultTableModel)view.productsTable.getModel()).setColumnCount(0);
            ((DefaultTableModel) view.productsTable.getModel()).setRowCount(0);
            TablePopulator.populateTable(view.productsTable, productLogic.selectAll().parallelStream().map(x -> (Object) x).collect(Collectors.toList()));
            ((DefaultTableModel)orderView.productsTable.getModel()).setColumnCount(0);
            ((DefaultTableModel) orderView.productsTable.getModel()).setRowCount(0);
            TablePopulator.populateTable(orderView.productsTable, productLogic.selectAll().parallelStream().map(x -> (Object) x).collect(Collectors.toList()));
            orderView.quantityTextField.setText("");
            orderView.setVisible(false);
            BillMaker.makeBill(customersOrderLogic.selectById((Integer)view.ordersTable.getValueAt(view.ordersTable.getRowCount()-1,0)));
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(orderView,exception.getMessage());
        }
    }
}