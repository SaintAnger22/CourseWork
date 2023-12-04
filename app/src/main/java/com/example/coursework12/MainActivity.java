package com.example.coursework12;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CourseAdapter.OnCourseItemClickListener{
    private RecyclerView.Adapter adapterCourses;
    public RecyclerView recyclerViewCourses;
    private Button btnCall;

    private void init(){
        btnCall = findViewById(R.id.btnCall);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottom_home) {
                    return true;
                } else if (item.getItemId() == R.id.bottom_cart) {
                    startActivity(new Intent(getApplicationContext(), CartActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.bottom_account) {
                    startActivity(new Intent(getApplicationContext(), AccountActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                }
                return false;
            }
        });
        initRecyclerView();
        }
    private void initRecyclerView() {
        ArrayList<CourseDomain> ItemsArraylist = new ArrayList<>();

        recyclerViewCourses = findViewById(R.id.view);
        recyclerViewCourses.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ItemsArraylist.add(new CourseDomain("C# Это просто!", "SkillBox", 100, "pic1", "Описание:\nC# по праву входит в топ лучших языков, которые интенсивно развиваются и пользуются большим спросом на рынке ИТ. C# применяется начиная от простейших программ и до огромных веб-сервисов, через которые каждый день проходят миллионы клиентов.","Русский"));
        ItemsArraylist.add(new CourseDomain("Kotlin для начинающих", "SkillBox", 80,  "picture2", "Описание:\nВ ходе курса вы изучите язык программирования Котлин (Kotlin) с нуля, а также научитесь использовать его для построения Андроид программ. Вы обучитесь всем ключевым моментам работы с Kotlin и сможете выполнить легкий переход от Java к Kotlin.", "Английский"));
        ItemsArraylist.add(new CourseDomain("Java Android Dev", "SkillBox", 120,  "picture3","Описание:\nДумаете над тем, чтобы начать путь мобильного разработчика? Предлагаем вашему вниманию курс «Разработка под Android для начинающих», созданный специалистами Google (владельцами Android и всего хорошего в мире ИТ)", "Русский"));


        adapterCourses = new CourseAdapter(ItemsArraylist);
        recyclerViewCourses.setAdapter(adapterCourses);

    }

    @Override
    public void onCourseItemClick(CourseDomain course) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("object", course);
        startActivity(intent);
    }

    public void onClickCall(View view)
    {
        String number = "123456789";
        Intent i = new Intent(Intent.ACTION_DIAL);
        i.setData(Uri.parse("tel:"+number));
        startActivity(i);
    }

    public void catBusinessList(View view)
    {
        Intent i = new Intent(this, BusinessCategory.class);
        startActivity(i);
    }

    public void catCreativeList(View view)
    {
        Intent i = new Intent(this, CreativeCategory.class);
        startActivity(i);
    }

    public void catCodingList(View view)
    {
        Intent i = new Intent(this, CodingCategory.class);
        startActivity(i);
    }

    public void catGamingList(View view)
    {
        Intent i = new Intent(this, GamingCategory.class);
        startActivity(i);
    }

}
