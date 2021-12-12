package presentation;

import businessLogicLayer.ClientController;
import businessLogicLayer.ReportUtil;
import model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Interfata grafica pentru clienti
 * Created by d.rona on 02.05.2019.
 */
public class ClientsFrame extends JFrame implements ActionListener {

    private JPanel panel1;

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    private JPanel panel2;

    private JTable tableClients;

    public  JTable getTableClients() {
        return tableClients;
    }

    public void setTableClients(JTable tableClients) {
        this.tableClients = tableClients;
    }

    private final JButton addClientButton;
    private final JButton editClientButton;
    private final JButton deleteClientButton;
    private final JButton viewAllClientsButton;
    private JButton searchButton;
    private JButton extractButton;
    private final JButton back;

    public ClientsFrame()
    {
        super("CLIENTS");
        this.setLayout(new FlowLayout());
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);  // to center it

        //PANELS
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBounds(0,0,800,500);
        panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setBounds(0,501,800,95);

        //BUTTONS
        addClientButton = new JButton();
        addClientButton.setText("add Client");

        editClientButton = new JButton();
        editClientButton.setText("edit Client");

        deleteClientButton = new JButton();
        deleteClientButton.setText("delete Client");

        viewAllClientsButton = new JButton();
        viewAllClientsButton.setText("view all Clients");

        searchButton = new JButton();
        searchButton.setText("Search");

        extractButton = new JButton();
        extractButton.setText("Extract");

        back = new JButton();
        back.setText("Back");

        //JTEXTFIELD
        final JTextField searchText = new JTextField(10);

        //TABLE

        ClientController clientController = new ClientController();
        List<Client> clientList = clientController.viewAllClients();

        String[] columnNames = {"id","Name","Address", "email", "age"};
        Object[][] allClients = new Object[clientList.size()][5];

        for (int i = 0; i < clientList.size(); i++) {
            Client client = clientList.get(i);
            allClients[i] = client.toArray();
        }

        tableClients = new JTable(allClients, columnNames);
        // tableClients.addColumn();

        tableClients.setPreferredScrollableViewportSize(new Dimension(700,480));
        tableClients.setFillsViewportHeight(true);

        // punem tableClients in scroll pane
        JScrollPane scrollPane = new JScrollPane(tableClients);

        // ADDING PANELS TO FRAME
        add(panel1);
        add(panel2);

        // ADDING COMPONENTS TO PANELS
        panel1.add(scrollPane);

        panel2.add(addClientButton);
        panel2.add(editClientButton);
        panel2.add(deleteClientButton);
        panel2.add(viewAllClientsButton);
        panel2.add(searchText);
        panel2.add(searchButton);
        panel2.add(extractButton);
        panel2.add(back);

        ClientsFrame clientsView = this; // pastram intr-o variabila "this"
        clientController.setClientsFrame(clientsView);
        clientController.refreshTableData(new LinkedList<>(clientController.viewAllClients()), clientsView.getTableClients());

        // si transmitem in client controller

        // ADD BUTTON ACTION
        addClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                NewClientFrame newClientFrame = new NewClientFrame();
                newClientFrame.setVisible(true);

            }
        });

        // EDIT BUTTON ACTION
        editClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = clientsView.getTableClients().getSelectedRow();
                //boolean gasitBug = false;

                String[] cells = new String[5];

                for(int i = 0; i < cells.length ; i++)
                {
                    if( clientsView.getTableClients().getModel().getValueAt(row,i) != null)
                    {
                        cells[i] = clientsView.getTableClients().getModel().getValueAt(row,i).toString();
                    }
                }

                for(int i = 0; i < cells.length ; i++)
                {
                    if(cells[i] == null)
                    {
                        cells[i] = "";
                    }
                }

                Client client = new Client();
                ClientController clientController = new ClientController();

                client.setId(Integer.parseInt(cells[2]));
                client.setName(cells[1]);
                client.setAddress(cells[0]);
                client.setEmail(cells[3]);
                client.setAge(Integer.parseInt(cells[4]));

                clientController.editClientFromFrame(client);
                clientController.refreshTableData(new LinkedList<>(clientController.viewAllClients()), clientsView.getTableClients());
                panel1.repaint();

            }
        });

        // DELETE BUTTON ACTION
        deleteClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ClientController clientController = new ClientController();
                //transmitem tablul nostru ca parametru in clientController, pentru a adauga noii clienti si apoi il setam

                int row = clientsView.getTableClients().getSelectedRow();
                String idClientFromTable = clientsView.getTableClients().getModel().getValueAt(row,2).toString();

                Client client = new Client();

                client.setId(Integer.parseInt(idClientFromTable));
                clientController.deleteClientFromFrame(client);

                clientController.refreshTableData(new LinkedList<>(clientController.viewAllClients()), clientsView.getTableClients());
                panel1.repaint();
            }
        });

        // VIEW ALL BUTTON ACTION
        viewAllClientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    ClientController clientController = new ClientController();
                    //transmitem tablul nostru ca parametru in clientController, pentru a adauga noii clienti si apoi il setam
                    clientController.refreshTableData(new LinkedList<>(clientController.viewAllClients()), clientsView.getTableClients());
                    panel1.repaint();

                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clientController.refreshTableData(new LinkedList<>( clientController.viewAllClientsWithFilter(searchText.getText())), clientsView.getTableClients());
                panel1.repaint();

            }
        });

        // EXTRACT BUTTON
        extractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                try{
                    List<Client> clientsList = new LinkedList<Client>();

                    if(searchText.getText().isEmpty())
                    {
                      clientsList = clientController.viewAllClients();
                    }
                    else
                    {
                        clientsList = clientController.viewAllClientsWithFilter(searchText.getText());
                    }
                    ReportUtil.deleteFileContent("FilterClients.txt");
                    for(Client  client : clientsList)
                    {
                        ReportUtil.writeLineToFile("FilterClients.txt", client.getName()+ "\t" + client.getEmail() + "\t" + client.getAddress() + "\t" + client.getAge() );
                    }


                    JOptionPane.showMessageDialog(null,"Success");
                }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Esec");
                    ex.printStackTrace();
                }

            }
        });

        // BACK BUTTON ACTION
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                clientsView.setVisible(false);
            }
        });

        panel2.revalidate();
        panel2.updateUI();
        panel2.repaint();



    }



    public void actionPerformed(ActionEvent e) {




    }
}
