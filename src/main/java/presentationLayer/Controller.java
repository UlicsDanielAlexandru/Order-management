package presentationLayer;
import businessLayer.*;
import model.Client;
import model.CustomersOrder;
import model.Product;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.List;

/**
 * Clasa Controller este utilizata pentru a lega clasele din model cu interfata grafica.
 */

public class Controller {
    private View view;
    private OrderView orderView;
    private ClientLogic clientLogic;
    private ProductLogic productLogic;
    private CustomersOrderLogic customersOrderLogic;

    public Controller() {
        this.view=new View();
        this.orderView=new OrderView(this.view);
        this.clientLogic=new ClientLogic();
        this.productLogic=new ProductLogic();
        this.customersOrderLogic=new CustomersOrderLogic();
        view.addClientsButtonListener(new ClientsButtonListener(this.view));
        view.addProductsButtonListener(new ProductsButtonListener(this.view));
        view.addOrdersButtonListener(new OrdersActionListener(this.view));
        view.addBackButtonListener(new BackButtonListener(this.view,clientLogic,productLogic));
        view.addInsertButtonListener(new InsertButtonListener());
        view.addDeleteButtonListener(new DeleteButtonListener());
        view.addUpdateButtonListener(new UpdateButtonListener());
        view.addFindButtonListener(new FindButtonListener());
        view.addMakeOrderButtonListener(new MakeOrderButtonListener());
        orderView.addOkButtonListener(new OkButtonListener(orderView,view,clientLogic,productLogic,customersOrderLogic));
        TablePopulator.populateTable(view.clientsTable,clientLogic.selectAll().parallelStream().map(x->(Object)x).collect(Collectors.toList()));
        TablePopulator.populateTable(view.productsTable,productLogic.selectAll().parallelStream().map(x->(Object)x).collect(Collectors.toList()));
        TablePopulator.populateTable(view.ordersTable,customersOrderLogic.selectAllOrders().parallelStream().map(x->(Object)x).collect(Collectors.toList()));
    }

    /**
     * Clasa InsertButtonListener este utilizata pentru a implementa functionalitatea
     * butonului de insert.
     */
    public class InsertButtonListener implements ActionListener {
        /**
         * Cand butonul este apasat se verifica daca operatia este realizata asupra
         * tabelului de clienti sau a celui de produse, urmand ca dupa inserare tabelul
         * respectiv sa fie actualizat. In cazul in care datele sunt gresite, se afiseaza
         * un mesaj.
         * @param e evenimentul de apasare al butonului de insert din interfata
         */
        public void actionPerformed(ActionEvent e) {
            try {
                if (view.clientsScrollPane.getParent() == view.tablePane) {
                    Client clientToInsert = new Client(view.firstNameTextField.getText(), view.lastNameTextField.getText(), view.addressTextField.getText(), view.emailTextField.getText(), view.phoneNumberTextField.getText());
                    clientLogic.insertClient(clientToInsert);
                    ((DefaultTableModel)view.clientsTable.getModel()).setColumnCount(0);
                    ((DefaultTableModel) view.clientsTable.getModel()).setRowCount(0);
                    TablePopulator.populateTable(view.clientsTable, clientLogic.selectAll().parallelStream().map(x -> (Object) x).collect(Collectors.toList()));
                } else {
                    double price;
                    int quantity;
                    try {
                        price=Double.parseDouble(view.priceTextField.getText());
                    }
                    catch (NumberFormatException exception)
                    {
                        throw new IllegalArgumentException("Price is not valid!");
                    }
                    try {
                        quantity=Integer.parseInt(view.quantityTextField.getText());
                    }
                    catch (NumberFormatException exception) {
                        throw new IllegalArgumentException("Quantity is not valid!");
                    }
                    Product productToInsert = new Product(view.nameTextField.getText(), view.brandTextField.getText(), price, quantity);
                    productLogic.insertProduct(productToInsert);
                    ((DefaultTableModel) view.productsTable.getModel()).setColumnCount(0);
                    ((DefaultTableModel) view.productsTable.getModel()).setRowCount(0);
                    TablePopulator.populateTable(view.productsTable, productLogic.selectAll().parallelStream().map(x -> (Object) x).collect(Collectors.toList()));
                }
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(view,exception.getMessage());
            } finally {
                if(view.clientsScrollPane.getParent()==view.tablePane) {
                    view.firstNameTextField.setText("");
                    view.lastNameTextField.setText("");
                    view.addressTextField.setText("");
                    view.emailTextField.setText("");
                    view.phoneNumberTextField.setText("");
                } else {
                    view.nameTextField.setText("");
                    view.brandTextField.setText("");
                    view.priceTextField.setText("");
                    view.quantityTextField.setText("");
                }
            }
        }
    }

