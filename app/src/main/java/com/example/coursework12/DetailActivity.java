package com.example.coursework12;

import android.os.Bundle;
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
    private TextView languageTxt, titleTxt, ownerTxt, priceTxt, descriptionTxt, numberOrderTxt;
    private int numberOrder = 0;
    private ManageCart manageCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_layout);

        initView();
        setVariable();

        manageCart = new ManageCart(DetailActivity.this);
        getBundle();
    }

    private void addToCart() {
        addToCartBtn.setText("Убрать из корзины");
        isItemInCart = true;
    }

    private void removeFromCart() {
        addToCartBtn.setText("Добавить в корзину");
        isItemInCart = false;
    }

    private void getBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            CourseDomain courseDomain = (CourseDomain) bundle.getSerializable("object");
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
                    addToCartBtn.setOnClickListener(v -> {
                        if (isItemInCart) {
                            if (numberOrder > 0) {
                                numberOrder = numberOrder - 1;
                                courseDomain.setNumberInCard(numberOrder);
                                manageCart.removeCourse(courseDomain);
                                removeFromCart();
                            }
                        }else {
                            numberOrder = numberOrder + 1;
                             courseDomain.setNumberInCard(numberOrder);
                             manageCart.addCourse(courseDomain);
                            addToCart();
                        }
                    });
                }
            }
        }
    }
    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        numberOrderTxt = findViewById(R.id.numberItem_Txt);
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