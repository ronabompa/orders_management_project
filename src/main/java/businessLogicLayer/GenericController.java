package businessLogicLayer;

import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

/** Aceasta clasa contine metode de reflection pentru a crea un tabel si a da refresh
 * Created by d.rona on 09.05.2019.
 */
public class GenericController {

    /**
     *
     * @param objectList
     * @return
     */
    public JTable createTable(List<Object> objectList)
    {
        JTable tabelGeneric = new JTable();
        refreshTableData(objectList,tabelGeneric);

        return tabelGeneric;
    }

    public void refreshTableData(List<Object> objectList, JTable jTable)
    {
        String tableName = objectList.get(0).getClass().getName();
        Method[] columnNames = objectList.get(0).getClass().getMethods();

        List<String> columnNamesString = new LinkedList<>();

        for (int i = 0; i < columnNames.length; i++)
        {
            // ne uitam daca avem o metoda de tip get

            if(columnNames[i].getName().contains("get") && !columnNames[i].getName().equals("getClass"))
            {
                // din acea metoda, scoate get si ramane doar numele variabilei
                columnNamesString.add(columnNames[i].getName().replace("get",""));
            }
        }
        //ii punem intr-un obiect
        Object[][] allObjects = new Object[objectList.size()][columnNamesString.size()];

        // afisam clientii din lista noastra in tabel
        for (int i = 0; i < objectList.size(); i++)
        {
            Object object = objectList.get(i);
            Method[] methods = object.getClass().getMethods();

            int counter = 0;
            for (int j = 0; j < methods.length; j++) {
                Method method = methods[j];
                //iau metodele pe rand, le apelez si pun rezultatul
                if (method.getName().contains("get")  && !method.getName().equals("getClass")) {
                    try {
                        allObjects[i][counter++] = method.invoke(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // luam tabelul pe care il avem
        DefaultTableModel tableModel = new DefaultTableModel(allObjects, columnNamesString.toArray(new String[columnNamesString.size()]));
        jTable.setModel(tableModel);

    }
}
