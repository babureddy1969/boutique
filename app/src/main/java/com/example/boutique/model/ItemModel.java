package com.example.boutique.model;

public class ItemModel {

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String img) {
        this.itemImage = img;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String c) {
        this.itemCode = c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int price) {
        this.itemPrice = price;
    }

    private String itemCode;
    private String itemName;
    private String itemDesc;
    private int itemPrice;
    private String itemImage;
    private String created_date;
    private int id;

    @Override
    public String toString() {
        return "ItemModel{" +
                "itemCode'" + itemCode + '\'' +
                "itemName='" + itemName + '\'' +
                ", itemDesc='" + itemDesc + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemImage='" + itemImage + '\'' +
                ", created_date='" + created_date + '\'' +
                ", id=" + id +
                '}';
    }
}
