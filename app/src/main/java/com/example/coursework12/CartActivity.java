package com.example.coursework12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CartActivity extends AppCompatActivity {
        private RecyclerView.Adapter adapter;
        private RecyclerView recyclerView;
        private TextView feeTxt, totalTxt, emptyTxt;
        private ManageCart manageCart;
        private ScrollView scrollView;
        private ImageView backBtn;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cart);

            initView();
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
        private void initList() {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(linearLayoutManager);
            adapter = new CartListAdapter(manageCart.getlistCart(), this, new ChangeNumberItemListener() {
                @Override
                public void changed() {
                    Calculate();
                }
            });

            recyclerView.setAdapter(adapter);

            if(manageCart.getlistCart().isEmpty()){
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
            backBtn = findViewById(R.id.backBtn);
            emptyTxt = findViewById(R.id.Empty_Txt);

        }
}
