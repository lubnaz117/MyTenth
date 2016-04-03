package com.example.lubna.mytenth;

/**
 * Created by Lubna on 4/3/2016.
 */
public class grocery {
    private int quantity;
    private String name;
    private String initials;

    public grocery() {}

    public grocery(String name, int quantity, String initials) {
        this.name = name;
        this.quantity = quantity;
        this.initials = initials;
    }

    public long getQuantity(){
        return quantity;
    }

    public String getInitials(){
        return initials;
    }

    public String getName(){
        return name;
    }
}
