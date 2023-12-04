package com.example.coursework12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    ArrayList<CourseDomain> listCourseSelected;
    private ManageCart manageCart;
    ChangeNumberItemListener changeNumberItemListener;
    public CartListAdapter(ArrayList<CourseDomain> listCourseSelected, Context context, ChangeNumberItemListener changeNumberItemListener) {
        this.listCourseSelected = listCourseSelected;
        manageCart = new ManageCart(context);
        this.changeNumberItemListener = changeNumberItemListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);

        return new ViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
        holder.title.setText(listCourseSelected.get(position).getTitle());
        holder.feeEachItem.setText("BYN" + listCourseSelected.get(position).getPrice());
        holder.num.setText(String.valueOf(listCourseSelected.get(position).getNumberInCard()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(listCourseSelected.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30,30,30,30))
                .into(holder.pic);


        holder.minusItem.setOnClickListener(v -> manageCart.minusNumberCourse(listCourseSelected, position, () -> {
            notifyDataSetChanged();
            changeNumberItemListener.changed();
        }));
    }
    @Override
    public int getItemCount() {
        return listCourseSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem, minusItem;
        ImageView pic;
        TextView num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_Txt);
            pic = itemView.findViewById(R.id.pic_course);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            minusItem = itemView.findViewById(R.id.minusCart_Btn);
            num = itemView.findViewById(R.id.numberItem_Txt);
        }
    }
}
