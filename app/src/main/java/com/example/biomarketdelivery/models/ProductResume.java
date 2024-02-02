package com.example.biomarketdelivery.models;

import java.util.ArrayList;
import java.util.List;

public class ProductResume {
    String id;
    int quantity;

    public ProductResume() {
    }

    public ProductResume(String id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



}
