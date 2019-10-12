/* Class : ThirdActivity
 * Author : 이재욱
 * Description : 'activity_main' 화면에서 로그인이 성공하면 출력되는 'third_layout'과 연결된 클래스입니다.
 *               버튼을 클릭하면 버튼의 색깔과 같은 색으로 레이아웃의 색깔이 바뀝니다.
 * Functions:
 *     changeLayoutBackGround : 인자로 온 버튼의 색깔로 레이아웃의 배경색을 바꾸는 함수입니다.
 *                              바뀔 레이아웃과 클릭된 버튼을 각각 Layout과 Button 클래스로 받고 반환값은 없습니다.
 * Update date : 2019/10/12 */


package com.example.a20181670_3layout;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ThirdActivity extends AppCompatActivity {

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

    /* Function : changeLayoutBackGround
    * Descrpition : Changing layout's background color to button's color.
    * Variable :
    *     btnColor : ColorDrawble instance, has btn's color.
    * Parameter : layout(ViewGroup), btn(Button) */
    protected void changeLayoutBackGround(ViewGroup layout, Button btn) {
        ColorDrawable btnColor = (ColorDrawable) btn.getBackground();
        layout.setBackground(btnColor);
    }
}