package presentationLayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa ClientsButtonListener este utilizata pentru a accesa interfata corespunzatoare
 * clientilor.
 */

public class ClientsButtonListener implements ActionListener {
    private View view;

    public ClientsButtonListener(View view)
    {
        this.view=view;
    }

    /**
     * Cand butonul este apasat se vor elimina componentele din scena, urmand a fi
     * adaugat tabelul cu clienti, butoanele cu operatiile posibile, cat si etichetele
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
        view.tablePane.add(view.firstNameLabel);
        view.tablePane.add(view.firstNameTextField);
        view.tablePane.add(view.lastNameLabel);
        view.tablePane.add(view.lastNameTextField);
        view.tablePane.add(view.addressLabel);
        view.tablePane.add(view.addressTextField);
        view.tablePane.add(view.emailLabel);
        view.tablePane.add(view.emailTextField);
        view.tablePane.add(view.phoneNumberLabel);
        view.tablePane.add(view.phoneNumberTextField);
        view.tablePane.add(view.clientsScrollPane);
        view.getContentPane().add(view.tablePane,BorderLayout.CENTER);
        view.getContentPane().add(view.backButtonPane,BorderLayout.PAGE_END);
        view.repaint();
        view.revalidate();
    }
}
