package com.example.a20181670_3layout;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class UserData extends AppCompatActivity {

    private static HashMap<String, String> userInfo = new HashMap<String, String>();
    private static final String FILE_NAME = "TestData.txt";

    public static void readFromFile(Context context) {
        try {
            File user_file = new File(context.getFileStreamPath(FILE_NAME).toString());
            Scanner reader = new Scanner(user_file);
            while(reader.hasNextLine()) {
                String userInfoStr = reader.nextLine();
                String[] userArray = userInfoStr.split(",");
                userInfo.put(userArray[0], userArray[1]);
            }

            reader.close();
        } catch (IOException ie) {
            Toast.makeText(context.getApplicationContext(), "FILE NOT FOUND",
                    Toast.LENGTH_SHORT).show();
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

    public static boolean findID(String id) {
        return userInfo.containsKey(id);
    }

    public static boolean matchPW(String id, String pw) {
        String origin_pw = userInfo.get(id);
        if (origin_pw.equals(pw)) {
            return true;
        }
        else {
            return false;
        }
    }
}
