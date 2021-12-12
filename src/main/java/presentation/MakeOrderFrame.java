package presentation;

import businessLogicLayer.ClientController;
import businessLogicLayer.OrderController;
import businessLogicLayer.ProductController;
import model.ClientOrder;
import model.OrderProducts;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;

/**
 * Fereastra in care introducem o noua comanda
 * Created by d.rona on 03.05.2019.
 */
public class MakeOrderFrame extends JFrame implements ActionListener {

    //PANELS
    private  JPanel panel1;
    private  JPanel panel2;
    private  JPanel panel3;
    private  JPanel panel4;

    //JTABELS
    private JTable tableClients;
    private JTable tableProducts;
    private JTable tableOrders;

    //SETTERS AND GETTERS
    public JTable getTableOrders() {
        return tableOrders;
    }

    public void setTableOrders(JTable tableOrders) {
        this.tableOrders = tableOrders;
    }

    public JTable getTableClients() {
        return tableClients;
    }

    public void setTableClients(JTable tableClients) {
        this.tableClients = tableClients;
    }

    public JTable getTableProducts() {
        return tableProducts;
    }

    public void setTableProducts(JTable tableProducts) {
        this.tableProducts = tableProducts;
    }

    //BUTTONS
    private final JButton makeOrderButton;
    private final JButton back;
    private JButton searchButton;
    private JButton extractButton;

    public MakeOrderFrame()
    {
        super("MAKE ORDER");
        setLayout(new FlowLayout());
        this.setSize(1900, 650);
        this.setLocationRelativeTo(null);  // to center it

        // PANEL
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setBounds(0,0,701,500);

        panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setBounds(750,0,601,500);

        panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        panel3.setBounds(1370,0,1890,500);

        panel4 = new JPanel();
        panel4.setLayout(new GridBagLayout());
        panel4.setBounds(0,502,0,120);


        //BUTTONS
        searchButton = new JButton();
        searchButton.setText("Search");

        extractButton = new JButton();
        extractButton.setText("Extract");

        back = new JButton();
        back.setText("Back");

        makeOrderButton = new JButton();
        makeOrderButton.setText("Order");

        //JLABELS
        JLabel quantityLabel = new JLabel("Introduceti cantitatea dorita: ");

        //JTEXTFIELDS
        final JTextField quantityText = new JTextField(10);
        final JTextField searchText = new JTextField(10);

        //JTABELS
        //CLIENTS
        tableClients = new JTable();
        ClientController clientController = new ClientController();
        clientController.refreshTableData(new LinkedList<>(clientController.viewAllClients()), this.getTableClients());

        tableClients.setPreferredScrollableViewportSize(new Dimension(700,500));
        tableClients.setFillsViewportHeight(true);

        // punem tableClients in scroll pane
        JScrollPane scrollPaneClients = new JScrollPane(tableClients);

        //PRODUCTS
        tableProducts = new JTable();
        ProductController productController = new ProductController();
        productController.refreshTableData(new LinkedList<>(productController.viewAllProducts()), this.getTableProducts());

        tableProducts.setPreferredScrollableViewportSize(new Dimension(600,500));
        tableProducts.setFillsViewportHeight(true);

        // punem tableProducts in scroll pane
        JScrollPane scrollPaneProducts = new JScrollPane(tableProducts);

        //ORDERS
        tableOrders = new JTable();
        OrderController orderController = new OrderController();
        orderController.showOrdersInTable(tableOrders); // luam tabelul pe care il avem si il transmitem ca param la metoda din controller


        tableOrders.setPreferredScrollableViewportSize(new Dimension(500,500));
        tableOrders.setFillsViewportHeight(true);

        // punem tableProducts in scroll pane
        JScrollPane scrollPaneOrders = new JScrollPane(tableOrders);

        // ADDING PANELS TO FRAME
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);

        // ADDING COMPONENTS TO PANELS
        panel1.add(scrollPaneClients);
        panel2.add(scrollPaneProducts);
        panel3.add(scrollPaneOrders);
        panel4.add(quantityLabel);
        panel4.add(quantityText);
        panel4.add(makeOrderButton);
//        panel4.add(searchText);
//        panel4.add(searchButton);
//        panel4.add(extractButton);
        panel4.add(back);


        // BACK BUTTON ACTION
        MakeOrderFrame makeOrderFrameThis = this;
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame mainFrame = new MainFrame();
                makeOrderFrameThis.setVisible(false);
            }
        });

        MakeOrderFrame orderView = this;
        // MAKE ORDER ACTION
        makeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               try {
                   OrderController orderController = new OrderController();

                   ClientOrder clientOrder = new ClientOrder();
                   OrderProducts orderProducts = new OrderProducts();

                   int rowClients = getTableClients().getSelectedRow();
                   int rowProducts = getTableProducts().getSelectedRow();

                   String[] cellsClients = new String[5];

                   for (int i = 0; i < cellsClients.length; i++) {
                       if (getTableClients().getModel().getValueAt(rowClients, i) != null) {
                           cellsClients[i] = getTableClients().getModel().getValueAt(rowClients, i).toString();
                       }
                   }

                   String[] cellsProducts = new String[4];

                   for (int i = 0; i < cellsProducts.length; i++) {
                       if (getTableProducts().getModel().getValueAt(rowProducts, i) != null) {
                           cellsProducts[i] = getTableProducts().getModel().getValueAt(rowProducts, i).toString();
                       }
                   }

                   clientOrder.setClientId(Integer.parseInt(cellsClients[2]));
                   clientOrder.setStatus("Available");  // DE MODIFICAT


                   orderProducts.setProductId(Integer.parseInt(cellsProducts[1]));
                   orderProducts.setQuantity(Integer.parseInt(quantityText.getText()));

                   // produsul nostru
                   Product product = new Product();
                   product.setId(Integer.parseInt(cellsProducts[1]));
                   product.setQuantityStock(orderController.checkStockAvailability(product).getQuantityStock()); ;

                   // cantiatea dorita
                   Integer quantityWanted = Integer.parseInt(quantityText.getText());

                   // verificam daca e understock
                   if(Integer.valueOf(product.getQuantityStock()).compareTo(quantityWanted) >= 0)
                   {

                       try
                       {
                          Integer clientOrderID = orderController.insertClientOrderFromFrame(clientOrder);
                           orderController.insertOrderProductsFromFrame(orderProducts, clientOrderID);
                       }
                       catch (Exception ex)
                       {
                           JOptionPane.showInputDialog(ex.getMessage());
                       }


                       //decrementam produsul dupa plasarea comenzii
                       product.setQuantityStock(product.getQuantityStock() - quantityWanted);

                      //setam in baza de date
                       orderController.setDecrementedQuantityStock(product);

                       //dam refresh tabelului
                       productController.refreshTableData(new LinkedList<>(productController.viewAllProducts()), orderView.getTableProducts());
                       panel2.repaint();

                       orderController.showOrdersInTable(orderView.getTableOrders());
                       panel3.repaint();

                   }
                   else
                   {
                       throw new Exception("Under stock") ;
                   }

                   JOptionPane.showMessageDialog(null,"Order registered");
                   orderController.showOrdersInTable(orderView.getTableOrders());
                   panel3.repaint();


               }catch (Exception e1)
               {
                   JOptionPane.showMessageDialog(null,e1.getMessage());
                   JOptionPane.showMessageDialog(null,"Order not registered");
                   e1.printStackTrace();
               }
            }
        });



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
