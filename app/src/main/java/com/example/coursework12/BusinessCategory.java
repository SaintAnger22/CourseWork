package com.example.coursework12;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class BusinessCategory extends AppCompatActivity {

    private RecyclerView.Adapter adapterCourseListBusiness;
    private RecyclerView recyclerViewCourse;
    private ImageView backBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_category);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        setVariable();
        initRecyclerView();
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
    }
    private void initRecyclerView() {
        ArrayList<CourseDomain> courseBusiness = new ArrayList<>();

        recyclerViewCourse = findViewById(R.id.listViewBusiness);
        recyclerViewCourse.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        courseBusiness.add(new CourseDomain("Профессия Бизнес-аналитик", "Маэстро", 150, "analitik", "Станьте ключевым игроком в мире бизнеса с нашим курсом Профессия Бизнес-аналитик. Этот обширный тренинг разработан для тех, кто стремится освоить навыки анализа данных, оптимизации бизнес-процессов и принятия стратегических решений. ", "Русский", "Минск", "4881844354"));
        courseBusiness.add(new CourseDomain("Сетевой маркетинг", "", 300, "marketing", "МЛМ бизнес бурно развивается и перемещается в Интернет. Уходят в историю те времена, когда в объявлениях о работе писали: сетевой маркетинг - не предлагать", "Английский", "Вашингтон", "5494949"));
        courseBusiness.add(new CourseDomain("Голос Профессионала: Разогрев", "Udemy", 100, "voice", "100% практика развития ваших рече-голосовых возможностей: чтобы голос не уставал, не срывался, точно выражал мысли и чувства, был сильным и привлекательным, глубоким и звучным, чтобы сигналил миру о вашей уверенности и компетентности.", "Русский", "Екатиринбург","65655959"));
        courseBusiness.add(new CourseDomain("Прибыльные продажи.", "Udemy", 200, "selling", "Системный подход развития прибыльных продаж. Выжимка 20-ти летнего опыта. 100% практический материал.", "Английский", "Брест", "54941654"));
        courseBusiness.add(new CourseDomain("WHITE SALES ONLINE", "GeekBrains", 500, "whitesales", "Мы создали емкую и насыщенную онлайн-программу обучения белым продажам, и решили по традиции подарить ее всем, кто хочет продавать по-белому!", "Английский", "Минск", "124554545"));

        adapterCourseListBusiness = new ButtonsListAdapter(courseBusiness);
        recyclerViewCourse.setAdapter(adapterCourseListBusiness);
    }

    private void initView() {
        backBtn = findViewById(R.id.backBtnBusiness);
    }
}
