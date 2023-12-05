package com.example.coursework12;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private TextView feeTxt, totalTxt, emptyTxt;
    private ManageCart manageCart;
    private ScrollView scrollView;
    private TinyDB tinydb;
    Button payBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        payBtn = findViewById(R.id.payBtn);

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog();
            }
        });


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();

        tinydb = new TinyDB(this);
        manageCart = new ManageCart(CartActivity.this);
        initList();
        Calculate();


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_cart);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_cart:
                    return true;
                case R.id.bottom_account:
                    startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });
    }

    private void savePaidCourses(ArrayList<CourseDomain> paidCourses) {
        ArrayList<CourseDomain> existingPaidCourses = tinydb.getListObject("PaidCoursesList");
        existingPaidCourses.addAll(paidCourses);
        tinydb.putListObject("PaidCoursesList", existingPaidCourses);
    }

    private void showSuccessDialog(){
        ConstraintLayout successConstraintLayout = findViewById(R.id.successConstraintLayout);
        View view = LayoutInflater.from(this).inflate(R.layout.success_dialog, successConstraintLayout);
        Button successDone = view.findViewById(R.id.successDone);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        successDone.findViewById(R.id.successDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();

            }
        });

        if (alertDialog.getWindow() !=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    private void showChoiceDialog(){
        ConstraintLayout choiceConstraintLayout = findViewById(R.id.choiceConstraintLayout);
        View view = LayoutInflater.from(this).inflate(R.layout.choice_dialog, choiceConstraintLayout);
        Button choiceYes = view.findViewById(R.id.choiceYes);
        Button choiceNo = view.findViewById(R.id.choiceNO);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        choiceYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSuccessDialog();

                ArrayList<CourseDomain> paidCourses = manageCart.getlistCarT();
                savePaidCourses(paidCourses);
                manageCart.clearCart();
                initList();
                Calculate();

                alertDialog.dismiss();
            }
        });

        choiceNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() !=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(manageCart.getlistCarT(), this, new ChangeNumberItemListener() {
            @Override
            public void changed() {
                Calculate();
            }
        });

        recyclerView.setAdapter(adapter);

        if(manageCart.getlistCarT().isEmpty()){ // Обратите внимание на использование правильного метода
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void Calculate() {
        double total = Math.round((manageCart.getTotalPrice()) * 100.0) / 100;

        totalTxt.setText("BYN" + total);
    }

    private void initView() {
        feeTxt = findViewById(R.id.feeEachItem);
        totalTxt = findViewById(R.id.totalTxt);
        recyclerView = findViewById(R.id.cart_view);
        scrollView = findViewById(R.id.scroll_view);
        emptyTxt = findViewById(R.id.Empty_Txt);

    }
}
