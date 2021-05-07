package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa este utilizate pentru a implementa fereastra in care utilizatorul alege
 * comanda, produsul si cantitatea pentru noua comanda.
 */

public class OrderView extends JFrame {
    protected JLabel quantityLabel=new JLabel("Quantity:");
    protected JTextField quantityTextField=new JTextField();
    protected JButton okButton=new JButton("OK");
    protected JPanel content=new JPanel();
    protected JTable clientsTable=new JTable();
    protected JTable productsTable=new JTable();
    protected JScrollPane clientsScrollPane=new JScrollPane(clientsTable);
    protected JScrollPane productsScrollPane=new JScrollPane(productsTable);
    private View view;

    public OrderView(View view)
    {
        this.view=view;
        content.setLayout(new FlowLayout(FlowLayout.CENTER,150,0));
        clientsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        clientsTable.setDefaultEditor(Object.class,null);
        clientsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        productsTable.setDefaultEditor(Object.class,null);
        productsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clientsScrollPane.setPreferredSize(new Dimension(700,300));
        productsScrollPane.setPreferredSize(new Dimension(700,300));
        content.add(clientsScrollPane);
        content.add(productsScrollPane);
        content.add(quantityLabel);
        content.add(quantityTextField);
        quantityTextField.setPreferredSize(new Dimension(100,20));
        content.add(okButton);
        this.setContentPane(content);
        this.setSize(1000,800);
        this.setVisible(false);
    }

    public void addOkButtonListener(ActionListener okButtonListener)
    {
        okButton.addActionListener(okButtonListener);
    }
}
