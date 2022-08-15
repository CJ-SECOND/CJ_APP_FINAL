package com.frontend.cj_app.user.join;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.frontend.cj_app.R;

public class Join_account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_account);
        Button button = findViewById(R.id.btn_next2);

        Spinner spinner_bankname = findViewById(R.id.spineer1);
        EditText et_account = findViewById(R.id.et_account);
        EditText et_banker = findViewById(R.id.et_banker);
        Intent intent = getIntent();
        String USER_ID = intent.getStringExtra("USER_ID");
        String USER_NAME = intent.getStringExtra("USER_NAME");
        String USER_PASSWORD = intent.getStringExtra("USER_PASSWORD");
        String USER_PHONENUM = intent.getStringExtra("USER_PHONENUM");
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Join_car_info.class);
                String user_bankname = spinner_bankname.getSelectedItem().toString();
                String user_account = et_account.getText().toString();
                if(spinner_bankname.getSelectedItem().toString().equals("은행")){
                    Toast.makeText(Join_account.this,"은행을 선택해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                //예금주와 이름 일치 확인
                if(!et_banker.getText().toString().equals(USER_NAME)){
                    Toast.makeText(Join_account.this,"예금주 이름이 다릅니다.",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(user_account.length() == 0 ){
                    Toast.makeText(Join_account.this,"계좌번호를 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("USER_BANKNAME",  user_bankname);
                intent.putExtra("USER_ACCOUNT", user_account);
                intent.putExtra("USER_ID",USER_ID);
                intent.putExtra("USER_NAME",USER_NAME);
                intent.putExtra("USER_PASSWORD",USER_PASSWORD);
                intent.putExtra("USER_PHONENUM",USER_PHONENUM);
                Log.i("test",USER_ID);
                Log.i("test",user_bankname);
                startActivity(intent);
            }
        });
    }
}