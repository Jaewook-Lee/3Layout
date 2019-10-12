/* Class : SecondActivity
 * Author : 이재욱
 * Description : 'activity_main' 화면에서 회원 가입 버튼을 누르면 출력되는 'second_layout' 과 연결된 클래스입니다.
 *               사용자에게 이름, 아이디, 비밀번호, 전화번호, 주소를 입력할 것을 요구합니다. 이 화면의 회원 가입 버튼을
 *               누르는 것으로 회원 가입이 완료됩니다. 취소 버튼을 누르면 'activity_main' 화면으로 돌아갑니다.
 * Funtions :
 *     isPWImpossible : 사용자가 입력한 비밀번호 안에 사용할 수 없는 특수 키들이 있는지 확인하는 함수입니다.
 *                      입력한 비밀번호를 String 클래스로 받아와서 boolean 값을 반환합니다.
 * Update date : 2019/10/12 */


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

        /* If button pressed, checks ID is possible to use or not. */
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

        /* If button pressed, checks if user is possible to sign-up. */
        Button btnSignUp2 = (Button) findViewById(R.id.btnSignUp2);
        btnSignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb_agree = (RadioButton) findViewById(R.id.btnAcceptRadio);
                if (rb_agree.isChecked()) {    // Checking if user agree or disagree.
                    String id = ((EditText) findViewById(R.id.idPlainText)).getText().toString();
                    String pw = ((EditText) findViewById(R.id.passwordArea)).getText().toString();
                    String pw_check = ((EditText) findViewById(R.id.checkPasswordArea)).getText().toString();
                    String name = ((EditText) findViewById(R.id.nameArea)).getText().toString();
                    String phone_num = ((EditText) findViewById(R.id.phoneArea)).getText().toString();
                    String addr = ((EditText) findViewById(R.id.addressArea)).getText().toString();

                    /* Checking if user didn't write something or not. */
                    if (id.equals("") || pw.equals("") || name.equals("") || phone_num.equals("") || addr.equals("")) {
                        Toast.makeText(getApplicationContext(), "모든 정보를 작성하셔야 합니다.", Toast.LENGTH_SHORT).show();
                    }

                    /* Checking if password followed rule or not. */
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

        /* If button clicked go back to 'activity_main' */
        Button btnBack = (Button) findViewById(R.id.btnCancel);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /* Function : isPWImpossible
    * Description : If password contains some special keys, password is invalid.
    * Variables:
    *     impossible_chars : Character type array, contains invalid special keys.
    * Parameter : password (String)
    * return : boolean */
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
