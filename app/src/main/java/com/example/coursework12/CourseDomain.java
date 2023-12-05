package com.example.coursework12;

import java.io.Serializable;

public class CourseDomain implements Serializable {
    private String title;
    private String owner;
    private int price;
    private String pic;
    private String descriptionTxt;
    private int numberInCard;
    private String languageTxt;
    private String locationTxt;
    private String ownerNumber;

    public String getLocationTxt() {
        return locationTxt;
    }

    public void setLocationTxt(String locationTxt) {
        this.locationTxt = locationTxt;
    }

    public CourseDomain(String title, String owner, int price, String pic, String descriptionTxt, String languageTxt, String locationTxt, String ownerNumber) {
        this.title = title;
        this.owner = owner;
        this.price = price;
        this.pic = pic;
        this.descriptionTxt = descriptionTxt;
        this.languageTxt = languageTxt;
        this.locationTxt= locationTxt;
        this.ownerNumber= ownerNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        CourseDomain that = (CourseDomain) obj;

        return title != null ? title.equals(that.title) : that.title == null &&
                owner != null ? owner.equals(that.owner) : that.owner == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }


    public String getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(String ownerNumber) {
        this.ownerNumber = ownerNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNumberInCard() {
        return numberInCard;
    }

    public void setNumberInCard(int numberInCard) {
        this.numberInCard = numberInCard;
    }

    public String getDescriptionTxt() {
        return descriptionTxt;
    }

    public void setDescriptionTxt(String descriptionTxt) {
        this.descriptionTxt = descriptionTxt;
    }

    public String getLanguageTxt() {
        return languageTxt;
    }

    public void setLanguageTxt(String languageTxt) {
        this.languageTxt = languageTxt;
    }

    public String getPicUrl() {
        return pic;
    }

    public void setPicUrl(String pic) {
        this.pic = pic;
    }
}
