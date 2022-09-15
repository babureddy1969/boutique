package com.example.boutique.model;

public class PaymentModel {
    private String created_date;
    private int id;
    private int tx_id;

    @Override
    public String toString() {
        return "PaymentModel{" +
                "created_date='" + created_date + '\'' +
                ", id=" + id +
                ", tx_id=" + tx_id +
                ", amount=" + amount +
                '}';
    }

    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }



    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTx_id() {
        return tx_id;
    }

    public void setTx_id(int tx_id) {
        this.tx_id = tx_id;
    }

}
