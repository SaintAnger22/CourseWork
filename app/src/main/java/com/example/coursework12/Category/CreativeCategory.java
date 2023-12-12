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


public class CreativeCategory extends AppCompatActivity {

    private RecyclerView.Adapter adapterCourseListCreative;
    private RecyclerView recyclerViewCourse;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creative_category);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        setVariable();
        initRecyclerView();
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
    }
    private void initRecyclerView() {
        ArrayList<CourseDomain> courseCreative = new ArrayList<>();

        recyclerViewCourse = findViewById(R.id.listViewCreative);
        recyclerViewCourse.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        courseCreative.add(new CourseDomain("Уроки на Пианино для детей", "Моцарт", 123, "piano", "Откройте для вашего ребенка мир музыки через веселый и вдохновляющий курс пианино! Специально разработанные уроки, креативные игры и вдохновляющая атмосфера помогут вашему ребенку раскрыть свой музыкальный потенциал. Давайте вместе создадим мелодию радости и увлечения!", "Русский", "Венеция", "54587"));
        courseCreative.add(new CourseDomain("Искусство линии: Скетчинг с Нуля", "Пикасо", 300, "sketch", "Мы проведем вас через увлекательное путешествие по миру скетчинга, начиная с основных приемов и переходя к созданию удивительных произведений искусства.", "Французский", "Рим", "442121"));
        courseCreative.add(new CourseDomain("Photoshop полный курс!", "Udemy", 150, "photoshop", "Наш полный курс предназначен как для тех, кто только начинает свой путь в редактировании фотографий, так и для опытных художников, желающих усовершенствовать свои навыки.", "Русский", "Минск", "12313"));
        courseCreative.add(new CourseDomain("Blender3D Базовый курс", "Blender3D", 0, "blender", "Если вы мечтаете овладеть навыками 3D-моделирования и анимации, этот курс создан специально для вас. Независимо от вашего опыта, вы сможете погрузиться в увлекательное путешествие по возможностям Blender и создать свои собственные 3D-проекты.", "Английский", "Сеул","1215445"));
        courseCreative.add(new CourseDomain("Abletone Курс молодого Битмейкера", "Skrillex", 100, "abletone", "Независимо от того, являетесь ли вы начинающим продюсером или опытным музыкантом, вы откроете для себя множество техник и инструментов в Ableton Live, чтобы воплотить в жизнь свои музыкальные идеи.", "Русский", "Москва","213123"));


        adapterCourseListCreative = new ButtonsListAdapter(courseCreative);
        recyclerViewCourse.setAdapter(adapterCourseListCreative);
    }

    private void initView() {
        backBtn = findViewById(R.id.backBtnCreative);
    }
}

