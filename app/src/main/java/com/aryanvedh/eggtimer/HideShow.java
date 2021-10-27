package com.aryanvedh.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HideShow extends AppCompatActivity {
    Boolean isHiden = false;
    TextView textView;

//    public void showhide(View view) {
//        if (!isHiden) {
//            textView.setVisibility(View.INVISIBLE);
//            isHiden = true;
//        } else {
//            textView.setVisibility(View.VISIBLE);
//            isHiden = false;
//
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hide_show);

        Button hide = findViewById(R.id.hideButton);
        Button show = findViewById(R.id.showButton);
        Button showHide = findViewById(R.id.showHideButton);
        TextView theText = findViewById(R.id.tvToShowHide);
        textView = findViewById(R.id.textView2);

        hide.setOnClickListener(view -> theText.setVisibility(View.INVISIBLE));

        show.setOnClickListener(view -> theText.setVisibility(View.VISIBLE));

        showHide.setOnClickListener(view -> {
            if (!isHiden){
                textView.setVisibility(View.INVISIBLE);
                isHiden = true;

            } else {
                textView.setVisibility(View.VISIBLE);
                isHiden = false;
            }
        });

    }
}
