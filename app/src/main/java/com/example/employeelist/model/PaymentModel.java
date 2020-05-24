package com.example.employeelist.model;

public class PaymentModel {
    private int amount;

    public int getTx_id() {
        return tx_id;
    }

    public void setTx_id(int tx_id) {
        this.tx_id = tx_id;
    }

    private int tx_id;

    public String getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(String created_date) {
        this.created_date = created_date;
    }

    private String created_date;
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentModel{" +
                "amount=" + amount +
                ", created_date='" + created_date + '\'' +
                ", id=" + id +
                '}';
    }

}
