package com.example.coursework12;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class ManageCart {

    private Context context;
    private TinyDB tinyDB;

    public ManageCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void addCourse(CourseDomain item) {
        ArrayList<CourseDomain> listcourse = getlistCarT();
        boolean existAlready = false;
        int n = 0;
        for (int y = 0; y < listcourse.size(); y++) {
            if (listcourse.get(y).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = y;
                break;
            }
        }
        if (existAlready) {
            listcourse.get(n).setNumberInCard(item.getNumberInCard());
        } else {
            listcourse.add(item);
        }
        tinyDB.putListObject("CartList", listcourse);
        Toast.makeText(context, "Добавлено в корзину", Toast.LENGTH_SHORT).show();
    }
    public void removeCourse(CourseDomain item) {
        ArrayList<CourseDomain> listcourse = getlistCarT();
        for (int i = 0; i < listcourse.size(); i++) {
            if (listcourse.get(i).getTitle().equals(item.getTitle())) {
                listcourse.remove(i);
                Toast.makeText(context, "Удалено из корзины", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        tinyDB.putListObject("CartList", listcourse);
    }

    public boolean isCourseInCart(CourseDomain item) {
        ArrayList<CourseDomain> listcourse = getlistCarT();
        for (int i = 0; i < listcourse.size(); i++) {
            if (listcourse.get(i).getTitle().equals(item.getTitle())) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<CourseDomain> getlistCart() {
        return tinyDB.getListObject("CartList");
    }
    public void clearCart() {
        tinyDB.putListObject("CartList", new ArrayList<>());
    }



    public ArrayList<CourseDomain> getlistCarT() {
        return tinyDB.getListObject("CartList");
    }

    public void minusNumberCourse(ArrayList<CourseDomain> listfood, int position, ChangeNumberItemListener changeNumberItemListener) {
        if (listfood.get(position).getNumberInCard() == 1) {
            listfood.remove(position);
        } else {
            listfood.get(position).setNumberInCard(listfood.get(position).getNumberInCard() - 1);
        }
        tinyDB.putListObject("CartList", listfood);
        changeNumberItemListener.changed();
    }
    public Double getTotalPrice() {
        ArrayList<CourseDomain> listcourse2 = getlistCarT();
        double priceTxt = 0;
        for (int i = 0; i < listcourse2.size(); i++) {
            priceTxt = priceTxt + (listcourse2.get(i).getPrice() * listcourse2.get(i).getNumberInCard());
        }
        return priceTxt;
    }

}
