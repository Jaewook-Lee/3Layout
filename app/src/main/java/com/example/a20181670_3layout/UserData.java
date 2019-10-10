package com.example.a20181670_3layout;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UserData extends AppCompatActivity {

    private HashMap<String, String> userInfo = new HashMap<String, String>();
    private static final String FILE_NAME = "TestData.txt";

    /* Constructor */
    public UserData(Context context) {
        readFromFile(context);
    }

    public static void readFromFile(Context context) {
        FileInputStream inFileStream;
        try {
            if ((inFileStream = context.openFileInput(FILE_NAME))!= null) {
                byte[] txt = new byte[inFileStream.available()];
                inFileStream.read(txt);

                String str = new String(txt);
                Toast.makeText(context.getApplicationContext(), str, Toast.LENGTH_SHORT).show();

                inFileStream.close();
            }
        } catch (IOException e) {
            Toast.makeText(context.getApplicationContext(),"File NOT FOUND", Toast.LENGTH_SHORT).show();
        }
    }

    public static void writeToFile(Context context, String[] info) {
        String infoStr = "";
        for (int i=0; i<info.length; i++) {
            if (i == info.length - 1) {
                infoStr += (info[i] + '\n');
            }
            else {
                infoStr += (info[i] + ",");
            }
        }

        FileOutputStream outFileStream = null;
        try {
            outFileStream = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            outFileStream.write(infoStr.getBytes());
        } catch (FileNotFoundException fe) {
            try {
                outFileStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                outFileStream.write(infoStr.getBytes());
            } catch (IOException ie) {}
        } catch (IOException ie) {} finally {
            try {
                outFileStream.close();
            } catch (IOException ie) {}
        }
    }

    public boolean findID(String id) {
        return userInfo.containsKey(id);
    }

    public boolean matchPW(String id, String pw) {
        String origin_pw = userInfo.get(id);
        if (origin_pw.equals(pw)) {
            return true;
        }
        else {
            return false;
        }
    }
}
