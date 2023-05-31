package View.Listener;

import Business.*;
import Model.*;
import View.RegisterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//OBSERVER PATTERN
//ConcreteObserver
public class RegisterListener implements ActionListener {

    RegisterPanel registerPanel;

    public RegisterListener(RegisterPanel registerPanel) {
        this.registerPanel = registerPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();


        if ("newAccount".equals(cmd)) {

            try {
                IClienteFactory factoryMethodProvider = new IClienteFactory();

                ICliente cliente = factoryMethodProvider.getICliente("CLIENTE");
                cliente.setUsername(registerPanel.getUsername());
                cliente.setPassword(HashClass.getInstance().encrypt(registerPanel.getPassword()));
                cliente.setName(registerPanel.getname());
                cliente.setSurname(registerPanel.getSurname());
                cliente.setEmail(registerPanel.getEmail());
                cliente.setPhoneNumber(registerPanel.getPhoneNumber());
                cliente.setAge(registerPanel.getAge());
                cliente.setResidence(registerPanel.getResidence());
                cliente.setJob(registerPanel.getJob());

                String[] options = {"SMS","PUSH", "EMAIL"};
                int ras = JOptionPane.showOptionDialog(null, "Seleziona canale di comunicazione preferito: ", "Canale Preferito", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                Cliente.CanalePreferito canalePreferito = Cliente.CanalePreferito.EMAIL;
                switch (ras) {
                    case 0 -> canalePreferito = Cliente.CanalePreferito.SMS;
                    case 1 -> canalePreferito = Cliente.CanalePreferito.PUSH;
                }

                cliente.setIdPuntoVendita(SessionManager.getInstance().getCurrentPuntoVendita().getIdPuntoVendita());
                cliente.setCanalePreferito(canalePreferito);

                NewUtenteResponse res = ClienteBusiness.getInstance().newAccount((Cliente) cliente);

                String reason = res.getMessage(); // da mostrare all'utente
                JOptionPane.showMessageDialog(registerPanel, reason, "Creazione utente", res.getjOptionPane());

                registerPanel.clearFields();
            }
             catch (NumberFormatException n) {
                JOptionPane.showMessageDialog(registerPanel,"Inserire numero nel campo \"età\"","Errore età", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
