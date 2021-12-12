package businessLogicLayer;

import dao.ProductDAO;
import model.Product;
import presentation.ProductsFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *  Prin aceasta clasa, interfata pentru Products, comunica A cu produsele din baza noastra de date
 * Created by d.rona on 03.05.2019.
 */
public class ProductController extends GenericController{

     static ProductsFrame productsFrame;
    
    // GETTERS & SETTERS
    public ProductsFrame getProductsFrame() {
        return productsFrame;
    }

    public void setProductsFrame(ProductsFrame productsFrame) {
        this.productsFrame = productsFrame;
    }

    public List<Product> viewAllProducts()
    {
        return ProductDAO.viewAllProduct("id <> -1");
    }

    public List<Product> viewAllProductsWithFilter(String filter)
    {
        return ProductDAO.viewAllProduct(filter);
    }

    // ADD
    public void addNewProductFromFrame(Product product)
    {
        ProductDAO productDAO = new ProductDAO();
        productDAO.addProduct(product);
    }

    // EDIT
    public void editProductFromFrame(Product product)
    {
        ProductDAO productDAO = new ProductDAO();
        productDAO.editProduct(product);
    }

    // DELETE
    public void deleteProductFromFrame(Product product)
    {
        ProductDAO productDAO = new ProductDAO();
        productDAO.deleteProduct(product);
    }
    
    public void showProductsInTable(JTable productsTable)
    {
         List<Product> productList = this.viewAllProducts();

        Object[][] allProducts = new Object[productList.size()][5];

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            allProducts[i] = product.toArray();
        }

        // luam tabelul pe care il avem
        DefaultTableModel tableModel = new DefaultTableModel(allProducts, new Object[4]);
        productsTable.setModel(tableModel);

    }





}
