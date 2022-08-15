package com.frontend.cj_app.user.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.frontend.cj_app.R;
import com.frontend.cj_app.common.api.RetrofitAPI;
import com.frontend.cj_app.common.payload.Login_Request;
import com.frontend.cj_app.common.payload.Login_Response;
import com.frontend.cj_app.common.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        Button LoginButton = findViewById(R.id.btn_login);
        EditText editText_id = findViewById(R.id.edittext_id);
        EditText editText_password = findViewById(R.id.edittext_password);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://[IP 주소]:8080")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            @Override
            public void onClick(View view) {

                String loginId = editText_id.getText().toString();
                String loginPW = editText_password.getText().toString();
                RetrofitAPI loginService = retrofit.create(RetrofitAPI.class);
                Login_Request data = new Login_Request(loginId,loginPW);
                loginService.LoginUser(data).enqueue(new Callback<Login_Response>() {
                    @Override
                    public void onResponse(Call<Login_Response> call, Response<Login_Response> response) {
                        Login_Response login = response.body(); //데이터받기
                        if (login.getUserSeq() > 0){
                            // 세션 저장
                            Session session = new Session(getApplicationContext());
                            session.setUserSeq(login.getUserSeq());

                            Intent intent = new Intent(getApplicationContext(), permissionset.class);
                            Toast.makeText(getApplicationContext(),"로그인하셨습니다.",Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }else
                            Toast.makeText(getApplicationContext(),"회원가입을 해주세요",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Login_Response> call, Throwable t) {

                    }
                });
            }
        });
    }
}