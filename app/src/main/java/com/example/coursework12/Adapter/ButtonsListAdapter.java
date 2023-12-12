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

public class ButtonsListAdapter extends RecyclerView.Adapter<ButtonsListAdapter.ViewHolder> {

    ArrayList<CourseDomain> itemsGraphic;
    Context context;

    public ButtonsListAdapter(ArrayList<CourseDomain> itemsGraphic) {
        this.itemsGraphic = itemsGraphic;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_viewholder_for_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ButtonsListAdapter.ViewHolder holder, int position) {
        holder.businessTitle.setText(itemsGraphic.get(position).getTitle());
        holder.priceCourseBusiness.setText("BYN " + itemsGraphic.get(position).getPrice());
        holder.ownerBusiness.setText(itemsGraphic.get(position).getOwner());

    int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(itemsGraphic.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,30,30))
                .into(holder.picBusiness);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("object", itemsGraphic.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
}

    @Override
    public int getItemCount() {
        return itemsGraphic.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView businessTitle, priceCourseBusiness, ownerBusiness;
        ImageView picBusiness;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            businessTitle = itemView.findViewById(R.id.businessTitle);
            picBusiness = itemView.findViewById(R.id.picBusiness);
            priceCourseBusiness = itemView.findViewById(R.id.priceCourseBusiness);
            ownerBusiness = itemView.findViewById(R.id.ownerBusiness);
        }
    }
}