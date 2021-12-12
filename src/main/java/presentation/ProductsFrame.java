package presentation;

import businessLogicLayer.ProductController;
import businessLogicLayer.ReportUtil;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Interfata grafica pentru produse
 * Created by d.rona on 03.05.2019.
 */
public class ProductsFrame extends JFrame implements ActionListener {

    private JPanel panel1;

    public JPanel getPanel1() {
        return panel1;
    }

    public void setPanel1(JPanel panel1) {
        this.panel1 = panel1;
    }

    private JPanel panel2;

    private JTable tableProducts;

    public JTable getTableProducts() {
        return tableProducts;
    }

    public void setTableProducts(JTable tableProducts) {
        this.tableProducts = tableProducts;
    }

    private JButton addProductButton;
    private JButton editProductButton;
    private JButton deleteProductButton;
    private JButton viewAllProductsButton;
    private JButton searchButton;
    private JButton extractButton;
    private JButton back;



    public ProductsFrame()
    {
        super("PRODUCTS");
        setLayout(new FlowLayout());
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
        addProductButton = new JButton();
        addProductButton.setText("add Product");

        editProductButton = new JButton();
        editProductButton.setText("edit Product");

        deleteProductButton = new JButton();
        deleteProductButton.setText("delete Product");

        viewAllProductsButton = new JButton();
        viewAllProductsButton.setText("view all Products");

        searchButton = new JButton();
        searchButton.setText("Search");

        extractButton = new JButton();
        extractButton.setText("Extract");

        back = new JButton();
        back.setText("Back");

        //JTEXTFIELD
        final JTextField searchText = new JTextField(10);

        tableProducts = new JTable();
        tableProducts.setPreferredScrollableViewportSize(new Dimension(700,480));
        tableProducts.setFillsViewportHeight(true);

        // punem tableProducts in scroll pane
        JScrollPane scrollPane = new JScrollPane(tableProducts);

        // ADDING PANELS TO FRAME
        add(panel1);
        add(panel2);

        // ADDING COMPONENTS TO PANELS
        panel1.add(scrollPane);

        panel2.add(addProductButton);
        panel2.add(editProductButton);
        panel2.add(deleteProductButton);
        panel2.add(viewAllProductsButton);
        panel2.add(searchText);
        panel2.add(searchButton);
        panel2.add(extractButton);
        panel2.add(back);

        ProductsFrame productsView = this; // pastram intr-o variabila "this"

        ProductController productController = new ProductController();
        productController.setProductsFrame(productsView);
        productController.refreshTableData(new LinkedList<>(productController.viewAllProducts()), productsView.getTableProducts());


        // ADD BUTTON ACTION
        addProductButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                NewProductFrame newProductFrame = new NewProductFrame();
                newProductFrame.setVisible(true);
            }
        });

        // EDIT BUTTON ACTION
        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = productsView.getTableProducts().getSelectedRow();

                String[] cells = new String[4];

                for(int i = 0; i < cells.length ; i++)
                {
                    if( productsView.getTableProducts().getModel().getValueAt(row,i) != null)
                    {
                        cells[i] = productsView.getTableProducts().getModel().getValueAt(row,i).toString();
                    }
                }

                Product product = new Product();
                ProductController productController = new ProductController();

                product.setName(cells[0]);
                product.setId(Integer.parseInt(cells[1]));
                product.setQuantityStock(Integer.parseInt(cells[3]));
                product.setPrice(Float.parseFloat(cells[2]));


                productController.editProductFromFrame(product);

                productController.refreshTableData(new LinkedList<>(productController.viewAllProducts()), productsView.getTableProducts());
                panel1.repaint();

            }
        });

        // DELETE BUTTON ACTION
        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ProductController productController = new ProductController();
                //transmitem tablul nostru ca parametru in productController, pentru a adauga noii producti si apoi il setam

                int row = productsView.getTableProducts().getSelectedRow();
                String idProductFromTable = productsView.getTableProducts().getModel().getValueAt(row,1).toString();

                Product product = new Product();

                product.setId(Integer.parseInt(idProductFromTable));
                productController.deleteProductFromFrame(product);

                productController.refreshTableData(new LinkedList<>(productController.viewAllProducts()), productsView.getTableProducts());

                panel1.repaint();
            }
        });

        // VIEW ALL BUTTON ACTION
        viewAllProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    ProductController productController = new ProductController();
                    //transmitem tablul nostru ca parametru in productController, pentru a adauga noii producti si apoi il setam
                    productController.refreshTableData(new LinkedList<>(productController.viewAllProducts()), productsView.getTableProducts());

                    panel1.repaint();


                }
            }
        });

        // EXTRACT BUTTON
        extractButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                try{

                    List<Product> productList = new LinkedList<Product>();

                    if(searchText.getText().isEmpty())
                    {
                        productList = productController.viewAllProducts();
                    }
                    else
                    {
                        productList = productController.viewAllProductsWithFilter(searchText.getText());
                    }

                    ReportUtil.deleteFileContent("FilterProducts.txt");
                    for(Product product : productList)
                    {
                        ReportUtil.writeLineToFile("FilterProducts.txt",product.getName()+ "\t" + product.getPrice() + "\t" + product.getQuantityStock() );
                    }

                    JOptionPane.showMessageDialog(null,"Success");
                }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Esec");
                    ex.printStackTrace();
                }

            }
        });

        // SEARCH BUTTON
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                productController.refreshTableData(new LinkedList<>( productController.viewAllProductsWithFilter(searchText.getText())), productsView.getTableProducts());
                panel1.repaint();

            }
        });

        // BACK BUTTON ACTION
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                productsView.setVisible(false);
            }
        });

        panel2.revalidate();
        panel2.updateUI();
        panel2.repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
