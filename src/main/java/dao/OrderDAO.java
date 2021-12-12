package dao;

import model.ClientOrder;
import model.OrderProducts;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * In aceasta clasa accesam datele comenzilor din baza de date (CRUD)
 * Created by d.rona on 01.05.2019.
 */
public class OrderDAO {

    public Integer insertClientOrder(ClientOrder clientOrder) throws Exception {
        String addClientOrderStatemenet = "INSERT INTO clientorder(id, status, clientId) VALUES(?,?,?)";

        Integer clientOrderID = 0;
        Connection conn = ConnectionFactory.getConnecion();

        if (conn != null) {
            // daca avem conexiune
            PreparedStatement st1 = null;
            try {
                // initializam query
                st1 = conn.prepareStatement(addClientOrderStatemenet, Statement.RETURN_GENERATED_KEYS);

                //adaugam parametrii la query
                st1.setInt(1, clientOrder.getId());
                st1.setString(2, clientOrder.getStatus());
                st1.setInt(3, clientOrder.getClientId());

                // executam query
               Integer updateRows =  st1.executeUpdate();
                ResultSet rs = st1.getGeneratedKeys();

                if (rs.next()) {
                    clientOrderID = ((Long)rs.getLong(1)).intValue();
                }

                if (updateRows <= 0) {

                    throw new Exception("Can't inset clientOrderID");
                }


            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        return clientOrderID;

    }

    public void insertOrderProducts(OrderProducts orderProducts, Integer clientOrderID) {
        String addOrderProductsStatemenet = "INSERT INTO orderproducts(id, clientOrderId, productId, quantity) VALUES(?,?,?,?)";

        Connection conn = ConnectionFactory.getConnecion();

        if (conn != null) {
            // daca avem conexiune
            PreparedStatement st2 = null;
            try {
                // initializam query
                st2 = conn.prepareStatement(addOrderProductsStatemenet);

                //adaugam parametrii la query
                st2.setInt(1, orderProducts.getId());
                st2.setInt(2, clientOrderID);
                st2.setInt(3, orderProducts.getProductId());
                st2.setInt(4, orderProducts.getQuantity());

                // executam query
                st2.execute();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<ClientOrder> viewAllClientOrders(String filter) {
        List<ClientOrder> clientsList = new ArrayList<ClientOrder>();

        String viewAllClientOrdersStatement = "SELECT * FROM clientOrder where " + filter;

        Connection conn = ConnectionFactory.getConnecion();

        if (conn != null) {
            Statement st = null;
            ResultSet rs = null;

            try {
                // create a statement to be used to get the results
                st = conn.createStatement();

                //execute the query and get the result set
                rs = st.executeQuery(viewAllClientOrdersStatement);
                while (rs.next()) {

                    ClientOrder clientOrder = new ClientOrder();

                    clientOrder.setId(rs.getInt(1));
                    clientOrder.setStatus(rs.getString(2));
                    clientOrder.setClientId(rs.getInt(3));

                    clientsList.add(clientOrder);

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return clientsList;

    }

    public List<OrderProducts> viewAllOrderProducts(String filter) {
        List<OrderProducts> orderProductsList = new ArrayList<OrderProducts>();

        String viewAllOrderProductsStatement = "SELECT * FROM orderproducts where " + filter;

        Connection conn = ConnectionFactory.getConnecion();

        if (conn != null) {
            Statement st = null;
            ResultSet rs = null;

            try {
                // create a statement to be used to get the results
                st = conn.createStatement();

                //execute the query and get the result set
                rs = st.executeQuery(viewAllOrderProductsStatement);
                while (rs.next()) {

                    OrderProducts orderProducts = new OrderProducts();

                    orderProducts.setId(rs.getInt(1));
                    orderProducts.setClientOrderId(rs.getInt(2));
                    orderProducts.setProductId(rs.getInt(3));
                    orderProducts.setQuantity(rs.getInt(4));

                    orderProductsList.add(orderProducts);

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return orderProductsList;
    }

    public Integer getClientOrderIDFromClientOrder(Integer clientID) {
        Integer clientOrderID = new Integer(0);
        String addClientOrderStatemenet = "SELECT id FROM clientorder where clientId = " + clientID.intValue();

        Connection conn = ConnectionFactory.getConnecion();

        if (conn != null) {
            Statement st = null;
            ResultSet rs = null;

            try {
                // create a statement to be used to get the results
                st = conn.createStatement();

                //execute the query and get the result set
                rs = st.executeQuery(addClientOrderStatemenet);

                clientOrderID.valueOf(rs.getInt(1));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return clientOrderID;
    }

}