    /**
     * Clasa DeleteButtonListener este utilizata pentru a implementa functionalitatea
     * butonului de delete.
     */
    public class DeleteButtonListener implements ActionListener {
        /**
         * Cand butonul este apasat se verifica daca operatia trebuie efectuata asupra
         * tabelului de clienti sau de produse, verificandu-se si daca un rand
         * a fost selectat, urmand ca dupa stergere tabelul sa fie actualizat. In caz
         * de eroare se afiseaza un mesaj.
         * @param e evenimentul de apasare al butonului din interfata
         */
        public void actionPerformed(ActionEvent e) {
            try {
                if(view.clientsScrollPane.getParent()==view.tablePane) {
                    if(view.clientsTable.getSelectedRow()==-1)
                        throw new IllegalArgumentException("A client must be selected!");
                    clientLogic.deleteById((Integer) view.clientsTable.getValueAt(view.clientsTable.getSelectedRow(),0));
                    ((DefaultTableModel)view.clientsTable.getModel()).setColumnCount(0);
                    ((DefaultTableModel) view.clientsTable.getModel()).setRowCount(0);
                    TablePopulator.populateTable(view.clientsTable, clientLogic.selectAll().parallelStream().map(x -> (Object) x).collect(Collectors.toList()));
                } else {
                    if(view.productsTable.getSelectedRow()==-1)
                        throw new IllegalArgumentException("A product must be selected!");
                    productLogic.deleteById((Integer)view.productsTable.getValueAt(view.productsTable.getSelectedRow(),0));
                    ((DefaultTableModel)view.productsTable.getModel()).setColumnCount(0);
                    ((DefaultTableModel) view.productsTable.getModel()).setRowCount(0);
                    TablePopulator.populateTable(view.productsTable, productLogic.selectAll().parallelStream().map(x -> (Object) x).collect(Collectors.toList()));
                }
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(view,exception.getMessage());
            }
        }
    }

    /**
     * Clasa UpdateButtonListener este utilizata pentru a implementa functionalitatea
     * butonului de update.
     */
    public class UpdateButtonListener implements ActionListener {
        /**
         * Cand butonul este apasat se verifica daca operatia trebuie realizata asupra
         * tabelului de clienti sau de produse, se verifica care campuri trebuie
         * actualizate si daca a fost selectat un rand, dupa care tabelul va fi
         * actualizat. In caz de eroare se afiseaza un mesaj.
         * @param e evenimentul de apasare al butonului din interfata
         */
        public void actionPerformed(ActionEvent e) {
            try {
                if(view.clientsScrollPane.getParent()==view.tablePane) {
                    if(view.clientsTable.getSelectedRow()==-1)
                        throw new IllegalArgumentException("A client must be selected!");
                    if(!view.firstNameTextField.getText().equals(""))
                        clientLogic.updateById("firstName",view.firstNameTextField.getText(),(Integer) view.clientsTable.getValueAt(view.clientsTable.getSelectedRow(),0));
                    if(!view.lastNameTextField.getText().equals(""))
                        clientLogic.updateById("lastName",view.lastNameTextField.getText(),(Integer) view.clientsTable.getValueAt(view.clientsTable.getSelectedRow(),0));
                    if(!view.addressTextField.getText().equals(""))
                        clientLogic.updateById("address",view.addressTextField.getText(),(Integer)view.clientsTable.getValueAt(view.clientsTable.getSelectedRow(),0));
                    if(!view.emailTextField.getText().equals(""))
                        clientLogic.updateById("email",view.emailTextField.getText(),(Integer) view.clientsTable.getValueAt(view.clientsTable.getSelectedRow(),0));
                    if(!view.phoneNumberTextField.getText().equals(""))
                        clientLogic.updateById("phoneNumber",view.phoneNumberTextField.getText(),(Integer) view.clientsTable.getValueAt(view.clientsTable.getSelectedRow(),0));
                    ((DefaultTableModel)view.clientsTable.getModel()).setColumnCount(0);
                    ((DefaultTableModel) view.clientsTable.getModel()).setRowCount(0);
                    TablePopulator.populateTable(view.clientsTable, clientLogic.selectAll().parallelStream().map(x -> (Object) x).collect(Collectors.toList()));
                } else {
                    if(view.productsTable.getSelectedRow()==-1)
                        throw new IllegalArgumentException("A product must be selected!");
                    if(!view.nameTextField.getText().equals(""))
                        productLogic.updateById("name",view.nameTextField.getText(),(Integer)view.productsTable.getValueAt(view.productsTable.getSelectedRow(),0));
                    if(!view.brandTextField.getText().equals(""))
                        productLogic.updateById("brand",view.brandTextField.getText(),(Integer)view.productsTable.getValueAt(view.productsTable.getSelectedRow(),0));
                    if(!view.priceTextField.getText().equals(""))
                        productLogic.updateById("price",Double.parseDouble(view.priceTextField.getText()),(Integer)view.productsTable.getValueAt(view.productsTable.getSelectedRow(),0));
                    if(!view.quantityTextField.getText().equals(""))
                        productLogic.updateById("quantity",Integer.parseInt(view.quantityTextField.getText()),(Integer)view.productsTable.getValueAt(view.productsTable.getSelectedRow(),0));
                    ((DefaultTableModel)view.productsTable.getModel()).setColumnCount(0);
                    ((DefaultTableModel) view.productsTable.getModel()).setRowCount(0);
                    TablePopulator.populateTable(view.productsTable, productLogic.selectAll().parallelStream().map(x -> (Object) x).collect(Collectors.toList()));
                }
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(view,exception.getMessage());
            } finally {
                if(view.clientsScrollPane.getParent()==view.tablePane) {
                    view.firstNameTextField.setText("");
                    view.lastNameTextField.setText("");
                    view.addressTextField.setText("");
                    view.emailTextField.setText("");
                    view.phoneNumberTextField.setText("");
                } else {
                    view.nameTextField.setText("");
                    view.brandTextField.setText("");
                    view.priceTextField.setText("");
                    view.quantityTextField.setText("");
                }
            }
        }
    }

