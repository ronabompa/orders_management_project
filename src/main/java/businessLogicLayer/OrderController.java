package businessLogicLayer;

import dao.OrderDAO;
import dao.ProductDAO;
import model.ClientOrder;
import model.OrderProducts;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.LinkedList;
import java.util.List;

/**
 *  Prin aceasta clasa, interfata pentru Orders, comunica cu datele clientOrder si OrderProduct din DataBase
 * Created by d.rona on 09.05.2019.
 */
public class OrderController extends GenericController {


    public Integer insertClientOrderFromFrame(ClientOrder clientOrder) throws Exception {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.insertClientOrder(clientOrder);
    }

    public void insertOrderProductsFromFrame(OrderProducts orderProducts, Integer clientOrderID)
    {
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.insertOrderProducts(orderProducts, clientOrderID);
    }

    public Product checkStockAvailability(Product product)
    {
        ProductDAO productDAO = new ProductDAO();
        Product productToReturn = new Product();

        productToReturn.setId((productDAO.getStockQuantity(product)).getId());
        productToReturn.setQuantityStock((productDAO.getStockQuantity(product)).getQuantityStock());

        return productToReturn;
    }

    public void setDecrementedQuantityStock(Product product)
    {
        ProductDAO productDAO = new ProductDAO();
        productDAO.setStockQuantity(product);
    }

    // VIEW ALL CLIENTORDERS
    public List<ClientOrder> viewAllClientOrders()
    {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.viewAllClientOrders("id <> -1");
    }

    // VIEW ALL CLIENTORDERS
    public List<OrderProducts> viewAllOrderProducts()
    {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.viewAllOrderProducts("id <> -1");
    }

    public void showOrdersInTable(JTable clientsTable)
    {
        // luam clientii din baza de date si ii punem intr-o lista
        List<ClientOrder> clientOrderList = this.viewAllClientOrders();
        List<OrderProducts> orderProductsList = this.viewAllOrderProducts();

        // unim 2 liste
        List<List<String>>  orderInfoListList = new LinkedList<>();

        for(ClientOrder clientOrder : clientOrderList)
        {
            boolean flag = false;

            List<String> rowOrderTabel = new LinkedList<String>();
            rowOrderTabel.add(clientOrder.getId() + "");  // id-ul Orderului
            rowOrderTabel.add(clientOrder.getClientId()+""); // id-ul Clientului

            for(OrderProducts orderProducts : orderProductsList)
            {
                if(orderProducts.getClientOrderId() == clientOrder.getId())
                {
                    rowOrderTabel.add(orderProducts.getProductId()+ "");
                    rowOrderTabel.add(orderProducts.getQuantity() + "");
                    flag = true;
                    break;
                }

            }

            if(flag)
            {
                orderInfoListList.add(rowOrderTabel);
            }

        }


        Object[][] allOrders = new String[orderInfoListList.size()][4];

        int i = 0;
        for (List<String> nestedList : orderInfoListList) {
            allOrders[i++] = nestedList.toArray(new String[nestedList.size()]);
        }

        String[] columnNames = {"Order ID" , "Client ID", "Product ID", "Porduct QUANTITY" };

        // luam tabelul pe care il avem
        DefaultTableModel tableModel = new DefaultTableModel(allOrders, columnNames);
        clientsTable.setModel(tableModel);

    }

}
