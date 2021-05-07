package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa View este utilizata pentru a implementa fereastra principala. La inceput vor fi
 * cele trei butoane pentru selectarea interfetelor pentru clienti, produse si comenzi,
 * dupa care aceastea vor fi eliminate, fiind adaugate elementele specifice fiecarei
 * categorii.
 */

public class View extends JFrame {
        protected JButton clientsButton=new JButton("Clients");
        protected JButton productsButton=new JButton("Products");
        protected JButton ordersButton=new JButton("Orders");
        protected JTable clientsTable=new JTable();
        protected JTable productsTable=new JTable();
        protected JTable ordersTable=new JTable();
        protected JButton backButton=new JButton("Back");
        protected JButton insertButton=new JButton("Insert");
        protected JButton updateButton=new JButton("Update");
        protected JButton deleteButton=new JButton("Delete");
        protected JButton findButton=new JButton("Find");
        protected JButton makeOrderButton=new JButton(" Make order");
        protected JPanel content=new JPanel();
        protected JPanel tablesButtonPane=new JPanel();
        protected JPanel operationsButtonPane=new JPanel();
        protected JPanel backButtonPane=new JPanel();
        protected JPanel tablePane=new JPanel();
        protected JScrollPane clientsScrollPane=new JScrollPane(clientsTable);
        protected JScrollPane productsScrollPane=new JScrollPane(productsTable);
        protected JScrollPane ordersScrollPane=new JScrollPane(ordersTable);
        protected JTextField firstNameTextField=new JTextField();
        protected JTextField lastNameTextField=new JTextField();
        protected JTextField addressTextField=new JTextField();
        protected JTextField emailTextField=new JTextField();
        protected JTextField phoneNumberTextField=new JTextField();
        protected JLabel firstNameLabel=new JLabel("First Name:");
        protected JLabel lastNameLabel=new JLabel("Last Name:");
        protected JLabel addressLabel=new JLabel("Address:");
        protected JLabel emailLabel=new JLabel("Email:");
        protected JLabel phoneNumberLabel=new JLabel("Phone number:");
        protected JTextField nameTextField=new JTextField();
        protected JTextField brandTextField=new JTextField();
        protected JTextField priceTextField=new JTextField();
        protected JTextField quantityTextField=new JTextField();
        protected JLabel nameLabel=new JLabel("Name:");
        protected JLabel brandLabel=new JLabel("Brand:");
        protected JLabel priceLabel=new JLabel("Price:");
        protected JLabel quantityLabel=new JLabel("Quantity:");

        public View()
        {
            content.setLayout(new BorderLayout(0,100));
            tablesButtonPane.setLayout(new FlowLayout());
            tablesButtonPane.add(clientsButton);
            tablesButtonPane.add(productsButton);
            tablesButtonPane.add(ordersButton);
            content.add(tablesButtonPane,BorderLayout.CENTER);
            this.setContentPane(content);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(1000,800);
            this.setVisible(true);
            firstNameTextField.setPreferredSize(new Dimension(100,20));
            lastNameTextField.setPreferredSize(new Dimension(100,20));
            addressTextField.setPreferredSize(new Dimension(100,20));
            emailTextField.setPreferredSize(new Dimension(100,20));
            phoneNumberTextField.setPreferredSize(new Dimension(100,20));
            nameTextField.setPreferredSize(new Dimension(100,20));
            brandTextField.setPreferredSize(new Dimension(100,20));
            priceTextField.setPreferredSize(new Dimension(100,20));
            quantityTextField.setPreferredSize(new Dimension(100,20));
            operationsButtonPane.setLayout(new FlowLayout());
            operationsButtonPane.add(insertButton);
            operationsButtonPane.add(updateButton);
            operationsButtonPane.add(deleteButton);
            operationsButtonPane.add(findButton);
            tablePane.setLayout(new FlowLayout());
            backButtonPane.setLayout(new FlowLayout());
            backButtonPane.add(backButton);
            clientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            clientsScrollPane.setPreferredSize(new Dimension(950,300));
            productsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            productsScrollPane.setPreferredSize(new Dimension(820,300));
            ordersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            ordersScrollPane.setPreferredSize(new Dimension(950,300));
            clientsTable.setDefaultEditor(Object.class,null);
            productsTable.setDefaultEditor(Object.class,null);
            ordersTable.setDefaultEditor(Object.class,null);
            clientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            ordersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }

        public void addClientsButtonListener(ActionListener clientsButtonListener)
        {
            clientsButton.addActionListener(clientsButtonListener);
        }

        public void addProductsButtonListener(ActionListener productsButtonListener)
        {
            productsButton.addActionListener(productsButtonListener);
        }

        public void addOrdersButtonListener(ActionListener ordersButtonListener)
        {
            ordersButton.addActionListener(ordersButtonListener);
        }

        public void addBackButtonListener(ActionListener backButtonListener)
        {
            backButton.addActionListener(backButtonListener);
        }

        public void addInsertButtonListener(ActionListener insertButtonListener)
        {
            insertButton.addActionListener(insertButtonListener);
        }

        public void addDeleteButtonListener(ActionListener deleteButtonListener)
        {
            deleteButton.addActionListener(deleteButtonListener);
        }

        public void addUpdateButtonListener(ActionListener updateButtonListener)
        {
            updateButton.addActionListener(updateButtonListener);
        }

        public void addFindButtonListener(ActionListener findButtonListener)
        {
            findButton.addActionListener(findButtonListener);
        }

        public void addMakeOrderButtonListener(ActionListener makeOrderButtonListener)
        {
            makeOrderButton.addActionListener(makeOrderButtonListener);
        }
}
