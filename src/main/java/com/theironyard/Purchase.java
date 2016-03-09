package com.theironyard;

import javax.persistence.*;

/**
 * Created by branden on 3/9/16 at 12:45.
 */
@Entity
public class Purchase {

    @Id
    @GeneratedValue
    @Column(name = "purchase_id")
    private int id;
    @Column(nullable = false)
    private String date;
    @Column(nullable = false)
    private String creditCard;
    @Column(nullable = false)
    private int cvv;
    @Column(nullable = false)
    private String category;

    @ManyToOne
    private Customer customer;


    public Purchase() {
    }

    public Purchase(String date, String creditCard, int cvv, String category) {
        this.date = date;
        this.creditCard = creditCard;
        this.cvv = cvv;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}