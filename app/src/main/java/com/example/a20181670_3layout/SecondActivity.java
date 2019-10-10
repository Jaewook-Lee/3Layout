package com.example.a20181670_3layout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        EditText et_id = (EditText) findViewById(R.id.idPlainText);
        EditText et_pw = (EditText) findViewById(R.id.passwordArea);
        EditText et_name = (EditText) findViewById(R.id.nameArea);
        EditText et_phone = (EditText) findViewById(R.id.phoneArea);
        EditText et_addr = (EditText) findViewById(R.id.addressArea);

        final String id = et_id.getText().toString();
        String pw = et_pw.getText().toString();
        String name = et_name.getText().toString();
        String phone_num = et_phone.getText().toString();
        String addr = et_addr.getText().toString();

        String[] userInfo = {id, pw, name, phone_num, addr};
        final UserData data = new UserData(SecondActivity.this);

        RadioButton rb_agree = (RadioButton) findViewById(R.id.btnAcceptRadio);
        if (rb_agree.isChecked()) {
            if (id.equals("") || pw.equals("") || name.equals("") || phone_num.equals("") || addr.equals("")) {
                Toast.makeText(getApplicationContext(), "모든 정보를 작성하셔야 합니다.", Toast.LENGTH_SHORT).show();
            }
            else {
                data.writeToFile(SecondActivity.this, userInfo);
                Toast.makeText(getApplicationContext(), "회원가입이 완료됐습니다." +
                        "다시 로그인해주세요.", Toast.LENGTH_LONG).show();
                finish();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "동의하셔야 회원가입이 가능합니다.", Toast.LENGTH_SHORT).show();
        }

        Button btnDuplicate = (Button) findViewById(R.id.btnDuplicate);
        btnDuplicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!data.findID(id)) {
                    Toast.makeText(getApplicationContext(), "가능합니다.", Toast.LENGTH_SHORT);
                }
                else {
                    Toast.makeText(getApplicationContext(), "이미 다른 사용자가 쓰고 있습니다.",
                            Toast.LENGTH_SHORT);
                }
            }
        });

        EditText checkPWArea = (EditText) findViewById(R.id.checkPasswordArea);
        checkPWArea.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    /* TODO :
                    *   If EditText gets focus, do something case by case
                    * */
                }
            }
        });
    }
}
