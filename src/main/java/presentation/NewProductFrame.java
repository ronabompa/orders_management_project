package presentation;

import businessLogicLayer.ProductController;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Fereastra in care introducem un nou produs
 * Created by d.rona on 03.05.2019.
 */
public class NewProductFrame extends JFrame implements ActionListener {

    private JPanel panel1;

    private JLabel nameProductLabel;
    private JLabel priceProductLabel;
    private JLabel quantityStockLabel;

    public NewProductFrame()
    {
        super("ADD NEW PRODUCT");
        setLayout(new FlowLayout());
       // this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);  // to center it

        // PANEL
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1,BoxLayout.PAGE_AXIS));

        //LABELS
        nameProductLabel = new JLabel("Introduceti numele noului produs");
        priceProductLabel = new JLabel("Introduceti pretul noului produs");
        quantityStockLabel = new JLabel("Introduceti cantitatea in stoc a noului produs");

        // TEXTFIELDS
        final JTextField nameProductTextField = new JTextField(30);
        final JTextField priceProductTextField = new JTextField(30);
        final JTextField quantityStockTextField = new JTextField(30);

        //BUTTONS
        final JButton addNewProductButton = new JButton();
        addNewProductButton.setText("Adauga");

        // ADDING PANELS TO FRAME
        this.add(panel1);

        // ADDING COMPONENTS TO PANELS
        panel1.add(nameProductLabel);
        panel1.add(nameProductTextField);

        panel1.add(priceProductLabel);
        panel1.add(priceProductTextField);

        panel1.add(quantityStockLabel);
        panel1.add(quantityStockTextField);

        panel1.add(addNewProductButton);

        NewProductFrame newProductFrame = this;

        addNewProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    Product product = new Product();

                    ProductController productController = new ProductController();
                    product.setName(nameProductTextField.getText());
                    product.setPrice(Float.parseFloat(priceProductTextField.getText()));
                    product.setQuantityStock(Integer.parseInt(quantityStockTextField.getText()));

                    productController.addNewProductFromFrame(product);

                    ProductsFrame productsFrame = productController.getProductsFrame();

                    //transmitem tablul nostru ca parametru in productController, pentru a adauga noii producti si apoi il setam
                    productController.refreshTableData(new LinkedList<>(productController.viewAllProducts()), productsFrame.getTableProducts());

                    //repaintPanel
                    JPanel panel1New = productsFrame.getPanel1();
                    panel1New.repaint();
                    productsFrame.setPanel1(panel1New);

                    JOptionPane.showMessageDialog(null,"Success");


                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Esec");

                    ex.printStackTrace();
                }
                finally {
                    newProductFrame.setVisible(false);
                }
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
