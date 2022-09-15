package com.example.boutique.model;

import java.util.Date;

public class TransactionModel {
    private int id;
    private String customerName;
    private String phone;
    private int saree;
    private int fall;
    private int kutchu;
    private int blouse;
    private int other;
    private int total;
    private int advance;
    private int paid;
    private int balance;
    private int discount;
    private String created_date;
    private String updated_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSaree() {
        return saree;
    }

    public void setSaree(int saree) {
        this.saree = saree;
    }

    public int getFall() {
        return fall;
    }

    public void setFall(int fall) {
        this.fall = fall;
    }

    public int getKutchu() {
        return kutchu;
    }

    public void setKutchu(int kutchu) {
        this.kutchu = kutchu;
    }

    public int getBlouse() {
        return blouse;
    }

    public void setBlouse(int blouse) {
        this.blouse = blouse;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAdvance() {
        return advance;
    }

    public void setAdvance(int advance) {
        this.advance = advance;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setCreatedDate(String dt) {
        this.created_date = dt;
    }
    public void setUpdatedDate(String dt) {
        this.updated_date = dt;
    }
    public String getCreatedDate() {
        return this.created_date;
    }
    public String getUpdatedDate() {
        return this.updated_date;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", phone='" + phone + '\'' +
                ", saree=" + saree +
                ", fall=" + fall +
                ", kutchu=" + kutchu +
                ", blouse=" + blouse +
                ", other=" + other +
                ", total=" + total +
                ", advance=" + advance +
                ", paid=" + paid +
                ", balance=" + balance +
                ", discount=" + discount +
                ", created_date='" + created_date + '\'' +
                '}';
    }
}
