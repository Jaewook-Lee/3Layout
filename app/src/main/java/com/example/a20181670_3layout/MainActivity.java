/* Class : MainActivity
* Author : 이재욱
* Description : 앱이 실행될 때 가장 먼저 뜨는 'activity_main' 화면과 연결된 클래스입니다.
*               사용자에게서 아이디와 비밀번호를 입력받아 로그인을 시도합니다.
*               기존 이용자가 아이디와 비밀번호를 정확히 입력하면 세 번째 화면으로 넘어가고 하나라도 틀리면
*               화면 하단에 Toast 메시지가 출력됩니다.
*               로그인은 로그인 버튼을 통해 할 수 있고, 회원 가입은 회원 가입 버튼을 통해 할 수 있습니다.
*               회원 가입 버튼을 누르면 두 번째 페이지로 넘어갑니다.
* Update date : 2019/10/12 */


package com.example.a20181670_3layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserData.readFromFile(MainActivity.this);

        Button btnSecondActivity, btnThirdActivity;
        btnSecondActivity = (Button) findViewById(R.id.btnLogin);
        btnThirdActivity = (Button) findViewById(R.id.btnSignUp);

        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), ThirdActivity.class);

                EditText idField, passwordField;
                idField = (EditText) findViewById(R.id.idPlainText);
                passwordField = (EditText) findViewById(R.id.passwordArea);

                String userID = idField.getText().toString();
                String userPassword = passwordField.getText().toString();

                /* Checking if ID and Password are correct or not.
                * If correct, shows third_layout. */
                if (UserData.findID(userID) && UserData.matchPW(userID, userPassword)) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 틀렸습니다." +
                            "다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /* Showing second_layout. */
        btnThirdActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}