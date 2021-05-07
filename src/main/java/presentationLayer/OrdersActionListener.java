package presentationLayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa OrdersActionListener este utilizata pentru a accesa interfata corespunzatoare
 * comenziilor.
 */

public class OrdersActionListener implements ActionListener {

    private View view;

    public OrdersActionListener(View view)
    {
        this.view=view;
    }

    /**
     * Cand butonul este apasat, toate componentele sunt elemininate, fiind adaugate
     * tabelul cu comenzile si butonul de creare al comenzilor
     * @param e evenimentul de apasare al butonului din interfata
     */

    public void actionPerformed(ActionEvent e) {
        view.getContentPane().removeAll();
        view.operationsButtonPane.removeAll();
        view.operationsButtonPane.add(view.makeOrderButton);
        view.getContentPane().add(view.operationsButtonPane, BorderLayout.PAGE_START);
        view.tablePane.removeAll();
        view.tablePane.add(view.ordersScrollPane);
        view.getContentPane().add(view.tablePane,BorderLayout.CENTER);
        view.getContentPane().add(view.backButtonPane,BorderLayout.PAGE_END);
        view.repaint();
        view.revalidate();
    }
}
