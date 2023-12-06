package com.example.coursework12;

import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

public class MyCoursesActivity extends AppCompatActivity {
    private RecyclerView.Adapter myCoursesAdapter;
    private RecyclerView my_courses_view;
    private TextView EmptyTxtMyCourses;
    private TinyDB tinydb;
    private ImageView backBtnMyCourses, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        tinydb = new TinyDB(this);

        my_courses_view = findViewById(R.id.my_courses_view);
        EmptyTxtMyCourses = findViewById(R.id.EmptyTxtMyCourses);
        backBtnMyCourses = findViewById(R.id.backBtnMyCourses);
        deleteBtn = findViewById(R.id.deleteBtn);

        ArrayList<CourseDomain> paidCourses = getPaidCourses();
        initMyCoursesList(paidCourses);
        setVariable();

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog();
            }
        });
    }

    private void showSuccessDialog(){
        ConstraintLayout successConstraintLayout = findViewById(R.id.successConstraintLayout);
        View view = LayoutInflater.from(this).inflate(R.layout.success_dialog, successConstraintLayout);
        Button successDone = view.findViewById(R.id.successDone);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();

        view.findViewById(R.id.successDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });if (alertDialog.getWindow() !=null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));}
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

                deleteSelectedCourses();

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

    private ArrayList<CourseDomain> getPaidCourses() {
        ArrayList<CourseDomain> allCourses = tinydb.getListObject("PaidCoursesList");

        HashSet<CourseDomain> uniqueCourses = new HashSet<>(allCourses);
        return new ArrayList<>(uniqueCourses);
    }


    private void initMyCoursesList(ArrayList<CourseDomain> paidCourses) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        my_courses_view.setLayoutManager(linearLayoutManager);

        myCoursesAdapter = new MyCoursesListAdapter(paidCourses, this);
        my_courses_view.setAdapter(myCoursesAdapter);

        if (paidCourses.isEmpty()) {
            EmptyTxtMyCourses.setVisibility(View.VISIBLE);
            my_courses_view.setVisibility(View.GONE);
        } else {
            EmptyTxtMyCourses.setVisibility(View.GONE);
            my_courses_view.setVisibility(View.VISIBLE);
        }
    }

    private void setVariable() {
        backBtnMyCourses.setOnClickListener(v -> finish());
    }

    private void deleteSelectedCourses() {
        ArrayList<CourseDomain> selectedCourses = ((MyCoursesListAdapter) my_courses_view.getAdapter()).getSelectedCourses();
        if (!selectedCourses.isEmpty()) {
            ArrayList<CourseDomain> paidCourses = getPaidCourses();

            paidCourses.removeAll(selectedCourses);

            tinydb.putListObject("PaidCoursesList", paidCourses);

            ((MyCoursesListAdapter) myCoursesAdapter).updateCourses(paidCourses);

            if (paidCourses.isEmpty()) {
                EmptyTxtMyCourses.setVisibility(View.VISIBLE);
                my_courses_view.setVisibility(View.GONE);
            } else {
                EmptyTxtMyCourses.setVisibility(View.GONE);
                my_courses_view.setVisibility(View.VISIBLE);
            }
        }
    }
}
