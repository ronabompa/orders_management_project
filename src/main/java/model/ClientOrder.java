package model;

import java.sql.Date;

/**
 * Clasa care contine datele ClientOrder cu care se vor lucra
 * Created by d.rona on 01.05.2019.
 */
public class ClientOrder {

    private int id;
    private String status;
    private int clientId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
