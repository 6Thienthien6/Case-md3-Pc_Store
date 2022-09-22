package com.casemd3pcstore.model;

import java.sql.Date;

public class Product {
    protected int id;
    protected String img;
    protected String name;

    protected String quantity;
    protected String price;
    protected int categoryId;
    protected Date createAt;
    protected String createBy;

    public Product() {
    }

    public Product(int id, String img, String name, String quantity, String price, int categoryId){
        this.id = id;
        this.img = img;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
    }
    public Product(String img, String name, String quantity, String price, int categoryId, Date createAt, String createBy){
        this.img = img;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
        this.createAt = createAt;
        this.createBy = createBy;
    }


    public Product(int id, String img, String name, String quantity, String price, int categoryId, Date createAt, String createBy) {
        this.id = id;
        this.img = img;
        this.name = name;
       this.quantity = quantity;
       this.price = price;
        this.categoryId = categoryId;
        this.createAt = createAt;
        this.createBy = createBy;
    }

    public Product(int id, String img, String name, String quantity, String price) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public Product( String img, String name, String quantity, String price, int categoryId) {
        this.img = img;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", createAt=" + createAt +
                ", createBy='" + createBy + '\'' +
                '}';
    }
}
