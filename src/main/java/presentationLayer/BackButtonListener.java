package presentationLayer;

import businessLayer.ClientLogic;
import businessLayer.ProductLogic;
import businessLayer.TablePopulator;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

/**
 * Clasa BackButtonListener este utilizate pentru a implementa functionalitatea
 * butonului de back din interfata.
 */

public class BackButtonListener implements ActionListener {

    private View view;
    private ClientLogic clientLogic;
    private ProductLogic productLogic;

    public BackButtonListener(View view,ClientLogic clientLogic,ProductLogic productLogic)
    {
        this.view=view;
        this.clientLogic=clientLogic;
        this.productLogic=productLogic;
    }

    /**
     * Cand butonul este apasat, toate tabelele vor fi actualizate, iar din fereastra se
     * vor elemina toate elementele, fiind adaugate butoanele pentru selectarea
     * categoriei dorite.
     * @param e evenimentul de apasare al butonului
     */

    public void actionPerformed(ActionEvent e) {
        ((DefaultTableModel)view.clientsTable.getModel()).setColumnCount(0);
        ((DefaultTableModel) view.clientsTable.getModel()).setRowCount(0);
        TablePopulator.populateTable(view.clientsTable, clientLogic.selectAll().parallelStream().map(x -> (Object) x).collect(Collectors.toList()));
        ((DefaultTableModel)view.productsTable.getModel()).setColumnCount(0);
        ((DefaultTableModel) view.productsTable.getModel()).setRowCount(0);
        TablePopulator.populateTable(view.productsTable, productLogic.selectAll().parallelStream().map(x -> (Object) x).collect(Collectors.toList()));
        view.getContentPane().removeAll();
        view.getContentPane().add(view.tablesButtonPane, BorderLayout.PAGE_START);
        view.repaint();
        view.revalidate();
    }
}