package presentationLayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa ProductsButtonListener este utilizata pentru a oferi acces la interfata
 * corespunzatoare produselor.
 */

public class ProductsButtonListener implements ActionListener {

    private View view;

    public ProductsButtonListener(View view)
    {
        this.view=view;
    }

    /**
     * Cand butonul este apasat, toate componentele sunt eliminate, fiind adaugate
     * tabelul cu produsele, butoanele cu operatiile posibile, cat si etichetele
     * si campurile pentru adaugarea datelor.
     * @param e evenimentul de apasare al butonului
     */

    public void actionPerformed(ActionEvent e) {
        view.getContentPane().removeAll();
        view.operationsButtonPane.removeAll();
        view.operationsButtonPane.add(view.insertButton);
        view.operationsButtonPane.add(view.updateButton);
        view.operationsButtonPane.add(view.deleteButton);
        view.operationsButtonPane.add(view.findButton);
        view.getContentPane().add(view.operationsButtonPane, BorderLayout.PAGE_START);
        view.tablePane.removeAll();
        view.tablePane.add(view.nameLabel);
        view.tablePane.add(view.nameTextField);
        view.tablePane.add(view.brandLabel);
        view.tablePane.add(view.brandTextField);
        view.tablePane.add(view.priceLabel);
        view.tablePane.add(view.priceTextField);
        view.tablePane.add(view.quantityLabel);
        view.tablePane.add(view.quantityTextField);
        view.tablePane.add(view.productsScrollPane);
        view.getContentPane().add(view.tablePane,BorderLayout.CENTER);
        view.getContentPane().add(view.backButtonPane,BorderLayout.PAGE_END);
        view.repaint();
        view.revalidate();
    }
}