package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Client;

/**
 * In aceasta clasa accesam datele clientilor din baza de date (CRUD)
 */
public class ClientDAO {

    public void addClient(Client client)
	{
        String addClientStatement = "INSERT INTO client(id, name, address, email, age) VALUES(?,?,?,?,?)";

        Connection conn = ConnectionFactory.getConnecion();

		if(conn != null)
		{
			// daca avem conexiune
			PreparedStatement st = null;
			  try
			 {
                 // initializam query
                 st = conn.prepareStatement(addClientStatement);

                 //adaugam parametrii la query
			     st.setInt(1, client.getId());
                 st.setString(2, client.getName());
                 st.setString(3, client.getAddress());
                 st.setString(4, client.getEmail());
                 st.setInt(5, client.getAge());

                 // executam query
                 st.execute();
			 }
			 catch (SQLException ex)
             {
                 ex.printStackTrace();
             }
		}
	}

	public void editClient(Client client)
	{
        String editClientStatement = "UPDATE client SET name = ?, address = ?, email = ?, age = ? WHERE id = ?";

        Connection conn = ConnectionFactory.getConnecion();

        if(conn != null)
        {
            PreparedStatement st = null;
            try
            {
                // initializam query
                st = conn.prepareStatement(editClientStatement);

                //adaugam parametrii la query
                st.setString(1, client.getName());
                st.setString(2, client.getAddress());
                st.setString(3, client.getEmail());
                st.setInt(4, client.getAge());
                st.setInt(5, client.getId());

                // executam query
                st.execute();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
	}

	public void deleteClient(Client client)
	{
        String deleteClientStatement = "DELETE FROM client WHERE id = ?";

        Connection conn = ConnectionFactory.getConnecion();

        if(conn != null)
        {
            PreparedStatement st = null;
            try
            {
                // initializam query
                st = conn.prepareStatement(deleteClientStatement);

                //adaugam parametrii la query
                st.setInt(1, client.getId());

                // executam query
                st.execute();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
	}

	public static List<Client> viewAllClient(String filter)
	{
        List<Client> listClients = new ArrayList<Client>();

        String viewAllClientStatement = "SELECT * FROM client where " + filter;

        Connection conn = ConnectionFactory.getConnecion();
        if(conn != null)
        {
            Statement st = null;
            ResultSet rs = null;

            try {

                // create a statement to be used to get the results
                st = conn.createStatement();
                //execute the query and get the result set
                rs = st.executeQuery(viewAllClientStatement);
                while (rs.next()) {

                    Client client = new Client();

                    client.setId(rs.getInt(1));
                    client.setName(rs.getString(2));
                    client.setAddress(rs.getString(3));
                    client.setEmail(rs.getString(4));
                    client.setAge(rs.getInt(5));

                    listClients.add(client);

                }

            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
            /*finally {
                try
                {
                    rs.close();
                    st.close();
                    conn.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }

            }*/
        }

        return listClients;
	}



			
}
