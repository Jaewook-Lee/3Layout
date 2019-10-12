package com.example.a20181670_3layout;

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

        Button btnDuplicate = (Button) findViewById(R.id.btnDuplicate);
        btnDuplicate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_id = (EditText) findViewById(R.id.idPlainText);
                String id = et_id.getText().toString();
                if (!UserData.findID(id)) {
                    Toast.makeText(getApplicationContext(), "가능합니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "이미 다른 사용자가 쓰고 있습니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnSignUp2 = (Button) findViewById(R.id.btnSignUp2);
        btnSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb_agree = (RadioButton) findViewById(R.id.btnAcceptRadio);
                if (rb_agree.isChecked()) {
                    String id = ((EditText) findViewById(R.id.idPlainText)).getText().toString();
                    String pw = ((EditText) findViewById(R.id.passwordArea)).getText().toString();
                    String pw_check = ((EditText) findViewById(R.id.checkPasswordArea)).getText().toString();
                    String name = ((EditText) findViewById(R.id.nameArea)).getText().toString();
                    String phone_num = ((EditText) findViewById(R.id.phoneArea)).getText().toString();
                    String addr = ((EditText) findViewById(R.id.addressArea)).getText().toString();
                    if (id.equals("") || pw.equals("") || name.equals("") || phone_num.equals("") || addr.equals("")) {
                        Toast.makeText(getApplicationContext(), "모든 정보를 작성하셔야 합니다.", Toast.LENGTH_SHORT).show();
                    }
                    else if (isPWImpossible(pw)) {
                        Toast.makeText(getApplicationContext(), "허용하지 않는 특수 키를 사용하셨습니다.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if (pw.length() < 6) {
                        Toast.makeText(getApplicationContext(), "비밀번호는 6자 이상이어야 합니다.",
                                Toast.LENGTH_SHORT).show();
                    }
                    else if (!pw.equals(pw_check)) {
                        Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다." +
                                " 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String[] userInfo = {id, pw, name, phone_num, addr};
                        UserData.writeToFile(SecondActivity.this, userInfo);
                        Toast.makeText(getApplicationContext(), "회원가입이 완료됐습니다." +
                                "앱을 다시 실행해주세요.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "동의하셔야 회원가입이 가능합니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean isPWImpossible(String password) {
        final char[] impossible_chars = {'~', '`', '!', '\'', '\"'};
        for (int i=0; i<password.length(); i++) {
            for (int j=0; j<impossible_chars.length; j++) {
                if (password.charAt(i) == impossible_chars[j]) {
                    return true;
                }
            }
        }
        return false;
    }
}
