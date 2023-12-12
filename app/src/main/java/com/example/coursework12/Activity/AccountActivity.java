package com.example.coursework12.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coursework12.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button LogoutBtn, MyCoursesBtn;
    private TextView tvUserName;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
            showSigned();
            String userName = "Вы вошли как:\n" + cUser.getEmail();
            tvUserName.setText(userName);
    }

    private void showSigned() {
        FirebaseUser user = mAuth.getCurrentUser();
    }

    private void init()
    {
        MyCoursesBtn = findViewById(R.id.MyCoursesBtn);
        tvUserName = findViewById(R.id.tvUserEmail);
        LogoutBtn = findViewById(R.id.LogoutBtn);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        init();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_account);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_cart:
                    startActivity(new Intent(getApplicationContext(), CartActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_account:
                    return true;
            }
            return false;
        });
    }

    public void onClickMyCourses(View view) {
        Intent i = new Intent(this,
                MyCoursesActivity.class);
        startActivity(i);
    }
    
    public void onClickLogout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}