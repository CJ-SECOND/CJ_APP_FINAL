package com.frontend.cj_app.user.join;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.frontend.cj_app.R;

public class Join_car_info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_carinform);
        Button button = findViewById(R.id.btn_next4);
        Spinner spinnercarcolor = findViewById(R.id.spinnercarcolor);
        EditText et_cartype = findViewById(R.id.et_carkind);
        EditText et_carnum = findViewById(R.id.et_carnum);
        Intent intent = getIntent();
        String USER_ID = intent.getStringExtra("USER_ID");
        String USER_NAME = intent.getStringExtra("USER_NAME");
        String USER_PASSWORD = intent.getStringExtra("USER_PASSWORD");
        String USER_PHONENUM = intent.getStringExtra("USER_PHONENUM");
        String USER_BANKNAME = intent.getStringExtra("USER_BANKNAME");
        String USER_ACCOUNT = intent.getStringExtra("USER_ACCOUNT");

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Join_area.class);
                String user_carnumcolor = spinnercarcolor.getSelectedItem().toString();
                String user_cartype = et_cartype.getText().toString();
                String user_carnum = et_carnum.getText().toString();

                //비밀번호 유효성 검사
                if(user_cartype.length() == 0){
                    Toast.makeText(Join_car_info.this,"차량종류를 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                //차량번호 길이 유효성 검사
                if(user_carnum.length() == 0){
                    Toast.makeText(Join_car_info.this,"차량번호를 입력해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                //차량번호판 색 유효성 검사
                if(spinnercarcolor.getSelectedItem().toString().equals("차량번호판 색")){
                    Toast.makeText(Join_car_info.this,"차량번호판의 색을 선택해주세요",Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("USER_CARTYPE", user_cartype);
                intent.putExtra("USER_CARNUM", user_carnum);
                intent.putExtra("USER_CARNUMCOLOR",user_carnumcolor);
                intent.putExtra("USER_BANKNAME",  USER_BANKNAME);
                intent.putExtra("USER_ACCOUNT", USER_ACCOUNT);
                intent.putExtra("USER_ID",USER_ID);
                intent.putExtra("USER_NAME",USER_NAME);
                intent.putExtra("USER_PASSWORD",USER_PASSWORD);
                intent.putExtra("USER_PHONENUM",USER_PHONENUM);
                startActivity(intent);
            }
        });
    }
}