    /**
     * Clasa FindButtonListener este utilizata pentru a implementa functionalitatea
     * butonului de find.
     */
    public class FindButtonListener implements ActionListener {
        /**
         * Cand butonul este apasat se verifica asupra carui tabel trebuie sa se
         * realizeze operatia, urmand ca apoi utilizatorul sa introduc un id
         * valid, dupa cautare tabelul este actualizat astfel incat sa contina doar
         * instanta cautata. In caz de eroare se afiseaza un mesaj.
         * @param e evenimentul de apasare al butonului din interfata
         */
        public void actionPerformed(ActionEvent e) {
            try {
                if (view.clientsScrollPane.getParent() == view.tablePane) {
                    int id;
                    try {
                        String input=JOptionPane.showInputDialog(view, "Enter wanted id:");
                        if(input==null)
                            return;
                        else
                            id=Integer.parseInt(input);
                    } catch (NumberFormatException exception) {
                        throw new NoSuchElementException("An id must be inserted!");
                    }
                    List<Object> clients = new ArrayList<>();
                    clients.add(clientLogic.selectById(id));
                    ((DefaultTableModel)view.clientsTable.getModel()).setColumnCount(0);
                    ((DefaultTableModel) view.clientsTable.getModel()).setRowCount(0);
                    TablePopulator.populateTable(view.clientsTable, clients);
                } else {
                    int id;
                    try {
                        String input=JOptionPane.showInputDialog(view, "Enter wanted id:");
                        if(input==null)
                            return;
                        else
                            id=Integer.parseInt(input);
                    } catch (NumberFormatException exception) {
                        throw new NoSuchElementException("An id must be inserted!");
                    }
                    List<Object> products = new ArrayList<>();
                    products.add(productLogic.selectById(id));
                    ((DefaultTableModel)view.productsTable.getModel()).setColumnCount(0);
                    ((DefaultTableModel) view.productsTable.getModel()).setRowCount(0);
                    TablePopulator.populateTable(view.productsTable, products);
                }
            } catch (NoSuchElementException exception) {
                JOptionPane.showMessageDialog(view,exception.getMessage());
            }
        }
    }

    /**
     * Clasa MakeOrderButtonListener este utilizata pentru a implementa functionalitatea
     * butonului de make order.
     */
    public class MakeOrderButtonListener implements ActionListener {
        /**
         * Cand butonul este apasat, tabelele din fereastra orderView sunt reactualizate
         * si fereastra este facuta vizibila.
         * @param e evenimentul de apasare al butonului
         */
        public void actionPerformed(ActionEvent e) {
            ((DefaultTableModel)orderView.clientsTable.getModel()).setColumnCount(0);
            ((DefaultTableModel) orderView.clientsTable.getModel()).setRowCount(0);
            TablePopulator.populateTable(orderView.clientsTable,clientLogic.selectAll().parallelStream().map(x->(Object)x).collect(Collectors.toList()));
            ((DefaultTableModel)orderView.productsTable.getModel()).setColumnCount(0);
            ((DefaultTableModel) orderView.productsTable.getModel()).setRowCount(0);
            TablePopulator.populateTable(orderView.productsTable, productLogic.selectAll().parallelStream().map(x -> (Object) x).collect(Collectors.toList()));
            orderView.setVisible(true);
        }
    }

}
