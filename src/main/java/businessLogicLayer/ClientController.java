package businessLogicLayer;

import dao.ClientDAO;
import model.Client;
import presentation.ClientsFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *  Prin aceasta clasa, interfata pentru Clients, comunica cu datele clientilor din DataBase
 */
public class ClientController extends GenericController {

        static ClientsFrame clientsFrame;

    // SETTERS & GETTERS
    public ClientsFrame getClientsFrame() {
       return clientsFrame;
    }

    public void setClientsFrame(ClientsFrame clientsFrame) {
        this.clientsFrame = clientsFrame;
    }


    // VIEW ALL CLIENTS
    public List<Client> viewAllClients()
    {
        return ClientDAO.viewAllClient("id <> -1");
    }

    public List<Client> viewAllClientsWithFilter(String filter)
    {
        return ClientDAO.viewAllClient(filter);
    }


    /**
     * Adauga un client in databse cu date introduse de utilizator
     * @param client
     */
    //ADD
    public void addNewClientFromFrame(Client client)
    {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.addClient(client);
    }

    //EDIT
    public void editClientFromFrame(Client client)
    {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.editClient(client);
    }

    //DELETE
    public void deleteClientFromFrame(Client client)
    {
        ClientDAO clientDAO = new ClientDAO();
        clientDAO.deleteClient(client);
    }


    /**
     * Afiseaza clientii din baza de date in tabelul de clienti din frame
     * @param clientsTable
     * @return table of clients (updated)
     */
    public void showClientsInTable(JTable clientsTable)
    {
        // luam clientii din baza de date si ii punem intr-o lista
        List<Client> clientList = this.viewAllClients();

       //ii punem intr-un obiect
        Object[][] allClients = new Object[clientList.size()][5];

        // afisam clientii din lista noastra in tabel
        for (int i = 0; i < clientList.size(); i++) {
            Client client = clientList.get(i);
            allClients[i] = client.toArray();
        }

        // luam tabelul pe care il avem
        DefaultTableModel tableModel = new DefaultTableModel(allClients, new Object[5]);
        clientsTable.setModel(tableModel);

    }


}


