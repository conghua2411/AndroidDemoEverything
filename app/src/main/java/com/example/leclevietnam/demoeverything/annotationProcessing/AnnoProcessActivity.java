package com.example.leclevietnam.demoeverything.annotationProcessing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.binder.Binding;
import com.example.binderannotation.BindView;
import com.example.binderannotation.OnClick;
import com.example.leclevietnam.demoeverything.R;
import com.example.leclevietnam.demoeverything.annotationProcessing.bindViewAnno.BindView2;

public class AnnoProcessActivity extends AppCompatActivity {

//    @BindView2(R.id.annoTextView)
//    TextView annoTextView;
//
//    @BindView2(R.id.annoBtn)
//    Button annoBtn;

    // new way
    @BindView(R.id.annoTextView)
    TextView annoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anno_process);

//        ViewBinder.bind(this);
        
        Binding.bind(this);

//        AnnoProcessActivity$Binding.bind(this);

//        annoTextView.setText("asdasdas123123dasd");
//
//        annoBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                annoTextView.setText("hello");
//            }
//        });
    }

    @OnClick(R.id.annoBtn)
    void annoBtnClick(View v) {
        annoTextView.setText("btn anno click");
    }
}
