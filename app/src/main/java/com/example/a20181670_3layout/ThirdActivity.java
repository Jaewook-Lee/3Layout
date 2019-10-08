package com.example.a20181670_3layout;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_layout);

        final ConstraintLayout myLayout = (ConstraintLayout) findViewById(R.id.background);

        Button redBtn, greenBtn, blueBtn, defaultBtn;
        redBtn = (Button) findViewById(R.id.btnRed);
        greenBtn = (Button) findViewById(R.id.btnGreen);
        blueBtn = (Button) findViewById(R.id.btnBlue);
        defaultBtn = (Button) findViewById(R.id.btnDefault);

        Button[] btnArray = {redBtn, greenBtn, blueBtn, defaultBtn};
        for (final Button btnTarget : btnArray) {
            btnTarget.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeLayoutBackGround(myLayout, btnTarget);
                }
            });
        }
    }

    protected void changeLayoutBackGround(ViewGroup layout, Button btn) {
        ColorDrawable btnColor = (ColorDrawable) btn.getBackground();
        layout.setBackground(btnColor);
    }
}