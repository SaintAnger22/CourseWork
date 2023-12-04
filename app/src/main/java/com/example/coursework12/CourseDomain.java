package com.example.coursework12;

import android.os.Parcel;

import java.io.Serializable;

public class CourseDomain implements Serializable {
    private String title;
    private String owner;
    private int price;
    private String pic;
    private String descriptionTxt;
    private int numberInCard;
    private String languageTxt;

    public CourseDomain(String title, String owner, int price, String pic, String descriptionTxt, String languageTxt) {
        this.title = title;
        this.owner = owner;
        this.price = price;
        this.pic = pic;
        this.descriptionTxt = descriptionTxt;
        this.languageTxt = languageTxt;
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

    // Метод для интерфейса Parcelable
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    // Метод для интерфейса Parcelable, записывает значения полей в Parcel
//    @Override
//    public void writeToParcel(@NonNull Parcel dest, int flags) {
//        dest.writeString(title);
//        dest.writeString(owner);
//        dest.writeDouble(price);
//        dest.writeString(pic);
//        dest.writeString(descriptionTxt);
//        dest.writeInt(numberInCard);
//        dest.writeString(languageTxt);
//    }
//
//    // Статический CREATOR для создания экземпляра класса из Parcel
//    public static final Creator<CourseDomain> CREATOR = new Creator<CourseDomain>() {
//        @Override
//        public CourseDomain createFromParcel(Parcel in) {
//            return new CourseDomain(in);
//        }
//
//        @Override
//        public CourseDomain[] newArray(int size) {
//            return new CourseDomain[size];
//        }
//    };

    // Приватный конструктор для создания экземпляра класса из Parcel
    private CourseDomain(Parcel in) {
        title = in.readString();
        owner = in.readString();
        price = in.readInt();
        pic = in.readString();
        descriptionTxt = in.readString();
        numberInCard = in.readInt();
        languageTxt = in.readString();
    }
}
