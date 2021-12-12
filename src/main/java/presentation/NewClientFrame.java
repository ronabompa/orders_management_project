package presentation;

import businessLogicLayer.ClientController;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Fereastra in care introducem un nou client
 * Created by d.rona on 02.05.2019.
 */
public class NewClientFrame extends JFrame implements ActionListener {

    private JPanel panel1;

    private JLabel nameClientLabel;
    private JLabel addressClientLabel;
    private JLabel emailClientLabel;
    private JLabel ageClientLabel;

    public NewClientFrame()
    {
        super("ADD NEW CLIENT");
        setLayout(new FlowLayout());
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);  // to center it

        // PANEL
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.PAGE_AXIS));

        //LABELS
        nameClientLabel = new JLabel("Introduceti numele noului client");
        addressClientLabel = new JLabel("Introduceti adresa noului client");
        emailClientLabel = new JLabel("Introduceti emailul noului client");
        ageClientLabel = new JLabel("Introduceti varsta noului client");

        // TEXTFIELDS
        final JTextField nameClientTextField = new JTextField(30);
        final JTextField addressClientTextField = new JTextField(30);
        final JTextField emailClientTextField = new JTextField(30);
        final JTextField ageClientTextField = new JTextField(30);

        //BUTTONS
        final JButton addNewClientButton = new JButton();
        addNewClientButton.setText("Adauga");


        final JButton back = new JButton();
        back.setText("Back");

        // ADDING PANELS TO FRAME
        this.add(panel1);

        // ADDING COMPONENTS TO PANELS
        panel1.add(nameClientLabel);
        panel1.add(nameClientTextField);

        panel1.add(addressClientLabel);
        panel1.add(addressClientTextField);

        panel1.add(emailClientLabel);
        panel1.add(emailClientTextField);

        panel1.add(ageClientLabel);
        panel1.add(ageClientTextField);

        panel1.add(addNewClientButton);

        NewClientFrame newClientFrame = this;
        addNewClientButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                    Client client = new Client();
                    ClientController clientController = new ClientController();
                    client.setName(nameClientTextField.getText());
                    client.setAddress(addressClientTextField.getText());
                    client.setEmail(emailClientTextField.getText());
                    client.setAge(Integer.parseInt(ageClientTextField.getText()));

                    clientController.addNewClientFromFrame(client);

                    ClientsFrame clientsFrame = clientController.getClientsFrame();


                    clientController.refreshTableData(new LinkedList<>(clientController.viewAllClients()), clientsFrame.getTableClients());

                    //repaintPanel
                    JPanel panel1New = clientsFrame.getPanel1();
                    panel1New.repaint();
                    clientsFrame.setPanel1(panel1New);

                    JOptionPane.showMessageDialog(null,"Success");

                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Esec");

                    ex.printStackTrace();
                }
                finally {
                    newClientFrame.setVisible(false);

                }

            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
