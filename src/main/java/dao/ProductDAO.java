package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * In aceasta clasa accesam datele produselor din baza de date (CRUD)
 * Created by d.rona on 01.05.2019.
 */
public class ProductDAO {

    public void addProduct(Product product)
    {
        String addProductStatement = "INSERT INTO product(id, name, price, quantityStock) VALUES(?,?,?,?)";

        Connection conn = ConnectionFactory.getConnecion();

        if(conn != null)
        {
            // daca avem conexiune
            PreparedStatement st = null;
            try
            {
                // initializam query
                st = conn.prepareStatement(addProductStatement);

                //adaugam parametrii la query
                st.setInt(1, product.getId());
                st.setString(2, product.getName());
                st.setFloat(3, product.getPrice());
                st.setInt(4, product.getQuantityStock());
                // executam query
                st.execute();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void editProduct(Product product)
    {
        String editProductStatement = "UPDATE product SET name = ?, price = ?, quantityStock = ? WHERE id = ?";

        Connection conn = ConnectionFactory.getConnecion();

        if(conn != null)
        {
            // daca avem conexiune
            PreparedStatement st = null;
            try
            {
                // initializam query
                st = conn.prepareStatement(editProductStatement);

                //adaugam parametrii la query
                st.setString(1, product.getName());
                st.setFloat(2, product.getPrice());
                st.setInt(3, product.getQuantityStock());
                st.setInt(4, product.getId());

                st.execute();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public void deleteProduct(Product product)
    {
        String deleteProductStatement = "DELETE FROM product WHERE id = ?";

        Connection conn = ConnectionFactory.getConnecion();

        if(conn != null)
        {
            PreparedStatement st = null;
            try
            {
                // initializam query
                st = conn.prepareStatement(deleteProductStatement);

                //adaugam parametrii la query
                st.setInt(1, product.getId());

                // executam query
                st.execute();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    public static List<Product> viewAllProduct(String filter)
    {
        List<Product> productsList = new ArrayList<Product>();

        String viewAllProductStatement = "SELECT * FROM product where " + filter;

        Connection conn = ConnectionFactory.getConnecion();

        if(conn != null)
        {
            Statement st = null;
            ResultSet rs = null;

            try
            {
                // create a statement to be used to get the results
                st = conn.createStatement();

                //execute the query and get the result set
                rs = st.executeQuery(viewAllProductStatement);
                while (rs.next()) {

                    Product product = new Product();

                    product.setId(rs.getInt(1));
                    product.setName(rs.getString(2));
                    product.setPrice(rs.getFloat(3));
                    product.setQuantityStock(rs.getInt(4));

                   productsList.add(product);

                }
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }

        return productsList;

    }

   public Product getStockQuantity(Product product)
    {

        String getQuantityStatement = "SELECT quantityStock FROM product WHERE id = " + product.getId();

        Connection conn = ConnectionFactory.getConnecion();

        if(conn != null)
        {
            Statement st = null;
            ResultSet rs = null;

            try
            {
                // create a statement to be used to get the results
                st = conn.createStatement();

                //execute the query and get the result set
                rs = st.executeQuery(getQuantityStatement);
                rs.next();
                product.setQuantityStock(rs.getInt(1));

            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }

        return product;
    }

    public void setStockQuantity(Product product)
    {
        String editProductStatement = "UPDATE product SET quantityStock = ? WHERE id = ?";

        Connection conn = ConnectionFactory.getConnecion();

        if(conn != null)
        {
            // daca avem conexiune
            PreparedStatement st = null;
            try
            {
                // initializam query
                st = conn.prepareStatement(editProductStatement);

                //adaugam parametrii la query

                st.setInt(1, product.getQuantityStock());
                st.setInt(2, product.getId());

                st.execute();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
        }
    }



}
