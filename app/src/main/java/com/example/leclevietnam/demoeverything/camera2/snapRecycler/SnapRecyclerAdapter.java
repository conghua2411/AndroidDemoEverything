package com.example.leclevietnam.demoeverything.camera2.snapRecycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.leclevietnam.demoeverything.R;

import java.util.ArrayList;
import java.util.List;

public class SnapRecyclerAdapter extends RecyclerView.Adapter<SnapRecyclerAdapter.FoodItem> {

    private List<String> mImageList;

    public SnapRecyclerAdapter(List<String> imageList) {
        mImageList = imageList;
    }

    public void addAll(ArrayList<String> imageList) {
        if (mImageList == null) {
            mImageList = new ArrayList<>();
        }
        mImageList.clear();
        mImageList.addAll(imageList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new FoodItem(
                LayoutInflater.from(viewGroup.getContext()).
                        inflate(
                                R.layout.item_snap_recycler_view,
                                viewGroup,
                                false
                        )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItem holder, int position) {
        Glide.with(holder.mImageView.getContext()).load(mImageList.get(position)).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    class FoodItem extends RecyclerView.ViewHolder {
        ImageView mImageView;

        FoodItem(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.img_item);
        }
    }
}
