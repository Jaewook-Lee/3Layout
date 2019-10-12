/* Class : UserData
 * Author : 이재욱
 * Description : 유저를 관리하는 간단한 클래스입니다. 이 클래스 속 함수들을 통해 내장 메모리 속 데이터들을 읽고 쓸 수 있습니다.
 *               아이디와 패스워드가 존재하는지 확인하는 기능도 있습니다. 함수들은 모두 static 으로 선언돼서 객체를 생성하지
 *               않아도 사용하실 수 있습니다.
 * Variables:
 *     userInfo : 유저들의 아이디와 비밀번호가 저장돼있는 HashMap 입니다. <아이디, 비밀번호> 쌍으로 이루어져있습니다.
 *                아이디와 비밀번호는 각각 String 클래스입니다.
 *     FILE_NAME : 유저들의 개인 정보들이 저장된 파일의 이름입니다. String 클래스입니다.
 * Functions:
 *     readFromFile : FILE_NAME 이름의 파일에 적힌 유저들의 정보들을 읽어 userInfo에 저장하는 함수입니다.
 *                    Context 클래스의 인스턴스를 인자로 받고 반환값은 없습니다.
 *     writeToFile : FILE_NAME 이름의 파일에 새 유저의 정보를 적는 함수입니다.
 *                   Context 클래스의 인스턴스와 새 유저 정보가 담긴 String 배열을 인자로 받고 반환값은 없습니다.
 *     findID : 사용자가 입력한 아이디가 userInfo 안에 존재하는 아이디인지 확인하는 함수입니다.
 *              String 객체인 아이디 값을 인자로 받고 존재 여부에 따른 boolean 값을 반환합니다.
 *     matchPW : 사용자가 입력한 비밀번호가 정확한 비밀번호인지 확인하는 함수입니다. 아이디를 통해 비밀번호를 찾습니다.
 *               String 객체인 아이디와 비밀번호를 인자로 받고 boolean 값을 반환합니다.
 * Update date : 2019/10/12 */


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

    /* Function : readFromFile
    * Description : 내장 메모리 속 존재하는 파일을 찾고, 파일이 존재하면 파일 속 내용을 읽어오는 함수입니다.
    * Variables :
    *     user_file : FILE_NAME의 이름을 가진 File 클래스 변수
    *     scanner : user_file 속 내용을 한 줄씩 읽기 위한 Scanner 클래스 변수
    *     userInfoStr : scanner를 통해 읽어온 사용자 정보 한 줄을 담은 String 클래스 변수
    *     userArray : userInfoStr를 ',' 기준으로 쪼개어 저장하는 String[] 변수
    * Parameter :
    *     context : Function caller 의 context를 담은 Context 클래스 변수 */
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
        } catch (IOException ie) {    // If file not found, send error toast message.
            Toast.makeText(context.getApplicationContext(), "FILE NOT FOUND",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /* Function : writeToFile
     * Description : 내장 메모리 속 존재하는 파일을 찾고, 파일이 존재하면 그 파일에 새 유저의 정보를 쓰는 함수입니다.
     * Variables :
     *     infoStr : 새 유저의 정보를 담을 String 캘래스 변수
     *     outFileStream : 새 유저의 정보를 작성하기 위한 FileOutputStream 클래스 변수
     * Parameter :
     *     context : Function caller 의 context를 담은 Context 클래스 변수
     *     info : 새 유저의 정보들이 담긴 String[] 변수 */
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
        } catch (FileNotFoundException fe) {    // If file not found, make new file.
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

    /* Function : findID
     * Description : 사용자가 입력한 아이디가 userInfo 안에 존재하는지 확인하는 함수입니다. 존재하면 true
     *               존재하지 않으면 false를 반환합니다.
     * Parameter :
     *     id : 사용자가 입력한 아이디를 담은 String 클래스 변수 */
    public static boolean findID(String id) {
        return userInfo.containsKey(id);
    }

    /* Function : matchPW
     * Description : 사용자가 입력한 비밀번호와 사용자가 회원 가입시 등록한 비밀번호가 같은지 확인하는 함수입니다.
     *               같으면 true, 다르면 false를 반환합니다.
     * Variables :
     *     origin_pw : 사용자가 회원 가입시 등록한 비밀번호를 담은 String 클래스 변수
     * Parameter :
     *     id : 사용자가 입력한 아이디를 담은 Stirng 클래스 변수
     *     pw : 사용자가 입력한 비밀번호를 담은 String 클래스 변수 */
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
