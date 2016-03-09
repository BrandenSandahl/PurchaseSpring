package com.theironyard;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by branden on 3/9/16 at 16:42.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private int id;

    @Column(nullable = false, unique = true)
    private String category;

    public Category() {
    }

    public Category(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}