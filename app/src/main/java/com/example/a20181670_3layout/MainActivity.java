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

                if (UserData.findID(userID) && UserData.matchPW(userID, userPassword)) {
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 틀렸습니다." +
                            "다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnThirdActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}