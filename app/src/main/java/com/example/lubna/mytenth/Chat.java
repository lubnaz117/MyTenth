package com.example.lubna.mytenth;

/**
 * @author greg
 * @since 6/21/13
 */
public class Chat {

    private String message;
    private String author;
    private double quantity;
    private double cost;
    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Chat() {
    }

    Chat(String message, String author, double quantity) {
        this.message = message;
        this.author = author;
        this.quantity = quantity;
        this.cost = 0;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public double getQuantity() {return quantity;}

    public double getCost() {return cost;}

}
