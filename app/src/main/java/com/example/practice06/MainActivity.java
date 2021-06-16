package com.example.practice06;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtName, edtPass;
    Button btnConfirm, btnExit;
    String password = "1234";
    String userName, userPassword;
    String title, message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       initView();

        // 종료버튼
       btnExit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               // 대화상자 (AlertDialog)
               // AlertDialog의 내부 클래스 Builder 클래스를 활용
               // 대화상자가 표시될 [context:Activity(target)]를 지정
               AlertDialog.Builder exitBuilder = new AlertDialog.Builder(MainActivity.this);
               exitBuilder.setTitle("경고");
               exitBuilder.setIcon(R.drawable.get_info);
               exitBuilder.setMessage("종료 하시겠습니까?");
               // 대화상자 버튼
               // 기본동작 : 대화상자가 사라짐.
               // 대화상자 버튼의 이벤트 OnClickListener를 무명 클래스로 설계.

               // Positive Button
               exitBuilder.setPositiveButton("종료", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                   finish();
                   }
               });

               // Negative Button
               exitBuilder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       // 냉무
                   }
               });
               exitBuilder.show(); // 대화상자를 표시

           }
       });

        // 확인버튼
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText를 통해 입력받은 데이터는 사용용도에 따라 toString으로 형변환이 필요함.
                userName = edtName.getText().toString();
                userPassword = edtPass.getText().toString();

                if(userName.equals("")) {
                    Toast.makeText(MainActivity.this,"아이디을 입력하세요.", Toast.LENGTH_SHORT).show();
                    edtPass.setText("");
                    edtName.requestFocus();

                } else if(userPassword.equals("")) {
                    Toast.makeText(MainActivity.this,"비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
                    edtName.requestFocus();

                } else {
                   checkPassword(userName, userPassword);
                }

            }
        });
    }

    public void initView() {
        edtName = (EditText)findViewById(R.id.name_editText);
        edtPass = (EditText)findViewById(R.id.pass_editText);
        btnConfirm = (Button)findViewById(R.id.main_button1);
        btnExit = (Button)findViewById(R.id.main_button2);
    }

    public void checkPassword(String userName, String userPassword) {
        if(userPassword.equals(password)) {

        // 대화상자로 출력
            title = "인증완료";
        message = String.format("%s님 환영합니다.", userName);
        showMessage(title, message);
        edtName.setText("");
        edtPass.setText("");
        edtName.requestFocus();

    } else {
            Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            edtPass.setText("");
            edtPass.requestFocus();
        }
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder messageBuilder = new AlertDialog.Builder(MainActivity.this);
        messageBuilder.setTitle(title);
        messageBuilder.setMessage(message);
        messageBuilder.setIcon(R.drawable.get_info);
        messageBuilder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        messageBuilder.show();
    }

    // 볼륨조절 이벤트 (key입력)
    // Activity의 onKeyDown() 콜백메소드를 오버라이딩.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // keyCode : 눌려진 키 코드
        // event : key action event (getAction()로 구분)
        // ACTION_DOWN : 키가 눌려질 때
        // ACTION_UP : 키를 누르고 땔 때
        if(event.getAction() == KeyEvent.ACTION_DOWN) {
            // 키가 눌려졌을 때 처리
            switch(keyCode) {
                case KeyEvent.KEYCODE_VOLUME_UP:
                    showMessage("정보", "볼륨을 올립니다.");
                    break;
                case KeyEvent.KEYCODE_VOLUME_DOWN:
                    showMessage("정보", "볼륨을 내립니다.");
                    break;
                case KeyEvent.KEYCODE_BACK:
                    finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}