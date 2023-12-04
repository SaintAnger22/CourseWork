package com.example.coursework12;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CodingCategory extends AppCompatActivity {

    private RecyclerView.Adapter adapterCourseListCoding;
    private RecyclerView recyclerViewCourse;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coding_category);

        initView();
        setVariable();
        initRecyclerView();
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
    }
    private void initRecyclerView() {
        ArrayList<CourseDomain> courseCoding = new ArrayList<>();

        recyclerViewCourse = findViewById(R.id.listViewCoding);
        recyclerViewCourse.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        courseCoding.add(new CourseDomain("Программирование на Python для детей ", "GeekBrains", 60, "phyton", "У Python простой код и понятный синтаксис: ребятам легко работать с переменными, алгоритмами, функциями, командами. После обучения у ребят останется фундамент знаний по программированию.", "Русский"));
        courseCoding.add(new CourseDomain("Разработчик на C++", "GeekBrains", 156, "cpp", "Разработчик C++ создаёт программы, приложения, игры, операционные системы, драйверы и другое высоконагруженное программное обеспечение. Если вы хотите выжать максимум из любого «железа», программировать роботов, создать свою ОС, язык или другую мощную и жадную до ресурсов штуку — вам понадобится язык программирования С++", "Английский"));
        courseCoding.add(new CourseDomain("C# Это просто!", "SkillBox", 100, "pic1", "C# по праву входит в топ лучших языков, которые интенсивно развиваются и пользуются большим спросом на рынке ИТ. C# применяется начиная от простейших программ и до огромных веб-сервисов, через которые каждый день проходят миллионы клиентов.","Русский"));
        courseCoding.add(new CourseDomain("Kotlin для начинающих", "SkillBox", 80,  "picture2", "В ходе курса вы изучите язык программирования Котлин (Kotlin) с нуля, а также научитесь использовать его для построения Андроид программ. Вы обучитесь всем ключевым моментам работы с Kotlin и сможете выполнить легкий переход от Java к Kotlin.", "Английский"));
        courseCoding.add(new CourseDomain("Java Android Dev", "SkillBox", 120,  "picture3","Думаете над тем, чтобы начать путь мобильного разработчика? Предлагаем вашему вниманию курс «Разработка под Android для начинающих», созданный специалистами Google (владельцами Android и всего хорошего в мире ИТ)", "Русский"));


        adapterCourseListCoding = new ButtonsListAdapter(courseCoding);
        recyclerViewCourse.setAdapter(adapterCourseListCoding);
    }

    private void initView() {
        backBtn = findViewById(R.id.backBtnCoding);
    }

}

