package com.example.coursework12.Category;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coursework12.Adapter.ButtonsListAdapter;
import com.example.coursework12.Domain.CourseDomain;
import com.example.coursework12.R;

import java.util.ArrayList;

public class GamingCategory extends AppCompatActivity {

    private RecyclerView.Adapter adapterCourseListGaming;
    private RecyclerView recyclerViewCourse;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaming_category);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initView();
        setVariable();
        initRecyclerView();
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
    }
    private void initRecyclerView() {
        ArrayList<CourseDomain> courseGaming = new ArrayList<>();

        recyclerViewCourse = findViewById(R.id.listViewGaming);
        recyclerViewCourse.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        courseGaming.add(new CourseDomain("Apex Legends", "Faide", 100, "apex", "Научитесь основам игры. Станьте PRO, с нашим курсом. Вы научитесь мувменту и тонкостям стрельбы", "Английский", "Вашингтон", "4842248"));
        courseGaming.add(new CourseDomain("Dota 2", "Evil Arthas", 1500, "dota", "Добро пожаловать в захватывающий мир Dota 2, где стратегия, командная работа и индивидуальное мастерство сочетаются для создания уникального опыта в мире многопользовательских онлайн-игр. ", "Русский?", "Где-то на Украине", "498412848"));
        courseGaming.add(new CourseDomain("Counter Strike", "S1mple", 120, "counterstrike", "Научитесь применять полезные фишки из pro-сцены и детально познакомитесь с экономикой игры.", "Русский", "Донецк", "5481"));
        courseGaming.add(new CourseDomain("World of Warcraft", "S1lverName", 300, "wow", "Наши опытные специалисты играющие в игру более 10 лет готовы поделиться своими знаниями с Вами! Мы с удовольствием поможем Вам освоить игру быстро и эффективно, вы перестаните быть абузой для рейда, вы начнете попадать в рейды и станите ценным кадром для любого хорошего рейда нацеленного на прогресс.", "Русский", "Москва", "6529494"));
        courseGaming.add(new CourseDomain("Hearthstone", "RDU", 40, "hearthstone", "Материал полезный, нужный и недооцененный, но по объемам сравним с небольшой книгой. При это он подойдет как новичкам игры, так и опытным асам.", "Английский", "Москва", "544849"));


        adapterCourseListGaming = new ButtonsListAdapter(courseGaming);
        recyclerViewCourse.setAdapter(adapterCourseListGaming);
    }

    private void initView() {
        backBtn = findViewById(R.id.backBtnGaming);
    }
}
