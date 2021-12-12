package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Fereastra principala, din care putem sa le accesam pe celelalte: Client, Products & Orders
 */
public class MainFrame extends JFrame implements ActionListener {

    private JPanel panel1;

    private JButton clientsButton;
    private JButton productsButton;
    private JButton ordersClientButton;

    // MAIN FRAME
    public MainFrame()
    {
        super("MAIN WINDOW");
        setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(360, 100);
        this.setLocationRelativeTo(null);  // to center it

        //PANEL
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        //BUTTONS
        clientsButton = new JButton();
        clientsButton.setText("Clients");

        productsButton = new JButton();
        productsButton.setText("Products");

        ordersClientButton = new JButton();
        ordersClientButton.setText("Make an order");

        // ADD PANEL TO FRAME
        add(panel1);

        // ADD COMPONENTS TO PANEL
        panel1.add(clientsButton);
        panel1.add(productsButton);
        panel1.add(ordersClientButton);

        //ACTION LISTENERS

        clientsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // + button de back in clientsVisible
                ClientsFrame clientsFrame = new ClientsFrame();
                clientsFrame.setVisible(true);


            }
        });

        productsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // + button de back in productsVisible
                ProductsFrame productsFrame = new ProductsFrame();
                productsFrame.setVisible(true);

            }
        });

        ordersClientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // set visible
                // + button de back in orderClient

                MakeOrderFrame makeOrderFrame = new MakeOrderFrame();
                makeOrderFrame.setVisible(true);

            }
        });
    }


    public void actionPerformed(ActionEvent e) {

    }
}
