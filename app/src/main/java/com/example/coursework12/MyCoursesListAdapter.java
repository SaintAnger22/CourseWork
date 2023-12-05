package com.example.coursework12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MyCoursesListAdapter extends RecyclerView.Adapter<MyCoursesListAdapter.ViewHolder> {
    private ArrayList<CourseDomain> myCoursesList;
    private ArrayList<Boolean> selectedCourses;
    private Context context;

    public MyCoursesListAdapter(ArrayList<CourseDomain> myCoursesList, Context context) {
        this.myCoursesList = myCoursesList;
        this.context = context;
        this.selectedCourses = new ArrayList<>(Collections.nCopies(myCoursesList.size(), false));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_mycourses, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseDomain course = myCoursesList.get(position);

        holder.title_Txt.setText(course.getTitle());

        int drawableResourceId = context.getResources().getIdentifier(course.getPicUrl(), "drawable", context.getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30, 30, 30, 30))
                .into(holder.pic_course);

        holder.checkBox.setChecked(selectedCourses.get(position));
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> selectedCourses.set(position, isChecked));
    }

    @Override
    public int getItemCount() {
        return myCoursesList.size();
    }

    public void updateCourses(ArrayList<CourseDomain> newCourses) {
        Set<CourseDomain> uniqueCourses = new HashSet<>(newCourses);
        myCoursesList.clear();
        myCoursesList.addAll(uniqueCourses);
        selectedCourses.clear();
        selectedCourses.addAll(Collections.nCopies(myCoursesList.size(), false));
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title_Txt;
        ImageView pic_course;
        CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_Txt = itemView.findViewById(R.id.title_Txt);
            pic_course = itemView.findViewById(R.id.pic_course);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }

    public ArrayList<CourseDomain> getSelectedCourses() {
        ArrayList<CourseDomain> selected = new ArrayList<>();
        for (int i = 0; i < myCoursesList.size(); i++) {
            if (selectedCourses.get(i)) {
                selected.add(myCoursesList.get(i));
            }
        }
        return selected;
    }
}
