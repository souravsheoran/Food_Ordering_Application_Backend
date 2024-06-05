package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Restaurants")
public class Add_Resturant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String rating;
    private String address;
    private String description;
    private int contact;
    private String openTime;
    private String closeTime;
    private String specialFeature;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User_Registration user;

    public Add_Resturant() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getSpecialFeature() {
        return specialFeature;
    }

    public void setSpecialFeature(String specialFeature) {
        this.specialFeature = specialFeature;
    }

    public User_Registration getUser() {
        return user;
    }

    public void setUser(User_Registration user) {
        this.user = user;
    }

    public Add_Resturant(String name, String rating, String address, String description, int contact, String openTime, String closeTime, String specialFeature, User_Registration user) {
        super();
        this.name = name;
        this.rating = rating;
        this.address = address;
        this.description = description;
        this.contact = contact;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.specialFeature = specialFeature;
        this.user = user;
    }
}
