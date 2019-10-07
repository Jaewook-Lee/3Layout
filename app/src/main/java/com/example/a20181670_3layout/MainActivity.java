package com.example.a20181670_3layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSecondActivity, btnThirdActivity;
        btnSecondActivity = (Button) findViewById(R.id.btnLogin);
        btnThirdActivity = (Button) findViewById(R.id.btnSignUp);

        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(), ThirdActivity.class);

                if (false) {    // statement will be fixed later.
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getBaseContext(), "아이디 또는 비밀번호가 틀렸습니다.",
                            Toast.LENGTH_SHORT).show();
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