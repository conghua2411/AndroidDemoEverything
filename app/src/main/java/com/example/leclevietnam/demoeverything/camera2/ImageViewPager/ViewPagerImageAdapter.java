package com.example.leclevietnam.demoeverything.camera2.ImageViewPager;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.leclevietnam.demoeverything.R;

import org.json.JSONArray;

import java.util.List;

public class ViewPagerImageAdapter extends PagerAdapter {
    List<String> listImageUrl;
    Context context;
    LayoutInflater inflater;

    public ViewPagerImageAdapter(Context context, List<String > listImageUrl) {
        this.context = context;
        this.listImageUrl = listImageUrl;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_viewpager_image, container, false);

        ImageView imageItem = view.findViewById(R.id.img_item);

        Log.d("test_image_adapter", "instantiateItem: " + listImageUrl.get(position));

        Glide.with(context).load(listImageUrl.get(position)).into(imageItem);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }

    @Override
    public int getCount() {
        return listImageUrl.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (RelativeLayout)o);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public void clear() {
        listImageUrl.clear();
        notifyDataSetChanged();
    }

    /**
     * update listImageUrl
     * @param jsonArray
     */
    public void addAll(JSONArray jsonArray) {
        clear();

        String tmp;

        for(int i = 0 ; i < jsonArray.length() ; i++) {
            tmp = "";

            try {
                tmp = jsonArray.getJSONObject(i).getJSONObject("image").getString("thumbnailLink");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(!tmp.isEmpty()) {
                listImageUrl.add(tmp);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * update listImageUrl
     * @param listUrl
     */
    public void addAll(List<String> listUrl) {
        clear();

        listImageUrl = listUrl;

//        if(!listUrl.isEmpty()) {
//            listImageUrl = listUrl;
//        }

        notifyDataSetChanged();
    }
}
