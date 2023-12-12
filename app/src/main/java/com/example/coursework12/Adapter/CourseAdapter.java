package com.example.coursework12.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.coursework12.Activity.DetailActivity;
import com.example.coursework12.Domain.CourseDomain;
import com.example.coursework12.R;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private ArrayList<CourseDomain> items;
    private OnCourseItemClickListener listener;
    Context context;
    public CourseAdapter(ArrayList<CourseDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_course, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

        @Override
        public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {

            holder.titleTxt.setText(items.get(position).getTitle());
            holder.ownerTxt.setText(items.get(position).getOwner());
            holder.priceTxt.setText(items.get(position).getPrice() + " BYN");
//            CourseDomain course = items.get(position);

            int drawableResourceId = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(), "drawable",holder.itemView.getContext().getPackageName());

            Glide.with(holder.itemView.getContext())
                    .load(drawableResourceId)
                    .transform(new GranularRoundedCorners(30,30,0,0))
                    .into(holder.pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("object", items.get(position));
                holder.itemView.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnCourseItemClickListener(OnCourseItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnCourseItemClickListener {
        void onCourseItemClick(CourseDomain course);
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        TextView titleTxt, ownerTxt, priceTxt;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.text_title);
            ownerTxt=itemView.findViewById(R.id.owner_Txt);
            priceTxt=itemView.findViewById(R.id.price_Text);
            pic=itemView.findViewById(R.id.pic);
        }
    }
}
