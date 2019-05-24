package com.example.leclevietnam.demoeverything.SnapRecyclerDemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.leclevietnam.demoeverything.R;
import com.example.leclevietnam.demoeverything.camera2.snapRecycler.SnapRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SnapRecyclerActivity extends AppCompatActivity {

    private RecyclerView snapRecycler;

    private SnapRecyclerAdapter mSnapRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap_recycler);

        snapRecycler = findViewById(R.id.snapRecycler);

        snapRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        setupAdapter();
    }

    private void setupAdapter() {
        List<String> imageList = getImageList();

        mSnapRecyclerAdapter = new SnapRecyclerAdapter(imageList);

        SnapHelper snapHelper = new LinearSnapHelper();

        snapHelper.attachToRecyclerView(snapRecycler);

        snapRecycler.setAdapter(mSnapRecyclerAdapter);
    }

    private List<String> getImageList() {

        List<String> imageList = new ArrayList<>();

        imageList.add("https://afamilycdn.com/k:thumb_w/600/WV8iA0A8EAfajQ9tIqEEPbrc36AeNK/Image/2015/08/1/pho-95c4c/pho.jpg");
        imageList.add("https://anh.eva.vn/upload/2-2017/images/2017-05-20/cach-nau-3-mon-pho-an-sang-cuc-nhanh-ma-ngon-mien-che-cach_lam_pho_bo_5-1495293166-width500height333.jpg");
        imageList.add("https://yeutre.vn/cdn/medias/uploads/23/23030-pho-bo-1.jpg");
        imageList.add("https://znews-photo.zadn.vn/w660/Uploaded/Ohunoaa/2016_12_22/8.jpg");
        imageList.add("https://anh.eva.vn//upload/2-2013/images/2013-06-27/1372309382-DSC01213.JPG");
        imageList.add("http://thoibaotaichinhvietnam.vn/Pictures102017/hothudung-tbtc/pho.jpg");
        imageList.add("http://image.vinanet.vn/zoom/640/Uploaded/thutrang/2017_02_24/phobo_WOJB.jpg");
        imageList.add("https://www.huongnghiepaau.com/wp-content/uploads/2017/11/5640d33785c3fb916e419f0e2096440d.jpg");
        imageList.add("http://boqi.vn/wp-content/uploads/2017/05/pho-bo.jpg");
        imageList.add("https://dulichhanoi.vn/wp-content/uploads/2016/08/pho-ha-noi.png");
        imageList.add("https://images.foody.vn/res/g64/637174/prof/s576x330/foody-mobile-hmb-jpg-112-636238750997215877.jpg");
        imageList.add("https://images.foody.vn/res/g64/637174/prof/s576x330/foody-mobile-hmb-jpg-112-636238750997215877.jpg");

        return imageList;
    }
}
