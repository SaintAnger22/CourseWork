package com.example.coursework12;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

public class DetailActivity extends AppCompatActivity {
    private Button addToCartBtn;
    private ImageView backBtn;
    private boolean isItemInCart = false;
    private TextView languageTxt, titleTxt, ownerTxt, priceTxt, descriptionTxt, locationTxt;
    private int numberOrder = 0;
    private ManageCart manageCart;
    Bundle bundle;
    CourseDomain courseDomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        bundle = getIntent().getExtras();
        courseDomain = (CourseDomain) bundle.getSerializable("object");

        initView();
        setVariable();
        manageCart = new ManageCart(DetailActivity.this);
        isItemInCart = manageCart.isCourseInCart(courseDomain);
        if(isItemInCart){
            addToCart();
            numberOrder = 1;
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        getBundle();
    }


    private void addToCart() {
        addToCartBtn.setText("Убрать из корзины");
        isItemInCart = true;
        Log.d("DetailActivity", "addToCart: Adding to cart");
    }

    private void removeFromCart() {
        Log.d("DetailActivity", "removeFromCart: Removing from cart");
        addToCartBtn.setText("Добавить в корзину");
        isItemInCart = false;
        Log.d("DetailActivity", "removeFromCart: isItemInCart = " + isItemInCart);
    }

    private void getBundle() {
        if (bundle != null) {
            if (courseDomain != null) {
                String picUrl = courseDomain.getPicUrl();
                ImageView imageView = findViewById(R.id.picImg);
                if (imageView != null && picUrl != null) {
                    int drawableResourceId = getResources().getIdentifier(picUrl, "drawable", getPackageName());

                    Glide.with(this)
                            .load(drawableResourceId)
                            .transform(new GranularRoundedCorners(30, 30, 0, 0))
                            .into(imageView);

                    titleTxt.setText(courseDomain.getTitle());
                    priceTxt.setText("BYN" + courseDomain.getPrice());
                    descriptionTxt.setText(courseDomain.getDescriptionTxt());
                    ownerTxt.setText(courseDomain.getOwner());
                    languageTxt.setText("Язык: " + courseDomain.getLanguageTxt());
                    locationTxt.setText(courseDomain.getLocationTxt());
                    addToCartBtn.setOnClickListener(v -> {
                        if (isItemInCart) {
                            if (numberOrder > 0) {
                                numberOrder = numberOrder - 1;
                                courseDomain.setNumberInCard(numberOrder);
                                manageCart.removeCourse(courseDomain);
                                removeFromCart();
                            }
                        } else {
                            numberOrder = numberOrder + 1;
                            courseDomain.setNumberInCard(numberOrder);
                            manageCart.addCourse(courseDomain);
                            addToCart();
                        }

                        isItemInCart = manageCart.isCourseInCart(courseDomain);
                        if (isItemInCart) {
                            addToCart();
                        } else {
                            removeFromCart();
                        }
                    });
                } else {
                    Log.e("DetailActivity", "ImageView or picUrl is null");
                }
            } else {
                Log.e("DetailActivity", "courseDomain is null");
            }
        }
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        locationTxt = findViewById(R.id.locationTxt);
        titleTxt = findViewById(R.id.titleTxt);
        priceTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        ownerTxt = findViewById(R.id.ownerTxt);
        backBtn = findViewById(R.id.backBtn);
        languageTxt = findViewById(R.id.languageTxt);
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
    }
}
